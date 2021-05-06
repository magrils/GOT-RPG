package control;

import java.util.ArrayList;
import java.util.List;

import board.Board;
import board.Player;

public class GameControl {

	//private Dispaly disp;
	private List<Player> players;
	private Board levelBoard;
	
	public GameControl() 
	{
		//this.disp = new Display();
		this.players=choosePlayers();
		this.levelBoard= readLevel();
	}
	
	private List<Player> choosePlayers()
	{
		List<Player> newPlayers = new ArrayList();
		
		// View -> ask for # of players
		// for while i<# : View -> choose new Player
		// 		add new Player to the list 
		return newPlayers;
	}
	private Board readLevel() 
	{
		// receive string/int to find level file
		
		// implement board read from file
		return new Board(new char[0][0],players);
	}
	
	private void playLevel()
	{
		int end=0;
		while(end==0) 
		{
			for (Player p: players) 
			{
				// View: (p)-> get action for p.name 
			}
			end=levelBoard.playTurn();
		}
		
		//for end = 1/-1
		// print result + start next level / end game
	}
}
