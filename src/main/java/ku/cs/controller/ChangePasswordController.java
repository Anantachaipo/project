package ku.cs.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import ku.cs.model.Admin;
import ku.cs.model.Officer;
import ku.cs.model.User;
import ku.cs.service.Account;

import java.io.IOException;

public class ChangePasswordController {

    @FXML
    private Label changePasswordLabel;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private PasswordField newPasswordPasswordField;

    @FXML
    private PasswordField confirmNewPasswordPasswordField;

    private Account account = new Account();

    private User user = LoginController.user;

    public ChangePasswordController() {
    }

    @FXML
    public void changePassword () {
        String password = passwordPasswordField.getText();
        String newPassword = newPasswordPasswordField.getText();
        String confirmNewPassword =  confirmNewPasswordPasswordField.getText();
        String status = account.changePassword(user,password,newPassword,confirmNewPassword);
        changePasswordLabel.setText(status);
        if(status.equals("P")) {
            try {
                com.github.saacsos.FXRouter.goTo("user_login");
            } catch (IOException e) {
                System.err.println("ไปที่หน้า user_login ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }
    }

    @FXML
    public void handleToCustomerSystems(){
        if(user instanceof Admin){
            try {
                com.github.saacsos.FXRouter.goTo("admin_main");
            } catch (IOException e) {
                System.err.println("ไปที่หน้า AdminSystems ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }
        else if(user instanceof Officer) {
            try {
                com.github.saacsos.FXRouter.goTo("officer_main");
            } catch (IOException e) {
                System.err.println("ไปที่หน้า officer_main ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }else {
            try {
                com.github.saacsos.FXRouter.goTo("menu");
            } catch (IOException e) {
                System.err.println("ไปที่หน้า menu ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }
    }
}
