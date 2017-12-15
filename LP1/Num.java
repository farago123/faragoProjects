
// Starter code for lp1.

// Change following line to your group number
// Changed type of base to long: 1:15 PM, 2017-09-08.
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Iterator;
// package cs6301.g42;

public class Num implements Comparable<Num> 
{

    static long defaultBase = 10;  // This can be changed to what you want it to be.
    long base = defaultBase;  // Change as needed
    LinkedList<Long> digits; 
    boolean isNegative;

    /* Start of Level 1 */
    Num(String s) 
    {
        // TO DO: check wether s is null, check whether s is a number, remove leading zeros

        digits = new LinkedList<Long>();
        int startingIndex = 0;
        long currentLong;

        if(s.charAt(0) == '-')
        {
            isNegative = true;
            startingIndex++;
        }
        else
        {
            isNegative = false;
        }

        for(int i = startingIndex; i < s.length(); i++)
        {
            currentLong = Long.parseLong(s.charAt(i) + "");
            digits.addFirst(new Long(currentLong));
        }

    }

    Num(long x) 
    {
        this(String.valueOf(x));
    }

    static Num add(Num a, Num b) 
    {

        if(a == null || b == null)
        {
            return null;
        }
        else
        {

            boolean negative = false;

            if(a.isNegative && !b.isNegative)
            {
                return subtract(b, a);
            }
            else if(!a.isNegative && b.isNegative)
            {
                return subtract(a, b);
            }
            else if(a.isNegative && b.isNegative)
            {
                negative = true;
            }

            Iterator<Long> i1 = a.digits.iterator();
            Iterator<Long> i2 = b.digits.iterator();
            long currentDigit1, currentDigit2, currentSum, carry = 0;
            String result = "";

            while(i1.hasNext() && i2.hasNext())
            {

                currentDigit1 = i1.next().longValue();
                currentDigit2 = i2.next().longValue();
                currentSum = currentDigit1 + currentDigit2 + carry; 

                if(currentSum > 9)
                {
                    carry = 1;
                }
                else
                {
                    carry = 0;
                }

                if(!i1.hasNext() && !i2.hasNext())
                {
                    result = currentSum + result;
                }
                else
                {
                    result = (currentSum%10) + result;
                }    
                

            }

            if(!i1.hasNext() && i2.hasNext())
            {
                while(i2.hasNext())
                {
                    currentDigit2 = i2.next().longValue();
                    currentSum = currentDigit2 + carry;

                    if(currentSum > 9)
                    {
                        carry = 1;
                    }
                    else
                    {
                        carry = 0;
                    }

                    if(!i2.hasNext())
                    {
                        result = currentSum + result;
                    }
                    else
                    {
                        result = (currentSum%10) + result;
                    }  
                }
            }
            else if(i1.hasNext() && !i2.hasNext())
            {
                while(i1.hasNext())
                {
                    currentDigit1 = i1.next().longValue();
                    currentSum = currentDigit1 + carry;

                    if(currentSum > 9)
                    {
                        carry = 1;
                    }
                    else
                    {
                        carry = 0;
                    }

                    if(!i1.hasNext())
                    {
                        result = currentSum + result;
                    }
                    else
                    {
                        result = (currentSum%10) + result;
                    }  
                }
            }

            Num finalAnswer = new Num(result);
            finalAnswer.isNegative = negative;

            return finalAnswer;

        }

    }

    static Num subtract(Num a, Num b) 
    {

        if(a == null || b == null)
        {
            return null;
        }
        else
        {
            if(!a.isNegative && b.isNegative)
            {
                b.isNegative = false;
                Num result = add(a, b);
                b.isNegative = true;

                return result;
            }
            else if(a.isNegative && !b.isNegative)
            {
                a.isNegative = false;
                b.isNegative = false;
                Num result = add(a, b);
                a.isNegative = true;
                b.isNegative = true;
                result.isNegative = true;

                return result;
            }
            else if(a.isNegative && b.isNegative)
            {
                a.isNegative = false;
                b.isNegative = false;
                Num result = subtract(b, a);
                a.isNegative = true;
                b.isNegative = true;

                return result;
            }
            else if(!a.isNegative && !b.isNegative)
            {

                boolean negative = false;
                Num topNum = a;
                Num bottomNum = b;

                if(a.compareTo(b) == -1)
                {
                    negative = true; 
                    topNum = b;
                    bottomNum = a;
                }

                Iterator<Long> i1 = topNum.digits.iterator();
                Iterator<Long> i2 = bottomNum.digits.iterator();
                long currentDigit1, currentDigit2, borrow = 0;
                String result = "";

                while(i1.hasNext() && i2.hasNext())
                {


                    currentDigit1 = i1.next().longValue() - borrow;

                    if(currentDigit1 == -1)
                    {
                        currentDigit1 = 9;
                    }

                    currentDigit2 = i2.next().longValue();
                    
                    if(currentDigit1 >= currentDigit2)
                    {
                        borrow = 0;
                    }
                    else
                    {
                        currentDigit1 += 10;
                        borrow = 1;
                    }

                    result = (currentDigit1 - currentDigit2) + result;      
                    
                }

                while(i1.hasNext())
                {
                    currentDigit1 = i1.next().longValue() - borrow;

                    if(currentDigit1 == -1)
                    {
                        currentDigit1 = 9;
                    }

                    result = currentDigit1 + result;
                    borrow = 0;
                }

                Num finalAnswer = new Num(result);
                finalAnswer.isNegative = negative;

                return finalAnswer;

            }

        }

        return null;    

    }

