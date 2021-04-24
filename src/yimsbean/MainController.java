/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yimsbean;

import image.ImageSet;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import sound.soundController;

public class MainController implements Initializable {

    soundController BGsong = new soundController();
    @FXML
    private Label lpBet, currentLP, currentEnemyLP, winLabel, currentNumEnemy, lpBetEnemy, specialUseLabel,currentBet;
    @FXML
    private HBox playerCard, playerCardLabel;
    @FXML
    private HBox enemyCard, enemyCardLabel;

    @FXML
    private Label currentNum;

    @FXML
    private AnchorPane specialCardPane;
    @FXML
    private Button drawCardBtn, keepCurrentBtn, useSpecialBtn, continueBtn, continueBtnLabel;

    Boolean showSpecialPane = false;
    Boolean showLPDecrease = false;
    public static int bet = 500;
    public static int currentMaximum = 21;
    @FXML
    private Rectangle card1;
    @FXML
    private Rectangle card2;
    @FXML
    private Rectangle card3;
    @FXML
    private Rectangle card4;
    @FXML
    private Rectangle card5;
    @FXML
    private Rectangle card6;
    @FXML
    private Rectangle card7;
    @FXML
    private Label playerCard1;
    @FXML
    private Label playerCard2;
    @FXML
    private Label playerCard3;
    @FXML
    private Label playerCard4;
    @FXML
    private Label playerCard6;
    @FXML
    private Label playerCard5;
    @FXML
    private Label playerCard7;
    @FXML
    private Rectangle enemy1;
    @FXML
    private Rectangle enemy2;
    @FXML
    private Rectangle enemy3;
    @FXML
    private Rectangle enemy4;
    @FXML
    private Rectangle enemy5;
    @FXML
    private Rectangle enemy6;
    @FXML
    private Rectangle enemy7;
    @FXML
    private Button specialCard1;
    @FXML
    private Button specialCard2;
    @FXML
    private Button specialCard3;
    @FXML
    private Button specialCard4;
    @FXML
    private Button specialCard5;

    @FXML
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

    @FXML
    public void specialBtnUsed(ActionEvent event) {

        System.out.println("SpecialUsed");
        if (!specialCardPane.getChildren().isEmpty()) {
            Button specialCardButtonTemp = (Button) event.getSource();
            String idTemp = specialCardButtonTemp.getId();
            char index = idTemp.charAt(idTemp.length() - 1);
            Button buttonTemp = (Button) specialCardPane.getChildren().get(index - '0' - 1);
            specialUseLabel.setText("You use "+YIMSBean.game.getPlayer().getSpecialCard()[index - '0' - 1].getEffect());
            System.out.println("Special[i] : i = " + (index - '0' - 1));
            YIMSBean.game.getPlayer().useSpecial(index - '0' - 1);
            
            
            continueBtnLabel.setDisable(false);
            continueBtnLabel.setVisible(true);
            specialUseLabel.setVisible(true);
            specialCardPane.setDisable(true);

            drawCardBtn.setDisable(true);
            keepCurrentBtn.setDisable(true);
            useSpecialBtn.setDisable(true);

        }
        System.out.println(YIMSBean.game.getPlayer().getSpecialCard());

    }

    @FXML
    public void keepCurrentBtnOnAction(ActionEvent event) {
        if (YIMSBean.game.getEnemy().enough) {
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
        } else {
            enemyDrawMethod();
        }

        update();
    }

    @FXML
    public void drawCardBtnOnAction(ActionEvent event) {
        playerDrawMethod();
        enemyDrawMethod();
        update();
    }

    @FXML
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
        //Start Game
        YIMSBean.game.getPlayer().setTotal(YIMSBean.game.getPlayer().getTotal() + YIMSBean.game.getDeck().playerDraw());
        update();
        YIMSBean.game.getEnemy().setTotal(YIMSBean.game.getEnemy().getTotal() + YIMSBean.game.getDeck().enemyDraw());
        //enemyUpdate();
        YIMSBean.game.getPlayer().setTotal(YIMSBean.game.getPlayer().getTotal() + YIMSBean.game.getDeck().playerDraw());
        update();
        YIMSBean.game.getEnemy().setTotal(YIMSBean.game.getEnemy().getTotal() + YIMSBean.game.getDeck().enemyDraw());
        update();
        //enemyUpdate();
        //Start Game
    }

    @FXML
    public void continueBtnLabelOnAction(ActionEvent event) {
        continueBtnLabel.setDisable(true);
        continueBtnLabel.setVisible(false);
        specialUseLabel.setVisible(false);
        specialCardPane.setDisable(false);

        drawCardBtn.setDisable(false);
        keepCurrentBtn.setDisable(false);
        useSpecialBtn.setDisable(false);
        update();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //BGsong.playBGsong();
    }

    public void playerDrawMethod() {
        YIMSBean.game.getPlayer().setTotal(YIMSBean.game.getPlayer().getTotal() + YIMSBean.game.getDeck().playerDraw());
        if (YIMSBean.game.getPlayer().isGetSpecial()) {
            update();
            YIMSBean.game.getPlayer().setSpecial(false);
        }
    }

    public void enemyDrawMethod() {
        if (YIMSBean.game.getEnemy().total < 18) {
            YIMSBean.game.getEnemy().setTotal(YIMSBean.game.getEnemy().getTotal() + YIMSBean.game.getDeck().enemyDraw());
            YIMSBean.game.getEnemy().enough = false;
        } else {
            YIMSBean.game.getEnemy().enough = true;
        }
    }

    public void update() {
        System.out.println("Current bet : "+bet);
        currentBet.setText(bet+"");
        System.out.println(YIMSBean.game.getPlayer().specialCardAmount());
        if (YIMSBean.game.getPlayer().isGetSpecial() == true) {
            if (YIMSBean.game.getPlayer().specialCardAmount() - 1 >= 0 && YIMSBean.game.getPlayer().getSpecialCard()[YIMSBean.game.getPlayer().specialCardAmount() - 1] != null) {
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
                temp.setFill(new ImagePattern(ImageSet.getCard(YIMSBean.game.getPlayer().getNumCard()[i].getNum())));
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
                temp.setFill(new ImagePattern(ImageSet.getCard(YIMSBean.game.getEnemy().getNumCard()[i].getNum())));
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
        currentNum.setText(YIMSBean.game.getPlayer().getTotal() + "/"+currentMaximum);
        currentNumEnemy.setText(YIMSBean.game.getEnemy().getTotal() + "/"+currentMaximum);
        if (YIMSBean.game.getPlayer().getTotal() > currentMaximum) {
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
    }

    public void clear() {
        YIMSBean.game.getPlayer().reset();
        
        currentNum.setText(YIMSBean.game.getPlayer().getTotal() + "/"+currentMaximum);
        YIMSBean.game.getEnemy().reset();
        currentNum.setText(YIMSBean.game.getEnemy().getTotal() + "/"+currentMaximum);
        YIMSBean.game.getDeck().reset();
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
        bet = 500;
        currentMaximum = 21;
    }
}
