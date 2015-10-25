package tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestsFirstBug {

	Dice dice1;
	Dice dice2;
	Dice dice3;
	Player player;
	int bet;
	Game game;
	List<DiceValue> cdv;

	@Before
	public void setUp() throws Exception {
        dice1 = mock(Dice.class);
        dice2 = mock(Dice.class);
        dice3 = mock(Dice.class);

        player = new Player("Tester", 10);
        bet = 5;
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
		cdv = null;
	}

	@Test
	public void balanceIncrease() {
		DiceValue pick = DiceValue.CROWN;
		when(dice1.getValue()).thenReturn(DiceValue.CROWN);
		when(dice2.getValue()).thenReturn(DiceValue.HEART);
		when(dice3.getValue()).thenReturn(DiceValue.ANCHOR);
		
		System.out.println("Current balance: " + player.getBalance());
		
		assertTrue(player.getBalance() == 10);
		int winnings = game.playRound(player, pick, bet);
		
		assertTrue(winnings == 10);
		assertTrue(player.getBalance() == 20);
		
		cdv = game.getDiceValues();
        System.out.printf("Rolled %s, %s, %s\n",
        		cdv.get(0), cdv.get(1), cdv.get(2));
         
         
        System.out.printf("%s won %d, balance now %d\n\n",
        player.getName(), winnings, player.getBalance());
         
	}
	
	

}
