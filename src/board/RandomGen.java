package board;

import java.util.Random;
public class RandomGen implements RandomGenerator {

	 
	private boolean det;
	
	public void setDetermin(boolean det)
	{
		this.det=det;
	}
	
	@Override
	public int nextInt(int n) {
		if(!det) 
		{
			return new Random().nextInt(n+1);
		}
		else
		{
			return 0; ////////// read from source
		}
	}

}
