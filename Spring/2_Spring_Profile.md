# Spring Profile

**Spring Profiles allow us to map our bean to different profiles - for example, dev, test, prod**.

## Use @Profile on a Bean

If we have a bean that should only be active during development, but not deployed in production.

```java
@Component
@Profile("dev")
public class DevDatasourceConfig{}

// Exclude example
@Component
@Profile("!dev")
public class DevDatasourceConfig{}
```

```xml
<beans profile="dev">
    <bean id="devDatasourceConfig"
      class="org.baeldung.profiles.DevDatasourceConfig" />
</beans>
``` 

## Set Profiles

Any Bean does not specify a profile belongs to "default" profile.

“spring.profiles.default” can be used to set the default profile.

### Programmatically via WebApplicationInitializer

**WebApplicationInitializer**  can be used to configure the ServletContext programmatically.

```java
@Configuration
public class MyWebApplicationInitializer implements WebApplicationInitializer {
 
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
  
        servletContext.setInitParameter(
          "spring.profiles.active", "dev");
        }
}
```

### Programmatically via ConfigurableEnvironment

```java
@Autowired
private ConfigurableEnvironment env;
...
env.setActiveProfiles("someProfile");
```

### Context Parameter in web.xml

```xml
<context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>/WEB-INF/app-config.xml</param-value>
</context-param>
<context-param>
    <param-name>spring.profiles.active</param-name>
    <param-value>dev</param-value>
</context-param>
```

### JVM System Parameter 

```
-Dspring.profiles.active=dev
```

### Unix Environment

```
export spring_profiles_active=dev
```

### Maven Profile

```xml
<profiles>
    <profile>
        <id>dev</id>
        <activation>
            <activeByDefault>true</activeByDefault>
        </activation>
        <properties>
            <spring.profiles.active>dev</spring.profiles.active>
        </properties>
    </profile>
    <profile>
        <id>prod</id>
        <properties>
            <spring.profiles.active>prod</spring.profiles.active>
        </properties>
    </profile>
</profiles>
```

Its value will be used to replace the @spring.profiles.active@ placeholder in application.properties:

```
spring.profiles.active=@spring.profiles.active@
```

Now, we need to enable resource filtering in pom.xml:

```xml
<build>
    <resources>
        <resource>
            <directory>src/main/resources</directory>
            <filtering>true</filtering>
        </resource>
    </resources>
    ...
</build>
```

And append a -P parameter to switch which Maven profile will be applied:

```
mvn clean package -Pprod
```

### @ActiveProfile in Tests

```
@ActiveProfiles("dev")
```