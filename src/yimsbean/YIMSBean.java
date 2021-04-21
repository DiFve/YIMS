/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yimsbean;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author jkbla
 */

public class YIMSBean extends Application implements Runnable {
    
    long delay=0;
    Boolean running = false;
    public static Game game;
    private Thread gameThread;
    
    FXMLLoader loader = new FXMLLoader(getClass().getResource("InGameUI.fxml"));
    MainController maincontroller = loader.getController();
    
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        game = new Game();
        Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Hello JavaFX");
        stage.show();

        gameThread = new Thread(this);
        gameThread.start();
        running = true;
    }

    @Override
    public void run() {
        System.out.println("Game running");
        final int MAX_FRAMES_PER_SEC = 60;
        final int MAX_UPDATES_PER_SEC = 60;

        final double uOPTIMAL_TIME = 1000000000 / MAX_UPDATES_PER_SEC;
        final double fOPTIMAL_TIME = 1000000000 / MAX_FRAMES_PER_SEC;

        double uDeltaTime = 0, fDeltaTime = 0;
        int frames = 0, updates = 0;
        long startTime = System.nanoTime();
        long timer = System.currentTimeMillis();

        while (running) {
            long currentTime = System.nanoTime();
            uDeltaTime += (currentTime - startTime);
            fDeltaTime += (currentTime - startTime);
            startTime = currentTime;
            if (uDeltaTime >= uOPTIMAL_TIME) {
                update();
                //System.out.println(MainMenu.menuActive);
                updates++;
                uDeltaTime -= uOPTIMAL_TIME;
            }
            if (fDeltaTime >= fOPTIMAL_TIME) {
                //System.out.println("Draw"); //Draw function like window.draw in SFML
                frames++;
                fDeltaTime -= fOPTIMAL_TIME;
            }

            if (System.currentTimeMillis() - timer >= 1000) {
                //System.out.println("UPS: " + updates + ", FPS: " + frames); //don't know why but delete = boom
                System.out.print(""); //don't know why but delete = boom
                updates = 0;
                frames = 0;
                timer += 1000;
            }

        }
        stop();
    }
    
    public void update() {
        if(!MainMenu.menuActive)
        {
            
            if(System.currentTimeMillis()-delay>1000)
            {
                //delay = System.currentTimeMillis();
                delay = System.currentTimeMillis();
            }
            
        }
    }
    
    

    public void stop() {
        running = false;
        gameThread.interrupt();
    }

}
