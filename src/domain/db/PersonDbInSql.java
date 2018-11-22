package domain.db;

import domain.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PersonDbInSql implements PersonDb{

    private Properties properties;
    private String url;

    public PersonDbInSql(Properties properties){
        this.properties = properties;
        this.url = properties.getProperty("url");
        try{
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public Person get(String personId) {
        if(personId == null || personId.isEmpty()){
            throw new DbException("No id given");
        }
        Person person = new Person();
        try{
            Connection connection = DriverManager.getConnection(url, properties);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM persons WHERE userid=?");
            statement.setString(1, personId);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                person.setUserid(result.getString("userid"));
                person.setEmail(result.getString("email"));
                person.setFirstName(result.getString("firstname"));
                person.setLastName(result.getString("lastname"));
                person.setPassword(result.getString("password"));
            }
            statement.close();
            connection.close();
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        return person;
    }

    @Override
    public List<Person> getAll() {
        List<Person> persons = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(url, properties);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM persons");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Person person = new Person();
                person.setUserid(result.getString("userid"));
                person.setEmail(result.getString("email"));
                person.setFirstName(result.getString("firstname"));
                person.setLastName(result.getString("lastname"));
                person.setPassword(result.getString("password"));
                persons.add(person);
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return persons;
    }

    @Override
    public void add(Person person) {
        if(person == null){
            throw new DbException("No person given");
        }
        try {
            Connection connection = DriverManager.getConnection(url, properties);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO persons(userid, email, password, firstname, lastname) VALUES(?, ?, ?, ?, ?)");
            statement.setString(1, person.getUserid());
            statement.setString(2, person.getEmail());
            statement.setString(3, person.getPassword());
            statement.setString(4, person.getFirstName());
            statement.setString(5, person.getLastName());
            statement.execute();
            statement.close();
            connection.close();
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void update(Person person) {
        if(person == null){
            throw new DbException("No person given");
        }
        try {
            Connection connection = DriverManager.getConnection(url, properties);
            PreparedStatement statement = connection.prepareStatement("UPDATE persons SET email=?, firstname=?, lastname=? WHERE userid=?");
            statement.setString(1, person.getEmail());
            statement.setString(2, person.getFirstName());
            statement.setString(3, person.getLastName());
            statement.setString(4, person.getUserid());
            statement.execute();
            statement.close();
            connection.close();
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void delete(String personId) {
        if(personId == null){
            throw new DbException("No id given");
        }
        try {
            Connection connection = DriverManager.getConnection(url, properties);
            PreparedStatement statement = connection.prepareStatement("DELETE FROM persons WHERE userid=?");
            statement.setString(1, personId);
            statement.execute();
            statement.close();
            connection.close();
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public int getNumberOfPersons() {
        int count;
        try {
            Connection connection = DriverManager.getConnection(url, properties);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT count(*) FROM persons");
            count = Integer.parseInt(result.getString("count"));
            statement.close();
            connection.close();
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        return count;
    }
}
