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
public class Card {
    private int[] numberCard = {1,1,1,1,1,1,1,1,1,1,1};
    private int[] specialCard = {2,2,2,2,2,2,2,2,2,2};
    
    
    public void setNumberCard(int numberCard,int index) {
        this.numberCard[index] = numberCard;
    }

    public void setSpecialCard(int[] specialCard) {
        this.specialCard = specialCard;
    }
    

    public int getNumberCard(int index) {
        return numberCard[index];
    }

    public int getSpecialCard(int index) {
        return specialCard[index];
    }
    
    
    
}
