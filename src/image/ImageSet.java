/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author folnw
 */
public class ImageSet {

    public static Image getCard(int number) {
        return card[number];
    }

    public static void setCard(Image[] card) {
        ImageSet.card = card;
    }

    public static Image[] card = new Image[]{new Image("image/0.png"), new Image("image/1.png"), new Image("image/2.png"),
        new Image("image/3.png"), new Image("image/4.png"), new Image("image/5.png"), new Image("image/6.png"),
        new Image("image/7.png"), new Image("image/8.png"), new Image("image/9.png"), new Image("image/10.png"),
        new Image("image/11.png")};

}
