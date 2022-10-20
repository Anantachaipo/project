package ku.cs.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import ku.cs.model.User;
import ku.cs.service.Account;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

public class AdminMainController {

    @FXML
    private Label accountNameLabel;

    @FXML
    private ImageView profile;

    private User user = LoginController.user;

    private Account account = new Account();

    @FXML
    public void initialize() {
        accountNameLabel.setText(user.getAccountName());
        profile.setImage(new Image("file:" + user.getPathProfile(), true));

    }

    @FXML
    public void handleToChangeImage(ActionEvent actionEvent){

        FileChooser chooser = new FileChooser();
        // SET FILECHOOSER INITIAL DIRECTORY
        chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        // DEFINE ACCEPTABLE FILE EXTENSION
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("images PNG JPG", "*.png", "*.jpg", "*.jpeg"));
        // GET FILE FROM FILECHOOSER WITH JAVAFX COMPONENT WINDOW
        Node source = (Node) actionEvent.getSource();
        //เลือกรูป
        File file = chooser.showOpenDialog(source.getScene().getWindow());
        if (file != null){
            try {
                // CREATE FOLDER IF NOT EXIST
                File destDir = new File("images");
                if (!destDir.exists()) destDir.mkdirs();
                // RENAME FILE
                String[] fileSplit = file.getName().split("\\.");
                String filename = LocalDate.now() + "_"+System.currentTimeMillis() + "."
                        + fileSplit[fileSplit.length - 1];
                Path target = FileSystems.getDefault().getPath(
                        destDir.getAbsolutePath()+System.getProperty("file.separator")+filename
                );
                // COPY WITH FLAG REPLACE FILE IF FILE IS EXIST
                Files.copy(file.toPath(), target, StandardCopyOption.REPLACE_EXISTING );
                // SET NEW FILE PATH TO IMAGE
                profile.setImage(new Image(target.toUri().toString()));
                account.changeImage(destDir + "/" + filename);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    public void handleToSignout(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("user_login");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า user_login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleToChangePassword(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("change_password");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า change_password ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleToManageUser(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("admin_manage_user");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า admin_manage_user ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleToAdminComplaint(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("admin_complaint");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า admin_complaint ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleToAdminProfile(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("admin_profile");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า admin_profile ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

}
