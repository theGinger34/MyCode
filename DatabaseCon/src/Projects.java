/**
 * -- ISTE 330 Group Project
 * -- Authors: Lowell Pence, Campbell Schweickhardt, Vincent Cheng
 */

import java.sql.*;
import java.util.ArrayList;

public class Projects {

    /**
     * find all projects in database by a given name
     * @param projectName the name of a project
     * @return a list of projects with a given name
     */
    public ArrayList<ArrayList<String>> findProjectsByName(String projectName){
      MySQLDB db = new MySQLDB(); // connects to db
   
      String sqlQuery = "SELECT projID, projectName, abstractText, ownerID FROM Project WHERE projectName = ?;";
      try {
          PreparedStatement stmt = db.PrepStmt(sqlQuery);
          stmt.setString(1, projectName);
          ResultSet rs = db.getData(stmt);
          ArrayList<ArrayList<String>> results = new ArrayList<>();
          ArrayList<String> row = new ArrayList<>();
          ResultSetMetaData rsmd = rs.getMetaData();
          while (rs.next()){
            for (int i=1; i<=rsmd.getColumnCount(); i++){
               row.add(rs.getString(i)); // add to row inner list
            
            } // for
            results.add(row);
          } // while
          ArrayList<Project> projectList = new ArrayList<>();

          if (results != null) {
              // take formatted results and create Project data objects with them
              for (ArrayList<String> result : results) {
                  projectList.add(new Project(Integer.parseInt(result.get(0)), result.get(1), result.get(2), result.get(3)));
              }
              return results;
          }
          else {
              return null;
          }
      }
      catch (DLException | SQLException e) {
          e.printStackTrace();
          return null;
      }
      finally {
          db.close();
      }
   } // end findProjectsByName

    /**
     * Finds any projects in the database by the owner ID
     * @param ownerID the user ID of the owner to search for owned projects
     * @return a list of projects owned by a given owner userID, or null if no results
     */
    public ArrayList<ArrayList<String>> findProjectsByOwner(String ownerID){
       MySQLDB db = new MySQLDB(); // connects to db

       String sqlQuery =
               "SELECT projID, projectName, abstractText, ownerID FROM Project WHERE ownerID = ?;";
      ArrayList<ArrayList<String>> results = new ArrayList<>();
       try{
       PreparedStatement stmt = db.PrepStmt(sqlQuery);
       stmt.setString(1, ownerID);
       ResultSet rs = db.getData(stmt);
          ArrayList<String> row = new ArrayList<>();
          ResultSetMetaData rsmd = rs.getMetaData();
          while (rs.next()){
            for (int i=1; i<=rsmd.getColumnCount(); i++){
               row.add(rs.getString(i)); // add to row inner list
            
            } // for
            results.add(row);
          } // while
       }
       catch(Exception e){
         db.close();
          e.printStackTrace();
          return null;
       }
       ArrayList<Project> projectList = new ArrayList<>();

       if (results != null) {
           // take formatted results and create Project data objects with them
           for (ArrayList<String> result : results) {
               projectList.add(new Project(Integer.parseInt(result.get(0)), result.get(1), result.get(2), result.get(3)));
           }
           db.close();
           return results;
       }
       else {
           return null;
       }
   } // end findProjectsByOwner

    /**
     * Returns a single project given the primary key projID
     * @param projID the unique key that links to a single project in the database
     * @return a single project object or null if no results from db
     */
    public ArrayList<ArrayList<String>> findProjectByID(Integer projID){
        MySQLDB db = new MySQLDB(); // connects to db

        String sqlQuery =
                "SELECT projID, projectName, abstractText, ownerID FROM Project WHERE projID = ?;";
        Project project = null;
        ArrayList<ArrayList<String>> results = new ArrayList<>();
        try{
        PreparedStatement stmt = db.PrepStmt(sqlQuery);
       stmt.setInt(1, projID);
        ResultSet rs = db.getData(stmt);
          ArrayList<String> row = new ArrayList<>();
          ResultSetMetaData rsmd = rs.getMetaData();
          while (rs.next()){
            for (int i=1; i<=rsmd.getColumnCount(); i++){
               row.add(rs.getString(i)); // add to row inner list
            
            } // for
            results.add(row);
           } // while
          }
          catch(Exception e){
          db.close();
          e.printStackTrace();
          return null;
         }
        if (results != null) {
        // take formatted results and create Project data objects with them
           for (ArrayList<String> result : results) {
              project = new Project(Integer.parseInt(result.get(0)), result.get(1), result.get(2), result.get(3));
           }
        }
        else {
           db.close();
           return null;
        }
        db.close();
        return results;
    } // end findProjectsByID

