package tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestSecondBug {
	
	Player player;
	Game game;
	int bet;
	Dice dice1;
	Dice dice2;
	Dice dice3;
	List<DiceValue> cdv;

	@Before
	public void setUp() throws Exception {
		player = new Player("Tester", 10);
        bet = 5;
        dice1 = new Dice();
        dice2 = new Dice();
        dice3 = new Dice();
        game = new Game(dice1, dice2, dice3);
        cdv = game.getDiceValues();
	}

	@After
	public void tearDown() throws Exception {
		dice1 = null;
		dice2 = null;
		dice3 = null;
		player = null;
		game = null;
	}

	@Test
	public void endBalanceZero() throws Exception {
		DiceValue pick = DiceValue.CROWN;
		while (player.balanceExceedsLimitBy(bet) && player.getBalance() < 200)
        {
        	System.out.printf("%s bet %d on %s\n",
        			 player.getName(), bet, pick); 
        	
        	int winnings = game.playRound(player, pick, bet);
    	   
            cdv = game.getDiceValues();
            
            System.out.printf("Rolled %s, %s, %s\n",
            		cdv.get(0), cdv.get(1), cdv.get(2));
            
            if (winnings > 0) {
                System.out.printf("%s won %d, balance now %d\n\n",
                		player.getName(), winnings, player.getBalance());
            }
            else {
                System.out.printf("%s lost, balance now %d\n\n",
                		player.getName(), player.getBalance());
            }
            if (player.getBalance() > 200) {
    	    	tearDown();
    	    	setUp();
    	    	endBalanceZero();
    		}
        }

        System.out.print(String.format("End Game %d: ", 1));
        System.out.println(String.format("%s now has balance %d\n", player.getName(), player.getBalance()));

	    assertTrue(player.getBalance() == 0); 
	}
}

