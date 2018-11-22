package ui.controller;

import domain.model.ShopService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface RequestHandler {

    void setModel(ShopService model);
    String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
