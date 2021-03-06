# Comparator and Comparable in Java

## Setting Up the Example

Let's take an example of football team - where we want to line up the players by their rankings. 

We will start by creating a simple Player class:

```java
public class Player {
    private int ranking;
    private String name;
    private int age;
     
    // constructor, getters, setters  
}
```

Next, let's create a PlayerSorter class to create our collection and make an attempt to sort using Collections.sort.

```java
public static void main(String[] args) {
    List<Player> footballTeam = new ArrayList<>();
    Player player1 = new Player(59, "John", 20);
    Player player2 = new Player(67, "Roger", 22);
    Player player3 = new Player(45, "Steven", 24);
    footballTeam.add(player1);
    footballTeam.add(player2);
    footballTeam.add(player3);
 
    System.out.println("Before Sorting : " + footballTeam);
    Collections.sort(footballTeam);
    System.out.println("After Sorting : " + footballTeam);
}
```

Here, as expected, this results in a compile - error:

```
The method sort(List<T>) in the type Collections 
  is not applicable for the arguments (ArrayList<Player>)
```

Let's understand what we did wrong here.

## Comparable

As the name suggests, **Comparable is an interface defining a strategy of comparing an object with another object of the same type. This is called class's "natural ordering"**.

Accordingly, in order to be able to sort - we define our Player object as comparable by implementing the Comparable interface:

```java
public class Player implements Comparable<Player> {
     
    //...
    @Override
    public int compareTo(Player otherPlayer) {
        return (this.getRanking() - otherPlayer.getRanking());
    }
}
```

**The sorting order is decided by the return value of the compareTo() method**.

The method returns a number indicating whether the object being comparsed is less than, equal or greater than the object being passed as argument.

Finally, when we run our PlayerSorter now, we can see our Players sorted by their ranking:

```
Before Sorting : [John, Roger, Steven]
After Sorting : [Steven, John, Roger]
```

Now that we have a clear understanding of natural ordering with Comparable, let's see how we can use other types of ordering, in more flexible manner than directly implementing interface.

## Comparator

**The Comparator interface defines a compare(arg1, arg2) method** with two arguments which represent compared objects works similarly to Comparable.compareTo() method.

### Creating Comparators

To create a Comparator, we have to implement the Comparator interface.

In our first example, we will create a comparator to use the ranking attribute of Player to sort the players:

```java
public class PlayerRankingComparator implements Comparator<Player> {
  
    @Override
    public int compare(Player firstPlayer, Player secondPlayer) {
       return (firstPlayer.getRanking() - secondPlayer.getRanking());
    }
}
```

Similarly, we can create a Comparator to use the age attribute of Player to sort the players:

```java
public class PlayerAgeComparator implements Comparator<Player> {
    @Override
    public int compare(Player firstPlayer, Player secondPlayer) {
       return (firstPlayer.getAge() - secondPlayer.getAge());
    }
}
```

### Comparators in Action

```java
PlayerRankingComparator playerComparator = new PlayerRankingComparator();
Collections.sort(footballTeam, playerComparator);

// Before Sorting : [John, Roger, Steven]
// After Sorting by ranking : [Steven, John, Roger]

PlayerAgeComparator playerComparator = new PlayerAgeComparator();
Collections.sort(footballTeam, playerComparator);

// Before Sorting : [John, Roger, Steven]
// After Sorting by age : [Roger, John, Steven]
```

## Java 8 Comparators

Java 8 provides new ways of defining Comparators by using lambda expressions and the comparing() static factory method.

```java
Comparator<Player> byRanking = (Player player1, Player player2) -> player1.getRanking() - player2.getRanking();
```

## Comparator vs Comparable

**The Comparable interface is a good choice when used for defining the default ordering or, in other words, if it's the main way of comparing objects**.

Then, we must ask ourselves why use a Comparator if we already have Comparable?

There are several reasons why:

* Sometimes, we can't modify the source code of the class whose objects we want to sort, thus making the use of Comparable impossible

* Using Comparators allows us to avoid adding additional code to our domain classes

* We can define multiple different comparison strategies which isn't possible when using Comparable



Reference

https://www.baeldung.com/java-comparator-comparable