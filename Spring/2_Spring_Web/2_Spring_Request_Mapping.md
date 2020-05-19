# Spring RequestMapping

**@RequestMapping is used to map web requests to Spring Controller methods**.

## @RequestMapping Basics

```java
@RequestMapping(
  value = "/ex/foos", 
  method = GET, 
  headers = "Accept=application/json")
@ResponseBody
public String getFoosAsJsonFromBrowser() {
    return "Get some Foos with Header Old";
}
```

The HTTP **method parameter has no default**.

## @RequestMapping Consumes and Produces

Http header中有两个概念：

**ContentType 用来告诉服务器当前发送的数据是什么格式**

**Accept 用来告诉服务器，客户端能认识哪些格式,最好返回这些格式中的其中一种**

**consumes 用来限制ContentType**

**produces 用来限制Accept**

Mapping **media types produced by a controller** method is worth special attentation. We can map a request based on its *Accept* header via *@RequestMapper* header attributes.

```java
@RequestMapping(
  value = "/ex/foos", 
  method = GET, 
  headers = "Accept=application/json")
@ResponseBody
public String getFoosAsJsonFromBrowser() {
    return "Get some Foos with Header Old";
}
```

Spring @RequestMapping annotation now **has the produces and consumes attributes**.

```java
@RequestMapping(
  value = "/ex/foos", 
  method = RequestMethod.GET, 
  produces = { "application/json", "application/xml" }
)
@ResponseBody
public String getFoosAsJsonFromREST() {
    return "Get some Foos with Header New";
}
```

## RequestMapping With Path Variables

**Parts of mapping URI(Uniform Resource Identifer) can be bound to variable via the @PathVariable annotation**.

```java
@RequestMapping(value = "/ex/foos/{id}", method = GET)
@ResponseBody
public String getFoosBySimplePathWithPathVariable(
  @PathVariable("id") long id) {
    return "Get a specific Foo with id=" + id;
}

@RequestMapping(value = "/ex/foos/{id}", method = GET)
@ResponseBody
public String getFoosBySimplePathWithPathVariable(
  @PathVariable String id) {
    return "Get a specific Foo with id=" + id;
}

// curl http://localhost:8080/spring-rest/ex/foos/1/bar/2
@RequestMapping(value = "/ex/foos/{fooid}/bar/{barid}", method = GET)
@ResponseBody
public String getFoosBySimplePathWithPathVariables
  (@PathVariable long fooid, @PathVariable long barid) {
    return "Get a specific Bar with id=" + barid + 
      " from a Foo with id=" + fooid;
}

// @PathVariable With RegEx
@RequestMapping(value = "/ex/bars/{numericId:[\\d]+}", method = GET)
@ResponseBody
public String getBarsBySimplePathWithPathVariable(
  @PathVariable long numericId) {
    return "Get a specific Bar with id=" + numericId;
}
```

## RequestMapping with Request Parameters

@RequestMapping allows easy **mapping of URL parameters with the @RequestParam annotation**.

```java
// http://localhost:8080/spring-rest/ex/bars?id=100

@RequestMapping(value = "/ex/bars", method = GET)
@ResponseBody
public String getBarBySimplePathWithRequestParam(
  @RequestParam("id") long id) {
    return "Get a specific Bar with id=" + id;
}

@RequestMapping(
  value = "/ex/bars", 
  params = { "id", "second" }, 
  method = GET)
@ResponseBody
public String getBarBySimplePathWithExplicitRequestParams(
  @RequestParam("id") long id) {
    return "Narrow Get a specific Bar with id=" + id;
}
```

## RequestMapping - Multiple Paths Mapped to the Same Controller Method

```java
@RequestMapping(
  value = { "/ex/advanced/bars", "/ex/advanced/foos" }, 
  method = GET)
@ResponseBody
public String getFoosOrBarsByPath() {
    return "Advanced - Get some Foos or Bars";
}
```

## RequestMapping - Multiple HTTP Request Methods to the Same Controller Method

```java
@RequestMapping(
  value = "/ex/foos/multiple", 
  method = { RequestMethod.PUT, RequestMethod.POST }
)
@ResponseBody
public String putAndPostFoos() {
    return "Advanced - PUT and POST within single method";
}
```

### Request - a Fallback for All Requests

```java
@RequestMapping(
  value = "*", 
  method = { RequestMethod.GET, RequestMethod.POST ... })
@ResponseBody
public String allFallback() {
    return "Fallback for All Requests";
}
```

## Ambiguous Mapping Error

The ambiguous mapping error occurs when Spring evaluates two or more request mappings to be the same for different controller methods.

The code snippet below will not result in ambiguous mapping error because both methods return different content types:

```java
@GetMapping(value = "foos/duplicate", produces = MediaType.APPLICATION_XML_VALUE)
public String duplicateXml() {
    return "<message>Duplicate</message>";
}
     
@GetMapping(value = "foos/duplicate", produces = MediaType.APPLICATION_JSON_VALUE)
public String duplicateJson() {
    return "{\"message\":\"Duplicate\"}";
}
```

Reference:

https://www.baeldung.com/spring-requestmapping