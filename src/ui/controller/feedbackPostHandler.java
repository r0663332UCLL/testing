package ui.controller;

import domain.model.ShopService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class feedbackPostHandler implements RequestHandler {

    ShopService shopService;

    @Override
    public void setModel(ShopService model) {
        shopService = model;
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getParameter("feedback").trim().isEmpty()){
            ArrayList<String> errors = new ArrayList<String>();
            errors.add("We REALLY appreciate your feedback, please fill out this form!");
            request.setAttribute("errors", errors);
            return "feedbackPage.jsp";
        } else {

        return "feedbackThankyou.jsp";
        }
    }
}
