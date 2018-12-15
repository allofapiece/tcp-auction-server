package com.ock.au.component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Action extends MvcComponent {
    private String name;
    private Map<String, Parameter> parameters;
    private String controllerName;
    private String urlString;

    public Action(String name, String controllerName) {
        this();
        this.name = name;
        this.controllerName = controllerName;
    }

    public Action() {
        parameters = new HashMap<>();
    }

    public void addParameter(Parameter parameter) {
        parameters.put(parameter.getName(), parameter);
    }

    public void addParameter(String name, Parameter parameter) {
        parameters.put(name, parameter);
    }

    public Map<String, Parameter> getParameters() {
        return parameters;
    }

    public List<String> getParameterNames() {
        return parameters.entrySet().stream()
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public void setParameters(Map<String, Parameter> parameters) {
        this.parameters = parameters;
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlString() {
        return urlString;
    }

    public void setUrlString(String urlString) {
        this.urlString = urlString;
    }
}
