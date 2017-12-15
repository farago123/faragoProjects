import java.util.LinkedList;
import java.util.Random;

public class Diameter
{

	// Return a longest path in g.  Algorithm is correct only if g is a tree.

	public static LinkedList<Graph.Vertex> diameter(Graph g) 
	{
		Random randomGenerator = new Random();
		Graph.Vertex rootNode = g.getVertex(randomGenerator.nextInt(g.size()));	      // choose a root node arbitrarily
		
		Bfs bfsObject1 = new Bfs(g, rootNode);                  // initialize and run first bfs from root node to get farthest vertex u1 from root node
		Graph.Vertex u1 = bfsObject1.bfs();

		Bfs bfsObject2 = new Bfs(g, u1);						// initialize and run second bfs from u1 to get farthest vertex u2 from u1
		Graph.Vertex u2 = bfsObject2.bfs();

		return bfsObject2.getPathFromRoot(u2);					// return path from u1 to u2 (a longest path in g)

	}

}