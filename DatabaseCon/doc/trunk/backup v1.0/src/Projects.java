/**
 * -- ISTE 330 Group Project
 * -- Authors: Lowell Pence, Campbell Schweickhardt, Vincent Cheng
 */

import java.sql.*;
import java.util.ArrayList;

public class Projects {

    /**
     * Formats a given result set into a 2D array list
     * @param rs the result set to format
     * @return the 2D array list that has only strings
     */
    private ArrayList<ArrayList<String>> resultSetFormatter(ResultSet rs) {
        try {
            ArrayList<ArrayList<String>> result = new ArrayList<>();
            ResultSetMetaData rsmd = rs.getMetaData();

            while (rs.next()) {
                ArrayList<String> row = new ArrayList<>();
                for (int i=1; i<rsmd.getColumnCount(); i++){

                    int type = rsmd.getColumnType(i);
                    // add to row inner list
                    if (type == Types.VARCHAR || type == Types.CHAR) {
                        row.add(rs.getString(i));
                    }
                    else {
                        // column is type int
                        row.add(rs.getInt(i) + "");
                    }
                } // for
                result.add(row);
            } // while
            if (result.size() > 0) {
                return result;
            }
            else {
                // result set is empty
                return null;
            }
        }
        catch (SQLException sqle) {
            sqle.printStackTrace();
            return null;
        } // end catch
    } // resultSetFormatter

