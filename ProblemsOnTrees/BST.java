import java.util.*;

public class BST <T extends Comparable<? super T>>
{

	class Entry<T> 
	{
	    T element;
	    Entry<T> leftChild;
	    Entry<T> rightChild;
	    Entry<T> parent;
	    boolean printed;

	    public Entry(T x)
	    {
	    	element = x;
	    	printed = false;
	    }

	    public String toString()
	    {
	    	return element.toString();
	    }
	}

	Entry<T> root;
	int size;

	public void preOrder(Entry<T> root)
	{
		if(root != null)
		{
			System.out.print(root + " ");
			preOrder(root.leftChild);
			preOrder(root.rightChild);
		}
	}

	public void postOrder(Entry<T> root)
	{
		if(root != null)
		{
			postOrder(root.leftChild);
			postOrder(root.rightChild);
			System.out.print(root + " ");
		}
	}

	public void levelOrder(Entry<T> root)
	{
		Queue<Entry<T>> queue = new LinkedList<Entry<T>>();

		while(root != null)
		{
			System.out.print(root + " ");
			queue.add(root.leftChild);
			queue.add(root.rightChild);
			root = queue.remove();
		}
	}

	public BST<T> reconstruct(T[] inOrder, T[] postOrder)
	{
		BST<T> bst = new BST<T>();
		T bstRoot = postOrder[postOrder.length-1];
		bst.root = new Entry<T>(bstRoot);
		int rootIndex = 0;

		for(int i = 0; i < inOrder.length; i++)
		{
			if(inOrder[i].compareTo(bstRoot) == 0)
			{
				rootIndex = i;
			}
		}

		T[] leftInOrder = Arrays.copyOfRange(inOrder, 0, rootIndex);
		T[] rightInOrder = Arrays.copyOfRange(inOrder, rootIndex+1, inOrder.length);
		T[] leftPostOrder = Arrays.copyOfRange(postOrder, 0, rootIndex);
		T[] rightPostOrder = Arrays.copyOfRange(postOrder, rootIndex, inOrder.length-1);

		bst.root.rightChild = reconstructHelper(rightInOrder, rightPostOrder);
		bst.root.leftChild = reconstructHelper(leftInOrder, leftPostOrder);

		return bst;
	}

	public Entry<T> reconstructHelper(T[] inOrder, T[] postOrder)
	{
		if(inOrder.length == 0)
		{
		   return null;	
		}

		T bstRoot = postOrder[postOrder.length-1];
		Entry<T> bstRootEntry = new Entry<T>(bstRoot);
		int rootIndex = 0;

		for(int i = 0; i < inOrder.length; i++)
		{
			if(inOrder[i].compareTo(bstRoot) == 0)
			{
				rootIndex = i;
			}
		}

		T[] leftInOrder = Arrays.copyOfRange(inOrder, 0, rootIndex);
		T[] rightInOrder = Arrays.copyOfRange(inOrder, rootIndex+1, inOrder.length);
		T[] leftPostOrder = Arrays.copyOfRange(postOrder, 0, rootIndex);
		T[] rightPostOrder = Arrays.copyOfRange(postOrder, rootIndex, inOrder.length-1);

		bstRootEntry.rightChild = reconstructHelper(rightInOrder, rightPostOrder);
		bstRootEntry.leftChild = reconstructHelper(leftInOrder, leftPostOrder);

		return bstRootEntry;
	}

	public T select(BST<T> tree, int k)
	{
		Entry<T> rootNode = tree.root;
		return select(rootNode, k);
	}

	public T select(Entry<T> rootNode, int k)
	{
		if(rootNode == null)
		{
			return null;
		}
		else
		{
			int leftSubtreeSize = size(rootNode.leftChild);

			if(leftSubtreeSize == k - 1)
			{
				return rootNode.element;
			}
			else if(leftSubtreeSize > k - 1)
			{
				return select(rootNode.leftChild, k);
			}
			else if(leftSubtreeSize < k - 1)
			{
				return select(rootNode.rightChild, k - (leftSubtreeSize + 1));
			}

		}

		return null;
	}

	public int size(Entry<T> root)
	{
		if(root == null)
		{
			return 0;
		}
		else
		{
			return 1 + size(root.leftChild) + size(root.rightChild);
		}
	}

