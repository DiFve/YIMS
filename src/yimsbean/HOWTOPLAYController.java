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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author yongy
 */
public class HOWTOPLAYController implements Initializable {

    @FXML
    private ImageView BackHAction;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void BackHExit(MouseEvent event) {
        Image howtoplay0 = new Image("/image/Back0.png");
        BackHAction.setImage(howtoplay0);
    }

    @FXML
    private void BackHEnter(MouseEvent event) {
        Image howtoplay1 = new Image("/image/Back1.png");
        BackHAction.setImage(howtoplay1);
    }

    @FXML
    private void HTPOnAction(ActionEvent event) throws IOException{
        Parent HTPParent = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        Scene HTPScene = new Scene(HTPParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(HTPScene);
        window.show();
    }

    
    
}
