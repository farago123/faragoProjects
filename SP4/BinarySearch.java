public class BinarySearch
{

    public static<T extends Comparable<? super T>> int binarySearch(T[] arr, T x)
    {

    	int p = 0;
    	int r = arr.length-1;
    	int q = 0;
    	int cmp = 0;

    	while(p <= r) 
		{
		    q = (p+r) >>> 1;
	        cmp = arr[q].compareTo(x);
	        
	        if(cmp < 0) 
	        {
				p = q+1;
		    } 
		    else if (cmp == 0) 
		    {  
				return q;
		    } 
		    else 
		    {
				r = q-1;
		    }
		}
	
		if(cmp > 0)
		{
			return q-1;
		}
		else
		{
			return q;
		}
    }

    public static void main(String[] args)
    {
    	Integer[] ints = {5, 9, 10, 13, 16};
    	System.out.println(binarySearch(ints, new Integer(12)));
    }
}








