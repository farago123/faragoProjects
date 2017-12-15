import java.util.PriorityQueue;

public class HuffmanCoding
{
	public static void main(String[] args)
	{
		PriorityQueue<CodingNode> q = new PriorityQueue<CodingNode>();

		q.add(new CodingNode('a', .45, null, null));
		q.add(new CodingNode('b', .13, null, null));
		q.add(new CodingNode('c', .12, null, null));
		q.add(new CodingNode('d', .16, null, null));
		q.add(new CodingNode('e', .09, null, null));
		q.add(new CodingNode('f', .05, null, null));

		while(q.size() > 1)
		{
			CodingNode x = q.remove();
			CodingNode y = q.remove();
			CodingNode z = new CodingNode('\0', x.getFrequency() + y.getFrequency(), x, y);
			q.add(z);
		}

		printCodes(q.remove(), "");

	}

	public static void printCodes(CodingNode root, String code)
	{
		if(root != null) 
		{
		  if(root.getLeftChild() == null && root.getRightChild() == null)
		  {
		  	 System.out.println(root.getCharacter() + ": " + code);
		  }	
		  else
		  {
		  	printCodes(root.getLeftChild(), code + 0);
		  	printCodes(root.getRightChild(), code + 1);
		  }
		}

	}
}