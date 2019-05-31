/**
 * -- ISTE 330 Group Project
 * -- Authors: Lowell Pence, Campbell Schweickhardt, Vincent Cheng
 */

public class User {

    private String username;
    private String password;
    private String userType;
    private String name;
    private boolean hasActiveSession;


    public User(String username, String password, String name, String userType) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.userType = userType;
    }
    
    public User(String username, String name, String userType) {
        this.username = username;
        this.name = name;
        this.userType = userType;
    }

    public String getUsername() {
        return this.username;
    }

    public String getName() {
        return this.name;
    }
    
    public String getUserType() {
        return this.userType;
    }

    public boolean isActive() {
        return this.hasActiveSession;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void toggleActiveSession() {
        hasActiveSession = !hasActiveSession;
    }

}