    /**
     * finds any projects in the database by a given contributor
     * @param contributorUserID the user ID to get associated projects from
     * @return a list of projects
     */
    public ArrayList<ArrayList<String>> findProjectsByContributor(String contributorUserID){
        MySQLDB db = new MySQLDB(); // connects to db
         Project project = null;
        String sqlQuery = "SELECT p.projID, p.projectName, p.abstractText, p.ownerID FROM Contributors c INNER JOIN " +
                "Project p ON c.projID = p.projID WHERE c.username = ?;";

        ArrayList<ArrayList<String>> results = new ArrayList<>();
        try{
          PreparedStatement stmt = db.PrepStmt(sqlQuery);
       stmt.setString(1, contributorUserID);
       ResultSet rs = db.getData(stmt);
          ArrayList<String> row = new ArrayList<>();
          ResultSetMetaData rsmd = rs.getMetaData();
          while (rs.next()){
            for (int i=1; i<=rsmd.getColumnCount(); i++){
               row.add(rs.getString(i)); // add to row inner list
            
            } // for
            results.add(row);
           } // while
          }
          catch(Exception e){
          db.close();
          e.printStackTrace();
          return null;
         }
        
        if (results != null) {
            // take formatted results and create Project data objects with them
            for (ArrayList<String> result : results) {
              project = new Project(Integer.parseInt(result.get(0)), result.get(1), result.get(2), result.get(3));
           }
            db.close();
            return results;
        }
        else {
            return null;
        }
    } // findProjectsByContributor

    /**
     * finds all projects in the database that have a given keyword
     * @param keyword the keyword to get all associated projects with
     * @return a list of projects or null if no results
     */
    public ArrayList<Project> findProjectsByKeyword(String keyword){
        MySQLDB db = new MySQLDB(); // connects to db

        String sqlQuery = "SELECT p.projID, p.projectName, p.abstractText, p.ownerID FROM Project p INNER JOIN " +
                "ProjectKeys pk ON p.projID = pk.projID INNER JOIN Keywords k ON pk.keyID = k.keyID WHERE k.keyword = ?;";

        try {
            PreparedStatement stmt = db.PrepStmt(sqlQuery);
            stmt.setString(1, keyword);
            ResultSet rs = db.getData(stmt);
            ArrayList<ArrayList<String>> results = new ArrayList<>();
        ArrayList<String> row = new ArrayList<>();
        ResultSetMetaData rsmd = rs.getMetaData();
        while (rs.next()){
            for (int i=1; i<=rsmd.getColumnCount(); i++){
               row.add(rs.getString(i)); // add to row inner list
            
            } // for
            results.add(row);
        }//while
            ArrayList<Project> projectList = new ArrayList<>();

            if (results != null) {
                // take formatted results and create Project data objects with them
                for (ArrayList<String> result : results) {
                    projectList.add(new Project(Integer.parseInt(result.get(0)), result.get(1), result.get(2), result.get(3)));
                }
                return projectList;
            }
            else {
                return null;
            }
        }
        catch (SQLException | DLException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            db.close();
        }

    } // end findProjectsByKeyword

    /**
     * Finds all keywords associated with a given project ID number
     * @param projID the projID of a project
     * @return a list of keywords
     */
    public ArrayList<String> findKeywordsByProjectID(int projID) {
        MySQLDB db = new MySQLDB();
        String sqlQuery = "SELECT keyword FROM Keywords INNER JOIN ProjectKeys ON Keywords.keyID = ProjectKeys.keyID " +
                "WHERE ProjectKeys.projID = ?;";

        ArrayList<ArrayList<String>> results = new ArrayList<>();
        try{
          PreparedStatement stmt = db.PrepStmt(sqlQuery);
            stmt.setInt(1, projID);
            ResultSet rs = db.getData(stmt);
          ArrayList<String> row = new ArrayList<>();
          ResultSetMetaData rsmd = rs.getMetaData();
          while (rs.next()){
            for (int i=1; i<=rsmd.getColumnCount(); i++){
               row.add(rs.getString(i)); // add to row inner list
            
            } // for
            results.add(row);
           } // while
          }
          catch(Exception e){
          db.close();
          e.printStackTrace();
          return null;
         }
        ArrayList<String> keywords = new ArrayList<>();

        if (results != null) {
            // take formatted results and create Project data objects with them
            for (ArrayList<String> result : results) {
                keywords.add(result.get(0));
            }
            db.close();
            return keywords;
        }
        else {
            return null;
        }

    }

