# 2D Array

2D array can be defined as an array of arrays. The 2D array is organized as matrices which can be represented as the collection of rows and columns.

However, 2D arrays are created to implement a relational database look alike data structure. It provides ease of holding bulk of data at once which can be passed to any number of functions whenever required.

## How to declare 2D Array 

The syntax of declaring two dimensional array is very much similar to that of a one dimensional array, given as follows.

```
int arr[max_rows][max_columns]
```

however it produces the data structure which looks like following

![alt text](https://static.javatpoint.com/ds/images/ds-2d-array.png)

Above image shows the two dimensional array, the elements are organized in the form of rows and columns. First element of the first row is represented by a[0][0] where the number shown in the first index is the number of that row while the number shown in the second index is the number of the column.

## Mapping 2D array to 1D array

When it comes to map a 2 dimensional array, most of us might think that why this mapping is required. However, 2D array exists from the user point of view. 2D arrays are created to implement a relational database lookalike data structure, in computer memory, the storage technique for 2D array is similar to that of an dimensional array.

**The size of two dimensional array is equal to the multiplication of number of rows and the number of columns present in the array**. We do need to map two dimensional array to the one dimensional array in order to store them in the memory.

A 3 X 3 two dimensional array is shown in the following image. However, this array needs to be mapped to a one dimensional array in order to store it into the memory.

![alt text](https://static.javatpoint.com/ds/images/ds-2d-array2.png)

There are two main techniques of storing 2D array elements into memory.

### Row Major ordering

In row major ordering, all the rows of the 2D array are stored into the memory contigously. Considering the array shown in the above image, its memory allocation according to row major order is shown as follows.

![alt text](https://static.javatpoint.com/ds/images/ds-2d-array-row-major-ordering.png)

### Column Major ordering

According to the column major ordering, all the columns of the 2D array are stored into the memory contiguously. The memory allocation of the array which is shown in in the above image is given as follows.

![alt text](https://static.javatpoint.com/ds/images/ds-2d-array-column-major-ordering.png)




