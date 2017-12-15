
// Starter code for LP4
// Do not rename this file or move it away from cs6301/g??

// change following line to your group number

import java.util.Queue;
import java.util.List;
import java.util.HashMap;
import java.util.LinkedList;

public class LP4 
{
    Graph g;
    Graph.Vertex s;
    TopVertex[] topVertices;

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

    // common constructor for all parts of LP4: g is graph, s is source vertex
    public LP4(Graph g, Graph.Vertex s) 
    {
	  this.g = g;
	  this.s = s;
    }

    // Part a. Return number of topological orders of g
    public long countTopologicalOrders() 
    {
        Queue<TopVertex> q = new LinkedList<TopVertex>();
        List<Graph.Vertex> topOrderList = new LinkedList<Graph.Vertex>();
        topVertices = new TopVertex[g.size()];
        int currentInDegree;
        int topNum = 0;

        for(Graph.Vertex u: g)          // compute indegree of each vertex and add it to the queue if its indegree is zero
        { 
            currentInDegree = u.revAdj.size();
            topVertices[u.name] = new TopVertex(currentInDegree, u);

            if(currentInDegree == 0)
            {
                q.add(topVertices[u.name]);
            }
        }

        return numTopOrders(g, q, topOrderList, topNum);
    }

    public int numTopOrders(Graph g, Queue<TopVertex> q, List<Graph.Vertex> topOrderList, int topNum) 
    {
        TopVertex currentTopVertex;
        Graph.Vertex v;
        TopVertex vTop;
        int numTopOrders = 0;
        int queueSize = q.size();
        int i = 0;

        while(i < queueSize)         // main loop of algorithm; repeatedly removes vertices from queue and adds them to topOrderList     
        {
            currentTopVertex = q.remove();
            topOrderList.add(currentTopVertex.vertex);

            for(Graph.Edge outEdge: currentTopVertex.vertex)            // update indegrees of adjacent vertices
            {
                v = outEdge.otherEnd(currentTopVertex.vertex);
                vTop = topVertices[v.name];
                vTop.inDegree--;

                if(vTop.inDegree == 0)          // if adjacent vertex now has indegree zero, add it to queue
                {
                    q.add(vTop);
                }

            }

            numTopOrders += numTopOrders(g, q, topOrderList, topNum+1);
            q.add(currentTopVertex);

            for(Graph.Edge outEdge: currentTopVertex.vertex)            // reset indegrees of adjacent vertices
            {
                v = outEdge.otherEnd(currentTopVertex.vertex);
                vTop = topVertices[v.name];
                vTop.inDegree--;

                if(vTop.inDegree == 0)          // if adjacent vertex now has indegree zero, add it to queue
                {
                    q.add(vTop);
                }

            }




            i++;

        }

        if(topNum != g.size())      // if g is not a DAG, return null
        {
            // return null;
            return 0;
        }
        else
        {
            // return topOrderList;
            return 0;
        }   

    }

    // Part b. Print all topological orders of g, one per line, and 
    //	return number of topological orders of g
    public long enumerateTopologicalOrders() 
    {
        return 0;
    }

    // Part c. Return the number of shortest paths from s to t
    // 	Return -1 if the graph has a negative or zero cycle
    public long countShortestPaths(Graph.Vertex t) 
    {
	   return 0;
    }
    
    // Part d. Print all shortest paths from s to t, one per line, and 
    //	return number of shortest paths from s to t.
    //	Return -1 if the graph has a negative or zero cycle.
    public long enumerateShortestPaths(Graph.Vertex t) 
    {
       return 0;
    }

    // Part e. Return weight of shortest path from s to t using at most k edges
    public int constrainedShortestPath(Graph.Vertex t, int k) 
    {
       return 0;
    }

    // Part f. Reward collection problem
    // Reward for vertices is passed as a parameter in a hash map
    // tour is empty list passed as a parameter, for output tour
    // Return total reward for tour
    public int reward(HashMap<Graph.Vertex,Integer> vertexRewardMap, List<Graph.Vertex> tour) 
    {
       return 0;
    }

    // Do not modify this function
    static void printGraph(Graph g, HashMap<Graph.Vertex,Integer> map, Graph.Vertex s, Graph.Vertex t, int limit) 
    {
    	System.out.println("Input graph:");

    	for(Graph.Vertex u: g) 
        {
    	    if(map != null) 
            { 
    		   System.out.print(u + "($" + map.get(u) + ")\t: ");
    	    } 
            else 
            {
    		   System.out.print(u + "\t: ");
    	    }

    	    for(Graph.Edge e: u) 
            {
    		   System.out.print(e + "[" + e.weight + "] ");
    	    }

    	    System.out.println();
    	}

    	if(s != null) 
        { 
            System.out.println("Source: " + s); 
        }

    	if(t != null) 
        { 
            System.out.println("Target: " + t); 
        }

    	if(limit > 0) 
        { 
            System.out.println("Limit: " + limit + " edges"); 
        }

    	System.out.println("___________________________________");
    }
}
