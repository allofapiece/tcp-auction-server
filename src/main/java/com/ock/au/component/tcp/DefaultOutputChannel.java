package com.ock.au.component.tcp;

import com.ock.au.exception.IllegalResourceException;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.Map;

public class DefaultOutputChannel implements OutputChannel {
    private ObjectOutputStream oiStream;

    public DefaultOutputChannel(Socket socket) {
        try {
            oiStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean send(Serializable entity) throws IllegalResourceException {
        return send(entity, this.getDefaultHeaders());
    }


    public boolean send(Serializable entity, Map<String, Header> headers) throws IllegalResourceException {
        headers.putAll(this.getDefaultHeaders());
        return send(new Message());
    }

    @Override
    public boolean send(Message message) throws IllegalResourceException {
        if (!(message.getPayload() instanceof Serializable)) {
            throw new IllegalResourceException("Sent entity is not serializable");
        }

        try {
            oiStream.writeObject(message);
            oiStream.flush();

            return true;
        } catch (IOException e) {

            return false;
        }
    }
}
