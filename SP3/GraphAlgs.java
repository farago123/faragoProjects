import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;

public class GraphAlgs
{
	
	// class to store information about a vertex in the topological sort algorithm

	public class TopVertex 
	{
		int inDegree;
		Graph.Vertex vertex;


		TopVertex(int inDegree, Graph.Vertex vertex)
		{
			this.inDegree = inDegree;
			this.vertex = vertex;
		}

    }

    public class DfsVertex 
	{
		DfsVertex parent;
		Graph.Vertex vertex;
		int color;
		int componentNumber;

		DfsVertex(DfsVertex parent, Graph.Vertex vertex, int color)
		{
			this.parent = parent;
			this.vertex = vertex;
			this.color = color;
		}
		
    }

    TopVertex[] topVertices;		// parallel array is used to store information used in algorithms

	public List<Graph.Vertex> toplogicalOrder1(Graph g) 
	{

		Queue<TopVertex> q = new LinkedList<TopVertex>();
		List<Graph.Vertex> topOrderList = new LinkedList<Graph.Vertex>();   // topOrderList will store final ordering of vertices
		int currentInDegree, topNum = 0;
		topVertices = new TopVertex[g.size()];

		for(Graph.Vertex u: g) 			// compute indegree of each vertex and add it to the queue if its indegree is zero
    	{ 
    		currentInDegree = u.revAdj.size();
    		topVertices[u.name] = new TopVertex(currentInDegree, u);

    		if(currentInDegree == 0)
			{
				q.add(topVertices[u.name]);
			}
    	}

    	TopVertex currentTopVertex;
    	Graph.Vertex v;
    	TopVertex vTop;

    	while(!q.isEmpty())			// main loop of algorithm; repeatedly removes vertices from queue and adds them to topOrderList		
		{
			currentTopVertex = q.remove();
			topNum++;
			topOrderList.add(currentTopVertex.vertex);

			for(Graph.Edge outEdge: currentTopVertex.vertex) 			// update indegrees of adjacent vertices
    		{
    			v = outEdge.otherEnd(currentTopVertex.vertex);
    			vTop = topVertices[v.name];
    			vTop.inDegree--;

    			if(vTop.inDegree == 0)			// if adjacent vertex now has indegree zero, add it to queue
    			{
    				q.add(vTop);
    			}

    		}	 
		}

		if(topNum != g.size())		// if g is not a DAG, return null
		{
			return null;
		}
		else
		{
			return topOrderList;
		}	

	}

	public List<Graph.Vertex> topologicalOrder2(Graph g)
	{
		List<Graph.Vertex> decFinList = new LinkedList<Graph.Vertex>();
		boolean[] backEdge = new boolean[1];
		dfs(g, decFinList, backEdge);

		// if(backEdge[0])
		// {
		// 	return null;
		// }
		// else
		// {
		// 	return decFinList;
		// }

		return decFinList;
		
	}

	public void dfs(Graph g, List<Graph.Vertex> decFinList, boolean[] backEdge)
	{
		int topNum = g.size();
		DfsVertex[] dfsVertices = new DfsVertex[g.size()];

		for(Graph.Vertex u: g)
		{
			dfsVertices[u.name] = new DfsVertex(null, u, 0);
		}

		for(Graph.Vertex u: g)
		{
			if(dfsVertices[u.name].color == 0)
			{
				dfsVisit(u, decFinList, dfsVertices, backEdge);
			}
		}

	}

	public void dfsVisit(Graph.Vertex u, List<Graph.Vertex> decFinList, DfsVertex[] dfsVertices, boolean[] backEdge)
	{
		dfsVertices[u.name].color = 1;

		for(Graph.Edge outEdge: u)
		{
			Graph.Vertex v = outEdge.otherEnd(u);

			if(dfsVertices[v.name].color == 0)
			{
				dfsVertices[v.name].parent = dfsVertices[u.name];
				dfsVisit(v, decFinList, dfsVertices, backEdge);
			}
			else if(dfsVertices[v.name].color == 1)
				 {
				 	backEdge[0] = true;
				 }

		}

		dfsVertices[u.name].color = 2;
		decFinList.add(0, u);

	}

	public int dfsSCC(Graph g, List<Graph.Vertex> decFinList)
	{
		int topNum = g.size();
		DfsVertex[] dfsVertices = new DfsVertex[g.size()];

		for(Graph.Vertex u: decFinList)
		{
			dfsVertices[u.name] = new DfsVertex(null, u, 0);
		}

		// System.out.println("here");

		int componentNumber = 0;

		for(Graph.Vertex u: decFinList)
		{
			if(dfsVertices[u.name].color == 0)
			{
				componentNumber++;
				dfsVisitSCC(u, dfsVertices, componentNumber);
			}
		}

		return componentNumber;

	}

	public void dfsVisitSCC(Graph.Vertex u, DfsVertex[] dfsVertices, int componentNumber)
	{
		dfsVertices[u.name].color = 1;
		dfsVertices[u.name].componentNumber = componentNumber;

		for(Graph.Edge outEdge: u)
		{
			Graph.Vertex v = outEdge.otherEnd(u);

			if(dfsVertices[v.name].color == 0)
			{
				dfsVertices[v.name].parent = dfsVertices[u.name];
				dfsVisitSCC(v, dfsVertices, componentNumber);
			}

		}

		dfsVertices[u.name].color = 2;

	}

	public int stronglyConnectedComponents(Graph g)
	{
		List<Graph.Vertex> decFinList = topologicalOrder2(g);
		reverseGraph(g);
		return dfsSCC(g, decFinList);
	}

	public void reverseGraph(Graph g)
	{
		for(Graph.Vertex vertex: g)
		{
			List<Graph.Edge> tmp = vertex.revAdj;
			vertex.revAdj = vertex.adj;
			vertex.adj = tmp;  
		}
	}

	public boolean testEulerian(Graph g)
	{
		int numSCCs = stronglyConnectedComponents(g);

		if(numSCCs == 1)    // if the graph is strongly connected check that in and out degrees are equal at each vertex
		{
			for(Graph.Vertex u: g)
			{
				if(u.adj.size() != u.revAdj.size())
				{
					return false;
				}
			}

			return true;
		}
		else
		{
			return false;
		}
	}

	public static void main(String[] args)
	{
		Scanner in;
		in = new Scanner(System.in);
		Graph g = Graph.readGraph(in, true);
		GraphAlgs ga = new GraphAlgs();
		System.out.println(ga.testEulerian(g));

	    // List<Graph.Vertex> topOrderList = ga.toplogicalOrder2(g);

	 //    if(topOrderList != null)
	 //    {
		// 	System.out.println(Arrays.toString(topOrderList.toArray()));
		// }
		// else
		// {
		// 	System.out.println("Graph is not a dag");
		// }


	}
}