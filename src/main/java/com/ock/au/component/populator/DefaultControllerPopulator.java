package com.ock.au.component.populator;

import com.ock.au.exception.IllegalResourceException;
import com.ock.au.component.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

public class DefaultControllerPopulator implements Populator {

    @Autowired
    private ApplicationContext ctx;

    private String defaultControllerUrl = "/";

    private List<String> exceptControllers;

    public DefaultControllerPopulator() {
        exceptControllers = new ArrayList<>();
        exceptControllers.add("basicErrorController");
    }

    @Override
    public Map<String, Map<String, Action>> populate() throws IllegalResourceException {
        Map<String, Map<String, Action>> actionMap = new HashMap<>();
        List<String> controllerNames = Arrays.asList(ctx.getBeanNamesForAnnotation(org.springframework.stereotype.Controller.class));

        for (String controllerName : controllerNames) {
            if (exceptControllers.contains(controllerName)) {
                continue;
            }

            Object controller = ctx.getBean(controllerName);
            String controllerUrl = extractCotrollerUrlPart(controller);

            Method[] actionMethods = controller.getClass().getDeclaredMethods();
            Map<String, Action> actions = new HashMap<>();

            for (Method actionMethod : actionMethods) {
                Action action = extractAction(actionMethod);
                action.setControllerName(controllerName);
                actions.put(action.getUrlString(), action);
                actionMap.put(controllerUrl, actions);
            }
        }

        return actionMap;

    }

    private String extractCotrollerUrlPart(Object controller) throws IllegalResourceException {
        RequestMapping requestMappingAnnotation = AnnotationUtils.findAnnotation(controller.getClass(), RequestMapping.class);

        if (requestMappingAnnotation == null || requestMappingAnnotation.value().length == 0) {
            throw new IllegalResourceException("Invalid request mapping annotation");
        }

        return requestMappingAnnotation.value()[0];
    }

    private Action extractAction(Method method) {
        Action action = new Action();

        RequestMapping requestMappingAnnotation = AnnotationUtils.findAnnotation(method, RequestMapping.class);
        String actionUrl = requestMappingAnnotation != null && requestMappingAnnotation.value().length != 0
                ? requestMappingAnnotation.value()[0]
                : defaultControllerUrl;

        action.setName(method.getName());
        action.setUrlString(actionUrl);

        Parameter[] parameters = method.getParameters();
        for (Parameter parameter : parameters) {
            com.ock.au.component.Parameter actionParameter = new com.ock.au.component.Parameter();

            RequestParam annotation = parameter.getAnnotation(RequestParam.class);

            actionParameter.setDefaultValue(annotation.defaultValue());
            actionParameter.setName(parameter.getName());
            actionParameter.setType(parameter.getType());

            action.addParameter(actionParameter);
        }

        return action;
    }

    public ApplicationContext getCtx() {
        return ctx;
    }

    public void setCtx(ApplicationContext ctx) {
        this.ctx = ctx;
    }
}
