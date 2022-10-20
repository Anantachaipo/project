package ku.cs.model;

import java.time.LocalDateTime;

public class Admin extends User{

    //admin

    public Admin(String accountName, String username, String password, String pathProfile, LocalDateTime lastLogin) {
        super(accountName, username, password, pathProfile, lastLogin, 0 );
    }
    public String toCsv(){
        return "Admin" + "," + getAccountName() + "," + getUsername() + "," + getPassword() +  "," + getPathProfile()+ "," + getLastLogin();
    }
}
