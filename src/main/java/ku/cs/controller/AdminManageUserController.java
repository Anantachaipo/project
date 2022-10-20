package ku.cs.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.model.Admin;
import ku.cs.model.User;
import ku.cs.model.UserList;
import ku.cs.service.UserDataSort;
import ku.cs.service.UserListFileDataSource;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AdminManageUserController {

    @FXML
    private ListView<User> userListView;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label timeLabel;

    @FXML
    private ImageView profile;

    @FXML
    private Label accountNameLabel;

    @FXML
    private Label statusLabel;

    @FXML
    private Button banButton;
    @FXML
    private Label tryToLoginLabel;

    private UserListFileDataSource dataSource;

    private UserList userList = new UserList();
    private UserDataSort userDataSort = new UserDataSort();

    private User user;

    @FXML
    private void initialize() {
        dataSource = new UserListFileDataSource();
        userList = dataSource.readData();
        userList.sort(userDataSort); // เรียงข้อมูล
        this.userList = removeAdmin(userList);
        showListView();
        clearSelectedMember();
        handleSelectedListView();
    }

    private void showListView() {
        userListView.getItems().addAll(userList.getUsers());
        userListView.refresh();
    }

    private void handleSelectedListView() {
        userListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<User>() {
                    @Override
                    public void changed(ObservableValue<? extends User> observable, User oldValue, User newValue) {
                        System.out.println(newValue + " is selected");
                        showSelectedMember(newValue);
                    }
                });
    }

    private void showSelectedMember(User user) {
        this.user = user;
        profile.setImage(new Image("file:"+user.getPathProfile(), true));
        accountNameLabel.setText(user.getAccountName());
        usernameLabel.setText(user.getUsername());
        //คนที่สมัครเฉยๆยังไม่ได้ login
        if(user.getLastLogin().equals(LocalDateTime.of(0,1,1,0,0))){
            timeLabel.setText("-");
        }
        else {
            //set pattern of LocalDateTime
            String date = user.getLastLogin().format(DateTimeFormatter.ofPattern("yyyy-MM-dd   HH:mm:ss"));
            timeLabel.setText(date);
        }
        if(user.getBan() == 0){
            banButton.setText("แบนผู้ใช้");
            statusLabel.setText("ปกติ");
            tryToLoginLabel.setText("-");
        }else{
            banButton.setText("ปลดแบน");
            statusLabel.setText("ถูกแบน");
            tryToLoginLabel.setText(String.valueOf(user.getBan() - 1));
        }

    }

    private UserList removeAdmin(UserList memberList){
        UserList removeAdmin = new UserList();
        for(User user:userList.getUsers()){
            if(!(user instanceof Admin)){
                removeAdmin.addUser(user);
            }
        }
        return removeAdmin;
    }
    private void clearSelectedMember() {
        accountNameLabel.setText("-");
        usernameLabel.setText("-");
        timeLabel.setText("-");
        banButton.setText("แบนผู้ใช้/ปลดแบน");
        statusLabel.setText("-");
        tryToLoginLabel.setText("-");
    }

    @FXML
    void handleToUpdateStatus(ActionEvent event) {

        if(user.getBan() == 0){
            user.setBan(1);
        }else{
            user.setBan(0);
        }
        userList = dataSource.readData();
        for(User user: userList.getUsers()){
            if(user.getUsername().equals(user.getUsername())){
                user.setBan(user.getBan());
            }
        }
        dataSource.writeData(userList);

        userListView.refresh();
        showSelectedMember(user);
    }
    @FXML
    public void handleToMainAddmin(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("admin_main");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า admin_main ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

}

