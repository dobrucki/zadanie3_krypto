package pl.lodz.p.kryptografia.zadanie3.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import pl.lodz.p.kryptografia.zadanie3.api.DigitalSignature;

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
        }
        catch(FileNotFoundException ex){
            System.out.println("Nie znaleziono pliku. " + ex);
        }
    }

    @FXML
    public void sign(){
        try {
            ds.signFile(file2.getText(), privateKey2.getText(), publicKey2.getText(), sign2.getText());
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }

    @FXML
    public void verify(ActionEvent event){
        try{
            boolean val = ds.verifyFile(file3.getText(), sign3.getText(), publicKey3.getText());
            Popup popup = new Popup();
            popup.show(((Node)event.getSource()).getScene().getWindow());
            System.out.println(val);
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }

}
