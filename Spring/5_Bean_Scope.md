## Bean Scope

### The Singleton Scope(Default)

This scopes the bean definition to a single instance per Spring IoC container. T**his single instance is stored in a cache such single beans, and all subsequent requests and references for that named bean return the cached object.**

### The Prototype Scope

**If the scope is set to prototype, the spring IoC container creates a new bean instance of the object everytime a request for that specific bean is made.**

**As a rule, use the prototype scope for all state-full beans and the singleton scope for stateless beans.**

Scopes below are only valid in the context of web-aware Spring Application Context.

### Request

This scopes a bean definition to an HTTP request.

### Session

This scopes a bean definition to an HTTP session.

### Global-Session

This scopes a bean definition to a global HTTP session.