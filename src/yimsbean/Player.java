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
    private Card[] numCards = new Card[100];
    private Card[] specialCards = new Card[100];
    private int numCardCount = 0;
    private int specialCardCount = 0;
    private Boolean getSpecialBool = false;
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
        if(!drawCard.isSpecialCard()){
            numCards[numCardCount++] = drawCard;    
        }
        else{
            specialCards[specialCardCount++] = drawCard;   
            this.getSpecialBool = true;
        }
    }
    public Boolean isGetSpecial(){
        return getSpecialBool;
    }
    public void setSpecial(Boolean bool){
        this.getSpecialBool = bool;
    }
    public void useSpecial(int specialCardIndex){
        System.out.println("at Index " + specialCardIndex + " used " + specialCards[specialCardIndex].getEffect());
        specialCardCount--;
    }
    public int specialCardAmount(){
        return specialCardCount;
    }
}
