/**
 * -- ISTE 330 Group Project
 * -- Authors: Lowell Pence, Campbell Schweickhardt, Vincent Cheng
 */

import java.util.ArrayList;
import java.sql.*;

public class Users {

    // Store users from result set here
   private ArrayList<User> userList;
   User u = new User();

    // perform search for projects
    // get back a result set and format it into a list then return it
    // same idea for all functions that query the DB

   /** 
    * find users in the database by a given name
    * @param String userName
    * @return ArrayList<ArrayList<String>> of users 
    */
   public ArrayList<ArrayList<String>> findUserByName(String userName){
      MySQLDB db = new MySQLDB(); // Connects to database
      try{
         String sqlQuery = "SELECT username, name, userType FROM User WHERE name = ?;";
         ArrayList<String> values = new ArrayList<>();
         PreparedStatement stmt = db.PrepStmt(sqlQuery);
         stmt.setString(1, userName);
       // Results from database, need to be formatted for front end user
         ResultSet rs = db.getData(stmt); 
         ArrayList<ArrayList<String>> result = new ArrayList<>();
         ArrayList<String> row = new ArrayList<>();
         ResultSetMetaData rsmd = rs.getMetaData();
         while (rs.next()){
            for (int i=1; i<=rsmd.getColumnCount(); i++){
               row.add(rs.getString(i)); // add to row inner list
            
            } // for
            result.add(row);
         } // while
         ArrayList<User> userList = new ArrayList<>();
      
         // Use the formatted results and create user data objects with them
         for (int i = 1; i<result.size(); i++) {
            userList.add(new User(row.get(0), row.get(1), row.get(2)));
         }
         db.close();
         return result;
      }//end try
      catch (DLException | SQLException e) {
          db.close();
          e.printStackTrace();
          return null;
      }//end catch
   } //end findUserByName

   /**
    * finds any users in the database that are associated with a given keyword
    * @param userKeyword
    * @return ArrayList<ArrayList<String>> of users
    */
   public ArrayList<ArrayList<String>> findUsersByKeyword(String userKeyword){
      MySQLDB db = new MySQLDB(); // Connects to database
      try{
         String sqlQuery = "SELECT User.username, User.userType, User.name FROM User INNER JOIN UserKeys ON User.username = " +
"UserKeys.username INNER JOIN Keywords ON UserKeys.keyID = Keywords.keyID WHERE Keywords.keyword = ?;";
   
         ArrayList<String> values = new ArrayList<>();
         PreparedStatement stmt = db.PrepStmt(sqlQuery);
         stmt.setString(1, userKeyword);
         // Results from database, need to be formatted for front end user
         ResultSet rs = db.getData(stmt); 
         ArrayList<ArrayList<String>> result = new ArrayList<>();
         ArrayList<String> row = new ArrayList<>();
         ResultSetMetaData rsmd = rs.getMetaData();
         while (rs.next()){
            for (int i=1; i<=rsmd.getColumnCount(); i++){
               row.add(rs.getString(i)); // add to row inner list
            
            } // for
            result.add(row);
         } // while
         ArrayList<User> userList = new ArrayList<>();
      
         // Use the formatted results and create user data objects with them
         for (int i = 0; i<result.size(); i++) {
            userList.add(new User(row.get(0), row.get(1), row.get(2)));
         }
         db.close();
         return result;
      }//end try
      catch (DLException | SQLException e) {
          db.close();
          e.printStackTrace();
          return null;
      }//end catch
   }//end findUsersByKeyword

