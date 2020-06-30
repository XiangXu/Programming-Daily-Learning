# Bubble Sort Algorithm

**Bubble Sort** is a simple algorithm which is used to sort a given set of n elements provided in form of an array with n number of elements. Bubble compares all the element one by one and sort them based on their values.

If the given array has to be stored in ascending order, then bubble sort will start by comparing the first element of the array with the second element, if the first element is greater than the second element, it will swap both the elements, and then move on to compare the second and the third element, and so on.

**If we have total n elements, then we need to repeat this process for n-1 times**.

It is known as **bubble sort**, because with every complete iteration the largest element in the given array, bubbles up towards the last place or the highest index, just like a water bubble rises up to the water surface.

**Sorting takes place by stepping through all the elements one-by-one and comparing it with the adjacent element and swapping them if required**.

## Implementing Bubble Sort Algorithm

1. Starting with the first element(index = 0), compare the current element with the next element of the array.
2. If the current element is greater than the next element of the array, swap them.
3. If the current element is less than the next element, move to the next element, repeat step 1.

```java
public static void bubbleSort(int[] arr)
{
    boolean flag = true;
    while(flag)
    {
        flag = false;
        int len = arr.length;
        for(int i=0; i<len; i++)
        {
            for(int j=1; j<len-i; j++)
            {
                if(arr[j-1] > arr[j])
                {
                    int temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
                    flag = true;
                }
            }
        }
    }
}
```

## Complexity Analysis of Bubble Sort

In Bubble Sort, **n-1** comparisons will be done in the 1st pass, **n-2** in 2nd pass, **n-3** in 3rd pass and so on. So the total number of comparisions will be,

```
(n-1) + (n-2) + (n-3) + ..... + 3 + 2 + 1
Sum = n(n-1)/2
i.e O(n2)
```

Hence the **time complexity** of Bubble Sort is **O(n<sup>2</sup>)**.

The main advantage of Bubble Sort is the simplicity of the algorithm.

The **space complexity** for Bubble Sort is **O(1)**, because only a single additional memory space is required i.e. for **temp** variable.

* Worst Case Time Complexity: O(n<sup>2</sup>)
* Best Case Time Complexity: O(n)
* Average Time Complexity: O(n<sup>2</sup>)
* Space Complexity: O(1)


Reference

https://www.studytonight.com/data-structures/bubble-sort