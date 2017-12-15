import java.util.Arrays;

public class MaxDifferencePair
{

	public static int[] maxDifferencePair1(int[] input)    
	{
		if(input == null)
		{
			return null;
		}

		if(input.length < 2)
		{
			return null;
		}

		int[] maxDifferencePair = new int[2];
		int maxDifference = input[1] - input[0];
		int currentDifference;

		for(int i = 0; i <= input.length-2; i++)
		{
			for(int j = i+1; j <= input.length-1; j++)
			{
				currentDifference = input[j] - input[i];

				if(currentDifference > maxDifference)
				{
				   maxDifference = currentDifference;
				   maxDifferencePair[0] = i;
				   maxDifferencePair[1] = j;	
				}
			}

		}

		if(maxDifferencePair[0] == 0 && maxDifferencePair[1] == 0)
		{
		   return null;	
		}
		else
		{
		   return maxDifferencePair;
		}
	}

	public static int[] maxDifferencePair2(int[] input)
	{
		if(input == null)
		{
			return null;
		}

		if(input.length < 2)
		{
			return null;
		}

		int min = input[0];
		int minIndex = 0;

		if(input[1] < min)
		{
			min = input[1];
			minIndex = 1;
		}

		int[] maxDifferencePair = new int[2];
		int maxDifference = input[1] - input[0];
		int currentMaxDifference;

		for(int i = 2; i <= input.length-1; i++)
		{
			currentMaxDifference = input[i] - min;

			if(currentMaxDifference > maxDifference)
			{
				maxDifference = currentMaxDifference;
				maxDifferencePair[0] = minIndex;
				maxDifferencePair[1] = i;
			}

			if(input[i] < min)
			{
				min = input[i];
				minIndex = i;
			}

		}

		if(maxDifferencePair[0] == 0 && maxDifferencePair[1] == 0)
		{
		   return null;	
		}
		else
		{
		   return maxDifferencePair;
		}

		
	}

	public static int secondMax(int[] input)
	{
		if(input == null)
		{
			return -1;
		}

		if(input.length < 2)
		{
		    return -1;	
		}

		int max = input[0];
		int secondMax = input[1];
		int currentElement;

		if(input[1] > input[0])
		{
		   max = input[1];
		   secondMax = input[0];	
		}

		for(int i = 2; i <= input.length-1; i++)
		{
			currentElement = input[i];

			if(currentElement > max)
			{
			   secondMax = max;
			   max = currentElement;
			}

			if(currentElement < max && currentElement > secondMax)
			{
			   secondMax = currentElement;
			}
		}

		return secondMax;

	}

	public static void threeInSortedOrder(int[] arr)
	{

		if(arr == null)
		{
			return;
		}

		if(arr.length < 3)
		{
			return;
		}

		int min = arr[0];
		int[] minPairSoFar = null;

		for(int i = 1; i <= arr.length-1; i++)
		{

			if(arr[i] > min && minPairSoFar == null)
			{
				minPairSoFar = new int[2];
				minPairSoFar[0] = min;
				minPairSoFar[1] = arr[i];
			}
			else if(arr[i] < min && minPairSoFar == null)
			{
			   min = arr[i];	
			}
			else if(minPairSoFar != null) 
			{
				 if(arr[i] > minPairSoFar[1])
				 {
				    System.out.println(minPairSoFar[0] + " " + minPairSoFar[1] + " " + arr[i]);
				    return;
				 }

				 if(arr[i] > minPairSoFar[0] && arr[i] < minPairSoFar[1])
				 {
				 	minPairSoFar[1] = arr[i];
				 }
				 else if(arr[i] < minPairSoFar[0])
				 {
				 	  if(arr[i] > min)
				 	  {
				 	  	 minPairSoFar[0] = min;
						 minPairSoFar[1] = arr[i];
				 	  }
				 	  else
				 	  {
				 	  	 min = arr[i];
				 	  }
				 }

			}


		}

		System.out.println("No sorted subsequence of length 3 found");


	} 

	public static void main(String[] args)
	{
		int[] input = {8,9,7,10,2,3,11};

		// System.out.println(Arrays.toString(maxDifferencePair1(input)));
		// System.out.println(Arrays.toString(maxDifferencePair2(input)));

		// System.out.println(secondMax(input));

		threeInSortedOrder(input);

	}
}