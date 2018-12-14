package ui.controller;

import domain.model.ShopService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class feedbackPostHandler implements RequestHandler {

    ShopService shopService;

    @Override
    public void setModel(ShopService model) {
        shopService = model;
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "feedbackThankyou.jsp";
    }
}
