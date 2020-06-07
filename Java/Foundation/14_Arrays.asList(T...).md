# Arrays.asList(T...)

1. **asList** method is one of the utility methods of **Array** class, it is static method thats why we can call this method by its name.

2. Now here is twist, **please note that this method doesn't create new ArrayList object**, it just returns a List reference to existing **Array** object(so now after using asList method, two references to existing **Array** object gets created).

3. This is the reason, all methods operate on **List** object, may **NOT** work on this Array object using **List** reference like for example, **Array** size is fixed in length, hence you obviously can not add or remove element from **Array** object using this **List** reference(like list.add(10) or list.remove(10) else it will throw **UnsupportOperationException**).

4. Any change you are doing using list reference **will be reflectd in existing Array object**(as you are operating on existing Array object by using list reference).

5. Note that, in Java 8, parameter must be Integer[] and not int[]. **Arrays.asList() of an int array returns a list with a single element**. The reason is because **generics only work with reference types**. That means List<int> is not allowed, so Array.asList cannot return List<int>. Instead Array.asList interprets its input as a single object and returns a list with that single element. 


Reference

https://stackoverflow.com/questions/16748030/difference-between-arrays-aslistarray-and-new-arraylistintegerarrays-aslist
