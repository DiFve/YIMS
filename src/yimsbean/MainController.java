/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yimsbean;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class MainController {
    @FXML
    private Label currentNum;
    @FXML
    private AnchorPane specialCardPane;
    Game game = new Game();
    Boolean showSpecialPane = false;
    public void useSpecialBtnOnAction(ActionEvent event)
    {
        System.out.println("Special");
        showSpecialPane = !showSpecialPane;
        if(showSpecialPane == false){  
            specialCardPane.setDisable(true);
            specialCardPane.setVisible(false);
        }
        else{
            specialCardPane.setDisable(false);
            specialCardPane.setVisible(true);    
        }
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
