/**
 * -- ISTE 330 Group Project
 * -- Authors: Lowell Pence, Campbell Schweickhardt, Vincent Cheng
 */

import java.sql.*;

/**
 * Class for interacting with the database
 */
public class MySQLDB {

    private static final String uri = "jdbc:mysql://localhost/research?autoReconnect=true&useSSL=false&useUnicode" +
            "=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "student";

    private Connection conn = null;

    /**
     * Instantiates the database and connects to it
     */
    public MySQLDB() {
        this.connect();
    }

    /**
     * closes opens, then connects to the database
     * @return boolean
     */
    public boolean connect() {
        String driver = "com.mysql.cj.jdbc.Driver";

        // open and connect to database
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(uri, user, password);
            return true;
        }
        catch(SQLException sqle) {
            return false;
        }

        catch(Exception e) {
            e.printStackTrace();
            return false;
        }

    } //end connect()

    /**
     * closes database
     * @return boolean
     */
    public boolean close() {
        try{
            conn.close();
            return true;
        }
        catch(SQLException sqle) {
            return false;
        }
        catch(Exception e) {
            e.printStackTrace(); // change this before finishing TODO
            return false;
        }
    }//end close()

    /**
     * Get data from database
     * @param stmt the prepared statement to execute
     * @return the resultset from the database
     */
    public ResultSet getData(PreparedStatement stmt) {
        try {
            return stmt.executeQuery();
        }
        catch (SQLException sqle) {
            sqle.printStackTrace();
            return null;
        }
    }

    /**
     * get data from database using a regular query. DOES NOT CHECK FOR SQL INJECTION
     * ASSUMES query is clean (precondition)
     * @param sqlString the create, update, or delete sql string
     * @return ResultSet of query
     */
    public ResultSet getData(String sqlString) {
        try {
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(sqlString);
        }
        catch (SQLException sqle) {
            sqle.getMessage();
            return null;
        }
    }

    /**
     * Used to change data in the database using a prepared statement
     * @param stmt the prepared statment to execute
     * @return the amount of records changed
     * @throws DLException
     */
    public int setData(PreparedStatement stmt) throws DLException {
        try {
            return stmt.executeUpdate();
        }

        catch (SQLException sqle) {
            throw new DLException(sqle, "Error inserting, updating, or deleting from the database.");
        }
    }

    /**
     * Used to update data in database, does not use prepared statements
     * @param sqlString the update sql string
     */
    public int setData(String sqlString) throws DLException {
        try {
            Statement stmt = conn.createStatement();
            return stmt.executeUpdate(sqlString);
        }
        catch (SQLException sqle) {
            throw new DLException(sqle, "Failed executing update.");
        }
    }
    /**
     * Starts a transaction by turning off auto commit
     * @return boolean
     */
    public boolean startTrans() {
        try {
            conn.setAutoCommit(false);
            return true;
        }
        catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }
        catch (NullPointerException npe) {
            return false;
        }
    }

    /**
     * Ends the transaction by commiting changes and turning auto commit back on
     * @return boolean
     */
    public boolean endTrans() {
        try {
            conn.commit();
            conn.setAutoCommit(true);
            return true;
        }
        catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }
        catch (NullPointerException npe) {
            return false;
        }
    }

    /**
     * Rolls back the transaction if a failure occurs
     * @return boolean
     */
    public void rollbackTrans() throws SQLException {
        try {
            conn.rollback();
            conn.setAutoCommit(true);
        }
        catch (SQLException sqle) {
            throw new SQLException("Rollback failed");
        }
    }
    
    /**
     * Prepares statements
     * @param sqlQuery String
     * @return PreparedStatement
     */
    public PreparedStatement PrepStmt(String sqlQuery) throws DLException{
       try{
         return conn.prepareStatement(sqlQuery);
       }
       catch (SQLException sqle) {
            throw new DLException(sqle, "Failed executing update.");
       }
    }
} // end class
