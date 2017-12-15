// Starter code for LP7
import java.util.*;

public class Flow 
{
    Graph g;
    Graph.Vertex s, t;
    HashMap<Graph.Edge, Integer> capacity, flow;

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

    BfsVertex[] bfsVertices; 

    public Flow(Graph g, Graph.Vertex s, Graph.Vertex t, HashMap<Graph.Edge, Integer> capacity, HashMap<Graph.Edge, Integer> flow) 
    {
        this.g = g;
        this.s = s;
        this.t = t;
        this.capacity = capacity;
        this.flow = flow;
        bfsVertices = new BfsVertex[g.size()];

        for(Graph.Vertex u: g)
        {
            bfsVertices[u.name] = new BfsVertex(u);
        }
    }

    public void bfs()
    {
        BfsVertex source = bfsVertices[s.name];         // initialize root node
        source.color = "gray";                                  // set root node's color to gray and distance to 0
        source.d = 0;

        Queue<BfsVertex> queue = new LinkedList<BfsVertex>();           
        queue.add(source);

        while(!queue.isEmpty())                                         // standard bfs implementation based on algorithm in clrs textbook 
        {                                                               
            BfsVertex u = queue.remove();                               // BfsVertex objects are used to store information for bfs such as keeping track
            Graph.Vertex correspondingVertex = u.graphVertex;           // of parent pointers, etc, without having to modify original Graph.Vertex objects          

            for(Graph.Edge e: correspondingVertex)
            {
                BfsVertex v = bfsVertices[e.otherEnd(correspondingVertex).name];        

                if(v.color.equals("white"))
                {
                    v.color = "grey";
                    v.d = u.d + 1;
                    v.parent = u;
                    queue.add(v);
                }

            }

            u.color = "black";
        }

        // for(BfsVertex v: bfsVertices)
        // {
        //     System.out.println(v.graphVertex + " " + v.d);
        // }

    }

    // Return max flow found by Dinitz's algorithm
    public int dinitzMaxFlow() 
    {
	   bfs();
       return findBlockingFlow();

    }

    public int findBlockingFlow()
    {

        HashSet<Graph.Edge> saturatedEdges = new HashSet<Graph.Edge>();
        int totalFlow = 0;
        int currentFlowAugmentation = 0;

        if(bfsVertices[t.name].parent != null)
        {
            while(true)
            {
                LinkedList<Graph.Edge> path = findAugmentingPath(saturatedEdges);
                currentFlowAugmentation = getMinCapacity(path);
                augmentPath(currentFlowAugmentation, path, saturatedEdges);
                totalFlow += currentFlowAugmentation;
                System.out.println(path + " " + currentFlowAugmentation + " " + totalFlow);
          
            }   

        }

        return totalFlow;

    }

    public void augmentPath(int flowAmount, LinkedList<Graph.Edge> path, HashSet<Graph.Edge> saturatedEdges)
    {
        for(Graph.Edge e: path)
        {
            int currentFlow = flow(e);
            int newFlow =  currentFlow + flowAmount;
            flow.put(e, newFlow);

            if(capacity(e) == flow(e))
            {
                saturatedEdges.add(e);
            }
        }
    }

    public int getMinCapacity(LinkedList<Graph.Edge> path)
    {
        int minCapacity = 0, currentCapacity;
        boolean firstIteration = true;

        for(Graph.Edge e: path)
        {
            currentCapacity = capacity(e) - flow(e);

            if(firstIteration)
            {
               minCapacity = currentCapacity;
               firstIteration = false;  
            }
            else if(currentCapacity < minCapacity)
            {
               minCapacity = currentCapacity;
            }
        }

        return minCapacity;

    }

