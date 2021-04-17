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
    private Boolean specialCard=false;
    private int cardNum = 99;
    private String specialEffectName="None";
    public Card(){
        
    }
    public Card(int cardNum){
        this.cardNum = cardNum;
    }
    public Card(String specialEffectName){
        this.specialEffectName = specialEffectName;
        this.specialCard = true;
    }
    public Boolean isSpecialCard(){
        return specialCard;
    }
    
    public String getEffect(){
        return specialEffectName;
    }
    
    
}