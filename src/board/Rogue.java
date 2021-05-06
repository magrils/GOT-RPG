package board;

import static java.util.stream.Collectors.toList;

import java.util.List;

public class Rogue extends Player {
	private Integer cost;
	private Integer currentEnergy;
	public Rogue(Integer cost,char tile, String name, Integer healthPool,Integer attackPoints,Integer defensePoints, Integer x_pos, Integer y_pos)
	{
		super( tile,  name,  healthPool, attackPoints, defensePoints,  x_pos,  y_pos);
		this.cost=cost;
		currentEnergy=100;
	}
	@Override
	public void specialAbility(Board board) {
	   if(currentEnergy<cost)
	   { }//System.out.println("You don't enough energy left to use Fan Of Knives"
	   else
	   {
		   currentEnergy-=cost;
		   
		   List<Enemy> enemies = board.getEnemies().stream().
					filter((nme) -> range(nme,this)<2).collect(toList());
		   for (Enemy nme: enemies) 
		   {
			   RandomGenerator random = new RandomGen();
			   int damage = Math.max(this.attackPoints-(random.nextInt(nme.defensePoints)),0);
			   nme.currentHealth-=damage;
		   }
	   }
		
	}

	@Override
	public void levelUpCostum() {
		currentEnergy=100;
		attackPoints+=3*lvl;
		
	}
	@Override
	public void onTick() {
		currentEnergy=Math.min(currentEnergy+10, 100);
	}
	

}