   /** 
    * finds any user in the database by a given username
    * @param userUserName
    * @return ArrayList<ArrayList<String>> of users 
    */
   public ArrayList<ArrayList<String>> findUserByUsername(String userUserName){
      MySQLDB db = new MySQLDB(); // Connects to database
      try{
         String sqlQuery = "SELECT username, name, userType FROM User WHERE username = ?;";
         ArrayList<String> values = new ArrayList<>();
         PreparedStatement stmt = db.PrepStmt(sqlQuery);
         stmt.setString(1, userUserName);
         // Results from database, need to be formatted for front end user
         ResultSet rs = db.getData(stmt); 
         ArrayList<ArrayList<String>> result = new ArrayList<>();
         ArrayList<String> row = new ArrayList<>();
         ResultSetMetaData rsmd = rs.getMetaData();
         while (rs.next()){
            for (int i=1; i<=rsmd.getColumnCount(); i++){
               row.add(rs.getString(i)); // add to row inner list
            
            } // for
            result.add(row);
         } // while
         ArrayList<User> userList = new ArrayList<>();
      
         // Use the formatted results and create user data objects with them
         for (int i = 1; i<result.size(); i++) {
            userList.add(new User(row.get(0), row.get(1), row.get(2)));
         }
         db.close();
         return result;
      }//end try
      catch (DLException | SQLException e) {
          db.close();
          e.printStackTrace();
          return null;
      }//end catch
   }//end findUserbyUsername

   /** 
    * finds any user in the database by a given userType
    * @param String userType
    * @return ArrayList<ArrayList<String>> of users 
    */
   public ArrayList<ArrayList<String>> findUserByUserType(String userType){
      MySQLDB db = new MySQLDB(); // Connects to database
      try{
         String sqlQuery = "SELECT username, name, userType FROM User WHERE userType = ?;";
         ArrayList<String> values = new ArrayList<>();
         PreparedStatement stmt = db.PrepStmt(sqlQuery);
         stmt.setString(1, userType);
         // Results from database, need to be formatted for front end user
         ResultSet rs = db.getData(stmt);
         ArrayList<ArrayList<String>> result = new ArrayList<>();
         ArrayList<String> row = new ArrayList<>();
         ResultSetMetaData rsmd = rs.getMetaData();
         while (rs.next()){
            for (int i=1; i<=rsmd.getColumnCount(); i++){
               row.add(rs.getString(i)); // add to row inner list
            
            } // for
            result.add(row);
         } // while
         ArrayList<User> userList = new ArrayList<>();
      
         // Use the formatted results and create user data objects with them
         for (int i = 1; i<result.size(); i++) {
            userList.add(new User(row.get(0), row.get(1), row.get(2)));
         }
         db.close();
         return result;
      }//end try
      catch (DLException | SQLException e) {
          db.close();
          e.printStackTrace();
          return null;
      }//end catch
   }//end findUserByUserType


   /** 
    * A user would be given a list of keywords that are in the database associated with projects
    * @param String username
    * @param String keyword
    * @return int 1 if sucsess, 0 if fail;
    */
   public int addKeywordToUser(String username, String keyword) { // keywords are pre determined by faculty when they
      // create a project
      MySQLDB db = new MySQLDB();
      Integer keyID;

      // get keyID from Keywords table using given keyword
      // don't need a prepared statement because keywords are added by faculty and checked when a project is made
      try {
         String getKeyIDQuery = "SELECT keyID FROM Keywords WHERE keyword = '" + keyword + "';";
         ResultSet rs = db.getData(getKeyIDQuery);
         rs.next();
         keyID = rs.getInt(1);

         // Insert into UserKeys table using keyID retrieved
         String addKeywordToUser = "INSERT INTO UserKeys (keyID, username) VALUES(?, ?);";
         try {
            PreparedStatement stmt = db.PrepStmt(addKeywordToUser);
            stmt.setInt(1, keyID);
            stmt.setString(2, username);
            return db.setData(stmt);
         }
         catch (DLException dle) {
            dle.printStackTrace();
            return 0;
         }
      }
      catch (SQLException sqle) {
         sqle.printStackTrace();
         return 0;
      }
   }//end addKeywordToUser
   
