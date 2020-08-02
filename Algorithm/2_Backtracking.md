# Backtracking

Backtracking is an algorithmic-technique for **solving problems recursively by trying to build a solution incremently**, one piece at a time, removing those solutions that fail to stasify the constraints of the probelm at any point of time.

There are three types of problems in backtracking

1. Decision Problem - In this, we search for a feasible(可行的) solution.
2. Optimization Problem - In this, we search for best solution.
3. Enumeration Problem - In this, we find all feasible solutions.

## How to determine if a problem can be solved using Bcktracking?

Generally, **every constraint satisfication problem which has clear and well-defined constrains on any objective solution, that incrementally builds candidate to the solution and abandons a candidate("backtracks") as soon as it determines that candidate cannot possibly be completed to a valid solution, can be solved by Backtracking**.

However, most of the problems that are discussed, can be solved using other known algorithms like Dynamic Programming or Greedy Algorithms in logarithmic, linear, linear-logarithmic time complexity in order of input size, and therefore, outshine the backtracking algorithm in every respect (**since backtracking algorithms are generally exponential in both time and space**). However, a few problems still remain, that only have backtracking algorithms to solve them until now.

Consider a situation that you have three boxes in front of you and only one of them has a gold coin in it but you do not know which one. So, in order to get the coin, you will have to open all of the boxes one by one. You will first check the fist box, if it does not contain the coin, you will have to close it and check the second box and so on until you find the coin. This is what backtracking is, that is solving all sub-problems one by one in order to reach the best possible solution.

Backtracking Problems In LeetCode:

1. https://github.com/XiangXu/LeetCode/blob/master/done/17_letter_combinations_of_a_phone_number.md

```java
private void backTracking(int index, String digits, StringBuilder sb, List<String> result)
{
    if(index == digits.length())
    {
        result.add(sb.toString());
        return;
    }
    
    String letters = keyboard[digits.charAt(index) - '0'];
    for(char letter : letters.toCharArray())
    {
        sb.append(letter);
        backTracking(index+1, digits, sb, result);
        sb.deleteCharAt(sb.length() - 1);
    }
}
```

Reference

https://www.geeksforgeeks.org/backtracking-introduction/#:~:text=Backtracking%20is%20an%20algorithmic%2Dtechnique,reaching%20any%20level%20of%20the
