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
    int total = 0;
    Card[] numCards = new Card[100]; //num tee gep ma pen num jing jing bab +1 laew
    Card[] specialCards = new Card[100];
    int numCardCount = 0;
    int specialCardCount = 0;
    Boolean getSpecialBool = false;
    Boolean specialHandFull = false;
    Game game;
    Boolean emptyNumHand = true;
    Card returnCard;

    private final String[] cardEffect = {"returnMyLatestCard", "returnEnemyLatestCard", "enemyBetx2", "drawExactCard", "drawBestCardForEnemy", "drawBestCardForMe", "changeToClosestTo24"};

    public Player(Game game) {
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

    public int getLP() {
        return LP;
    }

    private void resortSpecialCard(int start) {
        int i;
        for (i = start; i < specialCardCount; i++) {
            int j;
            j = i + 1;
            while (specialCards[j] == null && j < specialCardCount) {
                j++;
            }
            if (specialCards[j] != null) {
                specialCards[i] = specialCards[j];
            }
            specialCards[j] = null;
        }
    }

    public void pushInHand(Card drawCard) {
        if (!drawCard.isSpecialCard()) {
            numCards[numCardCount++] = drawCard;
            emptyNumHand = false;
        } else {
            specialCards[specialCardCount++] = drawCard;

            this.getSpecialBool = true;
            if (specialCardCount == 5) {
                specialHandFull = true;
            }
        }
    }

    public Boolean isNumHandEmpty() {
        return emptyNumHand;
    }

    public Boolean isGetSpecial() {
        return getSpecialBool;
    }

    public void setSpecial(Boolean bool) {
        this.getSpecialBool = bool;
    }

    public void useSpecial(int specialCardIndex) {
        //System.out.println("at Index " + specialCardIndex + " used " + specialCards[specialCardIndex].getEffect());

        String temp = specialCards[specialCardIndex].getEffect();
        if (temp.equals(cardEffect[0]) && !this.isNumHandEmpty()) {
            this.setTotal(this.total - numCards[numCardCount - 1].getNum());
            returnCard = numCards[numCardCount - 1];
            game.getDeck().returnCard(returnCard);
            game.getDeck().count--;
            this.popCard(); //returnMyLatest
            specialCards[specialCardIndex] = null;
            //System.out.println("Current Hand : " + this.numCardCount);
        }else if(this.isNumHandEmpty()){
            System.out.println("No Card To Return");
        }
        if (temp.equals(cardEffect[1]) && !YIMSBean.game.getEnemy().isNumHandEmpty()) {
            YIMSBean.game.getEnemy().setTotal(YIMSBean.game.getEnemy().getTotal() - YIMSBean.game.getEnemy().getNumCard()[YIMSBean.game.getEnemy().getNumCardCount() - 1].getNum());
            game.getDeck().returnCard(YIMSBean.game.getEnemy().getNumCard()[YIMSBean.game.getEnemy().getNumCardCount() - 1]);
            game.getDeck().count--;
            YIMSBean.game.getEnemy().popCard();
            specialCards[specialCardIndex] = null;
        }else if(YIMSBean.game.getEnemy().isNumHandEmpty()){
            System.out.println("No Card To Return");
        }
        this.resortSpecialCard(specialCardIndex);
        if (specialCardCount > 0) {
            specialCardCount--;
        }
        //System.out.println("specialCardCount : " + specialCardCount);
    }

    public void popCard() {
        if (numCardCount > 0) {
            numCards[numCardCount - 1] = null;
            numCardCount--;
        }
        if (numCardCount == 0) {
            emptyNumHand = true;
        }
    }

    public void resetSpecialInHand() {
        for (int i = 0; i < specialCardCount; i++) {
            specialCards[i] = null;
        }
    }

    public int specialCardAmount() {
        return specialCardCount;
    }

    public Boolean isSpecialHandFull() {
        return specialHandFull;
    }

    public Card[] getSpecialCard() {
        return specialCards;
    }

    public Card[] getNumCard() {
        return numCards;
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
        returnCard = null;

    }
}
