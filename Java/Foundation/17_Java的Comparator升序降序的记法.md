# Java的Comparator升序降序的记法

假如现在有一个装了Cat的list,Cat有name和age的属性,现在要把Cat按name的长度倒序:

```java
  public static void main(String[] args) {
        List<Cat> list = new ArrayList<>(Arrays.asList( new Cat("two", 17), new Cat("three", 15), new Cat("four", 16)));
        Collections.sort(list, new Comparator<Cat>() {
            @Override
            public int compare(Cat cat1, Cat cat2) {
                return cat2.getName().length() - cat1.getName().length();
            }
        });
        list.forEach((Cat c) -> System.out.println(c.getName()+ ":" + c.getAge()));
    }
```

在compare的方法中,有时候会不明白到底什么时候是正序,什么时候是倒序,有个小小的规律可以参考一下:

* 当 compare方法 返回 > 0的时候, 表示cat1 排在 cat2 后面 ;
* 当 compare方法 返回 < 0 的时候, 表示cat1 排在 cat2 前面;
* 当 compare 方法 返回 0 的时候, 表示cat1 和 cat2 位置不变;

或者你可以直接理解为当 cat1 -cat2 的时候是正序,cat2 - cat1为倒序


Reference

作者：黄二的NPE
链接：https://www.jianshu.com/p/1ff4f7605a63
来源：简书
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。