    public LinkedList<Graph.Edge> findAugmentingPath(HashSet<Graph.Edge> saturatedEdges)
    {
        HashSet<Graph.Edge> currentDeletedEdges = new HashSet<Graph.Edge>();
        LinkedList<Graph.Edge> currentPathEdges = new LinkedList<Graph.Edge>();
        Graph.Vertex currentNode = s;
        boolean validEdgeExists, wentBack;

        while(true)
        {   
            validEdgeExists = false;
            wentBack = false;

            for(Graph.Edge e: currentNode)
            {
                BfsVertex otherEndVertex = bfsVertices[e.to.name];
                BfsVertex thisVertex = bfsVertices[currentNode.name];

                if(!saturatedEdges.contains(e) && !currentDeletedEdges.contains(e) && otherEndVertex.d == thisVertex.d + 1)
                {
                   validEdgeExists = true;
                   currentPathEdges.add(e);
                   currentNode = e.to;

                   if(currentNode == t)
                   {
                      return currentPathEdges;
                   }

                   boolean everyEdgeDeleted = true;
                   boolean everyEdgeSaturated = true;

                   for(Graph.Edge outgoingEdge: currentNode)
                   {
                       if(!currentDeletedEdges.contains(outgoingEdge))
                       {
                          everyEdgeDeleted = false;
                       }

                       if(!saturatedEdges.contains(outgoingEdge))
                       {
                          everyEdgeSaturated = false;
                       }
                   }

                   if(currentNode.adj.isEmpty() || everyEdgeDeleted || everyEdgeSaturated)
                   {
                      validEdgeExists = false;
                      wentBack = true;
                      currentNode = bfsVertices[currentNode.name].parent.graphVertex;
                      currentPathEdges.removeLast();
                      currentDeletedEdges.add(e);
                   }

                   break;
                }

            }

            if(validEdgeExists == false && wentBack == false)
            {
              currentNode = bfsVertices[currentNode.name].parent.graphVertex;
              currentDeletedEdges.add(currentPathEdges.removeLast());
            }

        }           

    }

    // Return max flow found by relabelToFront algorithm
    public int relabelToFront() 
    {
	   return 0;
    }

    // flow going through edge e
    public int flow(Graph.Edge e) 
    {
	   return flow.get(e);
    }

    // capacity of edge e
    public int capacity(Graph.Edge e) 
    {
	   return capacity.get(e);
    }

    /* After maxflow has been computed, this method can be called to
       get the "S"-side of the min-cut found by the algorithm
    */
    public Set<Graph.Vertex> minCutS() 
    {
	   return null;
    }

    /* After maxflow has been computed, this method can be called to
       get the "T"-side of the min-cut found by the algorithm
    */
    public Set<Graph.Vertex> minCutT() 
    {
	   return null;
    }

    public static void main(String[] args)
    {
        Scanner in;
        in = new Scanner(System.in);
        Graph g = Graph.readGraph(in, true);
        Graph.Vertex source = g.getVertex(1);
        Graph.Vertex sink = g.getVertex(6);
        HashMap<Graph.Edge, Integer> capacities = new HashMap<Graph.Edge, Integer>();
        HashMap<Graph.Edge, Integer> flows = new HashMap<Graph.Edge, Integer>();

        for(Graph.Vertex v: g)
        {
            for(Graph.Edge e: v)
            {

                if(e.from.name+1 == 1 && e.to.name+1 == 3)
                {
                    capacities.put(e, 3);
                }
                else if(e.from.name+1 == 2 && e.to.name+1 == 1)
                {
                    capacities.put(e, 3);
                }
                else if(e.from.name+1 == 2 && e.to.name+1 == 4)
                {
                    capacities.put(e, 2);
                }
                else if(e.from.name+1 == 3 && e.to.name+1 == 1)
                {
                    capacities.put(e, 1);
                }
                else if(e.from.name+1 == 3 && e.to.name+1 == 2)
                {
                    capacities.put(e, 1);
                }
                else if(e.from.name+1 == 3 && e.to.name+1 == 5)
                {
                    capacities.put(e, 2);
                }
                else if(e.from.name+1 == 4 && e.to.name+1 == 2)
                {
                    capacities.put(e, 1);
                }
                else if(e.from.name+1 == 4 && e.to.name+1 == 6)
                {
                    capacities.put(e, 3);
                }
                else if(e.from.name+1 == 5 && e.to.name+1 == 2)
                {
                    capacities.put(e, 2);
                }
                else if(e.from.name+1 == 5 && e.to.name+1 == 3)
                {
                    capacities.put(e, 1);
                }
                else if(e.from.name+1 == 6 && e.to.name+1 == 4)
                {
                    capacities.put(e, 1);
                }
                else if(e.from.name+1 == 6 && e.to.name+1 == 5)
                {
                    capacities.put(e, 3);
                }
                // else if(e.from.name+1 == 6 && e.to.name+1 == 5)
                // {
                //     capacities.put(e, 10);
                // }

                flows.put(e, 0);
            }
        }

        Flow f = new Flow(g, source, sink, capacities, flows);
        System.out.println(f.dinitzMaxFlow());

    }
}