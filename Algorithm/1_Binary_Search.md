# Binary Search

Given a **sorted array** arr[] of n elements, write a function to search a given element x in arr[].

A simple approach is to do a linear search. The time complexity of above alogrithm is O(n). Another approach to perform the same task is using binary search.

**Binary Search**: search a **sorted array** by repeatedly dividing the search interval itself. Begin with an interval covering the whole array. If the value of the search key is less than the item in the middle of the interval, narrow the interval to the lower half. Otherwise narrow it to the upper half. Repatedly check until the value is found or the interval is empty.

The idea of binary search is to use the information that the array is stored and **reduce the time complexity to (OLogn)**.

![binarysearch](https://www.geeksforgeeks.org/wp-content/uploads/Binary-Search.png)

We basically ignore half of the elements just after one comparsion.

1. Compare x with the middle element.
2. If x matches with middle element, we return the middle index.
3. Else if x is greater than the middle element, then x can only lie in right half subarray after the mid element. So we recur for right half.
4. Else(x is smaller) recur for the left half.


## Recursive implementation of Binary Search

```java
int binarySearch(int arr[], int l, int r, int x) 
{ 
    if (r >= l) { 
        int mid = l + (r - l) / 2; 

        // If the element is present at the 
        // middle itself 
        if (arr[mid] == x) 
            return mid; 

        // If element is smaller than mid, then 
        // it can only be present in left subarray 
        if (arr[mid] > x) 
            return binarySearch(arr, l, mid - 1, x); 

        // Else the element can only be present 
        // in right subarray 
        return binarySearch(arr, mid + 1, r, x); 
    } 

    // We reach here when element is not present 
    // in array 
    return -1; 
} 
```

## Iterative implementation of Binary Search

```java
int binarySearch(int arr[], int x) 
{ 
    int l = 0, r = arr.length - 1; 
    while (l <= r) { 
        int m = l + (r - l) / 2; 

        // Check if x is present at mid 
        if (arr[m] == x) 
            return m; 

        // If x greater, ignore left half 
        if (arr[m] < x) 
            l = m + 1; 

        // If x is smaller, ignore right half 
        else
            r = m - 1; 
    } 

    // if we reach here, then element was 
    // not present 
    return -1; 
}

Reference

https://www.geeksforgeeks.org/binary-search/