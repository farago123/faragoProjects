import java.util.Arrays;

public class Sort
{

	public static void mergeSort(int[] arr, int[] tmp)
	{
		mergeSortHelper(arr, 0, arr.length - 1, tmp);
	}

	public static void mergeSortHelper(int[] arr, int p, int r, int[] tmp)
	{
		if(p < r)
		{
			int q = (int)Math.floor((p + r)/2.0);
			mergeSortHelper(arr, p, q, tmp);
			mergeSortHelper(arr, q+1, r, tmp);
			tmp = Arrays.copyOf(arr, arr.length);
			merge(arr, p, q, r, tmp);
		}
	}

	public static void merge(int[] arr, int p, int q, int r, int[] tmp)
	{

		int leftHalfIndex = p;								 // tmp is deep copy of arr used for merging
		int rightHalfIndex = q+1;
		int arrayIndex = p;

		while(leftHalfIndex <= q && rightHalfIndex <= r)     // merge two halves until at least one of the halves has been completely iterated
		{
			if(tmp[leftHalfIndex] <= tmp[rightHalfIndex])
			{
				arr[arrayIndex] = tmp[leftHalfIndex];
				leftHalfIndex++;
			}
			else
			{
				arr[arrayIndex] = tmp[rightHalfIndex];
				rightHalfIndex++;
			}

			arrayIndex++;
		}

		if(arrayIndex == r + 1)		// if both halves have been completely iterated then we are done
		{
			return;
		}
		else						// otherwise tag on elements of the unfinished half at the end of the array section
		{
			if(leftHalfIndex > q)
			{
				while(rightHalfIndex <= r)
				{
					arr[arrayIndex] = tmp[rightHalfIndex];
					rightHalfIndex++;
					arrayIndex++;
				}
			}
			else
			{
				while(leftHalfIndex <= q)
				{
					arr[arrayIndex] = tmp[leftHalfIndex];
					leftHalfIndex++;
					arrayIndex++;
				}

			}

		}

	}

	public static<T extends Comparable<? super T>> void mergeSort(T[] arr, T[] tmp)
	{
		mergeSortHelper(arr, 0, arr.length - 1, tmp);
	}

	public static<T extends Comparable<? super T>> void mergeSortHelper(T[] arr, int p, int r, T[] tmp)
	{
		if(p < r)
		{
			int q = (int)Math.floor((p + r)/2.0);
			mergeSortHelper(arr, p, q, tmp);
			mergeSortHelper(arr, q+1, r, tmp);
			tmp = Arrays.copyOf(arr, arr.length);
			merge(arr, p, q, r, tmp);
		}
	}

	public static<T extends Comparable<? super T>> void merge(T[] arr, int p, int q, int r, T[] tmp)
	{

		int leftHalfIndex = p;								 // tmp is deep copy of arr used for merging
		int rightHalfIndex = q+1;
		int arrayIndex = p;
		int compareToValue;

		while(leftHalfIndex <= q && rightHalfIndex <= r)     // merge two halves until at least one of the halves has been completely iterated
		{

			compareToValue = tmp[leftHalfIndex].compareTo(tmp[rightHalfIndex]);

			if(compareToValue <= 0)
			{
				arr[arrayIndex] = tmp[leftHalfIndex];
				leftHalfIndex++;
			}
			else
			{
				arr[arrayIndex] = tmp[rightHalfIndex];
				rightHalfIndex++;
			}

			arrayIndex++;
		}

		if(arrayIndex == r + 1)		// if both halves have been completely iterated then we are done
		{
			return;
		}
		else						// otherwise tag on elements of the unfinished half at the end of the array section
		{
			if(leftHalfIndex > q)
			{
				while(rightHalfIndex <= r)
				{
					arr[arrayIndex] = tmp[rightHalfIndex];
					rightHalfIndex++;
					arrayIndex++;
				}
			}
			else
			{
				while(leftHalfIndex <= q)
				{
					arr[arrayIndex] = tmp[leftHalfIndex];
					leftHalfIndex++;
					arrayIndex++;
				}

			}

		}

	}

	public static<T extends Comparable<? super T>> void nSquareSort(T[] arr)
	{

		T tmp;		 // tmp variable needed for swapping elements

		for(int i = 1; i < arr.length; i++)
		{
			for(int j = i; j > 0; j--)
			{
				if(arr[j-1].compareTo(arr[j]) == 1)           // if arr[j-1] is bigger than arr[j] then swap arr[j-1] with arr[j]
				{
					tmp = arr[j-1];
					arr[j-1] = arr[j];
					arr[j] = tmp;
				}
				else
				{
					break;             // otherwise break out of the inner for-loop
				}
			}

		}

	}


}
