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
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

public class MainController implements Initializable {

    @FXML
    private Label lpBet, currentLP, currentEnemyLP, winLabel, currentNumEnemy, lpBetEnemy;
    Boolean isCard1use = false, isCard2use = false, isCard3use = false, isCard4use = false, isCard5use = false, isCard6use = false, isCard7use = false;
    Boolean isEnemyCard1use = false, isEnemyCard2use = false, isEnemyCard3use = false, isEnemyCard4use = false, isEnemyCard5use = false, isEnemyCard6use = false, isEnemyCard7use = false;
    @FXML
    private HBox playerCard,playerCardLabel;
    //private Label playerCard1, playerCard2, playerCard3, playerCard4, playerCard5, playerCard6, playerCard7;
    @FXML
    private Rectangle enemy1, enemy2, enemy3, enemy4, enemy5, enemy6, enemy7;

    @FXML
    private Label currentNum;

    @FXML
    private AnchorPane specialCardPane;
    @FXML
    private Button drawCardBtn, keepCurrentBtn, useSpecialBtn, continueBtn;
    @FXML
    private Rectangle card1, card2, card3, card4, card5, card6, card7;

    @FXML
    private Label enemyCard1, enemyCard2, enemyCard3, enemyCard4, enemyCard5, enemyCard6, enemyCard7;

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
//        for(int i=0 ;i<YIMSBean.game.getPlayer().numCardCount;i++){
//            System.out.println(YIMSBean.game.getPlayer().getNumCard()[i].getNum()+1);
//        }
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

