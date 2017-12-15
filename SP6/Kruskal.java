import java.util.*;

public class Kruskal
{

	public static List<Graph.Edge> kruskal(Graph g)
	{
		int numVertices = g.size();
		DisjointSet.Node[] nodes = new DisjointSet.Node[g.size()];
		DisjointSet ds = new DisjointSet(g, nodes);
		List<Graph.Edge> mst = new LinkedList<Graph.Edge>();
		PriorityQueue<Graph.Edge> remainingEdges = new PriorityQueue<Graph.Edge>(new Comparator<Graph.Edge>(){
			public int compare(Graph.Edge one, Graph.Edge two) 
			{
				if(one.weight > two.weight)
				{
					return 1;
				}
				else if(two.weight > one.weight)
				{
					return -1;
				}
				else
				{
					return 0;
				}
			}
		});

		for(Graph.Vertex v: g)
		{
			for(Graph.Edge e: v)
			{
				if(!remainingEdges.contains(e))
				{
					remainingEdges.add(e);
				}
			}
		}

		while(mst.size() != numVertices - 1)
		{
			Graph.Edge minEdge = remainingEdges.poll();
			DisjointSet.Node u = nodes[minEdge.from.name];
			DisjointSet.Node v = nodes[minEdge.to.name];
			DisjointSet.Node ru = ds.find(u);
			DisjointSet.Node rv = ds.find(v);

			if(ru != rv)
			{
			   mst.add(minEdge);
			   ds.union(ru, rv); 	
			}
		}

		return mst;
	}

	public static void main(String[] args)
	{
		Scanner in;
		in = new Scanner(System.in);
		Graph g = Graph.readGraph(in, false);
		List<Graph.Edge> mst = kruskal(g);
		int mstWeight = 0;

		for(Graph.Edge e: mst)
		{
			mstWeight += e.weight;
		}

		System.out.println(mstWeight);

		for(Graph.Edge e: mst)
		{
			System.out.println(e);
		}


	}
}