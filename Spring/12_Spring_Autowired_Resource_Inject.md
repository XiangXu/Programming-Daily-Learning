# Wiring in Spring: @Autowired, @Resource and @Inject

## The @Resource Annotation

The **@Resource** annotation is part of JSR-250 annotation collection and is packed with Jakrata EE.

This annotation has the following execution paths, listed by precedence:

1. Match by Name
2. Match by Type
3. Match by Qualifier 

```java
@Resource(name="namedFile")
private File defaultFile;

@Configuration
public class ApplicationContextTestResourceNameType {
 
    @Bean(name="namedFile")
    public File namedFile() {
        File namedFile = new File("namedFile.txt");
        return namedFile;
    }
}
```

These execution paths are applicable to both setter and field injection.


## The @Injection Annotation

The **@Injection** belongs to the JSR-330 annotation collection.

This annotation has the following execution paths, listed by precedence:

1. Match by Type
2. Match by Qualifer
3. Match by Name

These execution paths are applicable to both setter and field injection. In order to access the @Inject annotation, the javax.inject library has to be declared as a Gradle or Maven dependency.

## The @Autowired Annotation

The behaviour of @Autowired annotation is similar to @Injection annotation. The only different is that **the @Autowired annotation is part of Spring framework**. **@Autowired supports required = false**. This annotation has the same execution paths as the @Inject annotation, listed in order of precedence:

1. Match by Type
2. Match by Qualifier
3. Match by Name


## Applying These Annotations

### Application-Wide use of Singletons Through Polymorphism

If the design is such that application behaviors are based on implementations of an interface or an abstract class, and these behaviors are used throughout application, then user either the @Inject or @Autowired annotation.

The benefit of this apporach is that when application is upgraded, or a patch needs to be applied in order by fix a bug; then classes can be swapped out with minimal negative impact to the overall application behavior, the primary default execution path is match-by-type.

### Fine-Grained(细粒性分布) Application Behavior Configuration Through Polymorphism

If the design is such that the application has complex behavior, each behavior is based on different interfaces/abstract classes, and usage of each of these implementations varies across the application, then use the @Resource annotation. In this scenario, the primary default execution path is match-by-name.

### Dependency Injection Should be Handled Solely by the Jakarta EE Platform

If there is a design mandate for all dependencies to be injected by the Jakarta EE Platform and not Spring, then the choice is between the @Resource annotation and the @Inject annotation. You should narrow down the final decision between the two annotations, based on which default execution path is required.

### Dependency Injection Should be Handled Solely by the Spring Framework

If the mandate is for all dependencies to be handled by the Spring Framework, the only choice is the @Autowired annotation.


### @Resource vs @Autowired

Both @Autowired (or @Inject) and @Resource work equally well. But there is a conceptual difference or a difference in the meaning

* @Resource means get me a known resource by name. The name is extracted from the name of the annotated setter or field, or it is taken from the name-Parameter.
  
* @Inject or @Autowired try to wire in a suitable other component by type. So, basically these are two quite distinct concepts. Unfortunately the Spring-Implementation of @Resource has a built-in fallback, which kicks in when resolution by-name fails. In this case, it falls back to the @Autowired-kind resolution by-type. While this fallback is convenient, IMHO it causes a lot of confusion, because people are unaware of the conceptual difference and tend to use @Resource for type-based autowiring.

### Difference between @Qualifier and @Resource

@Autowired can be used alone . If it is used alone , it will be wired by type . So problems arises if more than one bean of the same type are declared in the container as @Autowired does not know which beans to use to inject. As a result , use @Qualifier together with @Autowired to clarify which beans to be actually wired by specifying the bean name (wired by name)

@Resource is wired by name too . So if @Autowired is used together with @Qualifier , it is the same as the @Resource.

The difference are that @Autowired and @Qualifier are the spring annotation while @Resource is the standard java annotation (from JSR-250) . Besides , @Resource only supports for fields and setter injection while @Autowired supports fields , setter ,constructors and multi-argument methods injection.

It is suggested to use @Resource for fields and setter injection. Stick with @Qualifier and @Autowired for constructor or a multi-argument method injection.

Reference:

https://www.baeldung.com/spring-annotations-resource-inject-autowire

https://stackoverflow.com/questions/4093504/resource-vs-autowired

https://stackoverflow.com/questions/9106416/difference-between-qualifier-and-resource
