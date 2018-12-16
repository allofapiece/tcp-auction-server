package com.ock.au.component.tcp;

import org.springframework.validation.ObjectError;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Message implements Serializable {
    private Map<String, Header> headers;
    private Map<String, Object> payload;

    public Message() {
        headers = new HashMap<>();
        payload = new HashMap<>();
        Header status = new Header("status", "success");
        headers.put(status.getName(), status);
    }

    public Message(String message) {
        this();
        Header messageHeader = new Header("message", message);
        headers.put(messageHeader.getName(), messageHeader);
    }

    public Message(String message, String status) {
        this();
        Header messageHeader = new Header("message", message);
        Header statusHeader = new Header("status", status);
        headers.put(messageHeader.getName(), messageHeader);
        headers.put(statusHeader.getName(), statusHeader);
    }

    public Message(Map<String, Header> headers, Map<String, Object> payload) {
        this();
        this.headers = headers;
        this.payload = payload;
    }

    public void setMessage(String message) {
        headers.put("message", new Header("message", message));
    }

    public void setStatus(String status) {
        headers.put("status", new Header("status", status));
    }

    public void setErrors(List<ObjectError> errors) {
        headers.put("errors", new Header("errors", errors));
    }

    public Map<String, Header> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, Header> headers) {
        this.headers = headers;
    }

    public Header getHeader(String headerName) {
        return headers.get(headerName);
    }

    public Object getHeaderValue(String headerName) {
        return getHeader(headerName).getValue();
    }

    public Map<String, Object> getPayload() {
        return payload;
    }

    public void setPayload(Map<String, Object> payload) {
        this.payload = payload;
    }

    public void addPayload(String key, Object payload) {
        this.payload.put(key, payload);
    }
}
