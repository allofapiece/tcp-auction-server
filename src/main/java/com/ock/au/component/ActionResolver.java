package com.ock.au.component;

import com.ock.au.exception.NotFoundRouteException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class ActionResolver {
    @Autowired
    private ActionMapper actionMapper;

    private String urlPartSeparator = "/";

    private Map<String, Action> cache;

    public ActionResolver() {
        cache = new HashMap<>();
    }


    public Action resolve(String urlString) throws NotFoundRouteException {
        if (cache.containsKey(urlString)) {
            return cache.get(urlString);
        }

        String[] parts = urlString.split(urlPartSeparator);
        if (parts.length == 0) {
            throw new NotFoundRouteException("Not found route by " + urlString + " url");
        }
        Url url = new Url(parts[0], parts.length > 1 ? parts[1] : null);
        Action action = actionMapper.map(url);

        return action;
    }
}
