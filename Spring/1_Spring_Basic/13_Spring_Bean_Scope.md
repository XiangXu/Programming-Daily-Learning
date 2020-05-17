# Spring Bean Scope

The scope of a bean defines the life cycle and visibility of that bean in contexts in the contexts in which it is used.

The latest version of Spring framework defines 6 types of scopes:

* singleton
* prototype
* request
* session
* application
* websocket

## Singleton Scope

Defining a bean with singleton scope means the container creates a single instance of that bean, and all requests for that bean name will return the same object, which is cached. Any modifications to the object will be reflected in all references to the bean. This scope is the default value if no other scope is specified.

```java
@Bean
@Scope("singleton")
public Person personSingleton() {
    return new Person();
}

@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
```

## Prototype Scope

A bean with prototype scope will return different instance every time it is requested from the container.

```java
@Bean
@Scope("prototype")
public Person personPrototype() {
    return new Person();
}

@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
```

## Request Scope

The request scope creates a bean instance for a single HTTP request.

The **proxyMode** attribute is necessary because at the moment of the instantiation of the web application context, there is no active request. Spring will create a proxy to be injected as a dependency, and instantiate the target bean when it is needed in a request. 

```java
@Bean
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public HelloMessageGenerator requestScopedBean() {
    return new HelloMessageGenerator();
}

@Bean
@RequestScope
public HelloMessageGenerator requestScopedBean() {
    return new HelloMessageGenerator();
}

@Controller
public class ScopesController {
    @Resource(name = "requestScopedBean")
    HelloMessageGenerator requestScopedBean;
 
    @RequestMapping("/scopes/request")
    public String getRequestScopeMessage(final Model model) {
        model.addAttribute("previousMessage", requestScopedBean.getMessage());
        requestScopedBean.setMessage("Good morning!");
        model.addAttribute("currentMessage", requestScopedBean.getMessage());
        return "scopesExample";
    }
}
```

## Session Scope

The session scope creates a bean instance for a an HTTP session.

```java
@Bean
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public HelloMessageGenerator sessionScopedBean() {
    return new HelloMessageGenerator();
}

@Bean
@SessionScope
public HelloMessageGenerator sessionScopedBean() {
    return new HelloMessageGenerator();
}

@Controller
public class ScopesController {
    @Resource(name = "sessionScopedBean")
    HelloMessageGenerator sessionScopedBean;
 
    @RequestMapping("/scopes/session")
    public String getSessionScopeMessage(final Model model) {
        model.addAttribute("previousMessage", sessionScopedBean.getMessage());
        sessionScopedBean.setMessage("Good afternoon!");
        model.addAttribute("currentMessage", sessionScopedBean.getMessage());
        return "scopesExample";
    }
}
```

## Application Scope

The application scope creates the bean instance for the lifecycle of a ServletContext.

This is similar to the singleton scope but there is a very important difference with regards to the scope of the bean.

When beans are application scoped the same instance of the bean is shared across multiple servlet-based applications running in the same ServletContext, while singleton-scoped beans are scoped to a single application context only.

```java
@Bean
@Scope(
  value = WebApplicationContext.SCOPE_APPLICATION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public HelloMessageGenerator applicationScopedBean() {
    return new HelloMessageGenerator();
}

@Bean
@ApplicationScope
public HelloMessageGenerator applicationScopedBean() {
    return new HelloMessageGenerator();
}

@Controller
public class ScopesController {
    @Resource(name = "applicationScopedBean")
    HelloMessageGenerator applicationScopedBean;
 
    @RequestMapping("/scopes/application")
    public String getApplicationScopeMessage(final Model model) {
        model.addAttribute("previousMessage", applicationScopedBean.getMessage());
        applicationScopedBean.setMessage("Good afternoon!");
        model.addAttribute("currentMessage", applicationScopedBean.getMessage());
        return "scopesExample";
    }
}
```

## WebSocket Scope

WebSocket-scoped beans when first accessed are stored in the WebSocket session attributes. The same instance of the bean is then returned whenever that bean is accessed during the entire WebSocket session.

We can also say that it exhibits singleton behavior but limited to a WebSocket session only.

```java
@Bean
@Scope(scopeName = "websocket", proxyMode = ScopedProxyMode.TARGET_CLASS)
public HelloMessageGenerator websocketScopedBean() {
    return new HelloMessageGenerator();
}
```

Reference:

https://www.baeldung.com/spring-bean-scopes