    /**
     * find all projects in database by a given name
     * @param projectName the name of a project
     * @return a list of projects with a given name
     */
    public ArrayList<Project> findProjectsByName(String projectName){
      MySQLDB db = new MySQLDB(); // connects to db
   
      String sqlQuery = "SELECT projID, projectName, abstractText, ownerID FROM Project WHERE projectName = ?;";
      try {
          PreparedStatement stmt = db.PrepStmt(sqlQuery);
          stmt.setString(1, projectName);
          ResultSet rs = db.getData(stmt);
          ArrayList<ArrayList<String>> results = resultSetFormatter(rs);
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
    public ArrayList<Project> findProjectsByOwner(Integer ownerID){
       MySQLDB db = new MySQLDB(); // connects to db

       String sqlQuery =
               "SELECT projID, projectName, abstractText, ownerID FROM Project WHERE ownerID = " + ownerID + ";";

       ResultSet rs = db.getData(sqlQuery);
       ArrayList<ArrayList<String>> results = resultSetFormatter(rs);
       ArrayList<Project> projectList = new ArrayList<>();

       if (results != null) {
           // take formatted results and create Project data objects with them
           for (ArrayList<String> result : results) {
               projectList.add(new Project(Integer.parseInt(result.get(0)), result.get(1), result.get(2), result.get(3)));
           }
           db.close();
           return projectList;
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
    public Project findProjectByID(Integer projID){
        MySQLDB db = new MySQLDB(); // connects to db

        String sqlQuery =
                "SELECT projID, projectName, abstractText, ownerID FROM Project WHERE projID = " + projID + ";";
        Project project = null;
        ResultSet rs = db.getData(sqlQuery);
        ArrayList<ArrayList<String>> results = resultSetFormatter(rs);
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
        return project;
    } // end findProjectsByID

    /**
     * finds any projects in the database by a given contributor
     * @param contributorUserID the user ID to get associated projects from
     * @return a list of projects
     */
    public ArrayList<Project> findProjectsByContributor(Integer contributorUserID){
        MySQLDB db = new MySQLDB(); // connects to db

        String sqlQuery = "SELECT p.projID, p.projectName, p.abstractText, p.ownerID FROM Contributors c INNER JOIN " +
                "Project p ON c.projID = p.projID WHERE c.userID = " + contributorUserID + ";";

        ResultSet rs = db.getData(sqlQuery);
        ArrayList<ArrayList<String>> results = resultSetFormatter(rs);
        ArrayList<Project> projectList = new ArrayList<>();

        if (results != null) {
            // take formatted results and create Project data objects with them
            for (ArrayList<String> result : results) {
                projectList.add(new Project(Integer.parseInt(result.get(0)), result.get(1), result.get(2), result.get(3)));
            }
            db.close();
            return projectList;
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
            ArrayList<ArrayList<String>> results = resultSetFormatter(rs);
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
                "WHERE ProjectKeys.projID = " + projID + ";";

        ResultSet rs = db.getData(sqlQuery);
        ArrayList<ArrayList<String>> results = resultSetFormatter(rs);
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
     * @param user the user trying to add a project (must be of type "F" for faculty
     * @param projectName the name of the project
     * @param abstractText the content of the project
     * @param ownerUsername the owner's unique username
     * @return true if the add was successful, false otherwise
     */
    public boolean addProject(User user, String projectName, String abstractText, String ownerUsername) {

        if (user.isActive() && user.getUserType().equals("F")) {
            // connect to the database
            MySQLDB db = new MySQLDB();

            try {
                String sqlUpdate = "INSERT INTO Project (projectName, abstractText, ownerID) VALUES (?, ?, ?);";
                PreparedStatement stmt = db.PrepStmt(sqlUpdate);
                stmt.setString(1, projectName);
                stmt.setString(2, abstractText);
                stmt.setString(3, ownerUsername);

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
     * @param user the user trying to change a project
     * @param project the project to change
     * @param newName the new name
     * @return the updated project
     */
    public Project editProjectName(User user, Project project, String newName) {
        MySQLDB db = new MySQLDB();
        if (user.isActive() && user.getUserType().equals("F")) {
            if (project.getOwnerID().equals(user.getUsername())) {
                // project is owned by the user
                String sqlUpdate = "UPDATE Project SET projectName = ? WHERE projID = ?;";

                try {
                    PreparedStatement stmt = db.PrepStmt(sqlUpdate);
                    stmt.setString(1, newName);
                    stmt.setInt(2, project.getProjID());
                    if (db.setData(stmt) > 0) {
                        project.setProjectName(newName);
                        return project;
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
            }
            else {
                return null;
            }
        }
        else {
            return null;
        }
    }

    /**
     * Edits a project abstract text
     * @param user the user trying to change a project
     * @param project the project to change
     * @param newAbstractText the new abstract text
     * @return the updated project
     */
    public Project editProjectAbstract(User user, Project project, String newAbstractText) {
        MySQLDB db = new MySQLDB();
        if (user.isActive() && user.getUserType().equals("F")) {
            if (project.getOwnerID().equals(user.getUsername())) {
                // project is owned by the user
                String sqlUpdate = "UPDATE Project SET abstractText = ? WHERE projID = ?;";

                try {
                    PreparedStatement stmt = db.PrepStmt(sqlUpdate);
                    stmt.setString(1, newAbstractText);
                    stmt.setInt(2, project.getProjID());
                    if (db.setData(stmt) > 0) {
                        project.setAbstractText(newAbstractText);
                        return project;
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
            }
            else {
                return null;
            }
        }
        else {
            return null;
        }
    }

    /**
     * Edits a project's owner
     * @param user the user trying to change a project
     * @param project the project to change
     * @param newUsername the new user to change to
     * @return the updated project
     */
    public Project editProjectOwner(User user, Project project, String newUsername) {
        MySQLDB db = new MySQLDB();
        if (user.isActive() && user.getUserType().equals("F")) {
            if (project.getOwnerID().equals(user.getUsername())) {
                // project is owned by the user

                String sqlUpdate = "UPDATE Project SET ownerID = ? WHERE projID = ?;";

                try {
                    PreparedStatement stmt = db.PrepStmt(sqlUpdate);
                    stmt.setString(1, newUsername);
                    stmt.setInt(2, project.getProjID());
                    if (db.setData(stmt) > 0) {
                        project.setOwnerID(newUsername);
                        return project;
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
            }
            else {
                return null;
            }
        }
        else {
            return null;
        }
    }

    /**
     * Deletes a project
     * @param user the user trying to change a project
     * @param project the project to change
     * @return true if successful, false otherwise
     */
    public boolean deleteProject(User user, Project project) {
        MySQLDB db = new MySQLDB();
        if (user.isActive() && user.getUserType().equals("F")) {
            if (project.getOwnerID().equals(user.getUsername())) {
                // project is owned by the user
                String sqlUpdate = "DELETE FROM Project WHERE projID = ?";

                try {
                    PreparedStatement stmt = db.PrepStmt(sqlUpdate);
                    stmt.setInt(1, project.getProjID());
                    if (db.setData(stmt) > 0) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }
                catch (DLException | SQLException e) {
                    e.printStackTrace();
                    return false;
                }
                finally {
                    db.close();
                }
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    /**
     * Adds a contributor to a project
     * @param user the user trying to add a contributor to their project
     * @param project the project to add a contributor to
     * @param contributorUsername the username of the contributor
     * @return the updated project
     */
    public Project addContributorToProject(User user, Project project, String contributorUsername) {
        MySQLDB db = new MySQLDB();
        if (user.isActive() && user.getUserType().equals("F")) {
            if (project.getOwnerID().equals(user.getUsername())) {
                // project is owned by the user
                String sqlUpdate = "INSERT INTO Contributors VALUES (?, ?)";

                try {
                    PreparedStatement stmt = db.PrepStmt(sqlUpdate);
                    stmt.setInt(1, project.getProjID());
                    stmt.setString(2, contributorUsername);
                    if (db.setData(stmt) > 0) {
                        project.addContributor(contributorUsername);
                        return project;
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
            }
            else {
                return null;
            }
        }
        else {
            return null;
        }
    }

    /**
     * Removes a contributor from a project
     * @param user the user trying to remove a contributor from their project
     * @param project the project to remove a contributor from
     * @param contributorUsername the username of the contributor
     * @return the updated project
     */
    public Project deleteContributorFromProject(User user, Project project, String contributorUsername) {
        MySQLDB db = new MySQLDB();
        if (user.isActive() && user.getUserType().equals("F")) {
            if (project.getOwnerID().equals(user.getUsername())) {
                // project is owned by the user
                String sqlUpdate = "DELETE FROM Contributors WHERE projID = ? AND username = ?";

                try {
                    PreparedStatement stmt = db.PrepStmt(sqlUpdate);
                    stmt.setInt(1, project.getProjID());
                    stmt.setString(2, contributorUsername);
                    if (db.setData(stmt) > 0) {
                        project.removeContributor(contributorUsername);
                        return project;
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
            }
            else {
                return null;
            }
        }
        else {
            return null;
        }
    }

} // end Projects class
