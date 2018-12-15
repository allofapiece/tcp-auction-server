package com.ock.au.component.tcp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class DefaultInputChannel implements InputChannel {
    private ObjectInputStream oiStream;

    public DefaultInputChannel(Socket socket) {
        try {
            oiStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Message receive(ObjectInputStream oiStream) {
        this.oiStream = oiStream;
        return receive();
    }

    @Override
    public Message receive() {
        Message message = null;
        try {
            message = (Message) oiStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return message;
    }
}
