# Understanding of getBean()

**getBean()**: This is responsible for retriving a bean instance from Spring container.

## Retrieving Bean By Name

```java
Lion lion = (Tiger) context.getBean("lion");
```

**After retrieving the bean, we have to cast it to the desired type. This may produce another exception if the returned bean has a different type than we expected**.

## Retrieving Bean By Name and Type
```java
Lion lion = context.getBean("lion", Lion.class);
```

Compared to the previous method, this one is safer because we get the information about type mismatch instantly.

## Retrieving Bean By Type
```java
Lion lion = context.getBean(Lion.class);
```

In this case, **we need to pay special attention to a potentially ambiguous outcome**:
```java
assertThrows(NoUniqueBeanDefinitionException.class, () -> 
    context.getBean(Animal.class));
}
```

Reference

https://www.baeldung.com/spring-getbean

