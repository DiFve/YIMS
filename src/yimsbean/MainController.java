/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yimsbean;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class MainController {

    @FXML
    private Label currentNum,lpBet,currentLP,currentEnemyLP,winLabel;
    @FXML
    private AnchorPane specialCardPane;
    @FXML
    private Button drawCardBtn,keepCurrentBtn,useSpecialBtn,continueBtn;
    Game game = new Game();
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
            System.out.println(game.getPlayer().specialCardAmount());
            for (int i = 0; i < game.getPlayer().specialCardAmount(); i++) {
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
        game.getPlayer().useSpecial(index - '0' - 1);
    }

    public void keepCurrentBtnOnAction(ActionEvent event) {
        System.out.println("Keep");
        System.out.println("Print");
    }

    public void drawCardBtnOnAction(ActionEvent event) {
        game.getPlayer().setTotal(game.getPlayer().getTotal() + game.getDeck().draw());
        if (game.getPlayer().isGetSpecial()) {
            Button buttonTemp = (Button) specialCardPane.getChildren().get(game.getPlayer().specialCardAmount() - 1);
            buttonTemp.setDisable(false);
            buttonTemp.setVisible(true);
            buttonTemp.setText(game.getPlayer().getSpecialCard()[game.getPlayer().specialCardAmount()-1].getEffect());
            game.getPlayer().setSpecial(false);
        }
        currentNum.setText(game.getPlayer().getTotal() + "/21");
        if (game.getPlayer().getTotal() > 21) {
            lpBet.setVisible(true);
            lpBet.setText("-"+bet);
            winLabel.setVisible(true);
            continueBtn.setVisible(true);
            continueBtn.setDisable(false);
            drawCardBtn.setDisable(true);
            keepCurrentBtn.setDisable(true);
            useSpecialBtn.setDisable(true);
            game.getPlayer().setLP(game.getPlayer().getLP()-bet);
        }
    }
    public void continueBtnOnAction(ActionEvent event)
    {
        continueBtn.setVisible(true);
        continueBtn.setDisable(false);
        drawCardBtn.setDisable(false);
        keepCurrentBtn.setDisable(false);
        useSpecialBtn.setDisable(false);
        currentLP.setText(""+game.getPlayer().getLP());
        
        winLabel.setVisible(false);
        continueBtn.setVisible(false);
        continueBtn.setDisable(true);
        
        lpBet.setVisible(false);
        
        game.getPlayer().setTotal(0);
        currentNum.setText(game.getPlayer().getTotal() + "/21");
        game.getDeck().returnNumCardToDeck();
        game.getDeck().setCount(0);
    }
}