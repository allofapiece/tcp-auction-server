package com.ock.au.component;

public class Url extends MvcComponent {
    private String controllerName;
    private String actionName;

    public Url() {
    }

    public Url(String controllerName, String actionName) {
        this.controllerName = controllerName;
        this.actionName = actionName;
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }
}
