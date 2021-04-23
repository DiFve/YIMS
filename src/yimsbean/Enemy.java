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
public class Enemy {
    int LP = 5000;
    int total=0;
    Card[] numCards = new Card[100];
    Card[] specialCards = new Card[100];
    int numCardCount = 0;
    int specialCardCount = 0;
    Boolean getSpecialBool = false;
    Boolean specialHandFull = false;
    Boolean emptyNumHand = true;
    Game game;

    public void setEmptyNumHand(Boolean emptyNumHand) {
        this.emptyNumHand = emptyNumHand;
    }
    
    
    public Enemy(Game game){
        this.game = game;
    }
    public int getTotal() {
        return total;
    }
    public int getNumCardCount(){
        return numCardCount;
    }
    public void popCard(){
        if(numCardCount > 0){
            numCards[numCardCount-1]=null;
            numCardCount--;   
        }
        if(numCardCount == 0){
            emptyNumHand = true;
        }
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
            if(specialCardCount == 5){
                specialHandFull = true;
            }
        }
        emptyNumHand = false;
    }
    public Boolean isGetSpecial(){
        return getSpecialBool;
    }
    public void setSpecial(Boolean bool){
        this.getSpecialBool = bool;
    }
    public void useSpecial(int specialCardIndex){
        //System.out.println("at Index " + specialCardIndex + " used " + specialCards[specialCardIndex].getEffect());
        specialCardCount--;
    }
    public int specialCardAmount(){
        return specialCardCount;
    }
    
    public Boolean isSpecialHandFull(){
        return specialHandFull;
    }
    
    public Card[] getSpecialCard(){
        return specialCards;
    }
    
    public Card[] getNumCard(){
        return numCards;
    }
    public Boolean isNumHandEmpty(){
        return emptyNumHand;
    }
    
    public void reset() {
        total = 0;
        numCards = new Card[100]; //num tee gep ma pen num jing jing bab +1 laew
        specialCards = new Card[100];
        numCardCount = 0;
        specialCardCount = 0;
        getSpecialBool = false;
        specialHandFull = false;
        emptyNumHand = true;

    }
}
