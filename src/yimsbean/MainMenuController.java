/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yimsbean;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;

import javafx.stage.Stage;
import sound.soundController;

/**
 * FXML Controller class
 *
 * @author jkbla
 */
public class MainMenuController implements Initializable, ChangeListener {

    soundController click = new soundController();

    @FXML
    private Button gonextBtn;
    @FXML
    private Button creditBtn;
    @FXML
    private Button exitBtn;
    @FXML
    private Slider soundSlider;
    @FXML
    private ImageView PlayAction;
    @FXML
    private ImageView CreditAction;
    @FXML
    private ImageView ExitAction;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        soundSlider.valueProperty().addListener(this);
        soundSlider.setValue((soundController.volume) * 100.0);
        gonextBtn.setBackground(Background.EMPTY);
        creditBtn.setBackground(Background.EMPTY);
        exitBtn.setBackground(Background.EMPTY);
    }

    /**
     * Initializes the controller class.
     */
    @FXML

    private void gonextBtnOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InGameUI.fxml"));
        Parent mainMenuParent = loader.load();
        Scene mainMenuScene = new Scene(mainMenuParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(mainMenuScene);
        window.show();
        MainMenu.menuActive = false;
        click.playClickSound();
        MainController maincontroller = loader.getController();
        maincontroller.clear();
        YIMSBean.game.getPlayer().resetSpecial();
        YIMSBean.game.getEnemy().resetSpecial();
        YIMSBean.game.getPlayer().LP=1000;
        YIMSBean.game.getEnemy().LP=1000;
        //Start Game
        maincontroller.playerDrawMethod();
        maincontroller.update();
        maincontroller.enemyDrawMethod();
        maincontroller.update();
        maincontroller.playerDrawMethod();
        maincontroller.update();
        maincontroller.enemyDrawMethod();
        maincontroller.update();
        //Start Game
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

    @Override
    public void changed(ObservableValue ov, Object t, Object t1) {
        double value = (double) soundSlider.getValue();
        soundController.setVolume(value * 0.01);
    }

    @FXML
    private void PlayExit(MouseEvent event) {
        Image Play0 = new Image("/image/Play0.png");
        PlayAction.setImage(Play0);
    }

    @FXML
    private void PlayEnter(MouseEvent event) {
        Image Play1 = new Image("/image/Play1.png");
        PlayAction.setImage(Play1);
    }

    @FXML
    private void CreditExit(MouseEvent event) {
        Image Credit0 = new Image("/image/Credit0.png");
        CreditAction.setImage(Credit0);
    }

    @FXML
    private void CreditEnter(MouseEvent event) {
        Image Credit1 = new Image("/image/Credit1.png");
        CreditAction.setImage(Credit1);
    }

    @FXML
    private void ExitExit(MouseEvent event) {
        Image Exite0 = new Image("/image/EXIT0.png");
        ExitAction.setImage(Exite0);
    }

    @FXML
    private void ExitEnter(MouseEvent event) {
        Image Exite1 = new Image("/image/EXIT1.png");
        ExitAction.setImage(Exite1);
    }

}
