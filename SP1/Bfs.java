import java.util.Queue;
import java.util.LinkedList;

public class Bfs
{
    // class to store information about a vertex in the Bfs algorithm

	public class BfsVertex 
	{
		Graph.Vertex graphVertex;
		String color;
		int d;
		BfsVertex parent;

		BfsVertex(Graph.Vertex u) 
		{
		    graphVertex = u;
		    color = "white";
		    d = -1;
		    parent = null;
		}
    }

    Graph g;
    Graph.Vertex rootNode;
    BfsVertex[] bfsVertices;      // array of bfs vertices used in bfs method; allows us to get a BfsVertex

    public Bfs(Graph g, Graph.Vertex rootNode)
    {
    	this.g = g;
    	this.rootNode = rootNode;
    	bfsVertices = new BfsVertex[g.size()];

    	for(Graph.Vertex u: g) 
    	{ 
    		bfsVertices[u.name] = new BfsVertex(u);
    	}
    }

    // bfs method returns BfsVertex which is at a maximum distance from rootNode in the bfs tree 

    public Graph.Vertex bfs()
    {

    	BfsVertex maxDistVertex = bfsVertices[rootNode.name];			// initialize maximum distance node to the root node
    	maxDistVertex.color = "gray";									// set root node's color to gray and distance to 0
    	maxDistVertex.d = 0;

    	Queue<BfsVertex> queue = new LinkedList<BfsVertex>();           
    	queue.add(maxDistVertex);

    	while(!queue.isEmpty())											// standard bfs implementation based on algorithm in clrs textbook using a queue with minor modifications added
    	{																
    		BfsVertex u = queue.remove();								// BfsVertex objects are used to store information for bfs such as keeping track
    		Graph.Vertex correspondingVertex = u.graphVertex;			// of parent pointers, etc, without having to modify original Graph.Vertex objects			

    		for(Graph.Edge e: correspondingVertex)
    		{
    			BfsVertex v = bfsVertices[e.otherEnd(correspondingVertex).name];		

    			if(v.color.equals("white"))
    			{
    				v.color = "grey";
    				v.d = u.d + 1;

    				if(v.d > maxDistVertex.d)			// here we keep track of the farthest node from the root so far
    				{
    					maxDistVertex = v;
    				}

    				v.parent = u;
    				queue.add(v);
    			}
    		}

    		u.color = "black";
    	}

    	return maxDistVertex.graphVertex;				// return farthest node from the root as a Graph.Vertex object

    }

    // return path from rootNode to given vertex in the bfs tree

    public LinkedList<Graph.Vertex> getPathFromRoot(Graph.Vertex vertex)
    {
    	BfsVertex currentBfsVertex = bfsVertices[vertex.name];
    	LinkedList<Graph.Vertex> path = new LinkedList<Graph.Vertex>();

    	while(currentBfsVertex != null)			  // trace parent pointers backwards from given vertex, adding the current vertex to the path at each point
    	{
	       path.addFirst(currentBfsVertex.graphVertex);
	       currentBfsVertex = currentBfsVertex.parent;
	    }

	    return path;

    } 



}