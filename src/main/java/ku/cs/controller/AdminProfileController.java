package ku.cs.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;

public class AdminProfileController {

    @FXML
    public void handleBackToAdminMain(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("admin_main");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า admin_main ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
