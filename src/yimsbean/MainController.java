/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yimsbean;

import image.ImageSet;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MainController implements Initializable {

    Boolean canDraw = true;

    @FXML
    private Label lpBet, currentLP, currentEnemyLP, winLabel, currentNumEnemy, lpBetEnemy, specialUseLabel, yourBet, enemyBet;
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
    Boolean canContinue = true;
    public static int betPlayer = 500, betEnemy = 500;
    public static int currentMaximum = 21;
    Boolean enableUpdate = true;

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
            specialUseLabel.setText("You use " + YIMSBean.game.getPlayer().getSpecialCard()[index - '0' - 1].getEffect());
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
        System.out.println("");
        if (YIMSBean.game.getPlayer().total > currentMaximum) {
            if (YIMSBean.game.getPlayer().LP - betPlayer > 0) {
                winLabel.setVisible(true);
                winLabel.setText("You lose :(");
                lpBet.setVisible(true);
                lpBet.setText("-" + betPlayer);
                continueBtn.setVisible(true);
                continueBtn.setDisable(false);
                drawCardBtn.setDisable(true);
                keepCurrentBtn.setDisable(true);
                useSpecialBtn.setDisable(true);
                YIMSBean.game.getPlayer().setLP(YIMSBean.game.getPlayer().getLP() - betPlayer);
                currentLP.setText("" + YIMSBean.game.getPlayer().getLP());

            } else {
                lpBet.setVisible(true);
                lpBet.setText("-" + betPlayer);
                winLabel.setVisible(true);
                winLabel.setText("Game Over :(");
                continueBtn.setVisible(true);
                continueBtn.setDisable(false);
                YIMSBean.game.getPlayer().setLP(YIMSBean.game.getPlayer().getLP() - betPlayer);
                System.out.println("Bet player : " + betPlayer + "LP Player : " + YIMSBean.game.getPlayer().LP);

            }
            EndTurn();
        } else {
            if (YIMSBean.game.getEnemy().enough && enableUpdate) {
                if (YIMSBean.game.getPlayer().total > YIMSBean.game.getEnemy().total && YIMSBean.game.getEnemy().total <= currentMaximum) {
                    if (YIMSBean.game.getEnemy().LP - betEnemy > 0) {
                        winLabel.setText("You win!!!");
                        winLabel.setVisible(true);
                        lpBetEnemy.setVisible(true);
                        lpBetEnemy.setText("-" + betEnemy);
                        continueBtn.setVisible(true);
                        continueBtn.setDisable(false);
                        drawCardBtn.setDisable(true);
                        keepCurrentBtn.setDisable(true);
                        useSpecialBtn.setDisable(true);
                        YIMSBean.game.getEnemy().setLP(YIMSBean.game.getEnemy().getLP() - betEnemy);
                        currentEnemyLP.setText("" + YIMSBean.game.getEnemy().getLP());
                    } else {
                        lpBetEnemy.setVisible(true);
                        lpBetEnemy.setText("-" + betEnemy);
                        winLabel.setVisible(true);
                        winLabel.setText("You win game :)");
                        continueBtn.setVisible(true);
                        continueBtn.setDisable(false);
                        YIMSBean.game.getEnemy().setLP(YIMSBean.game.getEnemy().getLP() - betEnemy);
                        System.out.println("Bet player : " + betPlayer + "LP Player : " + YIMSBean.game.getPlayer().LP);
                    }

                } else if (YIMSBean.game.getPlayer().total == YIMSBean.game.getEnemy().total && YIMSBean.game.getEnemy().total <= currentMaximum) {
                    winLabel.setVisible(true);
                    winLabel.setText("Draw");
                    continueBtn.setVisible(true);
                    continueBtn.setDisable(false);
                    drawCardBtn.setDisable(true);
                    keepCurrentBtn.setDisable(true);
                    useSpecialBtn.setDisable(true);
                } else if (YIMSBean.game.getPlayer().total < YIMSBean.game.getEnemy().total && YIMSBean.game.getEnemy().total <= currentMaximum) {
                    if (YIMSBean.game.getPlayer().LP - betPlayer > 0) {
                        winLabel.setVisible(true);
                        winLabel.setText("You lose :(");
                        lpBet.setVisible(true);
                        lpBet.setText("-" + betPlayer);
                        continueBtn.setVisible(true);
                        continueBtn.setDisable(false);
                        drawCardBtn.setDisable(true);
                        keepCurrentBtn.setDisable(true);
                        useSpecialBtn.setDisable(true);
                        YIMSBean.game.getPlayer().setLP(YIMSBean.game.getPlayer().getLP() - betPlayer);
                        currentLP.setText("" + YIMSBean.game.getPlayer().getLP());
                    } else {
                        lpBet.setVisible(true);
                        lpBet.setText("-" + betPlayer);
                        winLabel.setVisible(true);
                        winLabel.setText("Game Over :(");
                        continueBtn.setVisible(true);
                        continueBtn.setDisable(false);
                        YIMSBean.game.getPlayer().setLP(YIMSBean.game.getPlayer().getLP() - betPlayer);
                        System.out.println("Bet player : " + betPlayer + "LP Player : " + YIMSBean.game.getPlayer().LP);
                    }
                }
                update();
                EndTurn();
            } else {
                enemyDrawMethod();
            }

        }

        if (YIMSBean.game.getEnemy().total > currentMaximum && enableUpdate) {
            if (YIMSBean.game.getEnemy().LP - betEnemy > 0) {
                winLabel.setText("You win!!!");
                winLabel.setVisible(true);
                lpBetEnemy.setVisible(true);
                lpBetEnemy.setText("-" + betEnemy);
                continueBtn.setVisible(true);
                continueBtn.setDisable(false);
                drawCardBtn.setDisable(true);
                keepCurrentBtn.setDisable(true);
                useSpecialBtn.setDisable(true);
                YIMSBean.game.getEnemy().setLP(YIMSBean.game.getEnemy().getLP() - betEnemy);
                currentEnemyLP.setText("" + YIMSBean.game.getEnemy().getLP());
            } else {
                lpBetEnemy.setVisible(true);
                lpBetEnemy.setText("-" + betEnemy);
                winLabel.setVisible(true);
                winLabel.setText("You win game :)");
                continueBtn.setVisible(true);
                continueBtn.setDisable(false);
                YIMSBean.game.getEnemy().setLP(YIMSBean.game.getEnemy().getLP() - betEnemy);
                System.out.println("Bet player : " + betPlayer + "LP Player : " + YIMSBean.game.getPlayer().LP);
            }

        }

    }

    @FXML
    public void drawCardBtnOnAction(ActionEvent event) {
        playerDrawMethod();
        update();
        if (YIMSBean.game.getEnemy().total > currentMaximum && enableUpdate) {
            if (YIMSBean.game.getEnemy().LP - betEnemy > 0) {
                winLabel.setText("You win!!!");
                winLabel.setVisible(true);
                lpBetEnemy.setVisible(true);
                lpBetEnemy.setText("-" + betEnemy);
                continueBtn.setVisible(true);
                continueBtn.setDisable(false);
                drawCardBtn.setDisable(true);
                keepCurrentBtn.setDisable(true);
                useSpecialBtn.setDisable(true);
                YIMSBean.game.getEnemy().setLP(YIMSBean.game.getEnemy().getLP() - betEnemy);
                currentEnemyLP.setText("" + YIMSBean.game.getEnemy().getLP());
                canDraw = false;
            } else {
                lpBetEnemy.setVisible(true);
                lpBetEnemy.setText("-" + betEnemy);
                winLabel.setVisible(true);
                winLabel.setText("You win game :)");
                continueBtn.setVisible(true);
                continueBtn.setDisable(false);
                YIMSBean.game.getEnemy().setLP(YIMSBean.game.getEnemy().getLP() - betEnemy);
                System.out.println("Bet player : " + betPlayer + "LP Player : " + YIMSBean.game.getPlayer().LP);
                canDraw = false;
            }
            EndTurn();

        } else if (YIMSBean.game.getPlayer().getTotal() > currentMaximum) {
            if (YIMSBean.game.getPlayer().LP - betPlayer > 0) {
                lpBet.setVisible(true);
                lpBet.setText("-" + betPlayer);
                winLabel.setText("You lose :(");
                winLabel.setVisible(true);
                continueBtn.setVisible(true);
                continueBtn.setDisable(false);
                drawCardBtn.setDisable(true);
                keepCurrentBtn.setDisable(true);
                useSpecialBtn.setDisable(true);
                YIMSBean.game.getPlayer().setLP(YIMSBean.game.getPlayer().getLP() - betPlayer);
                System.out.println("Bet player : " + betPlayer + "LP Player : " + YIMSBean.game.getPlayer().LP);
                canDraw = false;
            } else {
                lpBet.setVisible(true);
                lpBet.setText("-" + betPlayer);
                winLabel.setVisible(true);
                winLabel.setText("Game Over :(");
                continueBtn.setVisible(true);
                continueBtn.setDisable(false);
                YIMSBean.game.getPlayer().setLP(YIMSBean.game.getPlayer().getLP() - betPlayer);
                System.out.println("Bet player : " + betPlayer + "LP Player : " + YIMSBean.game.getPlayer().LP);
                canDraw = false;
            }
            EndTurn();
        }
        if (!YIMSBean.game.getEnemy().enough && canDraw) {
            enemyDrawMethod();
        }
    }

    @FXML
    public void continueBtnOnAction(ActionEvent event) {
        if (YIMSBean.game.getPlayer().LP > 0 && YIMSBean.game.getEnemy().LP > 0) {
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
            playerDrawMethod();
            enemyDrawMethod();
            update();
            playerDrawMethod();
            enemyDrawMethod();
            update();
            //Start Game
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
            Parent mainMenuParent = null;
            try {
                mainMenuParent = loader.load();
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Scene mainMenuScene = new Scene(mainMenuParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(mainMenuScene);
            window.show();
            keepCurrentBtn.setDisable(false);
            drawCardBtn.setDisable(false);
            useSpecialBtn.setDisable(false);
            continueBtnLabel.setDisable(true);
            continueBtnLabel.setVisible(false);
            specialUseLabel.setVisible(false);
            enableUpdate = true;
        }
    }

    @FXML
    public void continueBtnLabelOnAction(ActionEvent event) {
        if (showSpecialPane == false) {
            keepCurrentBtn.setDisable(false);
            drawCardBtn.setDisable(false);
            useSpecialBtn.setDisable(false);
            continueBtnLabel.setDisable(true);
            continueBtnLabel.setVisible(false);
            specialUseLabel.setVisible(false);
            enableUpdate = true;

        } else if (showSpecialPane == true) {
            continueBtnLabel.setDisable(true);
            continueBtnLabel.setVisible(false);
            specialUseLabel.setVisible(false);
            specialCardPane.setDisable(false);
            useSpecialBtn.setDisable(false);
            enableUpdate = true;
        }

        update();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //BGsong.playBGsong();
    }

    public void playerDrawMethod() {
        if (YIMSBean.game.getPlayer().total > currentMaximum) {

        } else {
            YIMSBean.game.getPlayer().setTotal(YIMSBean.game.getPlayer().getTotal() + YIMSBean.game.getDeck().playerDraw());
        }
        if (YIMSBean.game.getPlayer().isGetSpecial()) {
            YIMSBean.game.getPlayer().setSpecial(false);
        }
    }

    public void enemyDrawMethod() {
        for (int i = 0; i < 5; i++) {
            if (YIMSBean.game.getEnemy().getSpecialCard()[i] != null) {
                System.out.println("ENEMY HAVEEEEE" + YIMSBean.game.getEnemy().getSpecialCard()[i].getEffect());
            }
        }
        if (YIMSBean.game.getPlayer().total == 21) {
            for (int i = 0; i < 5; i++) {
                if (YIMSBean.game.getEnemy().getSpecialCard()[i] != null && YIMSBean.game.getEnemy().getSpecialCard()[i].getEffect().equals("drawBestCardForEnemy")) {
                    specialUseLabel.setText("Enemy use " + YIMSBean.game.getEnemy().getSpecialCard()[i].getEffect());
                    YIMSBean.game.getEnemy().useSpecial(i);
                    enableUpdate = false;
                    continueBtnLabel.setDisable(false);
                    continueBtnLabel.setVisible(true);
                    specialUseLabel.setVisible(true);
                    specialCardPane.setDisable(true);
                    enableUpdate = false;
                    drawCardBtn.setDisable(true);
                    keepCurrentBtn.setDisable(true);
                    useSpecialBtn.setDisable(true);
                    break;

                }
            }
            for (int i = 0; i < 5; i++) {
                if (YIMSBean.game.getEnemy().getSpecialCard()[i] != null && YIMSBean.game.getEnemy().getSpecialCard()[i].getEffect().equals("returnEnemyLatestCard")) {
                    specialUseLabel.setText("Enemy use " + YIMSBean.game.getEnemy().getSpecialCard()[i].getEffect());
                    YIMSBean.game.getEnemy().useSpecial(i);
                    enableUpdate = false;
                    continueBtnLabel.setDisable(false);
                    continueBtnLabel.setVisible(true);
                    specialUseLabel.setVisible(true);
                    specialCardPane.setDisable(true);
                    enableUpdate = false;
                    drawCardBtn.setDisable(true);
                    keepCurrentBtn.setDisable(true);
                    useSpecialBtn.setDisable(true);
                    break;

                }
            }
        } else if (YIMSBean.game.getEnemy().total > 11) {
            for (int i = 0; i < 5; i++) {
                int j;
                if (YIMSBean.game.getEnemy().getSpecialCard()[i] != null && YIMSBean.game.getEnemy().getSpecialCard()[i].getEffect().equals("drawBestCardForMe")) {
                    specialUseLabel.setText("Enemy use " + YIMSBean.game.getEnemy().getSpecialCard()[i].getEffect());
                    YIMSBean.game.getEnemy().useSpecial(i);
                    enableUpdate = false;
                    continueBtnLabel.setDisable(false);
                    continueBtnLabel.setVisible(true);
                    specialUseLabel.setVisible(true);
                    specialCardPane.setDisable(true);

                    drawCardBtn.setDisable(true);
                    keepCurrentBtn.setDisable(true);
                    useSpecialBtn.setDisable(true);
                    break;

                }
            }
        } else if (currentMaximum - YIMSBean.game.getEnemy().total == 3) {
            for (int i = 0; i < 5; i++) {
                if (YIMSBean.game.getEnemy().getSpecialCard()[i] != null && YIMSBean.game.getEnemy().getSpecialCard()[i].getEffect().equals("drawCardNo3")) {
                    specialUseLabel.setText("Enemy use " + YIMSBean.game.getEnemy().getSpecialCard()[i].getEffect());
                    YIMSBean.game.getEnemy().useSpecial(i);
                    enableUpdate = false;
                    continueBtnLabel.setDisable(false);
                    continueBtnLabel.setVisible(true);
                    specialUseLabel.setVisible(true);
                    specialCardPane.setDisable(true);

                    drawCardBtn.setDisable(true);
                    keepCurrentBtn.setDisable(true);
                    useSpecialBtn.setDisable(true);
                    break;

                }
            }
        } else if (currentMaximum - YIMSBean.game.getEnemy().total == 4) {
            for (int i = 0; i < 5; i++) {
                int j;
                if (YIMSBean.game.getEnemy().getSpecialCard()[i] != null && YIMSBean.game.getEnemy().getSpecialCard()[i].getEffect().equals("drawCardNo4")) {
                    specialUseLabel.setText("Enemy use " + YIMSBean.game.getEnemy().getSpecialCard()[i].getEffect());
                    YIMSBean.game.getEnemy().useSpecial(i);
                    enableUpdate = false;
                    continueBtnLabel.setDisable(false);
                    continueBtnLabel.setVisible(true);
                    specialUseLabel.setVisible(true);
                    specialCardPane.setDisable(true);
                    drawCardBtn.setDisable(true);
                    keepCurrentBtn.setDisable(true);
                    useSpecialBtn.setDisable(true);
                    break;

                }

            }
        } else if (currentMaximum - YIMSBean.game.getEnemy().total == 5) {
            for (int i = 0; i < 5; i++) {
                int j;
                if (YIMSBean.game.getEnemy().getSpecialCard()[i] != null && YIMSBean.game.getEnemy().getSpecialCard()[i].getEffect().equals("drawCardNo5")) {
                    specialUseLabel.setText("Enemy use " + YIMSBean.game.getEnemy().getSpecialCard()[i].getEffect());
                    YIMSBean.game.getEnemy().useSpecial(i);
                    enableUpdate = false;
                    continueBtnLabel.setDisable(false);
                    continueBtnLabel.setVisible(true);
                    specialUseLabel.setVisible(true);
                    specialCardPane.setDisable(true);

                    drawCardBtn.setDisable(true);
                    keepCurrentBtn.setDisable(true);
                    useSpecialBtn.setDisable(true);
                    break;

                }

            }
        } else if (currentMaximum - YIMSBean.game.getEnemy().total == 6) {
            for (int i = 0; i < 5; i++) {
                int j;
                if (YIMSBean.game.getEnemy().getSpecialCard()[i] != null && YIMSBean.game.getEnemy().getSpecialCard()[i].getEffect().equals("drawCardNo6")) {
                    specialUseLabel.setText("Enemy use " + YIMSBean.game.getEnemy().getSpecialCard()[i].getEffect());
                    YIMSBean.game.getEnemy().useSpecial(i);
                    enableUpdate = false;
                    continueBtnLabel.setDisable(false);
                    continueBtnLabel.setVisible(true);
                    specialUseLabel.setVisible(true);
                    specialCardPane.setDisable(true);

                    drawCardBtn.setDisable(true);
                    keepCurrentBtn.setDisable(true);
                    useSpecialBtn.setDisable(true);
                    break;

                }
            }
        }

        if (YIMSBean.game.getEnemy().total < currentMaximum - 3) {
            YIMSBean.game.getEnemy().setTotal(YIMSBean.game.getEnemy().getTotal() + YIMSBean.game.getDeck().enemyDraw());
            if (YIMSBean.game.getEnemy().total >= currentMaximum - 3) {
                YIMSBean.game.getEnemy().enough = true;
            } else {
                YIMSBean.game.getEnemy().enough = false;
            }
        } else if (YIMSBean.game.getEnemy().total >= currentMaximum - 3 && YIMSBean.game.getEnemy().total <= currentMaximum) {
            YIMSBean.game.getEnemy().enough = true;
        }
        update();
        if (YIMSBean.game.getEnemy().total > currentMaximum && currentMaximum != 24) {
            for (int i = 0; i < 5; i++) {
                if (YIMSBean.game.getEnemy().getSpecialCard()[i] != null && YIMSBean.game.getEnemy().getSpecialCard()[i].getEffect().equals("returnMyLatestCard")) {
                    specialUseLabel.setText("Enemy use " + YIMSBean.game.getEnemy().getSpecialCard()[i].getEffect());
                    YIMSBean.game.getEnemy().useSpecial(i);
                    YIMSBean.game.getEnemy().enough = false;
                    enableUpdate = false;
                    continueBtnLabel.setDisable(false);
                    continueBtnLabel.setVisible(true);
                    specialUseLabel.setVisible(true);
                    specialCardPane.setDisable(true);

                    drawCardBtn.setDisable(true);
                    keepCurrentBtn.setDisable(true);
                    useSpecialBtn.setDisable(true);
                    break;
                } else if (YIMSBean.game.getEnemy().getSpecialCard()[i] != null && YIMSBean.game.getEnemy().getSpecialCard()[i].getEffect().equals("changeToClosestTo24")) {
                    specialUseLabel.setText("Enemy use " + YIMSBean.game.getEnemy().getSpecialCard()[i].getEffect());
                    YIMSBean.game.getEnemy().useSpecial(i);
                    enableUpdate = false;
                    continueBtnLabel.setDisable(false);
                    continueBtnLabel.setVisible(true);
                    specialUseLabel.setVisible(true);
                    specialCardPane.setDisable(true);

                    drawCardBtn.setDisable(true);
                    keepCurrentBtn.setDisable(true);
                    useSpecialBtn.setDisable(true);
                    break;
                }
            }
        }
    }

    public void update() {
        if (enableUpdate) {
            if (YIMSBean.game.getEnemy().total >= 18) {
                YIMSBean.game.getEnemy().enough = true;
                if (currentMaximum - YIMSBean.game.getEnemy().total <= 2 && YIMSBean.game.getEnemy().total <= currentMaximum) {
                    for (int i = 0; i < 5; i++) {
                        if (YIMSBean.game.getEnemy().getSpecialCard()[i] != null && YIMSBean.game.getEnemy().getSpecialCard()[i].getEffect().equals("enemyBetx2")) {
                            specialUseLabel.setText("Enemy use " + YIMSBean.game.getEnemy().getSpecialCard()[i].getEffect());
                            YIMSBean.game.getEnemy().useSpecial(i);
                            continueBtnLabel.setDisable(false);
                            continueBtnLabel.setVisible(true);
                            specialUseLabel.setVisible(true);
                            specialCardPane.setDisable(true);
                            drawCardBtn.setDisable(true);
                            keepCurrentBtn.setDisable(true);
                            useSpecialBtn.setDisable(true);
                            break;
                        }
                    }
                }
            }
            yourBet.setText(betPlayer + "");
            enemyBet.setText(betEnemy + "");
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
                    if (i == 0) {
                        temp.setFill(new ImagePattern(ImageSet.getCard(YIMSBean.game.getEnemy().getNumCard()[i].getNum())));
                    } else {
                        temp.setFill(new ImagePattern(ImageSet.getCard(0)));
                    }
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
            currentNum.setText(YIMSBean.game.getPlayer().getTotal() + "/" + currentMaximum);
            currentNumEnemy.setText("X/" + currentMaximum);
        }

    }

    public void clear() {
        YIMSBean.game.getPlayer().reset();

        currentNum.setText(YIMSBean.game.getPlayer().getTotal() + "/" + currentMaximum);
        YIMSBean.game.getEnemy().reset();
        currentNum.setText(YIMSBean.game.getEnemy().getTotal() + "/" + currentMaximum);
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
        betPlayer = 500;
        betEnemy = 500;
        currentMaximum = 21;
        canDraw = true;
    }

    private void EndTurn() {
        for (int i = 0; i < 7; i++) {
            if (YIMSBean.game.getEnemy().getNumCard()[i] != null) {
                Rectangle temp = (Rectangle) enemyCard.getChildren().get(i);
                temp.setFill(new ImagePattern(ImageSet.getCard(YIMSBean.game.getEnemy().getNumCard()[i].getNum())));
                temp.setDisable(false);
                temp.setVisible(true);
            }
        }
        currentNumEnemy.setText(YIMSBean.game.getEnemy().getTotal() + "/" + currentMaximum);
    }
}
