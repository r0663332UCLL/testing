package domain.db;

import domain.model.Person;
import domain.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProductDbInSql implements ProductDb {

    private Properties properties;
    private String url;

    public ProductDbInSql(Properties properties){

        this.properties = properties;
        this.url = properties.getProperty("url");
        try{
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public Product get(int id) {
        if(id < 0){
            throw new DbException("ID can't be negative");
        }
        Product product = new Product();
        try{
            Connection connection = DriverManager.getConnection(url, properties);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM products WHERE productid=?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                product.setProductId(Integer.parseInt(result.getString("productid")));
                product.setPrice(result.getString("price"));
                product.setName(result.getString("name"));
                product.setDescription(result.getString("description"));
            }
            statement.close();
            connection.close();
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        return product;
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(url, properties);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM products");
            while (result.next()) {
                Product product = new Product();
                product.setProductId(Integer.parseInt(result.getString("productid")));
                product.setPrice(result.getString("price"));
                product.setName(result.getString("name"));
                product.setDescription(result.getString("description"));
                products.add(product);
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return products;
    }

    @Override
    public void add(Product product) {
        if(product == null){
            throw new DbException("No product given");
        }
        try {
            Connection connection = DriverManager.getConnection(url, properties);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO products(productid, name, description, price) VALUES(?, ?, ?, ?)");
            statement.setInt(1, product.getProductId());
            statement.setString(2, product.getName());
            statement.setString(3, product.getDescription());
            statement.setDouble(4, product.getPrice());
            statement.execute();
            statement.close();
            connection.close();
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void update(Product product) {
        if(product == null){
            throw new DbException("No person given");
        }
        try {
            Connection connection = DriverManager.getConnection(url, properties);
            PreparedStatement statement = connection.prepareStatement("UPDATE products SET name=?, description=?, price=? WHERE productid=?");
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getProductId());
            statement.execute();
            statement.close();
            connection.close();
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        if(id < 0){
            throw new DbException("ID is negative");
        }
        try {
            Connection connection = DriverManager.getConnection(url, properties);
            PreparedStatement statement = connection.prepareStatement("DELETE FROM products WHERE productid=?");
            statement.setInt(1, id);
            statement.execute();
            statement.close();
            connection.close();
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public int getNumberOfProducts() {
        int count;
        try {
            Connection connection = DriverManager.getConnection(url, properties);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT count(*) FROM products");
            count = Integer.parseInt(result.getString("count"));
            statement.close();
            connection.close();
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        return count;
    }
}
