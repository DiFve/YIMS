/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yimsbean;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author folnw
 */
public class CreditController implements Initializable {

    
    @FXML
    private ImageView BackCAction;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void creditOnAction(ActionEvent event) throws IOException {
       
        Parent creditParent = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        Scene creditScene = new Scene(creditParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(creditScene);
        window.show();
    }

    @FXML
    private void BackCExit(MouseEvent event) {
        Image BackC0 = new Image("/image/Back0.png");
        BackCAction.setImage(BackC0);
    }

    @FXML
    private void BackCEnter(MouseEvent event) {
        Image BackC1 = new Image("/image/Back1.png");
        BackCAction.setImage(BackC1);
    }

}
