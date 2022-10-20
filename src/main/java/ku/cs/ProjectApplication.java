package ku.cs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ProjectApplication extends Application{
    @Override
    public void start(Stage stage) throws IOException {
        com.github.saacsos.FXRouter.bind(this, stage, "User login", 1000, 600);
        configRoute();
        com.github.saacsos.FXRouter.goTo("user_login");
    }
//        FXMLLoader fxmlLoader = new FXMLLoader(ProjectApplication.class.getResource("user_login.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 650, 400);
//        stage.setTitle("Project-Name");
//        stage.setScene(scene);
//        stage.show();
    private static void configRoute() {
        String packageStr = "ku/cs/";
        com.github.saacsos.FXRouter.when("user_login", packageStr+"user_login.fxml");
        com.github.saacsos.FXRouter.when("admin_profile", packageStr+"admin_profile.fxml");
        com.github.saacsos.FXRouter.when("create_account", packageStr+"create_account.fxml");
        com.github.saacsos.FXRouter.when("complaint_add", packageStr+"complaint_add.fxml");
        com.github.saacsos.FXRouter.when("menu", packageStr+"menu.fxml");
        com.github.saacsos.FXRouter.when("profile", packageStr+"profile.fxml");
        com.github.saacsos.FXRouter.when("create_officer_account", packageStr+"create_officer_account.fxml");
        com.github.saacsos.FXRouter.when("admin_manage_user", packageStr+"admin_manage_user.fxml");
        com.github.saacsos.FXRouter.when("admin_main", packageStr+"admin_main.fxml");
        com.github.saacsos.FXRouter.when("admin_complaint", packageStr+"admin_complaint.fxml");
        com.github.saacsos.FXRouter.when("create_officer_account", packageStr+"create_officer_account.fxml");
        com.github.saacsos.FXRouter.when("officer_main", packageStr+"officer_main.fxml");
        com.github.saacsos.FXRouter.when("change_password", packageStr+"change_password.fxml");
        com.github.saacsos.FXRouter.when("complaint_detail", packageStr+"complaint_detail.fxml");

    }

    public static void main(String[] args) {
        launch();
    }
}
