/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yimsbean;

import java.util.Random;

public class Deck {
    static private int count=0;
    Card card = new Card();
    public Deck() {

    }
    public int draw()
    {
        int rand;
        Random random = new Random();
        int specialCardChance = random.nextInt(12); // 1 in 12
        if(specialCardChance==0)
        {
            System.out.println("Got a Special");
        }
        if(count<11)
        {
            System.out.println("Card");
            while(true)
            {
                rand = random.nextInt(11);
                if(card.getNumberCard(rand)==0)
                        continue;
                else
                {
                    count++;
                    card.setNumberCard(0,rand);
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

