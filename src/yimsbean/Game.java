/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yimsbean;

/**
 *
 * @author user
 */
public class Game {
    Deck deck = new Deck(this);
    Player player = new Player(this);
    
    public Deck getDeck(){
        return deck;
    }
    
    public Player getPlayer(){
        return player;
    }
    
}
