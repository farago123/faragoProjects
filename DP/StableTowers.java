import java.util.*;

public class StableTowers
{

	public static Set<ArrayList<Integer>> stableTowers(int m, int n, int k)
	{
		if(n <= 0 || k < 1 || m < 1)
		{
			return null;
		}

		HashMap<ArrayList<Integer>, Integer> currentStableTowers = new HashMap<ArrayList<Integer>, Integer>();

		for(int i = 1; i <= m; i++)
		{
		   	ArrayList<Integer> stableTower = new ArrayList<Integer>();
		   	stableTower.add(i);
		   	currentStableTowers.put(stableTower, 1);
		}

		for(int j = 2; j <= n; j++)
		{
		    Iterator<Map.Entry<ArrayList<Integer>, Integer>> it = currentStableTowers.entrySet().iterator();
		    HashMap<ArrayList<Integer>, Integer> newStableTowers = new HashMap<ArrayList<Integer>, Integer>();

		    while (it.hasNext()) 
		    {
		        Map.Entry<ArrayList<Integer>, Integer> pair = it.next();
		        ArrayList<Integer> currentStableTower = pair.getKey();
		        int x = currentStableTower.get(currentStableTower.size()-1);

		        for(int y = x; y <= m; y++)
				{
				    if(y == x)
				    {
				       int newCount = 1 + pair.getValue();
				       
				       if(newCount <= k)
				       {
				       	  ArrayList<Integer> stableTowerCopy = new ArrayList<Integer>();

				       	  for(Integer integer: currentStableTower)
				       	  {
				       	  	  stableTowerCopy.add(integer);
				       	  }

				       	  stableTowerCopy.add(y);	
				       	  newStableTowers.put(stableTowerCopy, newCount);
				       } 	
				    }
				    else
				    {
				    	ArrayList<Integer> stableTowerCopy = new ArrayList<Integer>();

			       	    for(Integer integer: currentStableTower)
			       	    {
			       	  	  stableTowerCopy.add(integer);
			       	    }

			       	    stableTowerCopy.add(y);	
			       	    newStableTowers.put(stableTowerCopy, 1);
				    }	
				}
		        
		    }

		    currentStableTowers = newStableTowers;   	
		}	

		return currentStableTowers.keySet();
		
	}

	public static void main(String[] args)
	{
		System.out.println(stableTowers(6, 8, 2));
	}
}