/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yimsbean;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {
    @FXML
    private Label currentNum;
    Game game = new Game();
    public void useSpecialBtnOnAction(ActionEvent event)
    {
        System.out.println("Special");
    }

    public void keepCurrentBtnOnAction(ActionEvent event)
    {
        System.out.println("Keep");
        System.out.println("Print");
    }

    public void drawCardBtnOnAction(ActionEvent event)
    {
        game.getPlayer().setTotal(game.getPlayer().getTotal()+game.getDeck().draw());
        currentNum.setText(game.getPlayer().getTotal()+"/21");
        if(game.getPlayer().getTotal()>21)
        {
            System.out.println("Lose");
        }
    }
}
