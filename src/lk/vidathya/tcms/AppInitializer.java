package lk.vidathya.tcms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AppInitializer extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/lk/vidathya/tcms/view/WelcomeForm.fxml"))));
        primaryStage.setTitle("VIDATHYA HIGHER EDUCATION CENTRE");
        primaryStage.setResizable(false);
        primaryStage.setFullScreen(true);
        primaryStage.getIcons().add(new Image("lk/vidathya/tcms/asset/icon/logoIcon.jpg"));
        primaryStage.show();
    }
}
