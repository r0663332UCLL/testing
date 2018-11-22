package ui.controller;

import domain.model.ShopService;
import sun.misc.Request;

public class HandlerFactory {

    public RequestHandler getHandler(String handlerName, ShopService model){
        RequestHandler handler = null;
        Class handlerClass = null;
        try {
            handlerClass = Class.forName("ui.controller." + handlerName);
            Object handlerObject = handlerClass.newInstance();
            handler = (RequestHandler) handlerObject;
            handler.setModel(model);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new ControllerException(e);
        }

        return handler;
    }
}
