/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yimsbean;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;





public class MainController implements Initializable{
    @FXML
    private Label  lpBet, currentLP, currentEnemyLP, winLabel;
    //StringProperty temp = new SimpleStringProperty(YIMSBean.game.getPlayer().getTotal()+"/21");
    
    
    @FXML
    public Label currentNum;
    
    @FXML
    private AnchorPane specialCardPane;
    @FXML
    private Button drawCardBtn, keepCurrentBtn, useSpecialBtn, continueBtn;
    Boolean showSpecialPane = false;
    Boolean showLPDecrease = false;
    int bet = 500;

    public void useSpecialBtnOnAction(ActionEvent event) {
        showSpecialPane = !showSpecialPane;
        if (showSpecialPane == false) {
            keepCurrentBtn.setDisable(false);
            drawCardBtn.setDisable(false);
            specialCardPane.setDisable(true);
            specialCardPane.setVisible(false);
        } else {
            drawCardBtn.setDisable(true);
            keepCurrentBtn.setDisable(true);
            specialCardPane.setDisable(false);
            specialCardPane.setVisible(true);
            System.out.println(YIMSBean.game.getPlayer().specialCardAmount());
            for (int i = 0; i < YIMSBean.game.getPlayer().specialCardAmount(); i++) {
                Button buttonTemp = (Button) specialCardPane.getChildren().get(i);
                buttonTemp.setDisable(false);
                buttonTemp.setVisible(true);
            }
        }
    }

    public void specialBtnUsed(ActionEvent event) {
        System.out.println("SpecialUsed");
        Button specialCardButtonTemp = (Button) event.getSource();
        String idTemp = specialCardButtonTemp.getId();
        char index = idTemp.charAt(idTemp.length() - 1);
        Button buttonTemp = (Button) specialCardPane.getChildren().get(index - '0' - 1);
        buttonTemp.setDisable(true);
        buttonTemp.setVisible(false);
        YIMSBean.game.getPlayer().useSpecial(index - '0' - 1);
    }

    public void keepCurrentBtnOnAction(ActionEvent event) {
        System.out.println("Keep");
        System.out.println("Print");
    }

    public void drawCardBtnOnAction(ActionEvent event) {
        YIMSBean.game.getPlayer().setTotal(YIMSBean.game.getPlayer().getTotal() + YIMSBean.game.getDeck().draw());
        if (YIMSBean.game.getPlayer().isGetSpecial()) {
            Button buttonTemp = (Button) specialCardPane.getChildren().get(YIMSBean.game.getPlayer().specialCardAmount() - 1);
            buttonTemp.setDisable(false);
            buttonTemp.setVisible(true);
            buttonTemp.setText(YIMSBean.game.getPlayer().getSpecialCard()[YIMSBean.game.getPlayer().specialCardAmount() - 1].getEffect());
            YIMSBean.game.getPlayer().setSpecial(false);
        }
        currentNum.setText(YIMSBean.game.getPlayer().getTotal() + "/21");
        if (YIMSBean.game.getPlayer().getTotal() > 21) {
            lpBet.setVisible(true);
            lpBet.setText("-" + bet);
            winLabel.setVisible(true);
            continueBtn.setVisible(true);
            continueBtn.setDisable(false);
            drawCardBtn.setDisable(true);
            keepCurrentBtn.setDisable(true);
            useSpecialBtn.setDisable(true);
            YIMSBean.game.getPlayer().setLP(YIMSBean.game.getPlayer().getLP() - bet);
        }
    }

    public void continueBtnOnAction(ActionEvent event) {
        continueBtn.setVisible(true);
        continueBtn.setDisable(false);
        drawCardBtn.setDisable(false);
        keepCurrentBtn.setDisable(false);
        useSpecialBtn.setDisable(false);
        currentLP.setText("" + YIMSBean.game.getPlayer().getLP());

        winLabel.setVisible(false);
        continueBtn.setVisible(false);
        continueBtn.setDisable(true);

        lpBet.setVisible(false);

        YIMSBean.game.getPlayer().setTotal(0);
        currentNum.setText(YIMSBean.game.getPlayer().getTotal() + "/21");
        YIMSBean.game.getDeck().returnNumCardToDeck();
        YIMSBean.game.getDeck().setCount(0);
    }
    


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //currentNum.textProperty().bind(temp);
    }


}
