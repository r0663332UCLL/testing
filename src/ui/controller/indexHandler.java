package ui.controller;

import domain.model.Person;
import domain.model.Product;
import domain.model.ShopService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class indexHandler implements RequestHandler {
    private ShopService shopservice;

    @Override
    public void setModel(ShopService model) {
        this.shopservice = model;
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        checkQuote(request, response);
        return "index.jsp";
    }

    private void checkQuote(HttpServletRequest request, HttpServletResponse response){
        String showquote = "yes";
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies){
            if (cookie.getName().equals("showquote")){
                showquote = cookie.getValue();
            }
        }
        if (request.getParameter("quotepreference") != null){
            Cookie cookie = new Cookie("showquote", request.getParameter("quotepreference"));
            response.addCookie(cookie);
            showquote = request.getParameter("quotepreference");
        }
        request.setAttribute("showquote", showquote);
    }
}