package tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TestThirdBug {

	Player player;
	Game game;
	int bet;
	Dice dice1;
	Dice dice2;
	Dice dice3;
	List<DiceValue> cdv;

	@Before
	public void setUp() throws Exception {
		
		player = new Player("Tester", 100);
        bet = 5;
        dice1 = new Dice();
        dice2 = new Dice();
        dice3 = new Dice();
        game = new Game(dice1, dice2, dice3);
        cdv = game.getDiceValues();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGameOdd() throws Exception {
		
		int winCount = 0;
        int loseCount = 0;
        
		DiceValue pick = DiceValue.CROWN;
		
		while (player.balanceExceedsLimitBy(bet) && player.getBalance() < 200)
        {
			dice1 = new Dice();
            dice2 = new Dice();
            dice3 = new Dice();
            game = new Game(dice1, dice2, dice3);
            
        	System.out.printf("%s bet %d on %s\n",
        			 player.getName(), bet, pick); 
        	
        	int winnings = game.playRound(player, pick, bet);
            cdv = game.getDiceValues();
            
            System.out.printf("Rolled %s, %s, %s\n",
            		cdv.get(0), cdv.get(1), cdv.get(2));
            
            if (winnings > 0) {
                System.out.printf("%s won %d, balance now %d\n\n",
                		player.getName(), winnings, player.getBalance());
                winCount++; 
            }
            else {
                System.out.printf("%s lost, balance now %d\n\n",
                		player.getName(), player.getBalance());
                loseCount++;
            }
            
        }

        System.out.print(String.format("End Game %d: ", 1));
        System.out.println(String.format("%s now has balance %d\n", player.getName(), player.getBalance()));

	    float winRate = (float) winCount/(winCount+loseCount);
	    System.out.println(String.format("Win count = %d, Lose Count = %d, %.2f", winCount, loseCount, winRate));
	    assertFalse(winRate > 0.41); 
	    assertFalse(winRate < 0.43);

	    
	}

}
