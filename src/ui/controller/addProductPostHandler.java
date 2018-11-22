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

public class addProductPostHandler implements RequestHandler {
    private ShopService shopservice;

    @Override
    public void setModel(ShopService model) {
        this.shopservice = model;
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product product = new Product();
        List<String> errors = null;
        product.setProductId(Integer.parseInt(request.getParameter("productToUpdate")));
        try {
            product.setName(request.getParameter("name"));
        } catch (ControllerException e) {
            errors.add(e.getMessage());
        }

        try {
            product.setDescription(request.getParameter("description"));
        } catch (ControllerException e) {
            errors.add(e.getMessage());
        }

        try {
            product.setPrice(request.getParameter("price"));
        } catch (ControllerException e) {
            errors.add(e.getMessage());
        }
        if (errors == null) {
            shopservice.updateProducts(product);
            RequestHandler productOverview = new productOverviewHandler();
            productOverview.setModel(shopservice);
            return productOverview.handleRequest(request, response);

        } else {
            request.setAttribute("errors", errors);
            RequestHandler updateProductGet = new updateProductGetHandler();
            updateProductGet.setModel(shopservice);
            return updateProductGet.handleRequest(request, response);
        }
    }
}