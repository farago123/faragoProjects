public class DisjointSet
{

	public class Node
	{
		Graph.Vertex element;
		Node p;
		int rank;

		public Node(Graph.Vertex element, int rank)
		{
			this.element = element;
			p = this;
			this.rank = rank;
		}
	}

	public DisjointSet(Graph g, Node[] nodes)
	{
		for(Graph.Vertex v: g)
		{
			nodes[v.name] = new Node(v, 0);
		}
	}

	public Node find(Node u)
	{
		if(u != u.p)
		{
			u.p = find(u.p);
		}
		
		return u.p;
	}

	public void union(Node x, Node y)
	{
		if(x.rank > y.rank)
		{
			y.p = x;
		}
		else if(y.rank > x.rank)
		{
			x.p = y;
		}
		else
		{
			x.rank++;
			y.p = x;
		}
	}


}