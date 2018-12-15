package com.ock.au.component.tcp;

import java.util.HashMap;
import java.util.Map;

public interface Channel {
    default Map<String, Header> getDefaultHeaders() {
        Map<String, Header> headers = new HashMap<>();

        return headers;
    }
}