        enemyClear();
        clear();
        System.out.println("--------------------------------------------");
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
        //currentNum.textProperty().bind(temp);
    }

    public void update() {
        if (YIMSBean.game.getPlayer().isGetSpecial() == true) {
            Button buttonTemp = (Button) specialCardPane.getChildren().get(YIMSBean.game.getPlayer().specialCardAmount() - 1);
            buttonTemp.setText(YIMSBean.game.getPlayer().getSpecialCard()[YIMSBean.game.getPlayer().specialCardAmount() - 1].getEffect());
        }
        for (int i = 0; i < 5; i++) {
            Button buttonTemp = (Button) specialCardPane.getChildren().get(i);
            buttonTemp.setDisable(true);
            buttonTemp.setVisible(false);
        }
        for (int i = 0; i < YIMSBean.game.getPlayer().specialCardAmount(); i++) {
            Button buttonTemp = (Button) specialCardPane.getChildren().get(i);
            buttonTemp.setDisable(false);
            buttonTemp.setVisible(true);
        }
        for (int i = 0; i < 7; i++) {
            Rectangle temp = (Rectangle) playerCard.getChildren().get(i);
            temp.setDisable(true);
            temp.setVisible(false);
        }
        for (int i = 0; i < 7; i++) {
            if (YIMSBean.game.getPlayer().getNumCard()[i] != null) {
                Rectangle temp = (Rectangle) playerCard.getChildren().get(i);
                temp.setDisable(false);
                temp.setVisible(true);
            }
            else{
                Rectangle temp = (Rectangle) playerCard.getChildren().get(i);
                temp.setDisable(true);
                temp.setVisible(false);
            }
        }
        for (int i = 0; i < 7; i++) {
            Label temp = (Label) playerCardLabel.getChildren().get(i);
            temp.setDisable(true);
            temp.setVisible(false);
        }
        for (int i = 0; i < 7; i++) {
            if (YIMSBean.game.getPlayer().getNumCard()[i] != null) {
                Label temp = (Label) playerCardLabel.getChildren().get(i);
                temp.setText(String.valueOf(YIMSBean.game.getPlayer().getNumCard()[i].getNum()));
                temp.setDisable(false);
                temp.setVisible(true);
            }
        }
        currentNum.setText(YIMSBean.game.getPlayer().getTotal() + "/21");
    }

    public void enemyUpdate() {
        currentNumEnemy.setText(YIMSBean.game.getEnemy().getTotal() + "/21");
        if (!isEnemyCard1use) {
            isEnemyCard1use = true;
            enemyCard1.setText("" + YIMSBean.game.getDeck().latestEnemyDraw);
            enemyCard1.setVisible(true);
            enemy1.setVisible(true);
        } else if (!isEnemyCard2use) {
            isEnemyCard2use = true;
            enemyCard2.setText("" + YIMSBean.game.getDeck().latestEnemyDraw);
            enemyCard2.setVisible(true);
            enemy2.setVisible(true);
        } else if (!isEnemyCard3use) {
            isEnemyCard3use = true;
            enemyCard3.setText("" + YIMSBean.game.getDeck().latestEnemyDraw);
            enemyCard3.setVisible(true);
            enemy3.setVisible(true);
        } else if (!isEnemyCard4use) {
            isEnemyCard4use = true;
            enemyCard4.setText("" + YIMSBean.game.getDeck().latestEnemyDraw);
            enemyCard4.setVisible(true);
            enemy4.setVisible(true);
        } else if (!isEnemyCard5use) {
            isEnemyCard5use = true;
            enemyCard5.setText("" + YIMSBean.game.getDeck().latestEnemyDraw);
            enemyCard5.setVisible(true);
            enemy2.setVisible(true);
        } else if (!isEnemyCard6use) {
            isEnemyCard6use = true;
            enemyCard6.setText("" + YIMSBean.game.getDeck().latestEnemyDraw);
            enemyCard6.setVisible(true);
            enemy2.setVisible(true);
        } else if (!isEnemyCard7use) {
            isEnemyCard7use = true;
            enemyCard7.setText("" + YIMSBean.game.getDeck().latestEnemyDraw);
            enemyCard7.setVisible(true);
            enemy7.setVisible(true);
        }
    }

    public void clear() {
        YIMSBean.game.getPlayer().setTotal(0);
        currentNum.setText(YIMSBean.game.getPlayer().getTotal() + "/21");
        YIMSBean.game.getDeck().returnNumCardToDeck();
        YIMSBean.game.getDeck().setCount(0);
        YIMSBean.game.getPlayer().popCard();
        for (int i = 0; i < 7; i++) {
            Rectangle temp = (Rectangle) playerCard.getChildren().get(i);
            temp.setDisable(true);
            temp.setVisible(false);
        }
//        for (int i = 0; i < 7; i++) {
//            if (YIMSBean.game.getPlayer().getNumCard()[i] != null) {
//                Rectangle temp = (Rectangle) playerCard.getChildren().get(i);
//                temp.setDisable(false);
//                temp.setVisible(true);
//            }
//        }
//        isCard1use = false;
//        playerCard1.setVisible(false);
//        card1.setVisible(false);
//
//        isCard2use = false;
//        playerCard2.setVisible(false);
//        card2.setVisible(false);
//
//        isCard3use = false;
//        playerCard3.setVisible(false);
//        card3.setVisible(false);
//
//        isCard4use = false;
//        playerCard4.setVisible(false);
//        card4.setVisible(false);
//
//        isCard5use = false;
//        playerCard5.setVisible(false);
//        card5.setVisible(false);
//
//        isCard6use = false;
//        playerCard6.setVisible(false);
//        card6.setVisible(false);
//
//        isCard7use = false;
//        playerCard7.setVisible(false);
//        card7.setVisible(false);
    }

    public void enemyClear() {
        YIMSBean.game.getEnemy().setTotal(0);
        currentNum.setText(YIMSBean.game.getEnemy().getTotal() + "/21");
        YIMSBean.game.getDeck().returnNumCardToDeck();
        YIMSBean.game.getDeck().setCount(0);

        isEnemyCard1use = false;
        enemyCard1.setVisible(false);
        enemy1.setVisible(false);

        isEnemyCard2use = false;
        enemyCard2.setVisible(false);
        enemy2.setVisible(false);

        isEnemyCard3use = false;
        enemyCard3.setVisible(false);
        enemy3.setVisible(false);

        isEnemyCard4use = false;
        enemyCard4.setVisible(false);
        enemy4.setVisible(false);

        isEnemyCard4use = false;
        enemyCard4.setVisible(false);
        enemy4.setVisible(false);

        isEnemyCard5use = false;
        enemyCard5.setVisible(false);
        enemy5.setVisible(false);

        isEnemyCard5use = false;
        enemyCard5.setVisible(false);
        enemy5.setVisible(false);

    }

}
