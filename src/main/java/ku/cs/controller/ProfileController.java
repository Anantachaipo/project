package ku.cs.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class ProfileController {

    @FXML
    public void handleBackToMenuButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("menu");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า menu ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    @FXML
    public void handleBackToUserLoginButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("user_login");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า user_login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
