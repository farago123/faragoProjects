import java.util.List;
import java.util.LinkedList;

public class GraphAlgorithm
{
    Graph g;

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

    public GraphAlgorithm(Graph g) {
	this.g = g;
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

    public int stronglyConnectedComponents(Graph g)
	{
		List<Graph.Vertex> decFinList = topologicalOrder2(g);
		reverseGraph(g);
		int numComponents = dfsSCC(g, decFinList);
		reverseGraph(g);

		return numComponents;
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

    public boolean testEulerian()
	{
		int numSCCs = stronglyConnectedComponents(this.g);

		if(numSCCs == 1)    // if the graph is strongly connected check that in and out degrees are equal at each vertex
		{

			for(Graph.Vertex u: this.g)
			{
				if(u.adj.size() != u.revAdj.size())
				{
					System.out.println("Graph is not Eulerian");
    				System.out.println("Reason: " + "inDegree = " + u.revAdj.size() + ", outDegree = " +  u.adj.size() + " at Vertex " + u);
					return false;
				}
			}

			return true;
		}
		else
		{
			System.out.println("Graph is not Eulerian");
    		System.out.println("Reason: Graph is not strongly connected");
			return false;
		}
	}
}

