public class ProblemsOnTrees
{

	public static void main(String[] args)
	{
		BST<Integer> bst = new BST<Integer>(); 
		
		bst.add(new Integer(7));
		bst.add(new Integer(3));
		bst.add(new Integer(11));
		bst.add(new Integer(1));
		bst.add(new Integer(5));
		bst.add(new Integer(9));
		bst.add(new Integer(13));

		bst.merge();

		// bst.add(new Integer(0));
		// bst.add(new Integer(2));
		// bst.add(new Integer(4));
		// bst.add(new Integer(6));

		// bst.add(new Integer(8));
		// bst.add(new Integer(10));
		// bst.add(new Integer(12));
		// bst.add(new Integer(14));


		// BST<Integer> bst = new BST<Integer>();
		// bst.root = bst.new Entry<Integer>(new Integer(8));
		// bst.root.leftChild = bst.new Entry<Integer>(new Integer(3));
		// bst.root.rightChild = bst.new Entry<Integer>(new Integer(10));
		// bst.root.rightChild.rightChild = bst.new Entry<Integer>(new Integer(14));
		// bst.root.rightChild.rightChild.leftChild = bst.new Entry<Integer>(new Integer(13));
		// bst.root.leftChild.leftChild = bst.new Entry<Integer>(new Integer(1));
		// bst.root.leftChild.rightChild = bst.new Entry<Integer>(new Integer(6));
		// bst.root.leftChild.rightChild.leftChild = bst.new Entry<Integer>(new Integer(4));
		// bst.root.leftChild.rightChild.rightChild = bst.new Entry<Integer>(new Integer(7));

		// bst.root.leftChild.parent = bst.root;
		// bst.root.rightChild.parent = bst.root;
		// bst.root.rightChild.rightChild.parent = bst.root.rightChild;
		// bst.root.rightChild.rightChild.leftChild.parent = bst.root.rightChild.rightChild;
		// bst.root.leftChild.leftChild.parent = bst.root.leftChild;
		// bst.root.leftChild.rightChild.parent = bst.root.leftChild;
		// bst.root.leftChild.rightChild.leftChild.parent = bst.root.leftChild.rightChild;
		// bst.root.leftChild.rightChild.rightChild.parent = bst.root.leftChild.rightChild;

		// System.out.println(bst.lca(bst.root.leftChild.rightChild, bst.root.leftChild.leftChild.leftChild));

		// bst.inOrder(bst.root);
		// System.out.println(bst.isBST(bst));

		// Integer[] array = {1, 5, 20, 31, 55, 57, 88};
		// BST<Integer> bst = new BST<Integer>();
		// bst.arrayToBST(array); 

		// System.out.println(bst.isHeightBalanced(bst.root));
		// System.out.println(bst.closest(bst, new Integer(120)));
		// System.out.println(bst.select(bst, 5));
		// bst.inOrder(bst.root);
		// System.out.println("");
		// bst.postOrder(bst.root);
		// System.out.println("");
		// Integer[] inOrder = {0, 2, 4, 7, 8, 20, 41};
		// Integer[] postOrder = {0, 4, 2, 20, 41, 8, 7};

		// BST<Integer> reconstructBST = bst.reconstruct(inOrder, postOrder);

		// reconstructBST.preOrder(reconstructBST.root);
		// System.out.println("");

		
	}
}