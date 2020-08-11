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
import java.util.List;

import model.Filter;
import model.Like;
import model.Location;
import model.Match;
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

                while (i <= columnNumber) {
                    System.out.println(rs.getString(i));
                    i++;
                }
            }


        }catch(Exception e){
            e.printStackTrace();
        }

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
        try {
            PreparedStatement getLocation = con.prepareStatement(locationQuery);
            getLocation.setInt(1,aUser.getUserID());
            ResultSet result = getLocation.executeQuery();
            Location tempLocation = new Location();
            tempLocation.setGPS(result.getFloat(1));
            tempLocation.setName(result.getString(2));
            return tempLocation;
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public int addUser( String email, String gender, String genderPreference, String firstName, int age ){
        int newUserId=-1;
        int nextUserID= getNextUserId(con);
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
