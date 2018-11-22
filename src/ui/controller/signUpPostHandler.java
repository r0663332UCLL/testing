package ui.controller;

import domain.model.Person;
import domain.model.Product;
import domain.model.ShopService;
import sun.misc.Request;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class signUpPostHandler implements RequestHandler {
    private ShopService shopservice;

    @Override
    public void setModel(ShopService model) {
        this.shopservice = model;
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> errors = new ArrayList<>();
        Person newPerson = new Person();
        try {
            newPerson.setEmail(request.getParameter("email"));
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
        try {
            newPerson.setFirstName(request.getParameter("firstName"));
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
        try {
            newPerson.setLastName(request.getParameter("lastName"));
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
        try {
            newPerson.setUserid(request.getParameter("userid"));
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
        try {
            newPerson.setPassword(request.getParameter("password"));
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
        if (errors.isEmpty()) {
            shopservice.addPerson(newPerson);
            RequestHandler index = new indexHandler();
            index.setModel(shopservice);
            return index.handleRequest(request, response);

        } else {
            request.setAttribute("errors", errors);
            RequestHandler signUpGet = new signUpGetHandler();
            signUpGet.setModel(shopservice);
            return signUpGet.handleRequest(request, response);
        }
    }
}