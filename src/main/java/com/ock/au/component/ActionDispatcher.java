package com.ock.au.component;

import com.ock.au.component.tcp.Message;
import com.ock.au.exception.NotFoundRouteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActionDispatcher extends MvcComponent {
    @Autowired
    private ApplicationContext ctx;

    @Autowired
    private ActionResolver resolver;

    private String parameterStartDelimiter = "[{]";
    private String parameterEndDelimiter = "[}]";

    public Message dispatch(String urlString, Message message) throws NotFoundRouteException {
        Message receiveMessage;
        urlString = optimazeUrlString(urlString);
        Action action = resolver.resolve(urlString);

        Pattern p = Pattern.compile(parameterStartDelimiter + "(.+)" + parameterEndDelimiter);
        Matcher m = p.matcher(urlString);
        while (m.find()) {
            String parameterName = m.group();
            parameterName = optimazeParameterName(parameterName);

            System.out.printf("ff");
        }

        Map<String, Object> payload = message.getPayload();

        if (!validateParameters(payload, action.getParameters())) {
            throw new NotFoundRouteException("Incompatible parameters");
        }

        Object controller = ctx.getBean(action.getControllerName());
        try {
            Set<Class> classes = new HashSet<>();
            for (Object obj : payload.values()) {
                classes.add(obj.getClass());
            }

            Method executableAction = controller.getClass().getDeclaredMethod(action.getName(), classes.toArray(new Class[0]));
            Object receiveObject = executableAction.invoke(controller, payload.values().toArray());
            try {
                receiveMessage = (Message) receiveObject;
            } catch (ClassCastException e) {
                receiveMessage = new Message();
                receiveMessage.getPayload().put("data", receiveObject);
            }
        } catch (ReflectiveOperationException e) {
            throw new NotFoundRouteException("Cannot resolve action");
        }

        return receiveMessage;
    }

    public Message dispatch(Message message) throws NotFoundRouteException {
        if (message.getHeaders().get("url") == null ||
                !(message.getHeaders().get("url").getValue() instanceof String)) {
            throw new NotFoundRouteException("Illegal format of url");
        }
        return dispatch((String) message.getHeaders().get("url").getValue(), message);
    }

    private boolean validateParameters(Map<String, Object> payload, Map<String, Parameter> parameters) {
        if (payload.size() != parameters.size()) {
            return false;
        }

        Iterator<Map.Entry<String, Object>> it = payload.entrySet().iterator();
        for (Map.Entry<String, Parameter> parameter : parameters.entrySet()) {
            Map.Entry<String, Object> pair = it.next();
            Object value = pair.getValue();

            if (!value.getClass().isAssignableFrom(parameter.getValue().getType())) {
                return false;
            }
        }

        return true;
    }

    private String optimazeUrlString(String urlString) {
        if (urlString.substring(0, 1).equals("/")) {
            return urlString.substring(1);
        }

        return urlString;
    }

    private String optimazeParameterName(String parameterName) {
        if (parameterName.substring(0, 1).equals("{")) {
            parameterName = parameterName.substring(1);
        }
        if (parameterName.substring(parameterName.length() - 1, parameterName.length()).equals("}")) {
            parameterName = parameterName.substring(0, parameterName.length() - 1);
        }

        return parameterName;
    }
}