	public Integer closest(BST<Integer> tree, Integer k) 
	{

		if(tree == null)
		{
			return null;
		}

		BST<Integer>.Entry<Integer> rootNode = tree.root;

		if(rootNode == null)
		{
			return null;
		}
		else
		{
			Integer currentInt = rootNode.element;
			int difference = Math.abs(k - currentInt);

			if(difference == 0)
			{
				return currentInt;
			}
			else
			{
				if(currentInt < k)
				{
				   BST<Integer> rightSubTree = new BST<Integer>();
				   rightSubTree.root = rootNode.rightChild;	
				   Integer closestOnRightSide = closest(rightSubTree, k);

				   if(closestOnRightSide == null)
				   {
				   	  return currentInt;
				   }
				   else
				   {
				   	  int rightSideDifference = Math.abs(k - closestOnRightSide);

				   	  if(difference < rightSideDifference)
				   	  {
				   	  	 return currentInt;
				   	  }
				   	  else
				   	  {
				   	  	 return closestOnRightSide;
				   	  }
				   }
				}
				else if(currentInt > k)
				{
				   BST<Integer> leftSubTree = new BST<Integer>();
				   leftSubTree.root = rootNode.leftChild;	
				   Integer closestOnLeftSide = closest(leftSubTree, k);

				   if(closestOnLeftSide == null)
				   {
				   	  return currentInt;
				   }
				   else
				   {
				   	  int leftSideDifference = Math.abs(k - closestOnLeftSide);

				   	  if(difference < leftSideDifference)
				   	  {
				   	  	 return currentInt;
				   	  }
				   	  else
				   	  {
				   	  	 return closestOnLeftSide;
				   	  }
				   }	
				}

			}
		}

		return null;
	}

	public Entry<T> find(T x)
	{
		// class object stack for stack of ancestors
		Stack<Entry<T>> stack = new Stack<Entry<T>>();
		stack.push(null);
		Entry<T> answer = find(root, x, stack); 
		return answer;
	}

	public Entry<T> find(Entry<T> t, T x, Stack<Entry<T>> stack)
	{
		if(t.element == null || t.element.compareTo(x) == 0)
		{
			return t;
		}

		while(true)
		{
			int compareTo = x.compareTo(t.element);

			if(compareTo == -1)
			{
				if(t.leftChild == null)
				{
					break;
				}
				else
				{
					stack.push(t);
					t = t.leftChild;
				}
			}
			else if(compareTo == 0)
			{
				break;
			}
			else if(compareTo == 1)
			{
				if(t.rightChild == null)
				{
					break;
				}
				else
				{
					stack.push(t);
					t = t.rightChild;
				}
			} 
		}

		return t;
	}

	public boolean contains(T x)
	{
		Entry<T> t = find(x);
		return t != null && t.element.compareTo(x) == 0;
	}

	public T min()
	{
		if(root == null)
		{
			return null;
		}

		Entry<T> t = root;

		while(t.leftChild != null)
		{
			t = t.leftChild;
		}

		return t.element;
	}

	public T max()
	{
		if(root == null)
		{
			return null;
		}

		Entry<T> t = root;

		while(t.rightChild != null)
		{
			t = t.rightChild;
		}

		return t.element;
	}

	public boolean add(T x)
	{
		if(root == null)
		{
			root = new Entry<T>(x);
			size = 1;
			return true;
		}

		Entry<T> t = find(x);
		int compareTo = x.compareTo(t.element);

		if(compareTo == 0)
		{
			t.element = x;
			return false;
		}
		else if(compareTo == -1)
		{
			t.leftChild = new Entry<T>(x);
			t.leftChild.parent = t;
		}
		else
		{
			t.rightChild = new Entry<T>(x);
			t.rightChild.parent = t;
		}

		size++;

		return true;
	}

	public int height(Entry<T> u) 
	{
	  if(u == null) 
	  { 
	  	return -1; 
	  }

	  int lh = height(u.leftChild);
	  int rh = height(u.rightChild);

	  return 1 + Math.max(lh, rh);
	} 

	public boolean isHeightBalanced(Entry<T> root)
	{
		if(root != null)
		{
			int lh = height(root.leftChild);
			int rh = height(root.rightChild);

			if(Math.abs(lh - rh) > 1)
			{
				return false;
			}

			isHeightBalanced(root.leftChild);
			isHeightBalanced(root.rightChild);
		}

		return true;
	}

