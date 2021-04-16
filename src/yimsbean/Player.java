/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yimsbean;

/**
 *
 * @author jkbla
 */
public class Player {
    private int LP = 5000;
    private int total=0;
    private Card[] handCards = {};
    private int handCardCount = 0;
    Game game;
    public Player(Game game){
        this.game = game;
    }
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    
    
    public void setLP(int LP) {
        this.LP = LP;
    }

    public int getLP() 
    {
        return LP;
    }
    
    public void pushInHand(Card drawCard){
        handCards[handCardCount++] = drawCard;
    }
    
    public Card useInHand(int indexInHand){
        return handCards[indexInHand];
    }
    
}
