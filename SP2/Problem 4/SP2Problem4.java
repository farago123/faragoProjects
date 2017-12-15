public class SP2Problem4
{

	public static void main(String[] args)
	{
		SinglyLinkedList<Integer> lst = new SinglyLinkedList<Integer>();

        for(int i=7; i<=14; i++) 
        {
            lst.add(new Integer(i));
        }

        lst.printList();
        lst.printListReverse1();
        lst.printListReverse2();
	}
}