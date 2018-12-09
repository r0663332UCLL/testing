package ui.controller;

import domain.model.DomainException;
import domain.model.Person;
import domain.model.Product;
import domain.model.ShopService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class addProductPostHandler implements RequestHandler {
    private ShopService shopservice;

    @Override
    public void setModel(ShopService model) {
        this.shopservice = model;
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product product = new Product();
        List<String> errors = new ArrayList<>();
        try {
            product.setProductId(Integer.parseInt(request.getParameter("productid")));
        } catch (DomainException|NumberFormatException e) {
            errors.add(e.getMessage());
        }
        try {
            product.setName(request.getParameter("name"));
        } catch (DomainException e) {
            errors.add(e.getMessage());
        }

        try {
            product.setDescription(request.getParameter("description"));
        } catch (DomainException e) {
            errors.add(e.getMessage());
        }

        try {
            product.setPrice(request.getParameter("price"));
        } catch (DomainException|NumberFormatException e) {
            errors.add(e.getMessage());
        }
        if (errors.isEmpty()) {
            shopservice.addProduct(product);
            RequestHandler productOverview = new productOverviewHandler();
            productOverview.setModel(shopservice);
            return productOverview.handleRequest(request, response);

        } else {
            request.setAttribute("errors", errors);
            RequestHandler addProductGet = new addProductGetHandler();
            addProductGet.setModel(shopservice);
            return addProductGet.handleRequest(request, response);
        }
    }
}