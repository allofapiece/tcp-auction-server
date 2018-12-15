package com.ock.au.component.tcp;

import java.io.ObjectInputStream;

public interface InputChannel extends Channel {
    Message receive();
    Message receive(ObjectInputStream oiStream);
}
