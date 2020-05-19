# Spring @RequestParam Annotation

**Simply put, we can use @RequestParam to extract query parameters, form parameters and even files from the request**.

## A Simple Mapping

```java
@GetMapping("/api/foos")
@ResponseBody
public String getFoos(@RequestParam String id) {
    return "ID: " + id;
}
```

## Specifying the Request Parameter Name

Sometimes we want these to be different, though. **We can configure @RequestParam name using name attribute**:

```java
@PostMapping("/api/foos")
@ResponseBody
public String addFoo(@RequestParam(name = "id") String fooId, @RequestParam String name) { 
    return "ID: " + fooId + " Name: " + name;
}
```

## Making an Optional Reqeust Parameter

**Method parameters annotated with @RequestParam are required by default**. We can configure our @ReqeustParam to be optional, though with **required** attribute.

```java
@GetMapping("/api/foos")
@ResponseBody
public String getFoos(@RequestParam(required = false) String id) { 
    return "ID: " + id;
}

// Java 8 optional
@GetMapping("/api/foos")
@ResponseBody
public String getFoos(@RequestParam Optional<String> id){
    return "ID: " + id.orElseGet(() -> "not provided");
}
```

## A Default Value for the Request Parameter

We can set default value to **@RequestParam** by using the **defaultValue** attribute:

```java
@GetMapping("/api/foos")
@ResponseBody
public String getFoos(@RequestParam(defaultValue = "test") String id) {
    return "ID: " + id;
}
```
This is like required=false, in that the user no longer needs to supply the parameter.

## Mapping All Parameters

**We can also have multiple parameters without defining their names or count by using using map**:

```java
@PostMapping("/api/foos")
@ResponseBody
public String updateFoos(@RequestParam Map<String,String> allParams) {
    return "Parameters are " + allParams.entrySet();
}
```

## Mapping a Multi-Value Parameter

```java
@GetMapping("/api/foos")
@ResponseBody
public String getFoos(@RequestParam List<String> id) {
    return "IDs are " + id;
}
```

**Spring MVC will map a comma-delimited id parameter**:

```java
http://localhost:8080/api/foos?id=1,2,3
----
IDs are [1,2,3]
```

Or a list of seperate id parameters:
```
http://localhost:8080/api/foos?id=1&id=2
----
IDs are [1,2]
```

## @RequestParam vs @PathVariable

**@RequestParams extract values from the query string**.

**@PathVariables extract values from the URI path**.

Both @RequestParam and @PathVariable can be optional. We should be careful when making @PathVariable optional to avoid conflicts in paths.