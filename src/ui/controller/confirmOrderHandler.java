package ui.controller;

import domain.model.Product;
import domain.model.ShopService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class confirmOrderHandler implements RequestHandler {

    ShopService service;
    @Override
    public void setModel(ShopService model) {
        this.service = model;
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("cart", null);
        return "orderSucces.jsp";
    }
}
