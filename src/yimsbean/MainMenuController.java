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
        //Start Game
        YIMSBean.game.getPlayer().setTotal(YIMSBean.game.getPlayer().getTotal() + YIMSBean.game.getDeck().playerDraw());
        maincontroller.update();
        YIMSBean.game.getEnemy().setTotal(YIMSBean.game.getEnemy().getTotal() + YIMSBean.game.getDeck().enemyDraw());
        maincontroller.update();
        YIMSBean.game.getPlayer().setTotal(YIMSBean.game.getPlayer().getTotal() + YIMSBean.game.getDeck().playerDraw());
        maincontroller.update();
        YIMSBean.game.getEnemy().setTotal(YIMSBean.game.getEnemy().getTotal() + YIMSBean.game.getDeck().enemyDraw());
        maincontroller.update();
        //maincontroller.enemyUpdate();
        int[] temp = YIMSBean.game.getDeck().debug();
        for(int i=0 ;i<11;i++){
            System.out.print(temp[i] + " "+"sdsdsdsdsds");
            System.out.println("");
        }
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

}
