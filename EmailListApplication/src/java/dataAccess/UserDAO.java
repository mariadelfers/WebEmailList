/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccess;

import model.User;
import java.sql.*;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fer Enriquez'
 */
public class UserDAO {
  
    private PreparedStatement statement;
    private Connection connection;
    private static final Logger logger = Logger.getLogger(UserDAO.class.getName());
    
    public UserDAO (Connection connection) {
        this.connection = connection;
    }
    /**
     * @return the connection
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * @param connection the connection to set
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public void addUser (User user) {
        try {   
            statement = connection.prepareStatement("INSERT INTO tec.user VALUES (?,?,?,?)");
            {
                statement.setString(1, user.getEmail());
                statement.setString(2, user.getFirstName());
                statement.setString(3, user.getLastName());
                statement.setString(4, user.getPassword());
                statement.executeUpdate();
            }
            statement.close();
        }
        catch (SQLException sqle) {
            logger.log(Level.SEVERE, sqle.toString(), sqle);
            throw new RuntimeException(sqle);
        }
    }
    
    
    public ArrayList<model.User> allUsers(){
        ArrayList<model.User> users = new ArrayList<>();
        User user = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM tec.user");
            synchronized (statement){
                ResultSet results = statement.executeQuery();
                
                while(results.next()){
                    user = new User();
                    user.setEmail(results.getString("email"));
                    user.setFirstName(results.getString("firstname"));
                    user.setLastName(results.getString("lastName"));
                    user.setPassword(results.getString("password"));
                    users.add(user);
                }
            }
            statement.close();
        }
        catch (SQLException sqle){
            logger.log(Level.SEVERE, sqle.toString(), sqle);
            throw new RuntimeException(sqle);
        }
        return users;
    }
    
}
