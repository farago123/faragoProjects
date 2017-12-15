import java.util.Iterator;
import java.util.Scanner;
import java.util.NoSuchElementException;

public class SinglyLinkedList<T> implements Iterable<T> {

    /** Class Entry holds a single node of the list */
    static class Entry<T> {
        T element;
        Entry<T> next;

        Entry(T x, Entry<T> nxt) {
            element = x;
            next = nxt;
        }
    }

    // Dummy header is used.  tail stores reference of tail element of list
    Entry<T> head, tail;
    int size;

    public SinglyLinkedList() {
        head = new Entry<>(null, null);
        tail = head;
        size = 0;
    }

    public Iterator<T> iterator() { return new SLLIterator<>(this); }

    private class SLLIterator<E> implements Iterator<E> {
	SinglyLinkedList<E> list;
	Entry<E> cursor, prev;
	boolean ready;  // is item ready to be removed?

	SLLIterator(SinglyLinkedList<E> list) {
	    this.list = list;
	    cursor = list.head;
	    prev = null;
	    ready = false;
	}

	public boolean hasNext() {
	    return cursor.next != null;
	}
	
	public E next() {
	    prev = cursor;
	    cursor = cursor.next;
	    ready = true;
	    return cursor.element;
	}

	// Removes the current element (retrieved by the most recent next())
	// Remove can be called only if next has been called and the element has not been removed
	public void remove() {
	    if(!ready) {
		throw new NoSuchElementException();
	    }
	    prev.next = cursor.next;
	    // Handle case when tail of a list is deleted
	    if(cursor == list.tail) {
		list.tail = prev;
	    }
	    cursor = prev;
	    ready = false;  // Calling remove again without calling next will result in exception thrown
	    size--;
	}
    }

    // Add new elements to the end of the list
    public void add(T x) {
	tail.next = new Entry<>(x, null);
	tail = tail.next;
	size++;
    }

    public void printList() {
	/* Code without using implicit iterator in for each loop:

        Entry<T> x = head.next;
        while(x != null) {
            System.out.print(x.element + " ");
            x = x.next;
        }
	*/

	System.out.print(this.size + ": ");
	for(T item: this) {
	    System.out.print(item + " ");
	}

	System.out.println();
    }

    public T kLinksFromEnd(int k)
	{
		if(size <= k || k < 0)
		{
		   return null;	
		}

		Entry<T> firstPointer = head.next;

		while(k != 0)
		{
			firstPointer = firstPointer.next;
			k--;
		}

		Entry<T> secondPointer = head.next;

		while(firstPointer.next != null)
		{
			firstPointer = firstPointer.next;
			secondPointer = secondPointer.next;
		}

		return secondPointer.element;

	}

	public T middle()
	{

		if(head.next == null)
		{
			return null;
		}

		Entry<T> firstPointer = head.next;
		Entry<T> secondPointer = head.next;

		while(true)
		{
			if(secondPointer.next == null)
			{
				return firstPointer.element;
			}

			if(secondPointer.next.next == null)
			{
				return firstPointer.element;
			}

			firstPointer = firstPointer.next;
			secondPointer = secondPointer.next.next;
		}
	}

	public void merge(SinglyLinkedList<Integer> list)
	{
		if(list == null || list.head.next == null)
		{
		   return;	
		}

		Entry<Integer> currentThis = (Entry<Integer>)head.next;
		Entry<Integer> prevThis = (Entry<Integer>)head;
		Entry<Integer> currentThat = list.head.next;
		Entry<Integer> nextThat;

		while(!(currentThis.next == null && currentThat.next == null))
		{
			Integer element1 = currentThis.element;
			Integer element2 = currentThat.element;

			int compareTo = element1.compareTo(element2);

			if(compareTo == -1)
			{
			   if(currentThis.next != null)
			   {
			   	  prevThis = prevThis.next;
			   	  currentThis = currentThis.next;
			   }
			   else
			   {
			   	  currentThis.next = currentThat;
			   	  return;
			   }	
			}
			else 
			{
			   prevThis.next = currentThat;
			   nextThat = currentThat.next;
			   currentThat.next = currentThis;
			   prevThis = currentThat;
			   currentThat = nextThat;
			}


		}
		
		currentThis.next = currentThat;


	}

    // Rearrange the elements of the list by linking the elements at even index
    // followed by the elements at odd index. Implemented by rearranging pointers
    // of existing elements without allocating any new elements.
    public void unzip() {
	if(size < 3) {  // Too few elements.  No change.
	    return;
	}

	Entry<T> tail0 = head.next;
	Entry<T> head1 = tail0.next;
	Entry<T> tail1 = head1;
	Entry<T> c = tail1.next;
	int state = 0;

	// Invariant: tail0 is the tail of the chain of elements with even index.
	// tail1 is the tail of odd index chain.
	// c is current element to be processed.
	// state indicates the state of the finite state machine
	// state = i indicates that the current element is added after taili (i=0,1).
	while(c != null) {
	    if(state == 0) {
		tail0.next = c;
		tail0 = c;
		c = c.next;
	    } else {
		tail1.next = c;
		tail1 = c;
		c = c.next;
	    }
	    state = 1 - state;
	}
	tail0.next = head1;
	tail1.next = null;
    }

    public static void main(String[] args) throws NoSuchElementException 
    {
        // int n = 10;
        // if(args.length > 0) {
        //     n = Integer.parseInt(args[0]);
        // }

        SinglyLinkedList<Integer> lst1 = new SinglyLinkedList<Integer>();
        SinglyLinkedList<Integer> lst2 = new SinglyLinkedList<Integer>();
        // for(int i=1; i<=n; i++) {
        //     lst.add(new Integer(i));
        // }
        lst1.add(new Integer(3));
        lst1.add(new Integer(6));
        lst1.add(new Integer(7));
        lst1.add(new Integer(9));

        lst2.add(new Integer(1));
        lst2.add(new Integer(2));
        lst2.add(new Integer(5));
        lst2.add(new Integer(8));

        lst1.printList();
        lst2.printList();
        lst1.merge(lst2);
        lst1.printList();

	// Iterator<Integer> it = lst.iterator();
	// Scanner in = new Scanner(System.in);
	// whileloop:
	// while(in.hasNext()) {
	//     int com = in.nextInt();
	//     switch(com) {
	//     case 1:  // Move to next element and print it
	// 	if (it.hasNext()) {
	// 	    System.out.println(it.next());
	// 	} else {
	// 	    break whileloop;
	// 	}
	// 	break;
	//     case 2:  // Remove element
	// 	it.remove();
	// 	lst.printList();
	// 	break;
	//     default:  // Exit loop
	// 	 break whileloop;
	//     }
	// }
	// lst.printList();
	// lst.unzip();
 //        lst.printList();
    }
}