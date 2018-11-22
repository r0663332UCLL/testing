package ui.controller;

import domain.model.*;
import org.apache.http.HttpResponse;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.logging.Handler;

@WebServlet("/Controller")
public class Servlet extends HttpServlet {

    private ShopService shopservice;

    @Override
    public void init() throws ServletException{
        super.init();
        ServletContext context = getServletContext();
        Properties properties = new Properties();
        Enumeration<String> parameternames = context.getInitParameterNames();
        while (parameternames.hasMoreElements()){
            String propertyname = parameternames.nextElement();
            properties.setProperty(propertyname, context.getInitParameter(propertyname));
        }
        shopservice = new ShopService(properties);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String action = request.getParameter("action") + "Handler";
        HandlerFactory handlerFactory = new HandlerFactory();
        RequestHandler handler = handlerFactory.getHandler(action, shopservice);
        String destination = handler.handleRequest(request, response);
        RequestDispatcher view = request.getRequestDispatcher(destination);
        view.forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }


    /*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {

            case "signUpPost":
                signUpPost(request, response);
                break;

            case "addProductPost":
                addProductPost(request, response);
                break;

            case "updateProductPost" :
                updateProductPost(request, response);
                break;

            case "checkPassword":
                checkPassword(request, response);
        }
    }*/


    /*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        switch (action) {

            case "userOverview":
                userOverview(request, response);
                break;

            case "index":
                index(request, response);
                break;

            case "productOverview":
                productOverview(request, response);
                break;

            case "signUpGet":
                signUpGet(request, response);
                break;

            case "addProductGet":
                addProductGet(request, response);
                break;

            case "removeProduct" :
                removeProduct(request, response);
                break;

            case "removeProductConfirmed" :
                removeProductConfirmed(request, response);
                break;

            case "removePerson" :
                removePerson(request, response);
                break;

            case "removePersonConfirmed" :
                removePersonConfirmed(request, response);
                break;

            case "updateProductGet" :
                updateProductGet(request, response);
                break;

            case "checkPasswordInput" :
                checkPasswordInput(request, response);
        }
    }*/

    /*private void checkPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Person person = shopservice.getPerson(request.getParameter("personToCheck"));
        String password = request.getParameter("password");
        String resultString = null;
        if (person.isCorrectPassword(password)){
            resultString = "Password is correct";

        } else {
            resultString = "Password is false";
        }
        request.setAttribute("resultString", resultString);
        RequestDispatcher view = request.getRequestDispatcher("checkPasswordInput.jsp");
        view.forward(request, response);
    }*/

    /*private void checkPasswordInput(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("checkPasswordInput.jsp");
        view.forward(request, response);
    }*/

    /*private void updateProductPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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
            productOverview(request, response);
        } else {
            request.setAttribute("errors", errors);
            updateProductGet(request, response);
        }
    }*/

    /*private void removeProductConfirmed(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        shopservice.deleteProduct(Integer.parseInt(request.getParameter("productToRemove")));
        productOverview(request, response);
    }*/

    /*private void userOverview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        request.setAttribute("personen", shopservice.getPersons());
        RequestDispatcher view = request.getRequestDispatcher("personoverview.jsp");
        view.forward(request, response);

    }*/

    /*private void productOverview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("producten", shopservice.getProducts());
        RequestDispatcher view = request.getRequestDispatcher("productoverview.jsp");
        view.forward(request, response);
    }*/

    /*private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher view = request.getRequestDispatcher("index.jsp");
        view.forward(request, response);

    }*/

    /*private void signUpGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher view = request.getRequestDispatcher("signUp.jsp");
        view.forward(request, response);

    }*/

    /*private void signUpPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<String> errors = new ArrayList<>();
        Person newPerson = new Person();
        try {
            newPerson.setEmail(request.getParameter("email"));
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
        try {
            newPerson.setFirstName(request.getParameter("firstName"));
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
        try {
            newPerson.setLastName(request.getParameter("lastName"));
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
        try {
            newPerson.setUserid(request.getParameter("userid"));
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
        try {
            newPerson.setPassword(request.getParameter("password"));
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
        if (errors.isEmpty()) {
            shopservice.addPerson(newPerson);
            index(request, response);
        } else {
            request.setAttribute("errors", errors);
            signUpGet(request, response);
        }
    }*/

    /*private void addProductGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("addProduct.jsp");
        view.forward(request, response);
    }*/

    /*private void addProductPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<String> errors = new ArrayList<>();
        Product newProduct = new Product();
        try {
            newProduct.setPrice(request.getParameter("price"));
        } catch (ControllerException |NumberFormatException e) {
            errors.add(e.getMessage());
        }
        try {
            newProduct.setDescription(request.getParameter("description"));
        } catch (ControllerException e) {
            errors.add(e.getMessage());
        }
        try {
            newProduct.setName(request.getParameter("name"));
        } catch (ControllerException e) {
            errors.add(e.getMessage());
        }
        try {
            newProduct.setProductId(Integer.parseInt(request.getParameter("productid")));
        } catch (ControllerException |NumberFormatException e) {
            errors.add(e.getMessage());
        }
        if (errors.isEmpty()) {
            shopservice.addProduct(newProduct);
            productOverview(request, response);
        } else {
            request.setAttribute("errors", errors);
            addProductGet(request, response);
        }
    }*/

    /*private void removeProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RequestDispatcher view = request.getRequestDispatcher("deleteConfirmation.jsp");
        view.forward(request, response);
    }*/

    /*private void updateProductGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setAttribute("productObject", shopservice.getProduct(Integer.parseInt(request.getParameter("productToUpdate"))));
        RequestDispatcher view = request.getRequestDispatcher("updateProduct.jsp");
        view.forward(request, response);

    }*/

    /*private void removePerson(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RequestDispatcher view = request.getRequestDispatcher("personDeleteConfirmation.jsp");
        view.forward(request, response);
    }*/

    /*private void removePersonConfirmed(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        shopservice.deletePerson(request.getParameter("personToRemove"));
        userOverview(request, response);
    }*/
}
