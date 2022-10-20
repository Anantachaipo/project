package ku.cs.service;

import ku.cs.controller.LoginController;
import ku.cs.model.Officer;
import ku.cs.model.User;
import ku.cs.model.UserList;

import java.time.LocalDateTime;

public class Account {

    //account

    public String recordAccount(String accountName, String username, String password, String confirmPassword, String pathProfile) {

        //ใช้ตรวจสอบข้อความที่กรอกเข้ามา
        if (accountName.equals("") || username.equals("")) {
            return "ยังไม่ได้กรอก AccountName หรือ UserName ";
        } else if (!checkPassword(password, confirmPassword)) {
            return "กรอก Password ไม่ตรงกัน";
        }

        User user = new User(accountName, username, password, pathProfile, 0);

        DataSource<UserList> dataSource;
        dataSource = new UserListFileDataSource();
        UserList userList = dataSource.readData();

        if (userList.searchByUsername(username))
            return "Username นี้ถูกใช้แล้ว";

        userList.addUser(user);

        dataSource.writeData(userList);

        return "P";
    }

    public String recordAccountOfficer(String accountName, String username, String password, String confirmPassword, String pathProfile,String organizationName) {

        //ใช้ตรวจสอบข้อความที่กรอกเข้ามา
        if (accountName.equals("") || username.equals("")) {
            return "ยังไม่ได้กรอก AccountName หรือ UserName ";
        } else if (!checkPassword(password, confirmPassword)) {
            return "กรอก Password ไม่ตรงกัน";
        }

        Officer officer = new Officer(accountName, username, password, pathProfile,organizationName );

        DataSource<UserList> dataSource;
        dataSource = new UserListFileDataSource();
        UserList userList = dataSource.readData();

        if (userList.searchByUsername(username))
            return "Username นี้ถูกใช้แล้ว";

        userList.addUser(officer);

        dataSource.writeData(userList);

        return "P";
    }


    //หน้า login
    public User checkUserName(String username, String password) {

        DataSource<UserList> dataSource;
        dataSource = new UserListFileDataSource();
        UserList userList = dataSource.readData();

        for (User user : userList.getUsers()) {
            if (user.getUsername().equals(username)) {
                if (user.getPassword().equals(password)) {
                    user.setLastLogin(LocalDateTime.now());
                    LoginController.user = user;
                    dataSource.writeData(userList);
                    return user;
                }
            }
        }

        return null;


    }
    public boolean checkPassword(String password, String confirmPassword) {
        if (password.equals("") || confirmPassword.equals("")) {
            System.out.println("ยังไม่ได้กรอก Password");
            return false;
        } else if (!password.equals(confirmPassword)) {
            System.out.println("กรอก Password ไม่ตรงกัน");
            return false;
        }
        return true;
    }
    //แบนผู้ใช้งาน
    public boolean checkBan(String username) {

        DataSource<UserList> dataSource;
        dataSource = new UserListFileDataSource();
        UserList userList = dataSource.readData();

        for (User user : userList.getUsers()) {
            if (user.getUsername().equals(username)&& user.getBan()>= 1 ) {
                user.setBan(user.getBan()+1);
                dataSource.writeData(userList);
                return false;

            }
        }

        return true;


    }

    public String changePassword(User otherMember, String password, String newPassword, String confirmNewPassword) {
        if (password.equals("") || newPassword.equals("") || confirmNewPassword.equals("")) {
            return "ยังกรอกข้อมูลไม่ครบ";
        } else if (!checkPassword(newPassword, confirmNewPassword)) {
            return "กรอก NewPassword ไม่ตรงกัน";
        } else if (!checkPassword(password, otherMember.getPassword())) {
            return "กรอก Password ไม่ถูกต้อง";
        } else if(password.equals(newPassword)){
            return  "Password ซ้ำกับ Password อันเดิม";
        }


        DataSource<UserList> dataSource;
        dataSource = new UserListFileDataSource();
        UserList userList = dataSource.readData();


        for (User user : userList.getUsers()) {
            if (user.getUsername().equals(otherMember.getUsername())) {
                user.setPassword(newPassword);

            }

        }
        dataSource.writeData(userList);
        return "P";

    }

    public void changeImage(String pathProfile){
        DataSource<UserList> dataSource;
        dataSource = new UserListFileDataSource();
        UserList userList = dataSource.readData();
        for(User user:userList.getUsers()){
            if(user.getUsername().equals(LoginController.user.getUsername())){
                user.setPathProfile(pathProfile);
            }
        }
        dataSource.writeData(userList);
    }
}