/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yimsbean;

import java.io.File;
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
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javafx.stage.Stage;
import sound.soundController;

/**
 * FXML Controller class
 *
 * @author jkbla
 */
public class MainMenuController {

    @FXML
    private Button gonextBtn;
    soundController click = new soundController();

    /**
     * Initializes the controller class.
     */
    @FXML

    private void gonextBtnOnAction(ActionEvent event) throws IOException {
        Parent mainMenuParent = FXMLLoader.load(getClass().getResource("InGameUI.fxml"));
        Scene mainMenuScene = new Scene(mainMenuParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(mainMenuScene);
        window.show();
        MainMenu.menuActive = false;
        click.playClickSound();
    }

    @FXML
    private void creditOnAction(ActionEvent event) throws IOException {

        Parent creditParent = FXMLLoader.load(getClass().getResource("credit.fxml"));
        Scene creditScene = new Scene(creditParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(creditScene);
        window.show();
        click.playClickSound();
    }

    @FXML
    private void ExitAction(ActionEvent event) throws IOException {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }

}
