/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yimsbean;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 *
 * @author user
 */
public class Game {
    private Deck deck;
    private Player player;
    private Enemy enemy;
    public Game()
    {
        deck = new Deck(this);
        player = new Player(this);
        enemy = new Enemy(this); 
    }
    
    
    public Deck getDeck() {
        return deck;
    }

    public Player getPlayer() {
        return player;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    
    
    
}