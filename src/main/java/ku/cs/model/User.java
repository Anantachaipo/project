package ku.cs.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class User {

    // user
    private String accountName;
    private String username;
    private String password;
    private int ban;
    private String pathProfile;
    private LocalDateTime lastLogin;


    public User(String accountName, String username, String password,String pathProfile, int ban) {
        this(accountName, username, password,pathProfile, LocalDateTime.of(0,1,1,0,0), ban);
    }

    public User(String accountName, String username, String password, String pathProfile, LocalDateTime lastLogin, int ban) {
        this.accountName = accountName;
        this.username = username;
        this.password = password;
        this.pathProfile = pathProfile;
        this.lastLogin = lastLogin;
        this.ban = ban;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getBan() {return ban;
    }

    public String getPathProfile() {
        return pathProfile;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public void setBan(int ban) {
        this.ban = ban;
    }

    public void setPathProfile(String pathProfile) {
        this.pathProfile = pathProfile;
    }

    @Override
    public String toString() {
        if(lastLogin.equals(LocalDateTime.of(0,1,1,0,0))){
            return "-------------------"  + "  |  " + "Username :  " + username ;
        }

        return  lastLogin.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "  |  " + "Username :  " + username ;
    }

    public String toCsv(){
        return "User" + "," + accountName + "," + username + "," + password + "," + pathProfile +  "," +lastLogin +","+ ban;
    }
}
