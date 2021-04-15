package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {
    Deck deck = new Deck();
    int total=0;
    @FXML
    private Label currentNum;
    public void useSpecialBtnOnAction(ActionEvent event)
    {
        System.out.println("Special");
    }

    public void keepCurrentBtnOnAction(ActionEvent event)
    {
        System.out.println("Keep");
    }

    public void drawCardBtnOnAction(ActionEvent event)
    {
        total+=deck.draw();
        currentNum.setText(total+"/21");
    }
}
