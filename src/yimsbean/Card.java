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
    private int[] numCard = {1,1,1,1,1,1,1,1,1,1,1};
    private int[] specialCard = {2,2,2,2,2,2,2,2,2,2};

    public int[] getNumCard() {
        return numCard;
    }

    public void setNumCard(int num,int index) {
        this.numCard[index] = num;
    }

    public int[] getSpecialCard() {
        return specialCard;
    }

    public void setSpecialCard(int num,int index) {
        this.specialCard[index] = num;
    }
    
    
}