   /** 
    * find all keywords given a username
    * @param String username
    * @return ArrayList<ArrayList<String>> of users 
    */
   public ArrayList<ArrayList<String>> findKeywordsByUser(String username){
      MySQLDB db = new MySQLDB(); // Connects to database
      try{
         String sqlQuery = "SELECT keyword FROM Keywords k INNER JOIN UserKeys uk ON k.keyID = uk.keyID WHERE" +
" uk.username = ?";
         ArrayList<String> values = new ArrayList<>();
         PreparedStatement stmt = db.PrepStmt(sqlQuery);
         stmt.setString(1, username);
         // Results from database, need to be formatted for front end user
         ResultSet rs = db.getData(stmt);
         ArrayList<ArrayList<String>> result = new ArrayList<>();
         ArrayList<String> row = new ArrayList<>();
         ResultSetMetaData rsmd = rs.getMetaData();
         while (rs.next()){
            for (int i=1; i<=rsmd.getColumnCount(); i++){
               row.add(rs.getString(i)); // add to row inner list
            
            } // for
            result.add(row);
         } // while
         db.close();
         return result;
      }//end try
      catch (DLException | SQLException e) {
          db.close();
          e.printStackTrace();
          return null;
      }//end catch
   }//end findKeywordsByUser
   
   /** 
    * find all users given a projID
    * @param projID
    * @return ArrayList<ArrayList<String>> of users 
    */
   public ArrayList<ArrayList<String>> findUserByProjID(int projID){
      MySQLDB db = new MySQLDB(); // Connects to database
      try{
         String sqlQuery = "SELECT User.username, User.name, User.userType FROM User INNER JOIN Contributors ON User.username = Contributors.username" +
"  WHERE Contributors.projID = ?;";
         ArrayList<String> values = new ArrayList<>();
         PreparedStatement stmt = db.PrepStmt(sqlQuery);
         stmt.setInt(1, projID);
         // Results from database, need to be formatted for front end user
         ResultSet rs = db.getData(stmt);
         ArrayList<ArrayList<String>> result = new ArrayList<>();
         ArrayList<String> row = new ArrayList<>();
         ResultSetMetaData rsmd = rs.getMetaData();
         while (rs.next()){
            for (int i=1; i<=rsmd.getColumnCount(); i++){
               row.add(rs.getString(i)); // add to row inner list
            
            } // for
            result.add(row);
         } // while
         ArrayList<User> userList = new ArrayList<>();
      
         // Use the formatted results and create user data objects with them
         for (int i = 0; i<result.size(); i++) {
            userList.add(new User(row.get(0), row.get(1), row.get(2)));
         }
         db.close();
         return result;
      }//end try
      catch (DLException | SQLException e) {
          db.close();
          e.printStackTrace();
          return null;
      }//end catch
   }//end findKeywordsByUser
   
   /** 
    * Checks to see if user is a user
    * @param user
    * @param password
    * @return boolean 
    */
   public boolean login(String username, String password){
      MySQLDB db = new MySQLDB(); // Connects to database
      try {
         String sqlQuery = "SELECT username, userType, name FROM User WHERE username = ? and password = ?;";
         PreparedStatement stmt = db.PrepStmt(sqlQuery);
         stmt.setString(1, username);
         stmt.setString(2, password);

         ResultSet rs = db.getData(stmt);
         u.isActive();
         db.close();
         return true;
      }//end try
      catch (DLException | SQLException e) {
          db.close();
          e.printStackTrace();
          return false;
      }//end catch
   }//end login

   /**
    * takes a user data object and logs off
    * @param user the user that is logging off
    * @return true if log off successful, false otherwise
    */
   public boolean logoff(String username) {
      u.toggleActiveSession();
      return true;
   }
   
   /** 
    * edit password in User table in database
    * @param username in User table in database
    * @param password in User table in database
    * @param newPassword 
    * @return int 
    */
   public int userEditPassword(String username, String password, String newPassword){
      MySQLDB db = new MySQLDB(); // Connects to database
      if (login(username, password)) {
         try {
            String sqlQuery = "UPDATE User SET password = ? WHERE username = ?;";
            PreparedStatement stmt = db.PrepStmt(sqlQuery);
            stmt.setString(2, username);
            stmt.setString(1, newPassword);
            int rc = db.setData(stmt);
            return rc;
         }//end try
         catch (DLException | SQLException e) {
             e.printStackTrace();
             return -1;
         }//end catch
         finally {
            db.close();
         }
     }
     else {
      return -1;
     }  
   }//end editPassword

} // end User

