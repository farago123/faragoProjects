import java.util.Arrays;
import java.util.Scanner;
import java.util.LinkedList;

public class TestDiameter
{
	public static void main(String[] args)
	{
		Scanner in;
		in = new Scanner(System.in);
		Graph g = Graph.readGraph(in);

		LinkedList<Graph.Vertex> longestPath = Diameter.diameter(g);
		System.out.println(Arrays.toString(longestPath.toArray()));

	}
}