import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Arrays;

public class PrimePowers
{

	private static class PrimesTriplet implements Comparable<PrimesTriplet>
	{

		private ArrayList<Integer> primesIndices;
		private ArrayList<Integer> powers;
		private int product;

		public PrimesTriplet(ArrayList<Integer> primesIndices, ArrayList<Integer> powers, int product)
		{
			this.primesIndices = primesIndices;
			this.powers = powers;
			this.product = product;
		}

		public int compareTo(PrimesTriplet other)
		{
			if(this.product < other.product)
			{
				return -1;
			}
			else if(this.product > other.product)
			{
				return 1;
			}
			else
			{
				return 0;
			}
		}

		public ArrayList<Integer> getPrimesIndices()
		{
			return primesIndices;
		}

		public ArrayList<Integer> getPowers()
		{
			return powers;
		}

		public int getProduct()
		{
			return product;
		}

	}

	// public static void perfectPowers(int n, ArrayList<Integer> perfectPowers)
	// {
	// 	PriorityQueue<Triplet> pq = new PriorityQueue<Triplet>();

	// 	if(n < 4)
	// 	{
	// 	   return;	
	// 	}
	// 	else
	// 	{
	// 		pq.add(new Triplet(2, 2, 4));

	// 		while(true)
	// 		{	
	// 			Triplet leastTriplet = pq.poll();
	// 			int a = leastTriplet.getFirstItem();
	// 			int b = leastTriplet.getSecondItem();
	// 			int aToTheB = leastTriplet.getThirdItem(); 

	// 			if(aToTheB > n)
	// 			{
	// 				return;
	// 			}
	// 			else if(!perfectPowers.contains(aToTheB))
	// 			{
	// 				perfectPowers.add(aToTheB);
	// 			}

	// 			if(a == 2)
	// 			{
	// 				pq.add(new Triplet(2, b+1, (int)Math.pow(2, b+1)));
	// 				pq.add(new Triplet(3, b, (int)Math.pow(3, b)));
	// 			}
	// 			else
	// 			{
	// 				pq.add(new Triplet(a+1, b, (int)Math.pow(a+1, b)));
	// 			}

	// 		}

	// 	}

	// }

	public static void primePowers(int n, ArrayList<Integer> perfectPowers, int[] primes)
	{
		PriorityQueue<PrimesTriplet> pq = new PriorityQueue<PrimesTriplet>();

		if(n < 2)
		{
		   return;	
		}
		else
		{
			ArrayList<Integer> indices = new ArrayList<Integer>();
			indices.add(0);
			ArrayList<Integer> powers = new ArrayList<Integer>();
			powers.add(1);
			pq.add(new PrimesTriplet(indices, powers, primes[0]));

			while(true)
			{
				PrimesTriplet leastTriplet = pq.remove();
				ArrayList<Integer> currentPrimesIndices = leastTriplet.getPrimesIndices();
				ArrayList<Integer> currentPowers = leastTriplet.getPowers();
				int product = leastTriplet.getProduct(); 

				if(product > n)
				{
					return;
				}
				else if(!perfectPowers.contains(product))
				{
					perfectPowers.add(product);
				}

				if(currentPrimesIndices.size() == 1 && currentPrimesIndices.get(0).intValue() == 0)
				{
					ArrayList<Integer> indices1 = new ArrayList<Integer>();
					indices1.add(0);
					ArrayList<Integer> powers1 = new ArrayList<Integer>();
					powers1.add(currentPowers.get(0).intValue() + 1);
					pq.add(new PrimesTriplet(indices1, powers1, primes[0]*product));

					ArrayList<Integer> indices2 = new ArrayList<Integer>();
					indices2.add(1);
					ArrayList<Integer> powers2 = new ArrayList<Integer>();
					powers2.add(currentPowers.get(0).intValue());
					pq.add(new PrimesTriplet(indices2, powers2, (int)Math.pow(primes[1], currentPowers.get(0).intValue())));

				}
				else
				{
					ArrayList<Integer> indices3 = new ArrayList<Integer>();

					for(Integer i: currentPrimesIndices)
					{
						indices3.add(i);
					}
					
					int nextIndex = currentPrimesIndices.get(currentPrimesIndices.size()-1)+1;
					indices3.add(nextIndex);

					ArrayList<Integer> powers3 = new ArrayList<Integer>();

					for(Integer j: currentPowers)
					{
						powers3.add(j);
					}

					powers3.add(1);

					if(nextIndex < primes.length)
					{
					   pq.add(new PrimesTriplet(indices3, powers3, primes[nextIndex]*product));
					}
					
				}
			}	

		}

	}


	public static void main(String[] args)
	{
		ArrayList<Integer> primePowers = new ArrayList<Integer>();
		int[] primes = {3,7};
		primePowers(30, primePowers, primes);
		System.out.println(Arrays.toString(primePowers.toArray()));
	}
}

