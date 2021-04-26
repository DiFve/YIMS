/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sound;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author folnw
 */
public class soundController {

    MediaPlayer mediaPlayer;
    String clickSound = "click.mp3";
    public static double volume = 1.0;

    public static void setVolume(double volume) {
        soundController.volume = volume;
    }

    public void playClickSound() {
        //playHitsound(clickSound);
    }

    private void playHitsound(String fileName) {
        String path = getClass().getResource(fileName).getPath();
        Media media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(volume);
        mediaPlayer.play();
    }

    public void playBGsong() {
        String path = getClass().getResource("BGsong.mp3").getPath();
        Media media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(volume);
        mediaPlayer.play();
    }
}
