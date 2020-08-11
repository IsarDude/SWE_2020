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

        getCurrentUser("test@test.de");
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

    public void getCurrentUser( String email) {
    String userQuery = "select * from user where email = ?";

        try {
            Statement st = con.createStatement();
            PreparedStatement getUser = con.prepareStatement(userQuery);
            getUser.setString(1, email);
            ResultSet rs = getUser.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();
            User currentUser= new User();
            while (rs.next()) {
                int columnNumber = rsmd.getColumnCount();
                int i = 1;
                currentUser.setUserID(rs.getInt(1));
                currentUser.setFirstName(rs.getString(2));
                currentUser.setAge(rs.getInt(3));
                currentUser.setGender(rs.getString(4));
                currentUser.setInfoText(rs.getString(5));

                Filter filter = new Filter(rs.getInt(6), rs.getInt(12),rs.getInt(7),rs.getString(9));
                currentUser.setFilter(filter);
                if(rs.getInt(10)==1){
                    currentUser.setVerified(true);
                }else{
                    currentUser.setVerified(false);
                }
                if(rs.getInt(11)==1){
                    currentUser.setVisible(true);
                }else{
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


        }catch(Exception e){
            e.printStackTrace();
        }

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
    /* Kommt noch
    public boolean checkForMatch(int otherUser, int currentUser){
        PreparedStatement st = null;

        try {
            st = con.prepareStatement("select * from like where User_UserID = ? and Liked_UserID=?");
            st.setInt(1, otherUser);
            st.setInt(2,currentUser);
            ResultSet res = st.executeQuery();
            if(res.next()){

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private int addMatch(int UserId1, int Userid2){
        try {
            PreparedStatement getNextId = con.prepareStatement("select idMatch from match ");
            ResultSet ids=getNextId.executeQuery();
            ids.last();
            int id1 = ids.getInt(1);
            int id2 = id1+1;
            PreparedStatement addMatches = con.prepareStatement("insert into match(idMatchId, timestamp, Liked_UserID)")
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
*/
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


    public int addUser( String email, String gender, String genderPreference, String firstName, int age ){
        int newUserId=-1;
        int nextUserID= getNextUserId();
        if(nextUserID!=0) {
            try {



                ResultSet res = null;
                String addUser = "INSERT INTO user(email, FirstName, Age, Gender,Gender_Preference, UserId )" + "values(?,?,?,?,?,?)";
                PreparedStatement pstmt = con.prepareStatement(addUser, Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, email);
                pstmt.setString(2, firstName);
                pstmt.setInt(3, age);
                pstmt.setString(4, gender);
                pstmt.setString(5, genderPreference);
                pstmt.setInt(6, nextUserID);

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
