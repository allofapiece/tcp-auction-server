package com.ock.au.component.tcp;

import com.ock.au.component.ActionDispatcher;
import com.ock.au.exception.IllegalResourceException;
import com.ock.au.exception.NotFoundRouteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.net.Socket;
import java.util.Map;

@Service
public class MessageHandler {

    private InputChannel inputChannel;
    private OutputChannel outputChannel;

    @Autowired
    private ActionDispatcher actionDispatcher;

    public void handle(Socket socket) {
        inputChannel = new DefaultInputChannel(socket);
        outputChannel = new DefaultOutputChannel(socket);

        Message message = inputChannel.receive();

        while (true) {
            try {
                Message response = actionDispatcher.dispatch(message);

                if (response.getHeader("noResponse") != null &&
                        ((boolean) response.getHeaderValue("noResponse"))) {
                    message = inputChannel.receive();
                    continue;
                }

                outputChannel.send(response);
            } catch (NotFoundRouteException e) {
                sendError(e.getMessage());
            } catch (IllegalResourceException e) {
                sendError(e.getMessage());
            }

            message = inputChannel.receive();
        }
    }

    public Message sendAndReceive(Serializable entity) {
        Message message = null;
        try {
            outputChannel.send(entity);
            message = inputChannel.receive();
        } catch (IllegalResourceException e) {
            e.printStackTrace();
        }

        return message;
    }

    public Message sendAndReceive(Serializable entity, Map<String, Header> headers) {
        Message message = null;
        try {
            outputChannel.send(entity, headers);
            message = inputChannel.receive();
        } catch (IllegalResourceException e) {
            e.printStackTrace();
        }

        return message;
    }

    private void sendError(String errorMessage) {
        Message message = new Message(errorMessage, "error");
        try {
            outputChannel.send(message);
        } catch (IllegalResourceException e) {
            e.printStackTrace();
        }
    }
}
