package com.ock.au;

import com.ock.au.component.ActionDispatcher;
import com.ock.au.component.ActionMapper;
import com.ock.au.component.ActionResolver;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.GenericApplicationContext;
import com.ock.au.component.tcp.Connector;
import com.ock.au.component.tcp.SocketConnector;
import com.ock.au.component.populator.DefaultControllerPopulator;
import com.ock.au.component.populator.Populator;

@SpringBootApplication
public class AuctionServerApplication {

    public static void main(String[] args) throws Exception {
        try (GenericApplicationContext context = new AnnotationConfigApplicationContext(
                AuctionServerApplication.class, ApplicationStarter.class, PersistenceConfig.class)) {

            context.start();
            context.stop();
        }
    }

    @Bean
    @Scope(value = "singleton")
    public Connector socketConnector() {
        return new SocketConnector();
    }

    @Bean
    @Scope(value = "singleton")
    public Populator controllerPopulator() {
        return new DefaultControllerPopulator();
    }

    @Bean
    @Scope(value = "singleton")
    public ActionMapper actionMapper() {
        return new ActionMapper();
    }

    @Bean
    @Scope(value = "singleton")
    public ActionResolver actionResolver() {
        return new ActionResolver();
    }

    @Bean
    @Scope(value = "singleton")
    public ActionDispatcher actionDispatcher() {
        return new ActionDispatcher();
    }

}
