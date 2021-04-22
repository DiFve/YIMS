/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yimsbean;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import sound.soundController;

public class MainController implements Initializable {

    soundController BGsong = new soundController();
    @FXML
    private Label lpBet, currentLP, currentEnemyLP, winLabel, currentNumEnemy, lpBetEnemy;
    @FXML
    private HBox playerCard, playerCardLabel;
    @FXML
    private HBox enemyCard, enemyCardLabel;

    @FXML
    private Label currentNum;

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
        }
        update();
        enemyUpdate();
    }

    public void specialBtnUsed(ActionEvent event) {
        System.out.println("SpecialUsed");
        if (!specialCardPane.getChildren().isEmpty()) {
            Button specialCardButtonTemp = (Button) event.getSource();
            String idTemp = specialCardButtonTemp.getId();
            System.out.println(idTemp);
            char index = idTemp.charAt(idTemp.length() - 1);
            Button buttonTemp = (Button) specialCardPane.getChildren().get(index - '0' - 1);
            buttonTemp.setDisable(true);
            buttonTemp.setVisible(false);
            YIMSBean.game.getPlayer().useSpecial(index - '0' - 1);
        }
        update();
    }

    public void keepCurrentBtnOnAction(ActionEvent event) {
        if (YIMSBean.game.getPlayer().total > YIMSBean.game.getEnemy().total) {
            winLabel.setText("You win!!!");
            winLabel.setVisible(true);
            lpBetEnemy.setVisible(true);
            lpBetEnemy.setText("-" + bet);
            continueBtn.setVisible(true);
            continueBtn.setDisable(false);
            drawCardBtn.setDisable(true);
            keepCurrentBtn.setDisable(true);
            useSpecialBtn.setDisable(true);
            YIMSBean.game.getEnemy().setLP(YIMSBean.game.getEnemy().getLP() - bet);
            currentEnemyLP.setText("" + YIMSBean.game.getEnemy().getLP());
        } else if (YIMSBean.game.getPlayer().total == YIMSBean.game.getEnemy().total) {
            winLabel.setVisible(true);
            winLabel.setText("Draw");
            continueBtn.setVisible(true);
            continueBtn.setDisable(false);
            drawCardBtn.setDisable(true);
            keepCurrentBtn.setDisable(true);
            useSpecialBtn.setDisable(true);
        } else {
            winLabel.setVisible(true);
            winLabel.setText("You lose :(");
            lpBet.setVisible(true);
            lpBet.setText("-" + bet);
            continueBtn.setVisible(true);
            continueBtn.setDisable(false);
            drawCardBtn.setDisable(true);
            keepCurrentBtn.setDisable(true);
            useSpecialBtn.setDisable(true);
            YIMSBean.game.getPlayer().setLP(YIMSBean.game.getPlayer().getLP() - bet);
            currentLP.setText("" + YIMSBean.game.getPlayer().getLP());
        }
        update();
    }

    public void drawCardBtnOnAction(ActionEvent event) {
        YIMSBean.game.getPlayer().setTotal(YIMSBean.game.getPlayer().getTotal() + YIMSBean.game.getDeck().playerDraw());
        if (YIMSBean.game.getPlayer().isGetSpecial()) {
            update();
            YIMSBean.game.getPlayer().setSpecial(false);
        }
        if (YIMSBean.game.getEnemy().total < 18) {
            YIMSBean.game.getEnemy().setTotal(YIMSBean.game.getEnemy().getTotal() + YIMSBean.game.getDeck().enemyDraw());
            enemyUpdate();
        }
        if (YIMSBean.game.getPlayer().getTotal() > 21) {
            lpBet.setVisible(true);
            lpBet.setText("-" + bet);
            winLabel.setText("You lose :(");
            winLabel.setVisible(true);
            continueBtn.setVisible(true);
            continueBtn.setDisable(false);
            drawCardBtn.setDisable(true);
            keepCurrentBtn.setDisable(true);
            useSpecialBtn.setDisable(true);
            YIMSBean.game.getPlayer().setLP(YIMSBean.game.getPlayer().getLP() - bet);
        }
        update();
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
        lpBetEnemy.setVisible(false);
        lpBet.setVisible(false);

        clear();
        enemyClear();
        //Start Game
        YIMSBean.game.getPlayer().setTotal(YIMSBean.game.getPlayer().getTotal() + YIMSBean.game.getDeck().playerDraw());
        update();
        YIMSBean.game.getEnemy().setTotal(YIMSBean.game.getEnemy().getTotal() + YIMSBean.game.getDeck().enemyDraw());
        enemyUpdate();
        YIMSBean.game.getPlayer().setTotal(YIMSBean.game.getPlayer().getTotal() + YIMSBean.game.getDeck().playerDraw());
        update();
        YIMSBean.game.getEnemy().setTotal(YIMSBean.game.getEnemy().getTotal() + YIMSBean.game.getDeck().enemyDraw());
        enemyUpdate();
        //Start Game
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //BGsong.playBGsong();
    }

    public void update() {

        if (YIMSBean.game.getPlayer().isGetSpecial() == true) {
            if(YIMSBean.game.getPlayer().specialCardAmount()-1 >= 0){
                Button buttonTemp = (Button) specialCardPane.getChildren().get(YIMSBean.game.getPlayer().specialCardAmount() - 1);
                buttonTemp.setText(YIMSBean.game.getPlayer().getSpecialCard()[YIMSBean.game.getPlayer().specialCardAmount() - 1].getEffect());
                
            }
        }
        for (int i = 0; i < 5; i++) {
            Button buttonTemp = (Button) specialCardPane.getChildren().get(i);
            buttonTemp.setDisable(true);
            buttonTemp.setVisible(false);
        }
        for (int i = 0; i < YIMSBean.game.getPlayer().specialCardAmount(); i++) {
            if (YIMSBean.game.getPlayer().getSpecialCard()[i] != null) {
                Button buttonTemp = (Button) specialCardPane.getChildren().get(i);
                buttonTemp.setText(YIMSBean.game.getPlayer().getSpecialCard()[i].getEffect());
                buttonTemp.setDisable(false);
                buttonTemp.setVisible(true);

            }
        }
        for (int i = 0; i < 7; i++) {
            Rectangle temp = (Rectangle) playerCard.getChildren().get(i);
            temp.setDisable(true);
            temp.setVisible(false);
        }
        for (int i = 0; i < 7; i++) {
            Label temp = (Label) playerCardLabel.getChildren().get(i);
            temp.setDisable(true);
            temp.setVisible(false);
        }

        for (int i = 0; i < 7; i++) {
            Rectangle temp = (Rectangle) enemyCard.getChildren().get(i);
            temp.setDisable(true);
            temp.setVisible(false);
        }
        for (int i = 0; i < 7; i++) {
            Label temp = (Label) enemyCardLabel.getChildren().get(i);
            temp.setDisable(true);
            temp.setVisible(false);
        }
        for (int i = 0; i < 7; i++) {
            if (YIMSBean.game.getPlayer().getNumCard()[i] != null) {
                Rectangle temp = (Rectangle) playerCard.getChildren().get(i);
                temp.setDisable(false);
                temp.setVisible(true);
            }
        }
        for (int i = 0; i < 7; i++) {
            if (YIMSBean.game.getPlayer().getNumCard()[i] != null) {
                Label temp = (Label) playerCardLabel.getChildren().get(i);
                temp.setText(String.valueOf(YIMSBean.game.getPlayer().getNumCard()[i].getNum()));
                temp.setDisable(false);
                temp.setVisible(true);
            }
        }
        for (int i = 0; i < 7; i++) {
            if (YIMSBean.game.getEnemy().getNumCard()[i] != null) {
                Rectangle temp = (Rectangle) enemyCard.getChildren().get(i);
                temp.setDisable(false);
                temp.setVisible(true);
            }
        }
        for (int i = 0; i < 7; i++) {
            if (YIMSBean.game.getEnemy().getNumCard()[i] != null) {
                Label temp = (Label) enemyCardLabel.getChildren().get(i);
                temp.setText(String.valueOf(YIMSBean.game.getEnemy().getNumCard()[i].getNum()));
                temp.setDisable(false);
                temp.setVisible(true);
            }
        }
        currentNum.setText(YIMSBean.game.getPlayer().getTotal() + "/21");
    }

    public void enemyUpdate() {

        for (int i = 0; i < 7; i++) {
            Rectangle temp = (Rectangle) enemyCard.getChildren().get(i);
            temp.setDisable(true);
            temp.setVisible(false);
        }
        for (int i = 0; i < 7; i++) {
            Label temp = (Label) enemyCardLabel.getChildren().get(i);
            temp.setDisable(true);
            temp.setVisible(false);
        }

        for (int i = 0; i < 7; i++) {
            if (YIMSBean.game.getEnemy().getNumCard()[i] != null) {
                Rectangle temp = (Rectangle) enemyCard.getChildren().get(i);
                temp.setDisable(false);
                temp.setVisible(true);
                //System.out.println("MEEEEEEEE"+i);
            }
        }
        for (int i = 0; i < 7; i++) {
            if (YIMSBean.game.getEnemy().getNumCard()[i] != null) {
                Label temp = (Label) enemyCardLabel.getChildren().get(i);
                temp.setText(String.valueOf(YIMSBean.game.getEnemy().getNumCard()[i].getNum()));
                temp.setDisable(false);
                temp.setVisible(true);
            }
        }
        currentNumEnemy.setText(YIMSBean.game.getEnemy().getTotal() + "/21");
    }

    public void clear() {
        YIMSBean.game.getPlayer().setTotal(0);
        currentNum.setText(YIMSBean.game.getPlayer().getTotal() + "/21");
        YIMSBean.game.getDeck().resetNumCardToDeck();
        YIMSBean.game.getDeck().resetSpecialCardToDeck();
        YIMSBean.game.getDeck().setCount(0);
        while (!YIMSBean.game.getPlayer().isNumHandEmpty()) {
            YIMSBean.game.getPlayer().popCard();
        }
        for (int i = 0; i < 7; i++) {
            Rectangle temp = (Rectangle) playerCard.getChildren().get(i);
            temp.setDisable(true);
            temp.setVisible(false);
        }
        for (int i = 0; i < 7; i++) {
            Label temp = (Label) playerCardLabel.getChildren().get(i);
            temp.setDisable(true);
            temp.setVisible(false);
        }
    }

    public void enemyClear() {
        YIMSBean.game.getEnemy().setTotal(0);
        currentNum.setText(YIMSBean.game.getEnemy().getTotal() + "/21");
        YIMSBean.game.getDeck().resetNumCardToDeck();
        YIMSBean.game.getDeck().setCount(0);

        while (!YIMSBean.game.getEnemy().isNumHandEmpty()) {
            YIMSBean.game.getEnemy().popCard();
        }
        for (int i = 0; i < 7; i++) {
            Rectangle temp = (Rectangle) enemyCard.getChildren().get(i);
            temp.setDisable(true);
            temp.setVisible(false);
        }
        for (int i = 0; i < 7; i++) {
            Label temp = (Label) enemyCardLabel.getChildren().get(i);
            temp.setDisable(true);
            temp.setVisible(false);
        }
    }

}
