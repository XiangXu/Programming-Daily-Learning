# Breadth First Search

Breadth first search is **graph traversal algorithm**. In this algorithm, lets say we start with node, i, then we will visit neighbours of i, then neighbours of neighbours of i and so on.

It is very much similar to which is used in binary tree. We use queue to traverse graph. **We put first node in queue.** It repeatedly extracts node and put its neighbours in the queue. Only difference with respect to binary tree is **we need to track if node have been visited or not**. It can be easily done with help of boolean variable visited in the node. If node have been already visited we won't visit it again.

## Algorithm

1. Create empty queue and push root node to it.
2. Do the following when queue is not empty
3. Pop a node from queue and print it.
4. Find neighbours of node with the help of adjacency(毗邻) matrix and check if node is already visited or not.
5. Push neighbours of node into queue if not null.

## Java BFS Example

Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example:

```
Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.
```

```java

class Solution 
{
    public int minPathSum(int[][] grid) 
    {
        if(grid == null || grid.length == 0)
            return 0;
        
        int rows = grid.length;
        int columns = grid[0].length;
        
        boolean[][] visited = new boolean[rows][columns];
        int[][] directions = { {1, 0}, {0, 1} };
      
        Queue<int[]> queue = new PriorityQueue<int[]>((a,b) -> a[2] - b[2]);
        queue.offer(new int[]{0, 0, grid[0][0]});

        while(!queue.isEmpty())
        {
            int[] point = queue.poll();
            
            for(int[] direction : directions)
            {
                int x = point[0] + direction[0];
                int y = point[1] + direction[1];
             
                if(x >= rows || y >= columns || visited[x][y] == true)
                    continue;

                visited[x][y] = true;
                int next[] = new int[]{x, y, point[2] + grid[x][y]};
               
                if(x == rows-1 &&  y == columns-1){
                    return next[2];
                }
                queue.offer(next);
            }
            
        }
        
        return grid[0][0];
    }
}
```

Reference

https://java2blog.com/breadth-first-search-in-java/


