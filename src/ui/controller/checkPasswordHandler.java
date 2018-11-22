package ui.controller;

import domain.model.Person;
import domain.model.ShopService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class checkPasswordHandler implements RequestHandler {
    private ShopService shopservice;

    @Override
    public void setModel(ShopService model) {
        this.shopservice = model;
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Person person = shopservice.getPerson(request.getParameter("personToCheck"));
        String password = request.getParameter("password");
        String resultString = null;
        if (person.isCorrectPassword(password)){
            resultString = "Password is correct";

        } else {
            resultString = "Password is false";
        }
        request.setAttribute("resultString", resultString);
        return "checkPasswordInput.jsp";
    }
}
