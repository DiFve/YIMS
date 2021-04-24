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
public class Enemy {

    int LP = 5000;
    int total = 0;
    Card[] numCards = new Card[100]; //num tee gep ma pen num jing jing bab +1 laew
    Card[] specialCards = new Card[100];
    int numCardCount = 0;
    int specialCardCount = 0;
    Boolean getSpecialBool = false;
    Boolean specialHandFull = false;
    Boolean enough = false;
    Game game;
    Boolean emptyNumHand = true;
    Card returnCard;

    private final String[] cardEffect = {"returnMyLatestCard", "returnEnemyLatestCard", "enemyBetx2", "drawCardNo3", "drawBestCardForEnemy", "drawBestCardForMe", "changeToClosestTo24", "drawCardNo4", "drawCardNo5", "drawCardNo6"};

    public Enemy(Game game) {
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
            specialCards[i] = specialCards[i + 1];
        }
        specialCards[specialCardCount] = null;
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
            System.out.println("THIS METHOD WAS USEEEEEEEEEEEEEEE" + specialCardCount);
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
        Boolean specialCanUse = false;
        String temp = specialCards[specialCardIndex].getEffect();
        if (numCardCount == 0) {
            emptyNumHand = true;
        }
        if (temp.equals(cardEffect[0]) && !emptyNumHand) {
            this.setTotal(this.total - numCards[numCardCount - 1].getNum());
            returnCard = numCards[numCardCount - 1];
            game.getDeck().returnCard(returnCard);
            game.getDeck().count--;
            specialCanUse = true;
            this.popCard(); //returnMyLatest
            //System.out.println("Current Hand : " + this.numCardCount);
        } else if (this.isNumHandEmpty()) {
            System.out.println("No Card To Return");
        }
        if (temp.equals(cardEffect[1]) && !YIMSBean.game.getPlayer().isNumHandEmpty()) {
            YIMSBean.game.getPlayer().setTotal(YIMSBean.game.getPlayer().getTotal() - YIMSBean.game.getPlayer().getNumCard()[YIMSBean.game.getPlayer().numCardCount - 1].getNum());
            //System.out.println(YIMSBean.game.getPlayer().total + "fdfd");
            game.getDeck().returnCard(YIMSBean.game.getPlayer().getNumCard()[YIMSBean.game.getPlayer().numCardCount - 1]);
            game.getDeck().count--;
            YIMSBean.game.getPlayer().popCard();
            specialCanUse = true;
        } else if (YIMSBean.game.getPlayer().isNumHandEmpty()) {
            System.out.println("No Card To Return");
        }
        if (temp.equals(cardEffect[2])) {
            MainController.betPlayer *= 2;
            specialCanUse = true;
        }
        if (temp.equals(cardEffect[3])) {
            if (game.getDeck().getNumCard()[2] != 0) {
                YIMSBean.game.getDeck().drawExatCard(3, this);
                total += 3;

            } else {
                System.out.println("There're no Card Number 3 in the deck");
            }
            specialCanUse = true;
        }

        if (temp.equals(cardEffect[4])) {
            int bestCard;
            bestCard = MainController.currentMaximum - YIMSBean.game.getPlayer().total;
            if (bestCard > 11) {
                bestCard = 11;
            }
            for (int i = bestCard; i > 0; i--) {
                if (YIMSBean.game.getDeck().getNumCard()[i - 1] == 1) {
                    System.out.println("Vard best : " + i);
                    YIMSBean.game.getDeck().drawExatCard(i, YIMSBean.game.getPlayer());
                    YIMSBean.game.getPlayer().total += i;
                    break;
                }
            }
            specialCanUse = true;

        }
        if (temp.equals(cardEffect[5])) {
            int bestCard;
            bestCard = MainController.currentMaximum - total;
            if (bestCard > 11) {
                bestCard = 11;
            }
            for (int i = bestCard; i > 0; i--) {
                if (YIMSBean.game.getDeck().getNumCard()[i - 1] == 1) {
                    System.out.println("Vard best : " + i);
                    YIMSBean.game.getDeck().drawExatCard(i, this);
                    total += i;
                    break;
                }
            }
            specialCanUse = true;
        }
        if (temp.equals(cardEffect[6])) {
            MainController.currentMaximum = 24;
            specialCanUse = true;
        }
        if (temp.equals(cardEffect[7])) {
            if (game.getDeck().getNumCard()[3] != 0) {
                YIMSBean.game.getDeck().drawExatCard(4, this);
                total += 4;
            } else {
                System.out.println("There're no Card Number 4 in the deck");
            }
            specialCanUse = true;
        }
        if (temp.equals(cardEffect[8])) {
            if (game.getDeck().getNumCard()[4] != 0) {
                YIMSBean.game.getDeck().drawExatCard(5, this);
                total += 5;
            } else {
                System.out.println("There're no Card Number 5 in the deck");
            }
            specialCanUse = true;
        }
        if (temp.equals(cardEffect[9])) {
            if (game.getDeck().getNumCard()[5] != 0) {
                YIMSBean.game.getDeck().drawExatCard(6, this);
                total += 6;
            } else {
                System.out.println("There're no Card Number 6 in the deck");
            }
            specialCanUse = true;
        }
        if (specialCanUse) {
            if (specialCardCount > 0) {
                specialCardCount--;
            }
            game.getDeck().returnCard(specialCards[specialCardIndex]);
            specialCards[specialCardIndex] = null;
            this.resortSpecialCard(specialCardIndex);
            specialCanUse = false;
            specialHandFull = false;
        }
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
        numCardCount = 0;
        getSpecialBool = false;
        emptyNumHand = true;
        returnCard = null;
        enough = false;
    }
}
