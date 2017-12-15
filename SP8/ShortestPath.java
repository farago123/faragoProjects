import java.util.*;

public class ShortestPath
{

	public class BfsVertex 
	{
		Graph.Vertex graphVertex;
		String color;
		int d;
		BfsVertex parent;
		int parity;

		BfsVertex(Graph.Vertex u) 
		{
		    graphVertex = u;
		    color = "white";
		    d = -1;
		    parent = null;
		}
    }

	Graph g; 
	Graph.Vertex s;
	BfsVertex[] bfsVertices;      // array of bfs vertices used in bfs method; allows us to get a BfsVertex

	public ShortestPath(Graph g, Graph.Vertex s)
	{
		this.g = g;
		this.s = s;
		bfsVertices = new BfsVertex[g.size()];

		for(Graph.Vertex u: g) 
    	{ 
    		bfsVertices[u.name] = new BfsVertex(u);
    	}
	}

	public Graph.Edge bfs()
	{
		BfsVertex source = bfsVertices[s.name];			// initialize maximum distance node to the root node
    	source.color = "gray";									// set root node's color to gray and distance to 0
    	source.d = 0;
    	source.parity = 0;

    	Queue<BfsVertex> queue = new LinkedList<BfsVertex>();           
    	queue.add(source);

    	while(!queue.isEmpty())											// standard bfs implementation based on algorithm in clrs textbook using a queue with minor modifications added
    	{																
    		BfsVertex u = queue.remove();								// BfsVertex objects are used to store information for bfs such as keeping track
    		Graph.Vertex correspondingVertex = u.graphVertex;			// of parent pointers, etc, without having to modify original Graph.Vertex objects			

    		for(Graph.Edge e: correspondingVertex)
    		{
    			BfsVertex v = bfsVertices[e.otherEnd(correspondingVertex).name];		

    			if(v.color.equals("white"))
    			{
    				v.parity = (-1*u.parity) + 1;
    				v.color = "grey";
    				v.d = u.d + 1;
    				v.parent = u;
    				queue.add(v);
    			}
    			else if(v.color.equals("gray") || v.color.equals("black"))
    			{
    				 if(u.parity == v.parity)
    				 {
    				 	return e;
    				 }
    			}

    		}

    		u.color = "black";
    	}

    	return null;

	}

	public List<Graph.Edge> findOddCycle()
	{
		Graph.Edge cycleEdge = bfs();

		if(cycleEdge == null)
		{
		  System.out.println("graph is bipartite");
		  return null;
		}
		else
		{

		  List<Graph.Edge> cycleEdges = new LinkedList<Graph.Edge>();
		  BfsVertex v1 = bfsVertices[cycleEdge.from.name];
		  BfsVertex v2 = bfsVertices[cycleEdge.to.name];

		  cycleEdges.add(cycleEdge);
		  BfsVertex previousVertex = v1;
		  BfsVertex currentVertex = previousVertex.parent;

		  while(currentVertex != null)
		  {
		  	cycleEdges.add(new Graph.Edge(previousVertex.graphVertex, currentVertex.graphVertex, 1));
		  	previousVertex = currentVertex;
		  	currentVertex = previousVertex.parent;
		  }

		  LinkedList<Graph.Edge> otherCycleEdges = new LinkedList<Graph.Edge>();
		  previousVertex = v2;
		  currentVertex = previousVertex.parent;

		  while(currentVertex != null)
		  {
		  	otherCycleEdges.addFirst(new Graph.Edge(previousVertex.graphVertex, currentVertex.graphVertex, 1));
		  	previousVertex = currentVertex;
		  	currentVertex = previousVertex.parent;
		  }

		  for(Graph.Edge e: otherCycleEdges)
		  {
		  	  cycleEdges.add(e);
		  }

		  Iterator<Graph.Edge> iterator = cycleEdges.iterator();
		  ArrayList<Graph.Edge> removedEdges = new ArrayList<Graph.Edge>();

		  while(iterator.hasNext())
		  {
		  	Graph.Edge e = iterator.next();

	  	    if(cycleEdges.indexOf(e) != cycleEdges.lastIndexOf(e))
	  	    {
	  	  	  iterator.remove();
	  	  	  removedEdges.add(e);
	  	    }

		  }

		  for(Graph.Edge e: removedEdges)
		  {
		  	 if(cycleEdges.contains(e))
		  	 {
		  	 	cycleEdges.remove(e);
		  	 }
		  }

		  return cycleEdges;

		}

	}

	public static void main(String[] args)
	{
		Scanner in;
		in = new Scanner(System.in);
		Graph g = Graph.readGraph(in, true);
		Graph.Vertex source = g.getVertex(1);
		ShortestPath sp = new ShortestPath(g, source);
		List<Graph.Edge> cycleEdges = sp.findOddCycle();

		if(cycleEdges != null)
		{
			for(Graph.Edge e: cycleEdges)
			{
			   System.out.println(e);
			}
		}
		
	}



}