    /**
     * Adds a project to the database if the user trying is of type Faculty and has logged in.
     * @param int projectID
     * @param projectName the name of the project
     * @param abstractText the content of the project
     * @param ownerUsername the owner's unique username
     * @return true if the add was successful, false otherwise
     */
    public boolean addProject(int projID, String projectName, String abstractText, String ownerUsername) {

        if (true) {
            // connect to the database
            MySQLDB db = new MySQLDB();

            try {
                String sqlUpdate = "INSERT INTO Project (projID, projectName, abstractText, ownerID) VALUES (?, ?, ?, ?);";
                PreparedStatement stmt = db.PrepStmt(sqlUpdate);
                stmt.setInt(1, projID);
                stmt.setString(2, projectName);
                stmt.setString(3, abstractText);
                stmt.setString(4, ownerUsername);

                if (db.setData(stmt) > 0) { // project added successfully
                    return true;
                }
                else {
                    return false;
                }
            } catch (SQLException | DLException e) {
                e.printStackTrace();
                return false;
            }
            finally {
                db.close();
            }
        }
        else {
            return false; // user does not have permission to add a project
        }
    }

    /**
     * Edits a project name
     * @param int projectID
     * @param newName the new name
     * @return int
     */
    public int editProjectName(int projID, String newName) {
        MySQLDB db = new MySQLDB();
        try{
         String sqlUpdate = "UPDATE Project SET projectName = ? WHERE projID = ?;";
         PreparedStatement stmt = db.PrepStmt(sqlUpdate);
         stmt.setString(1, newName);
         stmt.setInt(2, projID);
         int rc = db.setData(stmt);
         db.close();
         return rc;
      }//end try
      catch (DLException | SQLException e) {
          db.close();
          e.printStackTrace();
          return -1;
      }//end catch   
   }

    /**
     * Edits a project abstract text
     * @param int projectIDchange
     * @param newAbstractText the new abstract text
     * @return int
     */
    public int editProjectAbstract(int projID, String newAbstractText) {
        MySQLDB db = new MySQLDB();
        try{
         String sqlUpdate = "UPDATE Project SET abstractText = ? WHERE projID = ?;";
         PreparedStatement stmt = db.PrepStmt(sqlUpdate);
         stmt.setString(1, newAbstractText);
         stmt.setInt(2, projID);
         int rc = db.setData(stmt);
         db.close();
         return rc;
      }//end try
      catch (DLException | SQLException e) {
          db.close();
          e.printStackTrace();
          return -1;
      }//end catch   
   }

    /**
     * Edits a project's owner
     * @param int projectID
     * @param newUsername the new user to change to
     * @return int
     */
    public int editProjectOwner(int projID, String newUsername) {
        MySQLDB db = new MySQLDB();
        try{
         String sqlUpdate = "UPDATE Project SET ownerID = ? WHERE projID = ?;";
         PreparedStatement stmt = db.PrepStmt(sqlUpdate);
         stmt.setString(1, newUsername);
         stmt.setInt(2, projID);
         int rc = db.setData(stmt);
         db.close();
         return rc;
      }//end try
      catch (DLException | SQLException e) {
          db.close();
          e.printStackTrace();
          return -1;
      }//end catch   
   }

    /**
     * Deletes a project
     * @param int projectID
     * @return int
     */
    public int deleteProject(int projectID) {
        MySQLDB db = new MySQLDB();
        try{
         String sqlUpdate = "DELETE FROM Project WHERE projID = ?";
         PreparedStatement stmt = db.PrepStmt(sqlUpdate);
         stmt.setInt(1, projectID);
         int rc = db.setData(stmt);
         db.close();
         return rc;
      }//end try
      catch (DLException | SQLException e) {
          db.close();
          e.printStackTrace();
          return -1;
      }//end catch   
   }
    /**
     * Adds a contributor to a project
     * @param int projectID
     * @param contributorUsername the username of the contributor
     * @return int
     */
    public int addContributorToProject(int projectID, String contributorUsername) {
        MySQLDB db = new MySQLDB();
        try{
         String sqlUpdate = "INSERT INTO Contributors VALUES (?, ?)";
         PreparedStatement stmt = db.PrepStmt(sqlUpdate);
         stmt.setInt(1, projectID);
         stmt.setString(2, contributorUsername);
         int rc = db.setData(stmt);
         db.close();
         return rc;
      }//end try
      catch (DLException | SQLException e) {
          db.close();
          e.printStackTrace();
          return -1;
      }//end catch   
   }

    /**
     * Removes a contributor from a project
     * @param int projectID
     * @param contributorUsername the username of the contributor
     * @return int
     */
    public int deleteContributorFromProject(int projectID, String contributorUsername) {
        MySQLDB db = new MySQLDB();
        try{
         String sqlUpdate = "DELETE FROM Contributors WHERE projID = ? AND username = ?";
         PreparedStatement stmt = db.PrepStmt(sqlUpdate);
         stmt.setInt(1, projectID);
         stmt.setString(2, contributorUsername);
         int rc = db.setData(stmt);
         db.close();
         return rc;
      }//end try
      catch (DLException | SQLException e) {
          db.close();
          e.printStackTrace();
          return -1;
      }//end catch   
   }

} // end Projects class
