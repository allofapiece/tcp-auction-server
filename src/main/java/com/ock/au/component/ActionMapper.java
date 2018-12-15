package com.ock.au.component;

import com.ock.au.exception.IllegalResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import com.ock.au.component.populator.Populator;

import java.util.Map;

public class ActionMapper {
    private Map<String, Map<String, Action>> map;

    @Autowired
    private Populator controllerPopulator;

    @EventListener
    public void onContextRefresh(ContextRefreshedEvent event) {
        try {
            map = controllerPopulator.populate();
        } catch (IllegalResourceException e) {
            e.printStackTrace();
        }
    }

    public Action map(Url url) {
        return map.get(url.getControllerName()).get(url.getActionName());
    }


    public Map<String, Map<String, Action>> getMap() {
        return map;
    }

    public void setMap(Map<String, Map<String, Action>> map) {
        this.map = map;
    }

    public void put(String controllerName, Action action) {
        if (action.getControllerName() == null) {
            action.setControllerName(controllerName);
        }

        Map<String, Action> actions = map.get(controllerName);
        actions.put(action.getName(), action);

        map.put(controllerName, actions);
    }

    public void put(String controllerName, String actionName) {
        Map<String, Action> actions = map.get(controllerName);
        actions.put(actionName, new Action(actionName, controllerName));

        map.put(controllerName, actions);
    }
}
