import java.util.List;
import java.util.LinkedList;

public class Euler 
{
    int VERBOSE;
    List<Graph.Edge> tour;
    Graph g;
    Graph.Vertex start;
    EulerVertex[] eulerVertices;
    EulerEdge[][] eulerEdges;

    static class EulerVertex
    {
        boolean exploredTour;
        boolean visited;
        List<Graph.Edge> tour;
        Graph.Vertex vertex;

        public EulerVertex(Graph.Vertex vertex)
        {
            this.vertex = vertex;
            tour = new LinkedList<Graph.Edge>();
            exploredTour = false;
            visited = false;
        }
    }

    static class EulerEdge
    {
        boolean explored;
        Graph.Edge edge;

        public EulerEdge(boolean explored, Graph.Edge edge)
        {
            this.explored = explored;
            this.edge = edge;
        }
    }
    
    // Constructor
    Euler(Graph g, Graph.Vertex start) 
    {
    	VERBOSE = 1;
    	tour = new LinkedList<>();
        this.g = g;
        this.start = start;
        int numVertices = g.size();
        eulerVertices = new EulerVertex[numVertices];
        eulerEdges = new EulerEdge[numVertices][numVertices];

        for(Graph.Vertex u: g)
        {
            eulerVertices[u.name] = new EulerVertex(u);

            for(Graph.Edge e: u)
            {
                eulerEdges[e.from.name][e.to.name] = new EulerEdge(false, e);
            }
        }

    }

    // To do: function to find an Euler tour
    public List<Graph.Edge> findEulerTour() 
    {
    	findTours();
        
    	if(VERBOSE > 9) 
        { 
            printTours(); 
        }

    	stitchTours();
    	return tour;
    }

    boolean isEulerian() 
    {
        GraphAlgorithm ga = new GraphAlgorithm(g);
    	return ga.testEulerian();
    }

    // Find tours starting at vertices with unexplored edges
    void findTours() 
    {
        findTour(start, eulerVertices[start.name].tour);
        Graph.Vertex currentNode = getNodeWithUnexploredEdges();

        while(currentNode != null)
        {
            findTour(currentNode, eulerVertices[currentNode.name].tour);
            currentNode = getNodeWithUnexploredEdges();
        }

    }

    void findTour(Graph.Vertex u, List<Graph.Edge> tour)
    {
        Graph.Edge e = getUnexploredEdge(u);

        while(e != null)
        {
            tour.add(e);
            // System.out.println(e);
            eulerEdges[e.from.name][e.to.name].explored = true;
            u = e.to;
            e = getUnexploredEdge(u);
        }

    }

    Graph.Vertex getNodeWithUnexploredEdges()
    {
        for(Graph.Vertex u: this.g)
        {
            for(Graph.Edge e: u)
            {
                if(eulerEdges[e.from.name][e.to.name].explored == false)
                {
                    return u;
                }
            }
        }

        return null;        

    }

    Graph.Edge getUnexploredEdge(Graph.Vertex u)
    {
        for(Graph.Edge e: u)
        {
            if(eulerEdges[e.from.name][e.to.name].explored == false)
            {
                return e;
            }
        }

        return null;
    }

    /* Print tours found by findTours() using following format:
     * Start vertex of tour: list of edges with no separators
     * Example: lp2-in1.txt, with start vertex 3, following tours may be found.
     * 3: (3,1)(1,2)(2,3)(3,4)(4,5)(5,6)(6,3)
     * 4: (4,7)(7,8)(8,4)
     * 5: (5,7)(7,9)(9,5)
     *
     * Just use System.out.print(u) and System.out.print(e)
     */
    void printTours() 
    {
        for(EulerVertex ev: eulerVertices)
        {
            System.out.print(ev.vertex + ": ");

            for(Graph.Edge e: ev.tour)
            {
                System.out.print(e);
            }

            System.out.println("");
        }

        System.out.println("");
    }

    // Stitch tours into a single tour using the algorithm discussed in class
    void stitchTours() 
    {
        explore(this.start);
    }

    void explore(Graph.Vertex u)
    {
        Graph.Vertex tmp = u;
        EulerVertex uEuler = eulerVertices[u.name];
        uEuler.visited = true;

        for(Graph.Edge e: uEuler.tour)
        {
            this.tour.add(e);
            tmp = e.otherEnd(tmp);
            EulerVertex tmpEuler = eulerVertices[tmp.name];

            if(tmpEuler.exploredTour == false && tmpEuler.visited == false)
            {
                explore(tmp);
                eulerVertices[tmp.name].exploredTour = true;
            }

        }

    }

    void setVerbose(int v) 
    {
	    VERBOSE = v;
    }
}
