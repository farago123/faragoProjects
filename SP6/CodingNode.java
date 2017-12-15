public class CodingNode implements Comparable<CodingNode>
{

	private char character;
	private double frequency;
	private CodingNode leftChild, rightChild;

	public CodingNode(char character, double frequency, CodingNode leftChild, CodingNode rightChild)
	{
		this.character = character;
		this.frequency = frequency;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}

	public int compareTo(CodingNode other)
	{
		if(this.frequency < other.frequency)
		{
			return -1;
		}
		else if(this.frequency > other.frequency)
		{
			return 1;
		}
		else
		{
			return 0;
		}
		
	}

	public char getCharacter()
	{
		return character;
	}

	public double getFrequency()
	{
		return frequency;
	}

	public CodingNode getLeftChild()
	{
		return leftChild;
	}

	public CodingNode getRightChild()
	{
		return rightChild;
	}

}