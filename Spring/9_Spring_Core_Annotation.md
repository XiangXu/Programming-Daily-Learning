# Spring Core Annotations

## DI-Related Annotations

1. **@Autowired**: We can use it to mark a dependency which Spring is going to resolve and inject.
   
2. **@Bean**: It marks a factory method which instantiates a Spring bean. It must be in @Configuraiton classes.
   
3. **@Qualifier**: We use it with @Autowired to provide the bean id or bean name we want to use in ambiguous situations.

4. **@Required**: @Required on setter methods to mark dependencies that we want to populate through XML.

5. **@Value**: We can use it for injecting property value into beans. 

6. **@DependsOn**: Make Spring initialize other bean before the annotated one.

7. **@Lazy**: It can be used to initialize our bean lazily - when we need to create a bean when we request it, not a application startup.

8. **@Lookup**: It tells Spring to return an instance of the method's return type when we invoke it.

9. **@Primary**: We mark the most frequency used bean with it to and @Primary it will be chosen on unqualified injection points.

10. **@Scope**: It defines the scope of a @Component class or a @Bean definition. It can be: singleton, prototype, request, session, globalSession.

## Context Configuration Annotations

1. **@Profile**: If we want Spring to use a @Component or a @Bean method only when a specific profile is active, we can mark it with @Profile.

2. **@Import**: We can use specific @Configuraiton classes without component scanning with this annotation.

3. **@ImportResource**: Import XML configurations.

4. **@PropertySource**: It can define property files for application settings.

5. **@PropertySources**: Use it to specify multiple @PropertySource configurations.

    