/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yimsbean;

import java.util.Random;

public class Deck {
    Card card = new Card();
    static private int count=0;
    private int[] numCard = {1,1,1,1,1,1,1,1,1,1,1};
    private int[] specialCard = {2,2,2,2,2,2,2};
    Game game;
    public Deck(Game game) {
        this.game = game;
    }
    public int draw()
    {
        int rand;

        Random random = new Random();
        int specialCardChance = random.nextInt(12); // 1 in 12
        if(specialCardChance==0)
        {
            System.out.println("Got a Special");
            while(true){
                rand = random.nextInt(7);
                switch(rand){
                    case 0: 
                        game.getPlayer().pushInHand(new Card("returnMyLatestCard"));
                    case 1:
                        game.getPlayer().pushInHand(new Card("returnEnemyLatestCard"));
                    case 2:
                        game.getPlayer().pushInHand(new Card("enemyBetx2"));
                    case 3:
                        game.getPlayer().pushInHand(new Card("drawExactCard"));
                    case 4:
                        game.getPlayer().pushInHand(new Card("drawBestCardForEnemy"));
                    case 5:
                        game.getPlayer().pushInHand(new Card("drawBestCardForMe"));
                    case 6:
                        game.getPlayer().pushInHand(new Card("changeToClosestTo24"));
                }
            }
        }
        if(count<11)
        {
            System.out.println("Card");
            while(true)
            {
                rand = random.nextInt(11);
                if(numCard[rand]==0)
                    continue;
                else
                {
                    count++;
                    numCard[rand]--;
                    
                    break;
                }
            }
        }
        else
        {
            System.out.println("Out of Deck");
            return 0;
        }
        return rand+1;
    }
}

