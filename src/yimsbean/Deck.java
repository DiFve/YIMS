/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yimsbean;

import java.util.Random;

public class Deck {

    Card card = new Card();
    public static int count = 0;
    private int[] numCard = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    private int[] specialCard = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
    private String specialCardEffect[] = {"returnMyLatestCard", "returnEnemyLatestCard", "enemyBetx2", "drawCardNo3", "drawBestCardForEnemy", "drawBestCardForMe", "changeToClosestTo24", "drawCardNo4", "drawCardNo5", "drawCardNo6"};
    Game game;

    public Deck(Game game) {
        this.game = game;
    }
    public int latestDraw, latestEnemyDraw;

    public int[] getNumCard() {
        return numCard;
    }

    public void setNumCard(int[] numCard) {
        this.numCard = numCard;
    }

    
    public int playerDraw() {
        int rand = 0;

        Random random = new Random();
        int specialCardChance = random.nextInt(3); // 1 in 3
        if (specialCardChance == 0) {
            //System.out.println("Got a Special");
            while (true) {
                rand = random.nextInt(10);
                if (specialCard[0] == 0 && specialCard[1] == 0 && specialCard[2] == 0 && specialCard[3] == 0 && specialCard[4] == 0 && specialCard[5] == 0 && specialCard[6] == 0 && specialCard[7] == 0 && specialCard[8] == 0 && specialCard[9] == 0) {
                    System.out.println("Out of Special");
                    break;
                }
                if (specialCard[rand] == 0) {
                    continue;
                } else {
                    if (!game.getPlayer().isSpecialHandFull()) {
                        game.getPlayer().pushInHand(new Card(specialCardEffect[rand]));
                        System.out.println("you got : " + specialCardEffect[rand]);
                        specialCard[rand]--;
                        break;
                    } else {
                        //System.out.println("Hand Full :(");
                        break;
                    }
                }
            }
        }
        if (count < 11) {

            while (true) {
                rand = random.nextInt(11);
                if (numCard[rand] == 0) {
                } else {
                    count++;
                    game.getPlayer().pushInHand(new Card(rand + 1));
                    System.out.println("Player Draw : " + (rand + 1));
                    numCard[rand]--;
                    break;
                }
            }
        } else {
            System.out.println("Out of Deck");
            return 0;
        }
        latestDraw = rand + 1;
        return rand + 1;
    }

    public int enemyDraw() {
        int rand = 0;

        Random random = new Random();
        int specialCardChance = random.nextInt(3); // 1 in 3
        if (specialCardChance == 0) {
            //System.out.println("Got a Special");
            while (true) {
                rand = random.nextInt(10);
                if (specialCard[0] == 0 && specialCard[1] == 0 && specialCard[2] == 0 && specialCard[3] == 0 && specialCard[4] == 0 && specialCard[5] == 0 && specialCard[6] == 0 && specialCard[7] == 0 && specialCard[8] == 0 && specialCard[9] == 0) {
                    System.out.println("Out of Special");
                    break;
                }
                if (specialCard[rand] == 0) {
                    continue;
                } else {
                    if (!game.getEnemy().isSpecialHandFull()) {
                        System.out.println("sfdsjfodlsifjsilfdlksfkljds");
                        game.getEnemy().pushInHand(new Card(specialCardEffect[rand]));
                        System.out.println("you got : " + specialCardEffect[rand]);
                        specialCard[rand]--;
                        break;
                    } else {
                        //System.out.println("Hand Full :(");
                        break;
                    }
                }
            }
        }
        if (count < 11) {

            while (true) {
                rand = random.nextInt(11);
                if (numCard[rand] == 0) {
                } else {
                    count++;
                    game.getEnemy().pushInHand(new Card(rand + 1));
                    System.out.println("Player Draw : " + (rand + 1));
                    numCard[rand]--;
                    break;
                }
            }
        } else {
            System.out.println("Out of Deck");
            return 0;
        }
        latestDraw = rand + 1;
        return rand + 1;
    }

    public void returnCard(Card card) {
        System.out.println("Special index 0 left before: " + specialCard[0]);
        if (!card.isSpecialCard()) {
            numCard[card.getNum() - 1] = 1;
        } else {
            String temp = card.getEffect();
            if (temp.equals("returnMyLatestCard")) {
                specialCard[0]++;
            } else if (temp.equals("returnEnemyLatestCard")) {
                specialCard[1]++;
            }
        }
        System.out.println("Special index 0 left after: " + specialCard[0]);
    }

    public static void setCount(int count) {
        Deck.count = count;
    }

    public int[] debug() {
        return numCard;
    }

    public void reset() {
        count = 0;
        numCard = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    }
    
    public void drawExatCard(int num,Object obj) //use num yang mai -1 na yang mai index
    {
        latestDraw = num;
        count++;
        if(obj instanceof Player)
        {
            game.getPlayer().pushInHand(new Card(num));
        }
        else if(obj instanceof Enemy)
        {
            game.getEnemy().pushInHand(new Card(num));
        }
        numCard[num-1] = 0;
    }
    
    public void setNumCard(int index,int value){
        numCard[index]=value;
    }
}
