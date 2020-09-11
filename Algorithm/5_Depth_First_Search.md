# Depth First Search

**In DFS, you start with an un-visited node and start picking an adjacent node, until you have no choice, then you backtrack until you have another choice to pick a node, if not, you selected another un-visited node**.

DFS can be implementd in two ways:

* Recursive
* Iterative

## Iterative

Depath-first search can be implemented using iterative apprach.

![alt text](https://www.java2blog.com/wp-content/uploads/2015/12/Screen2BShot2B2015-12-272Bat2B11.19.482Bpm-1.png)

We start with node 40. It then visits node 20, node 50, node 70 respectively as they are directly connected. After that, it backtracks to node 20 and visited node 60, node 30 and node 10 respectively.

```java
	// Iterative DFS using stack
	public  void dfsUsingStack(Node node)
	{
		Stack<Node> stack=new  Stack<Node>();
		stack.add(node);
		while (!stack.isEmpty())
		{
			Node element=stack.pop();
			if(!element.visited)
			{
				System.out.print(element.data + " ");
				element.visited=true;
			}
			
			List<Node> neighbours=element.getNeighbours();
			for (int i = 0; i < neighbours.size(); i++) {
				Node n=neighbours.get(i);
				if(n!=null && !n.visited)
				{
					stack.add(n);
				}
			}
		}
	}
```

## Recursive

Depth first search can be implemented using recurison too. We don't need to maintain external stack, it will be taken by recurison.

```java
// Recursive DFS
public  void dfs(Node node)
{
    System.out.print(node.data + " ");
    List neighbours=node.getNeighbours();
    node.visited=true;
    for (int i = 0; i < neighbours.size(); i++) {
        Node n=neighbours.get(i);
        if(n!=null && !n.visited)
        {
            dfs(n);
        }
    }
}
```

## Java DFS Example

An image is represented by a 2-D array of integers, each integer representing the pixel value of the image (from 0 to 65535).

Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value newColor, "flood fill" the image.

To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color as the starting pixel), and so on. Replace the color of all of the aforementioned pixels with the newColor.

At the end, return the modified image.

```
Input: 
image = [[1,1,1],[1,1,0],[1,0,1]]
sr = 1, sc = 1, newColor = 2
Output: [[2,2,2],[2,2,0],[2,0,1]]
Explanation: 
From the center of the image (with position (sr, sc) = (1, 1)), all pixels connected 
by a path of the same color as the starting pixel are colored with the new color.
Note the bottom corner is not colored 2, because it is not 4-directionally connected
to the starting pixel.

Before:
[1,1,1]
[1,1,0]
[1,0,1]
    
After:
[2,2,2],
[2,2,0],
[2,0,1]   
```

Iterative

```java
class Solution 
{
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) 
    {
        if(image == null || image.length == 0 || image[0].length == 0)
            return image;
        
        int startColor = image[sr][sc];
        
        if(startColor == newColor)
            return image;
        
        int rows = image.length;
        int columns = image[0].length;
        
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{sr, sc});
        int[][] directions = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
        
        while(!stack.isEmpty())
        {
            int[] point = stack.pop();
            int currRow = point[0];
            int currColumn = point[1];
            
            if(currRow < 0 || currColumn < 0 || currRow >= rows || currColumn >= columns)
                continue;
            
            int currColor = image[currRow][currColumn];
            if(currColor == startColor)
            {
                image[currRow][currColumn] = newColor;
                for(int[] direction : directions)
                {
                    int x = direction[0] + currRow;
                    int y = direction[1] + currColumn;
                    
                    stack.push(new int[]{x, y});
                }
            }
            
        }
        
        return image;
    }
      
}
```

Recrusive

```java
class Solution 
{
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) 
    {
        if(image == null || image.length == 0 || image[0].length == 0)
            return image;
        
         if(image[sr][sc] == newColor) 
            return image;
        
        dfs(image, sr, sc, image[sr][sc], newColor);
        
        return image;
    }
     
    private void dfs(int[][] image, int sr, int sc, int currColor, int newColor)
    {
        if(sr < 0 || sc < 0 || sr >= image.length || sc >= image[0].length || image[sr][sc] != currColor)
            return;
        
        image[sr][sc] = newColor;
        
        dfs(image, sr+1, sc, currColor, newColor);
        dfs(image, sr-1, sc, currColor, newColor);
        dfs(image, sr, sc+1, currColor, newColor);
        dfs(image, sr, sc-1, currColor, newColor);
    }
}
```

Reference

https://java2blog.com/depth-first-search-in-java/