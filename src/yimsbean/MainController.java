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
    Deck deck = new Deck();
    Player player = new Player();
    @FXML
    private Label currentNum;
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
        player.setTotal(player.getTotal()+deck.draw());
        currentNum.setText(player.getTotal()+"/21");
        if(player.getTotal()>21)
        {
            System.out.println("Lose");
        }
    }
}
