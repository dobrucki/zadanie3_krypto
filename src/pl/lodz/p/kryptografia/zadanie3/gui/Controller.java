package pl.lodz.p.kryptografia.zadanie3.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;
import pl.lodz.p.kryptografia.zadanie3.api.DigitalSignature;

import java.io.File;
import java.io.FileNotFoundException;

public class Controller {
    @FXML
    private TextField publicKey1;
    @FXML
    private TextField privateKey1;
    @FXML
    private TextField publicKey2;
    @FXML
    private TextField privateKey2;
    @FXML
    private TextField file2;
    @FXML
    private TextField sign2;
    @FXML
    private TextField publicKey3;
    @FXML
    private TextField file3;
    @FXML
    private TextField sign3;

    private static DigitalSignature ds = new DigitalSignature();

    @FXML
    public void generateKeys(){
        try {
            ds.generateKeys(privateKey1.getText(), publicKey1.getText());
            AlertBox.display("Pomyślnie wygenerowano klucze. ");
        }
        catch(FileNotFoundException ex){
            AlertBox.display("Błąd", "Nie znaleziono pliku. ");
        }
    }

    @FXML
    public void sign(){
        try {
            ds.signFile(file2.getText(), privateKey2.getText(), publicKey2.getText(), sign2.getText());
            AlertBox.display("Pomyślnie wygenerowano podpis. ");
        }
        catch(Exception ex){
            AlertBox.display("Błąd", "Nieoczekiwany bład podpisu. ");
        }
    }

    @FXML
    public void verify(){
        try{
            boolean val = ds.verifyFile(file3.getText(), sign3.getText(), publicKey3.getText());
//            System.out.println(val);
            if(val)
                AlertBox.display("Pozytywna weryfikacja!");
            else
                AlertBox.display("Weryfikacja nie powiodła się!");
        }
        catch (Exception ex){
            AlertBox.display("Błąd", "Nieoczekiwany błąd weryfikacji. ");
        }
    }

    //////////////
    @FXML
    public void chooseFile(ActionEvent event){
        TextField tf = (TextField)((Button)event.getSource()).getParent().getChildrenUnmodifiable().get(0);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wybierz plik");
        File file = fileChooser.showOpenDialog(new Stage());
        tf.setText(file.getPath());
    }

}
