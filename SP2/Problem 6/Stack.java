import java.util.NoSuchElementException;

public class Stack<T>
{

	private T[] elements;
	private int currentIndex;
	private int stackSize;

	public Stack(int stackSize)
	{
		this.stackSize = stackSize;
		elements = (T[]) new Object[stackSize];
		currentIndex = 0;
	}

	public void push(T element)
	{
		if(currentIndex == stackSize)
		{
			throw new IllegalStateException("The stack is full, cannot add element");
		}
		else
		{
			elements[currentIndex] = element;
			currentIndex++;
		}
	
	}

	public boolean isEmpty()
	{
		if(currentIndex == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public T pop()
	{
		if(this.isEmpty())
		{
			throw new NoSuchElementException("Stack is empty");
		}
		else
		{
			currentIndex--;
			return elements[currentIndex];
		}
	}

	public T peek()
	{
		if(this.isEmpty())
		{
			throw new NoSuchElementException("Stack is empty");
		}
		else
		{
			int temp = currentIndex-1;
			return elements[temp];
		}
	}


	public static void main(String[] args)
	{
		Stack<Integer> stack = new Stack<Integer>(10);
		// Integer i1 = stack.peek(); 
		stack.push(new Integer(1));
		stack.push(new Integer(2));
		stack.push(new Integer(3));
		stack.push(new Integer(4));
		stack.push(new Integer(1));
		stack.push(new Integer(2));
		stack.push(new Integer(3));
		
		
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		stack.push(new Integer(4));
		stack.push(new Integer(1));
		stack.push(new Integer(2));
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		
		
	}
}