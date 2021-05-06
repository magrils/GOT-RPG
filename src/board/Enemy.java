package board;

import java.util.ArrayList;
import java.util.List;

public abstract class Enemy extends GameUnit {
	
	
	protected Integer xpValue;
	protected List<Player> attackers;
	
	
	public Enemy(char tile, String name, Integer healthPool,Integer attackPoints,Integer defensePoints, Integer x_pos, Integer y_pos, Integer xpValue){
		super(tile, name, healthPool, attackPoints,defensePoints, x_pos, y_pos);
		this.xpValue=xpValue;
		this.attackers= new ArrayList();
	}
	
	
	
	public abstract void onTurn(Board b);
	

	public void death(Board board)
	{
		// remove this enemy from the map and Enemy_list on Board 
		GamePiece[][]map=board.getMap();
		if(this.tile==map[pos.x][pos.y].tile)
			map[pos.x][pos.y]=new GamePiece('.');
		board.getEnemies().remove(this);
		
		//	allocate XP value among attackers
		int share = Math.round(xpValue/attackers.size());
		for (Player p : attackers) 
		{
			p.earnXP(share);
		}
	}
	
	@Override
	public boolean takeDamage(int dmg) 
	{
		if (dmg>0 & currentHealth>dmg) 
		{
			currentHealth-=dmg;
		}
		else if (dmg>currentHealth|currentHealth==0) 
		{
			currentHealth=0;
			return true; // dead
		}
		return false; // still alive
	}
	
	public boolean engagedBy(Enemy enemy) 
	{
		return false;
	}
	public boolean engagedBy(Player player) 
	{
		attackers.add(player);
		return true;
	}

}
