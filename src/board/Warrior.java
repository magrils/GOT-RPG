package board;

public class Warrior extends Player {
	private Integer cooldown;
	private Integer remaining;
	public Warrior(Integer cooldown,char tile, String name, Integer healthPool,Integer attackPoints,Integer defensePoints, Integer x_pos, Integer y_pos)
	{
		super( tile,  name,  healthPool, attackPoints, defensePoints,  x_pos,  y_pos);
		this.cooldown=cooldown;
		remaining=0;
	}
	public void levelUpCostum()
	{
		remaining=0;
		healthPool=healthPool+(5*lvl);
		defensePoints=defensePoints+lvl;
	}
	public void specialAbility(Board board)
	{
		if(remaining>0)
		{}//System.out.println("You need to wait until cool down is over to use special ability");
		else
		{
			remaining=cooldown;
			currentHealth=Math.min(currentHealth+(2*defensePoints), healthPool);
		}
	}
	@Override
	public void onTick() {
		{
			remaining-=1;
		}
		
	}
}
