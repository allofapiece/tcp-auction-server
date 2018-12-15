package com.ock.au;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import com.ock.au.component.tcp.Connector;
import com.ock.au.component.tcp.MessageHandler;

public class ApplicationStarter implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private Connector socketConnector;

    @Autowired
    private MessageHandler messageHandler;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        messageHandler.handle(socketConnector.connect());
    }
}
