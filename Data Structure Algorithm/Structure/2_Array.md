# Array

## Definition

* Arrays are defined as the collection of similar type of data items stored at contiguous memory location.
* Arrays is the simplest data structure where each element can be randomly accessed by using its index number.
  
## Properties of the Array

1. Each element is of same data type and carries a same size. int = 4 bytes.
2. Elements of the array are stored at contiguous memory locations where the first element is stored at the smallest memeory location.
3. Elements of the array can be randomly accessed since we can calculate the address of each element of the array with the given base address and the size of data element.

## Need of using Array

In computer programming, the most of the cases requires to store the large number of data of similar type. To store such amount of data, we need to define a large number of variables. It would be very difficult to remember names of all variables while writing the programms. Instead of naming all variables with different name, it is better to define an array and store all the elements into it.

## Complexity of Array Operations

| Algorithm | Average Case | Worst Case |
|-----------|--------------|------------|
| Access    | O(1)         | O(1)       |
| Search    | O(n)         | O(n)       |
| Insertion | O(n)         | O(n)       |
| Deletion  | O(n)         | O(n)       |

Space Complexity: O(n).

## Advantage of Array

* Array provides the single name for the group of variables of the same type therefore, it is easy to remember the name of all the elements of an array.

* Traversing an array is a very simple process, we just need to increment the base address of the array in order to visit each element one by one.

* Any element in the array can be directly accessed by using the index.

## Memory Allocation of the array

As we have mentioned before, all the data elements of an array are stored at contiguous location in the main memory. The name of the array represents the base address or the address of first element in the main memeory. Each element of the arrray if represented by a proper indexing.

The indexing of the array can be defined in three ways.

1. 0 (zero - based indexing) : The first element of the array will be arr[0].
2. 1 (one - based indexing) : The first element of the array will be arr[1].
3. n (n - based indexing) : The first element of the array can reside at any random index number.
