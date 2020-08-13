package datingDatabase;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.Filter;
import model.Hobby;
import model.Like;
import model.Location;
import model.Match;
import model.Subject;
import model.User;

public class ConnectMySql extends AsyncTask<String, Void, String> {
public static final String url = "jdbc:mysql://192.168.2.108:3306/mydb";
public static final String user = "master";
public static final String password = "SWE_2020_master";
private Connection con;

@Override
    protected String doInBackground(String... strings) {

    try {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(url, user, password);
        System.out.println("Databaseection success");


        //addUser(con,"test@test.de","female", "male", "test", 23);
        /*
        String result = "Database Connection Successful\n";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from user");

        ResultSetMetaData rsmd = rs.getMetaData();

        while (rs.next()) {
            int columnNumber = rsmd.getColumnCount();
            int i=1;
            while(i <= columnNumber) {
                System.out.println(rs.getString(i));
                i++;
            }
        }
*/
    } catch (Exception e) {
        e.printStackTrace();

    }


        return null;
    }

    public User getCurrentUser( String email, String password) {
    String userQuery = "select * from user where email = ?";
    User currentUser= new User();
        try {
            Statement st = con.createStatement();
            PreparedStatement getUser = con.prepareStatement(userQuery);
            getUser.setString(1, email);
            ResultSet rs = getUser.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();

            while (rs.next()) {
                if(password.equals(rs.getString(13))) {
                    int columnNumber = rsmd.getColumnCount();
                    int i = 1;
                    currentUser.setUserID(rs.getInt(1));
                    currentUser.setFirstName(rs.getString(2));
                    currentUser.setAge(rs.getInt(3));
                    currentUser.setGender(rs.getString(4));
                    currentUser.setInfoText(rs.getString(5));

                    Filter filter = new Filter(rs.getInt(6), rs.getInt(12), rs.getInt(7), rs.getString(9));
                    currentUser.setFilter(filter);
                    if (rs.getInt(10) == 1) {
                        currentUser.setVerified(true);
                    } else {
                        currentUser.setVerified(false);
                    }
                    if (rs.getInt(11) == 1) {
                        currentUser.setVisible(true);
                    } else {
                        currentUser.setVerified(false);
                    }
                    currentUser.setEmail(rs.getString(13));
                    currentUser.setLocation(fetchLocation(currentUser));
                    currentUser.setLikes(fetchLikes(currentUser.getUserID()));
                    currentUser.setMatches(fetchMatch(currentUser.getUserID()));
                    currentUser.setHobbies(fetchHobbys(currentUser.getUserID()));
                    currentUser.setSubject(fetchSubject(currentUser.getUserID()));
                    while (i <= columnNumber) {
                        System.out.println(rs.getString(i));
                        i++;
                    }
                }
            }



        }catch(Exception e){
            e.printStackTrace();
        }
    return currentUser;
    }

