import java.util.Arrays;

public class Main
{
	public static void main(String[] args)
	{
		SkipList<Integer> sl = new SkipList<Integer>();
		sl.add(2);
		sl.add(23);
		sl.add(453);
		sl.add(500);
		sl.add(600);
		sl.add(617);
		sl.add(655);
		
		// for(int i = 0; i < findList.length; i++)
		// {
		//    System.out.print(findList[i] + " ");
		// }

		System.out.println(sl.get(4));
		// sl.print();
	}
}