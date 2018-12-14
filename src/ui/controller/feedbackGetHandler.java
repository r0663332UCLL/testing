package ui.controller;

import domain.model.ShopService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class feedbackGetHandler implements RequestHandler {

    ShopService service;

    @Override
    public void setModel(ShopService model) {
        service = model;
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "feedbackPage.jsp";
    }
}
