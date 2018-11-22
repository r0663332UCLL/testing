package ui.desktop;

import java.awt.*;
import java.sql.*;
import java.util.Properties;

import domain.db.DbException;
import domain.db.Secret;

import javax.swing.*;

public class DesktopApp {
    public static void main(String[] args) throws SQLException {

        String userIdInput = JOptionPane.showInputDialog("userid");
        String firstNameInput = JOptionPane.showInputDialog("voornaam");
        String lastNameInput = JOptionPane.showInputDialog("achternaam");
        String passwordInput = JOptionPane.showInputDialog("wachtwoord");
        String emailInput = JOptionPane.showInputDialog("email");

        Properties properties = new Properties();
        String url = "jdbc:postgresql://databanken.ucll.be:51819/2TX37?currentSchema=r0663332";
        properties.setProperty("user", "local_r0663332");
        properties.setProperty("password", "RG)EZd;çMbO4sECE");
        properties.setProperty("ssl", "true");
        properties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
        properties.setProperty("sslmode","require");

        String sqlGet = "SELECT * FROM persons";
        String sqlAdd = "INSERT INTO persons(userid, email, firstname, lastname, password) VALUES(?,?,?,?,?)";
        ResultSet result = null;
        try{
            Connection connection = DriverManager.getConnection(url,properties);
            PreparedStatement statementAdd = connection.prepareStatement(sqlAdd);
            statementAdd.setString(1, userIdInput);
            statementAdd.setString(2, emailInput);
            statementAdd.setString(3, firstNameInput);
            statementAdd.setString(4, lastNameInput);
            statementAdd.setString(5, passwordInput);
            statementAdd.execute();
            PreparedStatement statementGet = connection.prepareStatement(sqlGet);
            result = statementGet.executeQuery();
            while(result.next()){
                String voornaam = result.getString("firstName");
                String achternaam = result.getString("lastName");
                String email = result.getString("email");
                System.out.println(voornaam + " " + achternaam + " " + email);
            }
            statementGet.close();
            connection.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
