package board;

import java.util.ArrayList;
import java.util.List;

public abstract class GameUnit extends GamePiece{
	protected String name;
	protected Integer healthPool;
	protected Integer currentHealth;
	protected Integer attackPoints;
	protected Integer defensePoints;
	protected Coordinates pos;
	public GameUnit(char tile, String name, Integer healthPool,Integer attackPoints, 
				Integer defensePoints, Integer x_pos, Integer y_pos)
	{
		super(tile);
		this.name= name;
		this.healthPool=healthPool;
		this.currentHealth=healthPool;
		this.attackPoints=attackPoints;
		this.defensePoints=defensePoints;
		this.pos=new Coordinates(x_pos,y_pos);
	}
	
	public Double range(Enemy m,Player p)
	{
		return Math.sqrt(Math.pow(m.pos.x-p.pos.x, 2)+Math.pow(m.pos.y-p.pos.y, 2));
	}
	public Double range(Coordinates posA,Coordinates posB)
	{
		return Math.sqrt(Math.pow(posA.x-posB.x, 2)+Math.pow(posA.y-posB.y, 2));
	}

	public void setPosition(int x_pos, int y_pos)
	{
		this.pos.x=x_pos;
		this.pos.y=y_pos;
	}

	protected List<Coordinates> getInRange(GamePiece[][] map, int rng)
	{
		List<Coordinates> coords = new ArrayList<Coordinates>();
		int minx , maxx , miny, maxy;
		minx=Math.max(pos.x-rng, 0);
		maxx=Math.min(pos.x+rng, map[0].length-1);
		miny=Math.max(pos.y-rng, 0);
		maxy=Math.min(pos.y+rng, map.length-1);
		
		for (int y=miny; y<=maxy ; y++) 
		{
			for (int x=minx;x<=maxx ; x++)
			{
				Coordinates curr = new Coordinates(x,y);
				if (range(pos,curr)<=rng) 
				{
					coords.add(curr);
				}
			}
		}
		return coords;
	} 
	
	public boolean moveUnit(Board board, int step)
	{
		GamePiece[][] map =board.getMap();
		if (step==1 && pos.y>0)
		{//Up
			if(map[pos.y-1][pos.x].passable)
			{
				map[pos.y][pos.x]= new GamePiece('.');
				map[pos.y-1][pos.x]=this;
				pos.y=pos.y-1;
				return true;
			}
			else if (map[pos.y-1][pos.x].engagedBy(this))
			{
				engage(board, (GameUnit)map[pos.y-1][pos.x]);
				return true;
			}
		}
		else if (step==2 && pos.x+1<map[0].length)
		{//Right
			if(map[pos.y][pos.x+1].passable)
			{
				map[pos.y][pos.x]= new GamePiece('.');
				map[pos.y][pos.x+1]=this;
				pos.x=pos.x+1;
				return true;
			}
			else if (map[pos.y][pos.x+1].engagedBy(this))
			{
				engage(board, (GameUnit)map[pos.y][pos.x+1]);
				return true;
			}
		}
		else if (step==3 && pos.y+1<map.length)
		{//Down
			if(map[pos.y+1][pos.x].passable)
			{
				map[pos.y][pos.x]= new GamePiece('.');
				map[pos.y+1][pos.x]=this;
				pos.y=pos.y+1;
				return true;
			}
			else if (map[pos.y+1][pos.x].engagedBy(this))
			{
				engage(board, (GameUnit)map[pos.y+1][pos.x]);
				return true;
			}
		}
		else if (step==4 && pos.x-1>0)
		{//Left
			if(map[pos.y][pos.x-1].passable)
			{
				map[pos.y][pos.x]= new GamePiece('.');
				map[pos.y][pos.x-1]=this;
				pos.x=pos.x-1;
				return true;
			}
			else if (map[pos.y][pos.x-1].engagedBy(this))
			{
				engage(board, (GameUnit)map[pos.y][pos.x-1]);
				return true;
			}
		}
		return false;
	}
	
	public void engage (Board board, GameUnit rival)
	{
		RandomGenerator random = new RandomGen();
		   int damage = Math.max(random.nextInt(this.attackPoints)-(random.nextInt(rival.defensePoints)),0);
		   if( rival.takeDamage(damage))
		   {
			   death(board);
		   }	
	}
	
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
	
	public abstract void death(Board board);
	public abstract boolean engagedBy(Enemy enemy);
	public abstract boolean engagedBy(Player player);
}