	public BST<T> arrayToBST(T[] arr)
	{

		if(arr == null)
		{
		   return null;	
		}

		BST<T> bst = new BST<T>();

		arrayToBST(arr, bst);

		return bst;

	}

	public void arrayToBST(T[] arr, BST<T> bst)
	{
		if(arr == null)
		{
		   return;	
		}

		if(arr.length == 1)
		{
		   bst.add(arr[0]);
		   return;
		}

		int middleIndex = arr.length/2;
		T middleElement = arr[middleIndex];
		bst.add(middleElement);
		arrayToBST(Arrays.copyOfRange(arr, 0, middleIndex), bst);
		arrayToBST(Arrays.copyOfRange(arr, middleIndex+1, arr.length), bst);

	}

	public boolean isBST(BST<T> tree)
	{
		ArrayList<T> prev = new ArrayList<T>();
		prev.add(null);
		return isBST(tree.root, prev);
	}

	public boolean isBST(Entry<T> root, ArrayList<T> prev)
	{

		boolean b1 = true, b2 = true, b3 = true;

		if(root != null)
		{ 
			b1 = isBST(root.leftChild, prev);

			if(prev.get(0) != null && root.element.compareTo(prev.get(0)) == -1)
			{
				b2 = false;
			}

			prev.remove(0);
			prev.add(root.element);
			b3 = isBST(root.rightChild, prev);
		}

		return b1 && b2 && b3;
	}

	public Entry<T> lca(Entry<T> a, Entry<T> b)
	{
		Entry<T> currentA = a;
		Entry<T> currentB = b;

		Hashtable<Entry<T>, Integer> visitedNodes = new Hashtable<Entry<T>, Integer>();
		visitedNodes.put(currentA, 0);
		visitedNodes.put(currentB, 0);

		while(true)
		{
			if(currentA == currentB)
			{
				return currentA;
			}
			else
			{
				if(currentA.parent != null)
				{
				   currentA = currentA.parent;
				}
				
				if(currentB.parent != null)
				{
				   currentB = currentB.parent;
				}

				if(visitedNodes.containsKey(currentA))
				{
					return currentA;
				}
				else if(visitedNodes.containsKey(currentB))
				{
					return currentB;
				}
				else
				{
					if(currentA.parent != null)
					{
					   visitedNodes.put(currentA, 0);
					}
					
					if(currentB.parent != null)
					{
					   visitedNodes.put(currentB, 0);
					}
				}

			}
			
		}

	}

	public int depth(Entry<T> u)
	{
		if(u == null)
		{
			return -1;
		}
		else
		{
		   return 1 + depth(u.parent);
		}
	}

	public void inOrder(Entry<T> root)
	{
		if(root != null)
		{
			inOrder(root.leftChild);
			System.out.print(root + " ");
			inOrder(root.rightChild);
		}
	}

	public void inOrderStep(Entry<T> root, boolean[] printed)
	{

		// System.out.println(root + " " + printed[0]);
		if(root != null && printed[0] == false)
		{
			inOrderStep(root.leftChild, printed);
			
			if(printed[0] == false && root.printed == false)
			{
			   System.out.println(root.element);
			   root.printed = true;
			   printed[0] = true;	
			}

			if(printed[0] == false)
			{
			  inOrderStep(root.rightChild, printed);
			}
			
			
		}

	}

	public Entry<T> merge()
	{
		boolean[] printed = new boolean[1];
		printed[0] = false;
		inOrderStep(root, printed);
		printed[0] = false;
		inOrderStep(root, printed);
		printed[0] = false;
		inOrderStep(root, printed);
		printed[0] = false;
		inOrderStep(root, printed);
		printed[0] = false;
		inOrderStep(root, printed);
		printed[0] = false;
		inOrderStep(root, printed);
		printed[0] = false;
		inOrderStep(root, printed);

		return null;
	}

	public static void main(String[] args)
	{
		BST<Integer> bst = new BST<Integer>();
		bst.add(new Integer(5));
		bst.add(new Integer(3));
		bst.add(new Integer(4));
		bst.add(new Integer(6));
		bst.add(new Integer(2));
		bst.add(new Integer(7));
		System.out.println(bst.contains(9));
	}

}