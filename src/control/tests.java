package control;

import java.util.ArrayList;
import java.util.List;

import board.Action;
import board.Board;
import board.Enemy;
import board.Player;
import board.Warrior;

public class tests {
	public static void main(String [ ] args)
	{
		test2();
  	
	
	}
	
	
	
	private static void test1() 
	{
		char[][] cmap ={{'#','#','#','#','#','#','#','#','#','#'},
						{'#','Q','.','.','#','.','.','.','.','#'},
						{'#','.','#','#','#','B','.','.','.','#'},
						{'#','.','#','.','.','.','.','.','.','#'},
						{'#','s','#','.','.','.','.','.','.','#'},
						{'#','.','#','#','#','.','.','.','.','#'},
						{'#','.','#','@','#','q','.','.','.','#'},
						{'#','.','.','.','#','.','.','.','.','#'},
						{'#','.','#','#','#','.','.','.','.','#'},
						{'#','.','.','k','.','.','.','.','.','#'},
						{'#','#','#','#','#','#','#','#','#','#'}}; 

		List<Player> plrs = new ArrayList();
		plrs.add(new Warrior(3 ,'@', "gil",100000,7500,5,0,0));
		Board myboard = new Board(cmap,plrs);
		/*
		Monster m=(Monster) myboard.getEnemies().get(2);
		m.onTurn(myboard);
		System.out.println(myboard.toString());
		m.onTurn(myboard);
		System.out.println(myboard.toString());
		m.onTurn(myboard);
		System.out.println(myboard.toString());
		*/
		int turns =0;
		while(turns<4) 
		{
			for(Enemy nme : myboard.getEnemies()) {nme.onTurn(myboard);}
			System.out.println(myboard.toString());
			turns++;
		}
		
	}

	private static void test2() 
	{
		Action a = Action.valueOf(""+'S');
		
	}
	
}
