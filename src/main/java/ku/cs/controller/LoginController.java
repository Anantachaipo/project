package ku.cs.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ku.cs.model.Admin;
import ku.cs.model.Officer;
import ku.cs.model.User;
import ku.cs.service.Account;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private Label loginLabel;


    public static User user ;

    private Account account = new Account();

    @FXML
    public void login () throws IOException{
        String userName = usernameTextField.getText();
        String password = passwordPasswordField.getText();
        loginLabel.setText("ไม่สามารถ login ได้ โปรดลองอีกครั้ง");
        user = account.checkUserName(userName,password) ;
        if (user != null) {
            if(account.checkBan(userName)) {
                if (user instanceof Admin) {
                    try {
                        com.github.saacsos.FXRouter.goTo("admin_main");
                    } catch (IOException e) {
                        System.err.println("ไปที่หน้า admin_main ไม่ได้");
                        System.err.println("ให้ตรวจสอบ Username หรือ Password");
                        System.err.println("ให้ตรวจสอบการกำหนด route");
                    }
                } else if (user instanceof Officer) {
                    try {
                        com.github.saacsos.FXRouter.goTo("officer_main");
                    } catch (IOException e) {
                        System.err.println("ไปที่หน้า officer_main ไม่ได้");
                        System.err.println("ให้ตรวจสอบ Username หรือ Password");
                        System.err.println("ให้ตรวจสอบการกำหนด route");
                    }
                } else {
                    try {
                        com.github.saacsos.FXRouter.goTo("menu");
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.err.println("ไปที่หน้า menu ไม่ได้");
                        System.err.println("ให้ตรวจสอบ Username หรือ Password");
                        System.err.println("ให้ตรวจสอบการกำหนด route");
                    }
                }
            }else{

                loginLabel.setText("บัญชีของคุณถูกระงับการใช้งาน");
            }

        }
    }

    @FXML
    public void handleCreateAccountButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("create_account");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า create_account ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleCreateOfficerAccountButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("create_officer_account");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า create_officer_account ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void test(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("complaint_detail");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า complaint_detail ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }


}
