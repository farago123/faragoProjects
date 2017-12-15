
import java.util.Iterator;
import java.util.Random;

// Skeleton for skip list implementation.

public class SkipList<T extends Comparable<? super T>> 
{

    class SkipListEntry<T extends Comparable<? super T>> 
    {
        T element;
        int k;
        SkipListEntry[] next;
        int inf;
        int index;

        public SkipListEntry(T element, int k, int inf, int index)
        {
            this.element = element;
            this.k = k;
            next = new SkipListEntry[k];
            this.inf = inf;
            this.index = index;
        }

        public String toString()
        {
            return element.toString();
        }

    }

    int maxLevel, size;
    SkipListEntry<T> head, tail;

    // Constructor
    public SkipList() 
    {
        maxLevel = 10;
        head = new SkipListEntry<T>(null, maxLevel, 1, -1);
        tail = new SkipListEntry<T>(null, maxLevel, -1, -1);

        for(int i = 0; i < maxLevel; i++)
        {
            head.next[i] = tail;
        }

        size = 0;

    }
    @SuppressWarnings("unchecked")
    // Add x to list. If x already exists, replace it. Returns true if new node is added to list
    public boolean add(T x) 
    {
	   SkipListEntry<T>[] prev = find(x);

       if(prev[0].next[0].inf == 0 && prev[0].next[0].element.compareTo(x) == 0)
       {
          prev[0].next[0].element = x;
          return false;
       }
       else
       {
          int lev = chooseLevel(maxLevel);
          SkipListEntry<T> n = new SkipListEntry<T>(x, lev, 0, prev[0].index + 1);

          for(int i = 0; i < lev; i++)
          {
             n.next[i] = prev[i].next[i];
             prev[i].next[i] = n;
          }

          SkipListEntry<T> currentItem = n.next[0];

          while(currentItem != tail)
          {
              currentItem.index++;
              currentItem = currentItem.next[0];
          }

          size++;

          return true;

       }

    }

    public int getIndex(SkipListEntry<T> entry)
    {
        return entry.index;
    }

    @SuppressWarnings("unchecked")

    public SkipListEntry<T>[] find(T x)
    {
        SkipListEntry<T> p = head;
        SkipListEntry[] prev = new SkipListEntry[maxLevel];

        for(int i = maxLevel - 1; i >= 0; i--)
        {

            // System.out.println(p.next[i].inf);

            if(p.next[i].element != null)
            {
                while(p.next[i].inf == -1 || p.next[i].element.compareTo(x) == -1)
                {
                    p = p.next[i];

                    if(p.next[i].element == null)
                    {
                        break;
                    }
                }
            }    

            prev[i] = p;
        }

        return prev;
    }

    public void print()
    {
        SkipListEntry p = head.next[0];

        while(p.inf != -1)
        {
            System.out.print(p.element + " ");
            p = p.next[0];
        }

        System.out.println("");
    }

    public int chooseLevel(int lev)
    {
        int i = 1;
        boolean b;
        Random r = new Random();

        while(i < lev)
        {
            b = r.nextBoolean();

            if(b)
            {
                i++;
            }
            else
            {
                break;
            } 
        }

        return i;

    } 

    // Find smallest element that is greater or equal to x
    @SuppressWarnings("unchecked")
    public T ceiling(T x) 
    {
	   return (T)find(x)[0].next[0].element;
    }

    // Does list contain x?
    @SuppressWarnings("unchecked")
    public boolean contains(T x) 
    {
       SkipListEntry<T>[] prev = find(x);
	     return prev[0].next[0].element.compareTo(x) == 0;
    }

    // Return first element of list
    @SuppressWarnings("unchecked")
    public T first() 
    {
	   return (T)head.next[0].element;
    }

    // Find largest element that is less than or equal to x
    public T floor(T x) 
    {
       if(contains(x))
       {
          return x;
       }
       else
       {
          return (T)find(x)[0].element;
       }
	   
    }

    // Return element at index n of list.  First element is at index 0.
    public T get(int n) 
    {
       if(n < 0 || n > size-1)
       {
          return null;
       }

       int currentIndex = 0;
       SkipListEntry<T> currentItem = head.next[0];

       int currentLevel;

       while(currentIndex != n)
       {
           currentLevel = currentItem.k-1;

           while(true)
           {

              if(currentItem.next != null && currentItem.next[currentLevel] != null && currentItem.next[currentLevel].index <= n)
              {
                 currentItem = currentItem.next[currentLevel];
                 currentIndex = currentItem.index;
                 break;
              }
              else
              {
                 currentLevel--;
              }
           }

       }
        
	   return currentItem.element;
    }

    // Is the list empty?
    public boolean isEmpty() 
    {
	   return false;
    }

    // Iterate through the elements of list in sorted order
    public Iterator<T> iterator() 
    {
	   return null;
    }

    // Return last element of list
    public T last() 
    {
	   return null;
    }

    // Reorganize the elements of the list into a perfect skip list
    public void rebuild() 
    {
	
    }

    // Remove x from list.  Removed element is returned. Return null if x not in list
    public T remove(T x) 
    {
	   return null;
    }

    // Return the number of elements in the list
    public int size() 
    {
	   return 0;
    }
}