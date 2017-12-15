import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Arrays;

public class PerfectPowers
{

	private static class Triplet implements Comparable<Triplet>
	{

		private int firstItem;
		private int secondItem;
		private int thirdItem;

		public Triplet(int firstItem, int secondItem, int thirdItem)
		{
			this.firstItem = firstItem;
			this.secondItem = secondItem;
			this.thirdItem = thirdItem;
		}

		public int compareTo(Triplet other)
		{
			if(this.thirdItem < other.thirdItem)
			{
				return -1;
			}
			else if(this.thirdItem > other.thirdItem)
			{
				return 1;
			}
			else
			{
				return 0;
			}
		}

		public int getFirstItem()
		{
			return firstItem;
		}

		public int getSecondItem()
		{
			return secondItem;
		}

		public int getThirdItem()
		{
			return thirdItem;
		}

	}

	public static void perfectPowers(int n, ArrayList<Integer> perfectPowers)
	{
		PriorityQueue<Triplet> pq = new PriorityQueue<Triplet>();

		if(n < 4)
		{
		   return;	
		}
		else
		{
			pq.add(new Triplet(2, 2, 4));

			while(true)
			{	
				Triplet leastTriplet = pq.poll();
				int a = leastTriplet.getFirstItem();
				int b = leastTriplet.getSecondItem();
				int aToTheB = leastTriplet.getThirdItem(); 

				if(aToTheB > n)
				{
					return;
				}
				else if(!perfectPowers.contains(aToTheB))
				{
					perfectPowers.add(aToTheB);
				}

				if(a == 2)
				{
					pq.add(new Triplet(2, b+1, (int)Math.pow(2, b+1)));
					pq.add(new Triplet(3, b, (int)Math.pow(3, b)));
				}
				else
				{
					pq.add(new Triplet(a+1, b, (int)Math.pow(a+1, b)));
				}

			}

		}

	}

	public static void main(String[] args)
	{
		ArrayList<Integer> perfectPowers = new ArrayList<Integer>();
		perfectPowers(30, perfectPowers);
		System.out.println(Arrays.toString(perfectPowers.toArray()));
	}
}

