# Example of Camel Properties component problem in Camel 2.16.3
> Demonstrates the problem with @PropertyInject and Spring-Boot in Camel 2.16.3

## Running this example
```
mvn spring-boot:run
```

## The problem...
Using @PropertyInject makes Spring-Boot to add *properties* component twice in the Camel context which gives exception as follows:
```
java.lang.reflect.InvocationTargetException
    at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
    at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
    at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
    at java.lang.reflect.Method.invoke(Method.java:498)
    at org.springframework.boot.maven.AbstractRunMojo$LaunchRunner.run(AbstractRunMojo.java:478)
    at java.lang.Thread.run(Thread.java:745)
Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'mySpringBootRouter': Initialization of bean failed; nested exception is org.apache.camel.spring.GenericBeansException: Error post processing bean: mySpringBootRouter; nested exception is org.apache.camel.RuntimeCamelException: java.lang.IllegalArgumentException: Cannot add component as its already previously added: properties
    at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:553)
    at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:482)
    at org.springframework.beans.factory.support.AbstractBeanFactory$1.getObject(AbstractBeanFactory.java:306)
    at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:230)
    at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:302)
    at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:197)
    at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:772)
    at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:839)
    at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:538)
    at org.springframework.boot.context.embedded.EmbeddedWebApplicationContext.refresh(EmbeddedWebApplicationContext.java:118)
    at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:766)
    at org.springframework.boot.SpringApplication.createAndRefreshContext(SpringApplication.java:361)
    at org.springframework.boot.SpringApplication.run(SpringApplication.java:307)
    at org.apache.camel.spring.boot.FatJarRouter.main(FatJarRouter.java:26)
    ... 6 more
Caused by: org.apache.camel.spring.GenericBeansException: Error post processing bean: mySpringBootRouter; nested exception is org.apache.camel.RuntimeCamelException: java.lang.IllegalArgumentException: Cannot add component as its already previously added: properties
    at org.apache.camel.spring.CamelBeanPostProcessor.postProcessBeforeInitialization(CamelBeanPostProcessor.java:154)
    at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.applyBeanPostProcessorsBeforeInitialization(AbstractAutowireCapableBeanFactory.java:408)
    at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1570)
    at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:545)
    ... 19 more
Caused by: org.apache.camel.RuntimeCamelException: java.lang.IllegalArgumentException: Cannot add component as its already previously added: properties
    at org.apache.camel.util.ObjectHelper.wrapRuntimeCamelException(ObjectHelper.java:1652)
    at org.apache.camel.impl.CamelPostProcessorHelper.getInjectionPropertyValue(CamelPostProcessorHelper.java:263)
    at org.apache.camel.impl.DefaultCamelBeanPostProcessor.injectFieldProperty(DefaultCamelBeanPostProcessor.java:206)
    at org.apache.camel.impl.DefaultCamelBeanPostProcessor$1.doWith(DefaultCamelBeanPostProcessor.java:172)
    at org.apache.camel.util.ReflectionHelper.doWithFields(ReflectionHelper.java:73)
    at org.apache.camel.impl.DefaultCamelBeanPostProcessor.injectFields(DefaultCamelBeanPostProcessor.java:168)
    at org.apache.camel.impl.DefaultCamelBeanPostProcessor.postProcessBeforeInitialization(DefaultCamelBeanPostProcessor.java:82)
    at org.apache.camel.spring.CamelBeanPostProcessor.postProcessBeforeInitialization(CamelBeanPostProcessor.java:148)
    ... 22 more
Caused by: java.lang.IllegalArgumentException: Cannot add component as its already previously added: properties
    at org.apache.camel.impl.DefaultCamelContext.addComponent(DefaultCamelContext.java:369)
    at org.apache.camel.util.CamelContextHelper.lookupPropertiesComponent(CamelContextHelper.java:578)
    at org.apache.camel.impl.CamelPostProcessorHelper.getInjectionPropertyValue(CamelPostProcessorHelper.java:237)
    ... 28 more
```