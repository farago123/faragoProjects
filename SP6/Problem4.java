import java.util.*;

public class Problem4
{
	static class PrimVertex 
	{
		boolean seen;
		PrimVertex parent;
		Graph.Vertex vertex;
		
		PrimVertex(boolean seen, PrimVertex parent, Graph.Vertex vertex) 
		{
		    this.seen = seen;
		    this.parent = parent;
		    this.vertex = vertex;
		}
    }

	public static int prim1(Graph g, Graph.Vertex src)
	{
		PrimVertex[] primVertices = new PrimVertex[g.size()];

		for(Graph.Vertex v: g)
		{
			primVertices[v.name] = new PrimVertex(false, null, v);
		}

		primVertices[src.name].seen = true;
		int wmst = 0;
		PriorityQueue<Graph.Edge> q = new PriorityQueue<Graph.Edge>(new Comparator<Graph.Edge>() 
		{
		    public int compare(Graph.Edge e1, Graph.Edge e2) 
		    {
		        if (e1.weight < e2.weight) return -1;
		        if (e1.weight == e2.weight) return 0;
		        return 1;
		    }
		}
		);

		for(Graph.Edge e: src)
		{
		    q.add(e);	
		}

		while(!q.isEmpty())
		{
			Graph.Edge e = q.poll();
			PrimVertex a = primVertices[e.from.name];
			PrimVertex b = primVertices[e.to.name];
			PrimVertex u, v;

			if(a.seen && b.seen)
			{
				continue;
			}

			if(a.seen)
			{
				u = a;
				v = b;
			}
			else
			{
				u = b;
				v = a;
			}

			v.seen = true;
			v.parent = u;
			wmst += e.weight;

			for(Graph.Edge e2: v.vertex)
			{
				if(!primVertices[e2.otherEnd(v.vertex).name].seen)
				{
					q.add(e2);
				}
			}

		}

		return wmst;
	}

	public static void main(String[] args) 
	{
		Scanner in;
		in = new Scanner(System.in);
		Graph g = Graph.readGraph(in);
		Graph.Vertex src = g.getVertex(2);
		System.out.println(prim1(g, src));
	
    }
}