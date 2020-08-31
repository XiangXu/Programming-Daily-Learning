# Bubble Sort

Bubble Sort is the simplest sorting algorithm that works by repeatedly swapping the adjacent elements if they are in wrong order.

Example

First Pass:

```
( 5 1 4 2 8 ) –> ( 1 5 4 2 8 ), Here, algorithm compares the first two elements, and swaps since 5 > 1.
( 1 5 4 2 8 ) –>  ( 1 4 5 2 8 ), Swap since 5 > 4
( 1 4 5 2 8 ) –>  ( 1 4 2 5 8 ), Swap since 5 > 2
( 1 4 2 5 8 ) –> ( 1 4 2 5 8 ), Now, since these elements are already in order (8 > 5), algorithm does not swap them.
```

Second Pass:

```
( 1 4 2 5 8 ) –> ( 1 4 2 5 8 )
( 1 4 2 5 8 ) –> ( 1 2 4 5 8 ), Swap since 4 > 2
( 1 2 4 5 8 ) –> ( 1 2 4 5 8 )
( 1 2 4 5 8 ) –>  ( 1 2 4 5 8 )
```

Now, the array is already sorted, but our algorithm does not know if it is completed. The algorithm needs one whole pass without any swap to know it is sorted.

```
( 1 2 4 5 8 ) –> ( 1 2 4 5 8 )
( 1 2 4 5 8 ) –> ( 1 2 4 5 8 )
( 1 2 4 5 8 ) –> ( 1 2 4 5 8 )
( 1 2 4 5 8 ) –> ( 1 2 4 5 8 )
```

Following is the implementations of Bubble Sort.

```java
public class BubbleSort
{
    // Bubble sort implementation
    private static void bubbleSort(int arr[])
    {
        boolean isSorted = true;
        int tmp;
        for(int i=0; i<arr.length; i++)
        {
            //Everytime when bubble sort finish, the larget number will be moved to the last index.
            for(int j=1; j<arr.length-i; j++)
            {
                if(arr[j] < arr[j-1])
                {
                    tmp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = tmp;
                    isSorted = false;
                }
            }
            
            //Exist earlier
            if(isSorted)
                break;
        }
        
    }
    
    // Function to print an array  
   private  static void printArray(int arr[]) 
    { 
        for (int i = 0; i < arr.length; i++) 
            System.out.print(arr[i] + " "); 
        System.out.println(); 
    } 
  
    // Driver program 
    public static void main(String args[]) 
    { 
        int arr[] = { 64, 34, 25, 12, 22, 11, 90 }; 
        bubbleSort(arr); 
        System.out.println("Sorted array: "); 
        printArray(arr); 
    } 
}
```


**Worst and Average Case Time Complexity**: O(n*n). Worst case occurs when array is reverse sorted.

**Best Case Time Complexity**: O(n). Best case occurs when array is already sorted.

**Auxiliary Space**: O(1)



Reference

https://www.geeksforgeeks.org/bubble-sort/