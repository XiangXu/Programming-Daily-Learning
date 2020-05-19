# The Spring @Controller and @RestController Annotations

**@RestController is a convenience annotation that combines @Controller and @ResponseBody** - which eliminates the need to annotate every reqquest handling method of the controller class with the @ResponseBody annotation.

## Spring MVC @Controller

Classic controllers can be annotated with the **@Controller** annotation. This is simply a specilization of the **@Component** class and allows implementation classes to be autodetected through the classpath scanning.

@Controller is typically used in combination with @RequestMapping annotation used on request handling methods.

```java
@Controller
@RequestMapping("books")
public class SimpleBookController {
 
    @GetMapping("/{id}", produces = "application/json")
    public @ResponseBody Book getBook(@PathVariable int id) {
        return findBookById(id);
    }
 
    private Book findBookById(int id) {
        // ...
    }
}
```

**The request handling method is annotated with @ResponseBody. This annotation enables automatic serialization of the return object into the HttpResponse**.

## Spring MVC @RestController

@RestController is a specialized version of the controller. It includes the @Controller and @ResponseBody annotations and as a result, simplifies the controller implementation:

```java
@RestController
@RequestMapping("books-rest")
public class SimpleBookRestController {
     
    @GetMapping("/{id}", produces = "application/json")
    public Book getBook(@PathVariable int id) {
        return findBookById(id);
    }
 
    private Book findBookById(int id) {
        // ...
    }
}
```

**The controller is annotated with the @RestController annotation, therefore the @ResponseBody isn't required**.

Reference:

https://www.baeldung.com/spring-controller-vs-restcontroller

