import java.util.Arrays;
import java.util.Random;

public class TestSort
{
	public static void main(String[] args)
	{
		int size = 16*31250;
		int[] a = new int[size];
		Random r = new Random();
		Integer[] b = new Integer[size];

		for(int i = 0; i < size; i++)
		{
			b[i] = new Integer(r.nextInt(1000) + 1);
		}

		Timer timer = new Timer();

		System.out.println(Arrays.toString(b));
		timer.start();
		Sort.mergeSort(b, Arrays.copyOf(b, b.length));
		timer.end();
		System.out.println(timer);

		for(int i = 0; i < size; i++)
		{
			a[i] = r.nextInt(1000) + 1;
		}

		int[] a2 = Arrays.copyOf(a, a.length);
		Arrays.sort(a2);

		System.out.println(Arrays.toString(a));

		Sort.mergeSort(a, Arrays.copyOf(a, a.length));

		System.out.println(Arrays.equals(a, a2));
		System.out.println(Arrays.toString(a));

	}
}