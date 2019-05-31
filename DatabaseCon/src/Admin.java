/**
 * -- ISTE 330 Group Project
 * -- Authors: Lowell Pence, Campbell Schweickhardt, Vincent Cheng
 */

import java.sql.*;

/**
 * A class admins to be able to add and edit information for users
 */
public class Admin {

   /** 
    * Add user to database
    * @param username in User table in database
    * @param password in User table in database
    * @param name in User table in database
    * @param userType in User table in database
    * @return int 
    */
   public int addUser(String username, String password, String name, String userType){
      MySQLDB db = new MySQLDB(); // Connects to database
      try {
         String sqlQuery = "INSERT INTO User (username, password, name, userType) VALUES (?, ?, ?, ?);";
         PreparedStatement stmt = db.PrepStmt(sqlQuery);
         stmt.setString(1, username);
         stmt.setString(2, password);
         stmt.setString(3, name);
         stmt.setString(4, userType);
         int rc = db.setData(stmt);
         db.close();
         return rc;
      }//end try
      catch (DLException | SQLException e) {
          db.close();
          e.printStackTrace();
          return -1;
      }//end catch
   }//end addUser
   
   /** 
    * edit password in User table in database
    * @param username in User table in database
    * @param password in User table in database
    * @return int 
    */
   public int editPassword(String username, String password){
      MySQLDB db = new MySQLDB(); // Connects to database
      try{
         String sqlQuery = "UPDATE User SET password = ? WHERE username = ?;";
         PreparedStatement stmt = db.PrepStmt(sqlQuery);
         stmt.setString(1, password);
         stmt.setString(2, username);
         int rc = db.setData(stmt);
         db.close();
         return rc;
      }//end try
      catch (DLException | SQLException e) {
          db.close();
          e.printStackTrace();
          return -1;
      }//end catch   
   }//end editPassword
   
   /** 
    * edit userType in User table in database
    * @param username in User table in database
    * @param userType in User table in database
    * @return int 
    */
   public int editUserType(String username, String userType){
      MySQLDB db = new MySQLDB(); // Connects to database
      try{
         String sqlQuery = "UPDATE User SET userType = ? WHERE username = ?;";
         PreparedStatement stmt = db.PrepStmt(sqlQuery);
         stmt.setString(1, userType);
         stmt.setString(2, username);
         int rc = db.setData(stmt);
         db.close();
         return rc;
      }//end try
      catch (DLException | SQLException e) {
          db.close();
          e.printStackTrace();
          return -1;
      }//end catch
   }//end editUserType
   
   /** 
    * edit name in User table in database
    * @param username in User table in database
    * @param name in User table in database
    * @return int 
    */
   public int editName(String username, String name){
      MySQLDB db = new MySQLDB(); // Connects to database
      try{
         String sqlQuery = "UPDATE User SET name = ? WHERE username = ?;";
         PreparedStatement stmt = db.PrepStmt(sqlQuery);
         stmt.setString(1, name);
         stmt.setString(2, username);
         int rc = db.setData(stmt);
         db.close();
         return rc;
      }//end try
      catch (DLException | SQLException e) {
          db.close();
          e.printStackTrace();
          return -1;
      }//end catch
   }//end editName
   
   /** 
    * delete user in User table in database
    * @param username in User table in database
    * @return int 
    */
   public int deleteUser(String username){
      MySQLDB db = new MySQLDB(); // Connects to database
      try{
         String sqlQuery = "DELETE FROM User WHERE username = ?;";
         PreparedStatement stmt = db.PrepStmt(sqlQuery);
         stmt.setString(1, username);
         int rc = db.setData(stmt);
         db.close();
         return rc;
      }//end try
      catch (DLException | SQLException e) {
          db.close();
          e.printStackTrace();
          return -1;
      }//end catch
   }//end deleteUser

   
} // end User