    // Implement Karatsuba algorithm for excellence credit
    static Num product(Num a, Num b) 
    {
	   if(a == null || b == null)
       {
          return null;
       }
       else
       {

          if(a.digits.size() == 1 && a.digits.getFirst().longValue() == 0)
          {
             return new Num(0); 
          }

          if(b.digits.size() == 1 && b.digits.getFirst().longValue() == 0)
          {
             return new Num(0); 
          }

          boolean negative = false;

          if(!(a.isNegative && b.isNegative) && !(!a.isNegative && !b.isNegative))
          {
              negative = true;
          }

          long longA = 0;
          int i = 0;

          for(Long digit: a.digits)
          {
             longA += digit.longValue()*((long)Math.pow(10, i));
             i++;
          }

          Num bSum = new Num(0);

          for(int j = 0; j < longA; j++)
          {
             bSum = add(bSum, b);
          }

          bSum.isNegative = negative;

          return bSum;

       }

    }

    // Use divide and conquer
    static Num power(Num a, long n) 
    {

       if(n == 0)
       {
          return new Num(1);
       }
       else if(n == 1)
       {
          return a;
       }
       else
       {
          Num s = power(a, n/2);
          Num sSquared = product(s, s);

          if(n%2 == 0)
          {
             return sSquared;
          }
          else
          {
             return product(sSquared, a);
          }

       }

    }
    /* End of Level 1 */

    /* Start of Level 2 */
    static Num divide(Num a, Num b) 
    {
	     long longA = 0, longB = 0;
       int i = 0;

       for(Long digit: a.digits)
       {
          longA += digit*Math.pow(10, i);
          i++;
       }

       i = 0;

       for(Long digit: b.digits)
       {
          longB += digit*Math.pow(10, i);
          i++;
       }

       long answer = longA/longB;
       // System.out.println(answer);
       Num answerNum = new Num(answer);
       boolean negative = false;

       if((a.isNegative && !b.isNegative) || (!a.isNegative && b.isNegative))
       {
          negative = true;
       }

       answerNum.isNegative = negative;

       return answerNum;
    }

    static Num mod(Num a, Num b) 
    {
	   return null;
    }

    // Use divide and conquer
    static Num power(Num a, Num n) 
    {
	   return null;
    }

    static Num squareRoot(Num a) 
    {

      Num start = new Num(0);
      Num end = a;
      Num ans = null;

      while(start.compareTo(end) == -1 || start.compareTo(end) == 0)
      {
          Num mid = divide(add(start, end), new Num(2));
          Num midSquared = product(mid, mid);
          int compareTo1 = a.compareTo(midSquared);

          if(compareTo1 == 0)
          {
             return mid;
          }
          else if(compareTo1 == 1)
          {
             start = add(mid, new Num(1));
             ans = mid;
          }
          else
          {
             end = subtract(mid, new Num(1));
          }

      }

      return ans;

    }
    /* End of Level 2 */


    // Utility functions
    // compare "this" to "other": return +1 if this is greater, 0 if equal, -1 otherwise
    public int compareTo(Num other) 
    {

       if(other == null)
       {
          return -1;
       } 
       else
       {
          if(this.isNegative && !other.isNegative)
          {
            return -1; 
          }
          else if(!this.isNegative && other.isNegative)
          {
            return 1;
          }
          else if(this.digits.size() > other.digits.size())
          {
             if(!this.isNegative && !other.isNegative)
             {
                return 1;
             }
             else if(this.isNegative && other.isNegative)
             {
                return -1;
             }
             
          }
          else if(this.digits.size() < other.digits.size())
          {
             if(!this.isNegative && !other.isNegative)
             {
                return -1;
             }
             else if(this.isNegative && other.isNegative)
             {
                return 1;
             }

          }
          else
          {
              LinkedList<Long> thisNumber = new LinkedList<Long>();
              LinkedList<Long> otherNumber = new LinkedList<Long>();

              for(Long digit: this.digits)
              {
                 thisNumber.addFirst(new Long(digit));
              }

              for(Long digit: other.digits)
              {
                 otherNumber.addFirst(new Long(digit));
              }

              Iterator<Long> i1 = thisNumber.iterator();
              Iterator<Long> i2 = otherNumber.iterator();

              while(i1.hasNext())
              {
                 long currentDigit1 = i1.next().longValue();
                 long currentDigit2 = i2.next().longValue();

                 if(currentDigit1 > currentDigit2)
                 {
                    if(!this.isNegative && !other.isNegative)
                    {
                        return 1;
                    }
                    else if(this.isNegative && other.isNegative)
                    {
                        return -1;
                    }
                    
                 }
                 else if(currentDigit1 < currentDigit2)
                 {
                    if(!this.isNegative && !other.isNegative)
                    {
                        return -1;
                    }
                    else if(this.isNegative && other.isNegative)
                    {
                        return  1;
                    }
                 }

              }

              return 0;
          }

       }
       return 0;
    }
    
    // Output using the format "base: elements of list ..."
    // For example, if base=100, and the number stored corresponds to 10965,
    // then the output is "100: 65 9 1"
    void printList() 
    {
    }
    
    // Return number to a string in base 10
    public String toString() 
    { 
       String output = ""; 

       for(Long l: digits)
       {
          output = l.longValue() + output;
       }

       if(this.isNegative)
       {
          output = "-" + output;
       }

       return output;
    }

    public long base() { return base; }
}
