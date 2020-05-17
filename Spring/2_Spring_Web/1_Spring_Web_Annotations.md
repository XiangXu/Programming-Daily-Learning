# Spring Web Annotations

## @RequestMapping

**RequestMapping** marks request handler method inside @Controller classes. It can be configured using:

* **path**: or its alises, **name**, and **value**: which URL the method is mapped to.
* **method**: compatible HTTP methods.
* **params**: filters requests based on presence, absence, or value of HTTP parameters.
* **headers**: filters requests based on presence, absence, or value of HTTP headers.
* **consumes**: which media types the method can consume in the HTTP request body.
* **produces**: which media types the method can produce in the HTTP response body.

You can provide **default settings for all handler methods in a @Conrtoller class** if we apply this annotation on the class level. The only **exception is the URL which Spring won't override** with method levels settings but appends the two path parts.

```java
@Controller
@RequestMapping(value = "/vehicles", method = RequestMethod.GET)
class VehicleController {
 
    @RequestMapping("/home")
    String home() {
        return "home";
    }
}
```

## @RequestBody

**@RequestBody** marks request handler methods inside @Controller classes.

```java
@PostMapping("/save")
void saveVehicle(@RequestBody Vehicle vehicle) {
    // ...
}
```

The deserialization is automatic and depends on the content type of the request.

## @PathVariable

**@PathVariable** indicates that **a method argument is bound to a URL template variable**.

```java
@RequestMapping("/{id}")
Vehicle getVehicle(@PathVariable("id") long id) {
    // ...
}

@RequestMapping("/{id}")
Vehicle getVehicle(@PathVariable(required = false) long id) {
    // ...
}
```

## @RequestParam

We use **@RequestParam** for **accessing HTTP request parameters**.

```java
@RequestMapping("/buy")
Car buyCar(@RequestParam(defaultValue = "5") int seatCount) {
    // ...
}
```

**Other HTTP requests parts we can access: cookies and headers by using @CookieValue and @RequestHeader**.

## @Path variable vs @RequestParam

* @PathVariable 是从一个URI模板里面来取值（/后面?之前）

* @RequestParam 是从request里面取值（？之后）

```
http://localhost:8080/springmvc/hello/101?param1=10&param2=20
```

```java
@RequestMapping("/hello/{id}")
    public String getDetails(@PathVariable(value="id") String id,
    @RequestParam(value="param1", required=true) String param1,
    @RequestParam(value="param2", required=false) String param2){
}
```

## @ResponseBody

If we mark a request handler method with **@ResponseBody**, **Spring treats the result of the method as the response itself**.

@ResponseBody，一般是使用在单独的方法上的，需要哪个方法返回json数据格式，就在哪个方法上使用，具有针对性。

@RestController，一般是使用在类上的，它表示的意思其实就是结合了@Controller和@ResponseBody两个注解，

## @ExceptionHandler

We can use this annotation to declare **custom error handler method**. Spring calls this method when a request handler method throws any of specificed exception:

```java
@ExceptionHandler(IllegalArgumentException.class)
void onIllegalArgumentException(IllegalArgumentException exception) {
    // ...
}
```

## @ResponseStatus

**@ResponseStatus** can be used to specify the **desired HTTP status of the response** if we annotate a request handler method with this annotation.

```java
@ExceptionHandler(IllegalArgumentException.class)
@ResponseStatus(HttpStatus.BAD_REQUEST)
void onIllegalArgumentException(IllegalArgumentException exception) {
    // ...
}
```

## @RestController

The **@RestController** **combines @Controller and @ResponseBody**.


Reference: 

https://blog.csdn.net/u010002184/article/details/80386322

https://www.baeldung.com/spring-mvc-annotations