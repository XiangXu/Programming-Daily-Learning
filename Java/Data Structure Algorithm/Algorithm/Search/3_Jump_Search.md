# Jump Search Algorithm

Jump Search Algorithm is a relatively new alogrithm for searching an element in **a sorted array**.

The fundamental idea behind this searching technique is to search fewer number of elements compared to linear search algorithm(which scans every element in the array to check if it matches with the element being searched or not). This can be done by skipping some fixed number of array elements or **jumping ahead by fixed number of steps** in every iteration.

Lets consider a sorted array A[] of size n, with indexing ranging between 0 and n-1, and elemente x that needs to be searched in the array A[]. For implementing this algorithm, a block of size m is also required, that can be skipped or jumped in every iteration. Thus, the algorithm works as follow:

* **Iteration 1**: if(x == A[0]), then success, else if (x > A[0]), then jump to the next block.
* **Iteration 2**: if(x == A[m]), then success, else if (x > A[M]), then jump to the next block.
* **Iteration 3**: if (x==A[2m]), then success, else, if (x > A[2m]), then jump to the next block.
* At any point in time, if (x < A[km]), then a linear search is performed from index A[(k-1)m] to A[km].

## Optimal Size of m(Block size to be skipped)

The worst-case scenario requires:

* **n/m** jumps, and
* **(m-1)** comparisons(in case of linear search if x < A[km])

In the worst case, we have to do N/B jumps and if the element is not present, we perform B-1 comparisons. Therefore, the total number of comparisons in the worst case will be ((N/B) + B-1). The value of the function ((N/B) + B-1) will be minimum when B = √N.

Calculation steps:

m/m = n * m<sup>-1</sup> -> -1 * (n * m<sup>-2</sup>) -> -n/m<sup>2</sup>

m-1 = 1 * (m-1)<sup>0</sup> = 1


Hence, the optimal jump size is √n, where n is the size of the array to be searched or the total number of elements to be searched.

## Algorithm of Jump Search

```java
public static int jumpSearch(int[] values, int target)
{
    if(values == null || values.length == 0)
        return -1;

    int len = values.length;
    int jump = (int)Math.floor(Math.sqrt(len));
    int start = 0;
    int end = jump;

    while(values[end] < target && end < len)
    {
        start = end;
        end += jump;
        if(end > len - 1)
            return -1;
    }

    for(int i=start; i<end; i++)
    {
        if(values[i] == target)
            return i;
    }

    return -1;
}
```

## Complexity Analysis for Jump Search

### Time Complexity

The while loop in the above C++ code executes n/m times because the loop counter increments by m times in every iteration. Since the optimal value o**f m= √n , thus, n/m=√n resulting in a time complexity of O(√n)**.

### Space Complexity

The space complexity of this algorithm is **O(1)** since it does not requireany other data structure for its implementation.


### Advantages and disadvantages of Jump Search Algorithm

1. It is faster than the liner search technique which has a time complexity of O(n) for searching an element.
2. It is slower than binary search algorithm which searches an element O(logn).
3. It requires the input array to be sorted.

Reference

https://www.studytonight.com/data-structures/jump-search-algorithm

https://iq.opengenus.org/jump-search-algorithm/