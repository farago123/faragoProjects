import java.util.*;

public class Select
{

	public static void getKLargest1(int[] a, int k, int[] kLargest)
	{
		int n = a.length;

		if(k == 0)
		{
			return;
		}

		if(k > n)
		{
			return;
		}

		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() 
		{
		    public int compare(Integer lhs, Integer rhs) 
		    {
		        if (lhs < rhs) return 1;
		        if (lhs.equals(rhs)) return 0;
		        return -1;
		    }
		}
		);

		for(int i = 0; i < n; i++)
		{
			pq.add(a[i]);
		}

		for(int j = 0; j < k; j++)
		{
			kLargest[j] = pq.poll();
		}

	}

	public static Integer[] getKLargest2(List<Integer> a, int k)
	{
		int n = a.size();
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		Iterator<Integer> it = a.iterator();
		Integer[] kLargest = new Integer[k];

		for(int i = 0; i < k; i++)
		{
		    if(it.hasNext())
		    {
		    	pq.add(it.next());
		    }		
		    else
		    {
		    	return pq.toArray(kLargest);
		    }
		}

		while(it.hasNext())
		{
			int x = it.next().intValue();

			if(x > pq.peek().intValue())
			{
				pq.poll();
				pq.add(x);
			}
		}

		return pq.toArray(kLargest);

	}


	public static int[] getKLargest3(int[] a, int k)
	{
		int n = a.length;

		if(k <= 0)
		{
			return new int[0];
		}

		if(k > n)
		{
			return a;
		}

		getKLargest3(a, 0, n, k);
		 
		return Arrays.copyOfRange(a, n-k, n);

	}

	public static void getKLargest3(int[] a, int p, int n, int k)
	{
		int r = p + n - 1;

		if(n < 20)
		{
			insertionSort(a, p, r);
		}
		else
		{
			int q = partition(a, p, r);
			int left = q-p;
			int right = r-q;

			if(right >= k)
			{
				getKLargest3(a, q+1, right, k);
			}
			else if(right+1 == k)
			{
				return;
			}
			else
			{
				getKLargest3(a, p, left, k-(right+1));
			}

		}
	}

	public static void insertionSort(int[] intArray, int left, int right) 
	{
	    int in, out;

	    for (out = left + 1; out <= right; out++) {
	      int temp = intArray[out];
	      in = out;

	      while (in > left && intArray[in - 1] >= temp) {
	        intArray[in] = intArray[in - 1];
	        --in;
	      }
	      intArray[in] = temp;
	    }
	}

    private static int partition(int array[], int first, int last) 
    {
  
	    int x = array[first];
	    
	    int i = first - 1;
	    
	    int j = last + 1;
	    
	    while (true) {
	    
	      do {
	        j--;
		
	      } while (array[j] > x);
	      
	      do  {
	        
		  	i++;
		
	      } while (array[i] < x);
	      
	      if ( i < j ) {
	      
	        int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
		
	      }
	      
	      else
	      
	        return j;
	    }
	}


	public static void main(String[] args)
	{
		int[] a = {1,22,345,5,23,678,45,3};
		int k = 4;
		// int[] kLargest = new int[k];
		// getKLargest1(a, k, kLargest);
		// System.out.println(Arrays.toString(kLargest));
		// ArrayList<Integer> arrayList = new ArrayList<Integer>(Arrays.asList(a));
		// Integer[] b = getKLargest2(arrayList, k);

		// System.out.println(Arrays.toString(b));

		System.out.println(Arrays.toString(getKLargest3(a, k)));

	}

}