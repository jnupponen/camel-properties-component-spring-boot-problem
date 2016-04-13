package com.example;

import org.apache.camel.PropertyInject;
import org.apache.camel.spring.boot.FatJarRouter;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MySpringBootRouter extends FatJarRouter {

    @PropertyInject("inject-value")
    private String injectedValue;
    
    @Override
    public void configure() {
        from("timer:trigger")
        .log("PropertyInject: "+injectedValue)
        ;
    }
}
