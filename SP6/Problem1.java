import java.util.*;

public class Problem1
{

	public static ArrayList<ArrayList<Integer>> kFragments(int[] input, int k)
	{

		int n = input.length;

		if(k > n)
		{
			k = n;
		}

		ArrayList<ArrayList<Integer>> kFragments = new ArrayList<ArrayList<Integer>>();
		int fragmentSize = n/k;
		int currentIndex = 0;

		for(int i = 0; i < k - 1; i++)
		{
			ArrayList<Integer> fragment = new ArrayList<Integer>(); 

			for(int j = 0; j < fragmentSize; j++)
			{
				fragment.add(input[currentIndex]);
				currentIndex++; 
			}

			kFragments.add(fragment);
		}

		ArrayList<Integer> fragment = new ArrayList<Integer>(); 

		while(currentIndex < n)
		{
			fragment.add(input[currentIndex]);
			currentIndex++; 
		}

		kFragments.add(fragment);

		return kFragments;

	}

	public static int[] kMergeSort(int[] input, int k)
	{

		if(input.length == 1)
		{
			return input;
		}

		ArrayList<ArrayList<Integer>> oldkFragments = kFragments(input, k);
		ArrayList<ArrayList<Integer>> newkFragments = new ArrayList<ArrayList<Integer>>(); 

		for(ArrayList<Integer> fragment: oldkFragments)
		{
			int[] fragmentAsArray = fragment.stream().mapToInt(i -> i).toArray();
			int[] sortedFragment1 = kMergeSort(fragmentAsArray, k);
			ArrayList<Integer> sortedFragment2 = new ArrayList<Integer>();

			for (int i = 0; i < sortedFragment1.length; i++)
			{
				sortedFragment2.add(Integer.valueOf(sortedFragment1[i]));
			}
  
			newkFragments.add(sortedFragment2);
		}

		return kWayMerge(newkFragments);

	}

	public static int[] kWayMerge(ArrayList<ArrayList<Integer>> kSortedFragments)
	{

		ArrayList<Integer> result = new ArrayList<Integer>();
		PriorityQueue<ArrayList<Integer>> pq = new PriorityQueue<ArrayList<Integer>>(new Comparator<ArrayList<Integer>>() 
		{
		    public int compare(ArrayList<Integer> a1, ArrayList<Integer> a2) 
		    {
		        if (a1.get(0).intValue() < a2.get(0).intValue()) return -1;
		        if (a1.get(0).intValue() == a2.get(0).intValue()) return 0;
		        return 1;
		    }
		}
		);

		for(ArrayList<Integer> fragment: kSortedFragments) 
		{
			pq.add(fragment);
		}

		while(!pq.isEmpty())
		{
			ArrayList<Integer> minFragment = pq.poll();
			Integer firstElement = minFragment.remove(0);
			result.add(firstElement);

			if(!minFragment.isEmpty())
			{
				pq.add(minFragment);
			}

		}

		return result.stream().mapToInt(i -> i).toArray();
	}

	public static void main(String[] args)
	{

		int[] input = {456,34,33,22,11,10,6,4,1};
		System.out.println(Arrays.toString(kMergeSort(input, 7)));


	}

}