/**
 * -- ISTE 330 Group Project
 * -- Authors: Lowell Pence, Campbell Schweickhardt, Vincent Cheng
 */

public class User {

    /**
     * the username of a user, must be unique as it is used as the primary key in the database
     */
    private String username;

    /**
     * the password of a user
     */
    private String password;

    /**
     * the type of user "F" for faculty or "S" for student
     */
    private String userType;

    /**
     * the name of the user
     */
    private String name;

    /**
     * whether or not a user is currently logged in
     */
    private boolean hasActiveSession;

    public User() {}

    /**
     * the user constructor
     * @param username
     * @param password
     * @param name
     * @param userType S or F
     */
    public User(String username, String password, String name, String userType) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.userType = userType;
    }

    /**
     * second user constructor, doesn't specify password
     * @param username
     * @param name
     * @param userType
     */
    public User(String username, String name, String userType) {
        this.username = username;
        this.name = name;
        this.userType = userType;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the type of user S or F
     */
    public String getUserType() {
        return this.userType;
    }

    /**
     * @return whether or not the user is active (true or false)
     */
    public boolean isActive() {
        return this.hasActiveSession;
    }

    /**
     * sets the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * sets the nmae
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * sets the user type
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * toggles the session
     */
    public void toggleActiveSession() {
        hasActiveSession = !hasActiveSession;
    }

}
