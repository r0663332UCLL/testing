package ui.controller;

import domain.model.Product;
import domain.model.ShopService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class removeFromCartHandler implements RequestHandler {

    ShopService shopservice;
    @Override
    public void setModel(ShopService model) {
        this.shopservice = model;
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<Product> cart = (ArrayList<Product>) session.getAttribute("cart");
        Product productToRemove = null;
        for(Product product : cart){
            if((product.getProductId() == Integer.parseInt(request.getParameter("productToRemove")))){
                productToRemove = product;
            }
        }
        cart.remove(productToRemove);
        RequestHandler cartOverviewHandler = new cartOverviewHandler();
        cartOverviewHandler.setModel(shopservice);
        return cartOverviewHandler.handleRequest(request, response);
    }
}
