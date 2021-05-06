package board;

import java.util.ArrayList;
import java.util.List;

public class Board {
	private List<Enemy> enemies;
	private List<Player> players;//setting foundations for multiplayer
	private GamePiece[][] map;
	public Board (char[][] cMap,List<Player> players)
	{
		this.players=players;
		this.enemies= new ArrayList();
		this.map = new GamePiece[cMap.length][cMap[0].length];
		int plyr = 0;
		
		for(int i=0;i<cMap.length;i++)
		{
			for(int j=0;j<cMap[i].length;j++)
			{
				if (cMap[i][j]=='#') 
				{
					map[i][j]= new GamePiece('#');
				}
				else if (cMap[i][j]=='.') 
				{
					map[i][j]= new GamePiece('.');
				}
				else if (cMap[i][j]=='@') 
				{
					if (plyr < players.size()) 
					{
						Player plr =players.get(plyr);
						plr.setPosition(j,i);
						map[i][j] = plr;
						plyr++;
					}
				}
				else 
				{
					try 
					{
						Foe foe = Foe.valueOf(""+cMap[i][j]);
						Enemy bad = foe.createFoe(cMap[i][j], j, i);
						map[i][j]=bad;
						enemies.add(bad);
					}
					catch(IllegalArgumentException e)
					{
						map[i][j]= new GamePiece('.');
					}
				}
			}
		}
	}
	
	public List<Player> getPlayers()
	{
		return players;
	}
	public List<Enemy> getEnemies()
	{
		return enemies;
	}
	public GamePiece[][] getMap()
	{
		return map;
	}
	//toString is used for testing purposes
	public String toString()  
	{
		String s="";
		for(int i=0;i<map.length;i++)
		{
			s=s+System.getProperty("line.separator");
			for(int j=0;j<map[i].length;j++)
			{
				String c=""+map[i][j].tile;
				s=s+c;
			}
		}
		return s;
	}

	
	public int playTurn() 
	{
		int end = 0;
		
		// make each player/enemy play its turn
		int a=0;
		for(Player p : players) 
		{
			p.onTick();
			p.playTurn(this);
		}
		for (Enemy nme : enemies) 
		{
			nme.onTurn(this);
		}
		
		// after round is over check whether game or level is over
		if (players.size()==0) 
		{
			end = -1; //game-over
		}
		else if (enemies.size()==0) 
		{
			end = 1; //level won
		}
		
		return end;
	}
}
