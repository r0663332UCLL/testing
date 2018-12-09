package ui.controller;

import domain.model.Product;
import domain.model.ShopService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class cartOverviewHandler implements RequestHandler {

    ShopService shopservice;
    @Override
    public void setModel(ShopService model) {
        this.shopservice = model;
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<Product> cart = (ArrayList<Product>)session.getAttribute("cart");
        double total = 0;
        if(cart != null){
            for(Product product : cart){
                total += product.getPrice();
            }
        } else {
            cart = new ArrayList<Product>();
        }
        request.setAttribute("total", total);
        request.setAttribute("cart", cart);
        return "cartOverview.jsp";
    }
}
