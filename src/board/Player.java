package board;


public abstract class Player extends GameUnit {
	protected Integer xp;
	protected Integer lvl;
	private Action action;
	public Player(char tile, String name, Integer healthPool,Integer attackPoints,Integer defensePoints, Integer x_pos, Integer y_pos){
		super(tile, name, healthPool, attackPoints,defensePoints, x_pos, y_pos);
		
		xp=0;
		lvl=1;
	}
	
	private void setAction(Action newAction)
	{
		action=newAction;
	}
	
	public void playTurn(Board board) 
	{
		if (action!=null) 
		{
			action.play(board, this);
		}
	}
	
	public void earnXP(int addedXp) 
	{
		this.xp += addedXp;
		while(xp>=50*this.lvl) 
		{
			levelUp();
		}
	}
	public void levelUp()
	{
		xp=xp-(50*lvl);
		lvl++;
		healthPool=healthPool+(10*lvl);
		currentHealth=healthPool;
		attackPoints=attackPoints+(5*lvl);
		defensePoints=defensePoints+(2*lvl);
		levelUpCostum();
	}
	public void death(Board board)
	{
		this.tile='X';
		GamePiece[][] map = board.getMap();
		map[pos.y][pos.x].tile=this.tile;
		board.getPlayers().remove(this);
		//needs to notify death to manager
		// when board runs 'PlayTurn' function check if Players list size > 0
		// to see if game is still on
	}
	
	
	
	//----visitor pattern engagment check---
	
	public boolean engagedBy(Enemy enemy) 
	{
		return true;
	}
	public boolean engagedBy(Player player) 
	{
		return false;
	}
	//----------------------------------
	
	
	public abstract void specialAbility(Board board);
	public abstract void levelUpCostum();
	public abstract void onTick();
	

}
