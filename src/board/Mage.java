package board;

import static java.util.stream.Collectors.toList;

import java.util.List;

public class Mage extends Player{
	private Integer spellpower;
	private Integer manapool;
	private Integer currentmana;
	private Integer cost;
	private Integer hitTimes;
	private Integer range;
	public Mage(Integer spellpower,Integer manapool,Integer cost,Integer hitTimes,Integer range,char tile, String name, Integer healthPool,Integer attackPoints,Integer defensePoints, Integer x_pos, Integer y_pos)
	{
		super( tile,  name,  healthPool, attackPoints, defensePoints,  x_pos,  y_pos);
		this.spellpower=spellpower;
		this.manapool=manapool;
		currentmana=manapool/4;
		this.cost=cost;
		this.hitTimes=hitTimes;
		this.range=range;
	}
	public void levelUpCostum()
	{
		manapool+=25*lvl;
		currentmana=Math.min(currentmana+(manapool/4), manapool);
	}
	public void specialAbility(Board board)
	{
		if(currentmana<cost)
		{
			System.out.println("You don't have enough mana points to use Blizzard");
		}
		else
		{
			currentmana-=cost;
			
			List<Enemy> enemies = board.getEnemies().stream().
					filter((nme) -> range(nme,this)<range).collect(toList());
			Integer hits=0;
			while(hits<hitTimes) 
			{
				RandomGenerator random = new RandomGen();
				Enemy nme = enemies.get(random.nextInt(enemies.size()));
				int damage = Math.max(spellpower-(random.nextInt(nme.defensePoints)),0);
				nme.currentHealth-=damage;
				hits++;
			}
		}
	}
	@Override
	public void onTick() {
		currentmana=Math.min(manapool, currentmana+1);
		
	}


	

}
