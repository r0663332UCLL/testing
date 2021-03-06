package ui.controller;

import domain.model.Person;
import domain.model.Product;
import domain.model.ShopService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class updateProductGetHandler implements RequestHandler {
    private ShopService shopservice;

    @Override
    public void setModel(ShopService model) {
        this.shopservice = model;
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("productObject", shopservice.getProduct(Integer.parseInt(request.getParameter("productToUpdate"))));
        return "updateProduct.jsp";
    }
}