    public void addHobby(Hobby newHobby, int currentUserID){
        String line ="insert ignore into hobby(name) values(?)";
        try {
            PreparedStatement st = con.prepareStatement(line);
            st.setString(1,newHobby.getName());
            st.executeUpdate();
            PreparedStatement newID = con.prepareStatement("select idHobby from Hobby where name ="+newHobby.getName());
            ResultSet res = newID.executeQuery();
            if(res.next()){
                PreparedStatement newHasUser= con.prepareStatement("insert ignore into hobby_has_user(Hobby_idHobby, User_UserID) values(?,?)");
                newHasUser.setInt(1,res.getInt(1));
                newHasUser.setInt(2,currentUserID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public boolean removeHobby(Hobby deletedHobby, int userID){

        String getHobbyId = "select idHobby where name = "+deletedHobby.getName();
        try {
            PreparedStatement id = con.prepareStatement(getHobbyId);
            ResultSet ids = id.executeQuery();
            if(ids.next()){
                int hobbyId= ids.getInt(1);
                String line = "delete from hobby_has_user where idHobby=? and UserID=?";
                PreparedStatement delete = con.prepareStatement(line);
                delete.setInt(1,hobbyId);
                delete.setInt(2,userID);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }
    public boolean changeSubject(Subject subject, int userID){
        String querySubject ="select from subject where Name ="+subject.getName();
        try {
            PreparedStatement sub = con.prepareStatement(querySubject);
            ResultSet res = sub.executeQuery();
            if(!res.next()){
                String addSubject = "insert ignore into subject(Name) values(?) ";
                PreparedStatement add = con.prepareStatement(addSubject);
                add.setString(1,subject.getName());
                add.executeUpdate();
            }
            String addNewConnection ="insert ignore into subject_has_user(subject_Name,user_userID) values(?,?)";
            PreparedStatement connection = con.prepareStatement(addNewConnection);
            connection.setString(1,subject.getName());
            connection.setInt(2,userID);
            int i = connection.executeUpdate();
            if(i<=0){
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public void addFoto(){

    }
    public void removeFoto(){

    }
    public boolean changeLocation(float gps, String name, int userId){
        String update = "insert ignore into location(GPS,Name,User_USerID) value(?,?,?); update location(GPS,Name) value(?,?) where User_UserID=?";
        try {
            PreparedStatement prep = con.prepareStatement(update);
            prep.setFloat(1,gps);
            prep.setFloat(4,gps);
            prep.setString(2,name);
            prep.setString(5,name);
            prep.setInt(3,userId);
            prep.setInt(6,userId);
            int i = prep.executeUpdate();
            if(i<=0){
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public LinkedList<User> getUserForPool(int ageMax, int ageMin, String genderPreference, String gender){
        String userQuery = "select UserID,FirstName,Age;Gender,About_Me, verified from user where (visible =1 and (age between ?and?) and Gender =? and Gender_Preference=?)";
        LinkedList<User> pool = new LinkedList<>();
        try {
            Statement st = con.createStatement();
            PreparedStatement getUser = con.prepareStatement(userQuery);
            getUser.setInt(1,ageMin);
            getUser.setInt(2,ageMax);
            getUser.setString(3, genderPreference);
            getUser.setString(4,gender);
            ResultSet rs = getUser.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();

            while (rs.next()) {
                User tempUser= new User();
                int columnNumber = rsmd.getColumnCount();
                int i = 1;
                tempUser.setUserID(rs.getInt(1));
                tempUser.setFirstName(rs.getString(2));
                tempUser.setAge(rs.getInt(3));
                tempUser.setGender(rs.getString(4));
                tempUser.setInfoText(rs.getString(5));


                tempUser.setVisible(true);
                if(rs.getInt(5)==1){
                    tempUser.setVerified(true);
                }else{
                    tempUser.setVerified(false);
                }



                tempUser.setLocation(fetchLocation(tempUser));


                tempUser.setHobbies(fetchHobbys(tempUser.getUserID()));
                tempUser.setSubject(fetchSubject(tempUser.getUserID()));

                pool.add(tempUser);
            }


        }catch(Exception e){
            e.printStackTrace();
        }
        return pool;
    }

    public void addLike(int otherUserId,int currentUserId){
        PreparedStatement st = null;
        try {
            st = con.prepareStatement("select idLike from like");
            ResultSet ids = st.executeQuery();
            ids.last();
            int newId = ids.getInt(1)+1;
            String query = "insert into like(idLike, User_UserID,likedUserID) values(?,?,?)";
            PreparedStatement stNew = con.prepareStatement(query);
            st.setInt(1,newId);
            st.setInt(2,currentUserId);
            st.setInt(3,otherUserId);
            stNew.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean checkForMatch(int otherUser, int currentUser){
        PreparedStatement st = null;

        try {
            st = con.prepareStatement("select * from like where User_UserID = ? and Liked_UserID=?");
            st.setInt(1, otherUser);
            st.setInt(2,currentUser);
            ResultSet res = st.executeQuery();
            if(res.next()){
                addMatch(currentUser,otherUser);
                return true;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    private void addMatch(int UserId1, int UserId2){
        try {
            PreparedStatement getNextId = con.prepareStatement("select idMatch from match ");
            ResultSet ids=getNextId.executeQuery();
            ids.last();
            int id1 = ids.getInt(1);
            int id2 = id1+1;
            PreparedStatement adMatches= con.prepareStatement("insert into match(idMatch, timestamp, Liked_UserID, User_UserID) values((?,current_timestamp,?,?),(?,current_timestamp,?,?))");
            adMatches.setInt(1,id1);
            adMatches.setInt(2,UserId1);
            adMatches.setInt(3,UserId2);
            adMatches.setInt(4,id2);
            adMatches.setInt(5,UserId2);
            adMatches.setInt(6,UserId1);
            adMatches.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Subject fetchSubject(int UserID) {
        String query = "select Name, Semester from subject where idSubject = (select Subject_idSubject where User_UserID ="+ UserID + ")";
        Subject sub = new Subject ();
        try {
            PreparedStatement st = con.prepareStatement(query);
            ResultSet res = st.executeQuery();

            sub.setName(res.getString(1));
            sub.setSemester(res.getInt(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sub;

    }

    public LinkedList<Hobby> fetchHobbys(int UserID){
        String query = "select * from hobby where idHobby = (select Hobby_idHobby where User_UserID="+UserID +")";
        LinkedList<Hobby>  hobbies= new LinkedList<Hobby>();
        try {
            PreparedStatement st = con.prepareStatement(query);
            ResultSet res = st.executeQuery();

            while (res.next()){
                Hobby hob = new Hobby(res.getString(1));
                hobbies.add(hob);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hobbies;
    }



    public ArrayList<Like> fetchLikes(int UserID){
        String likeQuery = "select * from like where USER_UserID =" + UserID;
        ArrayList<Like> temp = new ArrayList<Like>();
        try {
            PreparedStatement st = con.prepareStatement(likeQuery);
            ResultSet res = st.executeQuery();
            while(res.next()){
                Like newLike = new Like(res.getInt(1));
                temp.add(newLike);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public ArrayList<Match> fetchMatch(int UserID){
        String likeQuery = "select * from match where USER_UserID =" + UserID;
        ArrayList<Match> temp = new ArrayList<Match>();
        try {
            PreparedStatement st = con.prepareStatement(likeQuery);
            ResultSet res = st.executeQuery();
            while(res.next()){
                Match newMatch = new Match(res.getInt(1), res.getInt(4));
                temp.add(newMatch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public Location fetchLocation( User aUser){
        String locationQuery = "select GPS,Name from where User_UserID = ?";
        Location tempLocation = new Location();
        try {
            PreparedStatement getLocation = con.prepareStatement(locationQuery);
            getLocation.setInt(1,aUser.getUserID());
            ResultSet result = getLocation.executeQuery();

            tempLocation.setGPS(result.getFloat(1));
            tempLocation.setName(result.getString(2));
            return tempLocation;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tempLocation;
    }


    public int addUser( String email, String gender, String genderPreference, String firstName, int age, String password ){
        int newUserId=-1;
        int nextUserID= getNextUserId();
        if(nextUserID!=0) {
            try {



                ResultSet res = null;
                String addUser = "INSERT INTO user(email, FirstName, Age, Gender,Gender_Preference, UserId,password )" + "values(?,?,?,?,?,?,?)";
                PreparedStatement pstmt = con.prepareStatement(addUser, Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, email);
                pstmt.setString(2, firstName);
                pstmt.setInt(3, age);
                pstmt.setString(4, gender);
                pstmt.setString(5, genderPreference);
                pstmt.setInt(6, nextUserID);
                pstmt.setString(7,password);
                int rowAffected = pstmt.executeUpdate();
                if (rowAffected == 1) {
                    res = pstmt.getGeneratedKeys();
                    if (res.next()) {
                        newUserId = res.getInt(1);
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return newUserId;
    }


public int getNextUserId(){
    int nextID = 0;
try {
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery("select UserID from user");

    ResultSetMetaData rsmd = rs.getMetaData();
    rs.last();
    int lastID=rs.getInt(1);
    nextID=lastID+1;

}catch(Exception e){
    e.printStackTrace();
    }
return nextID;
}



}
