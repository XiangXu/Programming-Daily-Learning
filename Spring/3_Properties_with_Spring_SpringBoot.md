# Properties with Spring and Spring Boot

**@PropertySource** is a convenient annotation for including PropertySource to Spring's Environment and allowing to inject prpoerties via **@Value** into class attributes.

**@PropertySource is used together with @Configuration to define properties configuration**.

## Register a Properties File via Java Annotations
```java
@Configuration
@PropertySource("classpath:foo.properties")
public class PropertiesWithJavaConfig {
    //...
}

// dynamically select the right file at runtime
@PropertySource({ 
  "classpath:persistence-${envTarget:mysql}.properties"
})
```

## Defining Multiple Property Locations
```java
@PropertySource("classpath:foo.properties")
@PropertySource("classpath:bar.properties")
public class PropertiesWithJavaConfig {
    //...
}

@PropertySources({
    @PropertySource("classpath:foo.properties"),
    @PropertySource("classpath:bar.properties")
})
public class PropertiesWithJavaConfig {
    //...
}
```

## Register a Properties File in XML

```xml
<context:property-placeholder location="classpath:foo.properties" />
<context:property-placeholder location="classpath:foo.properties, classpath:bar.properties"/>
```

* The foo.properties file should be placed under /src/main/resources so that it will be available on the classpath at runtime.
* ignore-unresolvable=”true” allows the resolution mechanism to pass to others in the context without throwing an exception.
  
## Using / Injecting Properties

```java
@Value( "${jdbc.url}" )
private String jdbcUrl;
```

```xml
<bean id="dataSource">
  <property name="url" value="${jdbc.url}" />
</bean>
```

we can obtain the value using Environment API:
```java
@Autowired
private Environment env;
...
dataSource.setUrl(env.getProperty("jdbc.url"));
```

**Using property-placeholder will not expose the properties to the Spring Environment – this means that retrieving the value like this will not work – it will return null**.

## Properties With Spring Boot

### application.properties - the Default Property File

Boot applies its typical convention over configuration approach to property files. This means we can simply put an "application.properties" file in our "src/main/resources" directory, and it will be auto-detected.

**By using default file, we don't have to explicitly register a PropertySource, or even provide a path to a property file**.

Configure a different file at runtime:
```
java -jar app.jar --spring.config.location=classpath:/another-location.properties
```

### Environment Specific Properties File

We can simply define an “application-environment.properties” file in the “src/main/resources” directory – and then set a Spring profile with the same environment name.

If define a "staging" environment, that means we'll have to define a staging profile and then application-staging.properties.

### The @TestPropertySource Annotation
```java
@RunWith(SpringRunner.class)
@TestPropertySource("/foo.properties")
public class FilePropertyInjectionUnitTest {
 
    @Value("${foo}")
    private String foo;
 
    @Test
    public void whenFilePropertyProvided_thenProperlyInjected() {
        assertThat(foo).isEqualTo("bar");
    }
}
// Specify names and values directly
@RunWith(SpringRunner.class)
@TestPropertySource(properties = {"foo=bar"})
public class PropertyInjectionUnitTest {
 
    @Value("${foo}")
    private String foo;
 
    @Test
    public void whenPropertyProvided_thenProperlyInjected() {
        assertThat(foo).isEqualTo("bar");
    }
}

@RunWith(SpringRunner.class)
@SpringBootTest(
  properties = {"foo=bar"}, classes = SpringBootPropertiesTestApplication.class)
public class SpringBootPropertyInjectionIntegrationTest {
 
    @Value("${foo}")
    private String foo;
 
    @Test
    public void whenSpringBootPropertyProvided_thenProperlyInjected() {
        assertThat(foo).isEqualTo("bar");
    }
}
```

## Hierarchical Properties

If you have properties that are grouped together, we can make use of @ConfigurationProperties
```
database.url=jdbc:postgresql:/localhost:5432/instance
database.username=foo
database.password=bar
And then let's use the annota
```

```java
@ConfigurationProperties(prefix = "database")
public class Database {
    String url;
    String username;
    String password;
}
```

## Randomization of Property Values
```
random.number=${random.int}
random.long=${random.long}
random.uuid=${random.uuid}
```

Reference

http://zetcode.com/spring/propertysource/

https://www.baeldung.com/properties-with-spring
