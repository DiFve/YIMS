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
    private Label currentNum;
    @FXML
    private AnchorPane specialCardPane;
    @FXML
    //private Button specialCardButton1,specialCardButton2,specialCardButton3,specialCardButton4,specialCardButton5;
    Game game = new Game();
    Boolean showSpecialPane = false;

    public void useSpecialBtnOnAction(ActionEvent event) {
        showSpecialPane = !showSpecialPane;
        if (showSpecialPane == false) {
            specialCardPane.setDisable(true);
            specialCardPane.setVisible(false);
        } else {
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
            game.getPlayer().setSpecial(false);
        }
        currentNum.setText(game.getPlayer().getTotal() + "/21");
        if (game.getPlayer().getTotal() > 21) {
            System.out.println("Lose");
        }
    }
}
