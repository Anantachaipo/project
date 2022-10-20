package ku.cs.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import ku.cs.service.ComplaintRecord;

import java.io.IOException;

public class ComplaintAddController {

    @FXML
    private TextField headLineNameTextField;

    @FXML
    private MenuButton menuButton;

    @FXML
    private TextField detailTextField;

    @FXML
    private Label submitLabel;

    private ComplaintRecord complaintRecord = new ComplaintRecord();

    @FXML
    private void submitComplaint() {
        String headLineName = headLineNameTextField.getText();
        String category = menuButton.getText();
        String detail = detailTextField.getText();

        String status = complaintRecord.recordComplaint(headLineName, category, detail);
        submitLabel.setText(status);
        if (status.equals("P")) {
            try {
                com.github.saacsos.FXRouter.goTo("complaint_detail");
            } catch (IOException e) {
                System.err.println("ไปที่หน้า complaint_detail ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }

    }

    @FXML
    public void handleToItem1() { menuButton.setText("คำร้องทั่วไป");}
    @FXML
    public void handleToItem2(){
        menuButton.setText("Add-Drop");
    }
    @FXML
    public void handleToItem3(){
        menuButton.setText("กิจกรรม");
    }













    @FXML
    public void handleBackButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("menu");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า menu ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }


}
