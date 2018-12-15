package com.ock.au.component.tcp;


import com.ock.au.exception.IllegalResourceException;

import java.io.Serializable;
import java.util.Map;

public interface OutputChannel extends Channel {
    boolean send(Serializable entity) throws IllegalResourceException;
    boolean send(Serializable entity, Map<String, Header> headers) throws IllegalResourceException;
    boolean send(Message message) throws IllegalResourceException;
}
