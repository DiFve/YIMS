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
    private String specialCardEffect[] = {"returnMyLatestCard","returnEnemyLatestCard","enemyBetx2","drawExactCard","drawBestCardForEnemy","drawBestCardForMe","changeToClosestTo24"};
    Game game;
    public Deck(Game game) {
        this.game = game;
    }
    public int draw()
    {
        int rand;

        Random random = new Random();
        int specialCardChance = random.nextInt(2); // 1 in 5
        if(specialCardChance==0)
        {
            System.out.println("Got a Special");
            while(true){
                rand = random.nextInt(7);
                if(specialCard[rand] == 0){
                    continue;
                }else{
                    if(!game.getPlayer().isSpecialHandFull()){
                        game.getPlayer().pushInHand(new Card(specialCardEffect[rand]));
                        System.out.println("you got : "+specialCardEffect[rand]);
                        specialCard[rand]--;
                        break;    
                    }
                    else{
                        System.out.println("Hand Full :(");
                        break;
                    }
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
    
    public void returnNumCardToDeck()
    {
        for(int i=0;i<11;i++)
        {
            numCard[i] = 1;
        }
    }

    public static void setCount(int count) {
        Deck.count = count;
    }
}