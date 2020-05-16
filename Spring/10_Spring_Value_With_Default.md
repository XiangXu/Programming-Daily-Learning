# Using Spring @Value with Defaults

Spring's @Value annotation provides a convenient way to **inject property values into components**. It's also quite useful to **provide sensible defaults for case where a property may not be present**.

## String Defaults

If some.key cannot be resolved, then *StringWithDefaultValue* will be set to default value of "my default value".

```java
@Value("${some.key:my default value}")
private String stringWithDefaultValue;

@Value("${some.key:})"
private String stringWithBlankDefaultValue;
```

## Primitives

```java
@Value("${some.key:true}")
private boolean booleanWithDefaultValue;

@Value("${some.key:42}")
private int intWithDefaultValue
```

## Arrays

```java
@Value("${some.key:one,two,three}")
private String[] stringArrayWithDefaults;
 
@Value("${some.key:1,2,3}")
private int[] intArrayWithDefaults;
```

## Using SpEL

We can use Spring Expression Language to specify an expression and a default.

```java
@Value("#{systemProperties['some.key'] ?: 'my default system property value'}")
private String spelWithDefaultValue;
```