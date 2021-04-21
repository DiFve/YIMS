/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yimsbean;

/**
 *
 * @author jkbla,SroLyQ
 */
public class Player {
    int LP = 5000;
    int total=0;
    Card[] numCards = new Card[100]; //num tee gep ma pen num jing jing bab +1 laew
    Card[] specialCards = new Card[100];
    int numCardCount = 0;
    int specialCardCount = 0;
    Boolean getSpecialBool = false;
    Boolean specialHandFull = false;
    Game game;
    private final String[] cardEffect = {"returnMyLatestCard", "returnEnemyLatestCard", "enemyBetx2", "drawExactCard", "drawBestCardForEnemy", "drawBestCardForMe", "changeToClosestTo24"};
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
    private void resortSpecialCard(){
        int i=0;
        for(i=0;i<specialCardCount;i++){
            specialCards[i]=specialCards[i+1];
        }
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
    }
    public Boolean isGetSpecial(){
        return getSpecialBool;
    }
    public void setSpecial(Boolean bool){
        this.getSpecialBool = bool;
    }
    public void useSpecial(int specialCardIndex){
        System.out.println("at Index " + specialCardIndex + " used " + specialCards[specialCardIndex].getEffect());
        String temp = specialCards[specialCardIndex].getEffect();
        this.resortSpecialCard();
        if(temp.equals(cardEffect[0])){
            System.out.println("total : " +total);
            System.out.println("totalsss : " +(numCards[numCardCount-1].getNum()));
            this.setTotal(this.total - numCards[numCardCount-1].getNum());
            this.popCard(); //returnMyLatest
        }
        else if(temp.equals(cardEffect[1])){
            game.getEnemy().popCard();
        }
        if(specialCardCount > 0){
            specialCardCount--;
        }
    }
    public void popCard(){
        while(numCardCount > 0){
            numCards[numCardCount-1]=null;
            numCardCount--;   
        }
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
    
}
