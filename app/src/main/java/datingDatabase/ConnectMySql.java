package datingDatabase;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectMySql extends AsyncTask<String, Void, String> {
public static final String url = "jdbc:mysql://192.168.2.108:3306/mydb";
public static final String user = "master";
public static final String password = "SWE_2020_master";


@Override
    protected String doInBackground(String... strings) {

    try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, user, password);
        System.out.println("Databaseection success");

        getCurrentUser(con,"test@test.de");
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

    public void getCurrentUser(Connection con, String email) {
    String query = "select * from user where email = ?";
        try {
            Statement st = con.createStatement();
            PreparedStatement getUser = con.prepareStatement(query);
            getUser.setString(1, email);
            ResultSet rs = getUser.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();

            while (rs.next()) {
                int columnNumber = rsmd.getColumnCount();
                int i = 1;
                while (i <= columnNumber) {
                    System.out.println(rs.getString(i));
                    i++;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public int addUser(Connection con, String email, String gender, String genderPreference, String firstName, int age ){
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


public int getNextUserId(Connection con){
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
