package ui.controller;

import domain.model.Product;
import domain.model.ShopService;
import sun.misc.Request;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class addToCartHandler implements RequestHandler {
    private ShopService shopservice;


    @Override
    public void setModel(ShopService model) {
            this.shopservice = model;
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product product = shopservice.getProduct(Integer.parseInt(request.getParameter("productToAdd")));
        HttpSession session = request.getSession();
        ArrayList<Product> cart = (ArrayList) session.getAttribute("cart");
        if(cart == null){
            cart = new ArrayList<Product>();
            cart.add(product);
            session.setAttribute("cart", cart);
        } else {
            cart.add(product);
        }
        RequestHandler productOverviewHandler = new productOverviewHandler();
        productOverviewHandler.setModel(shopservice);
        return productOverviewHandler.handleRequest(request, response);
    }
}
