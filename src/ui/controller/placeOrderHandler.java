package ui.controller;

import domain.model.Product;
import domain.model.ShopService;
import sun.misc.Request;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class placeOrderHandler implements RequestHandler {

    ShopService service;
    @Override
    public void setModel(ShopService model) {
        this.service = model;
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Product> cart = (ArrayList<Product>)request.getSession().getAttribute("cart");
        if (cart == null || cart.isEmpty()){
            HandlerFactory factory = new HandlerFactory();
            return factory.getHandler("productOverviewHandler", service).handleRequest(request, response);
        }
        return "orderAdress.jsp";
    }
}
