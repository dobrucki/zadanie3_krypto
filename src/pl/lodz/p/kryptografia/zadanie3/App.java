package pl.lodz.p.kryptografia.zadanie3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.lodz.p.kryptografia.zadanie3.api.DigitalSignature;

import java.security.NoSuchAlgorithmException;

public class App extends Application {

    public static void main(String[] args) throws Exception{
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("gui/gui.fxml"));
        Scene scene = new Scene(root, 1000, 600);
        primaryStage.setTitle("Signature App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
