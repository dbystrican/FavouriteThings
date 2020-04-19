package com.bystrican.favouriteThings.JDBC;


import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Stateless
@Local(DatabaseInterface.class)
@Alternative
public class SQLDatabaseService implements DatabaseInterface {

    private final String url = "jdbc:postgresql://localhost/postgres";
    private final String user = "postgres";
    private final String password = "postgres";

    public boolean delete(int id)  {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        boolean returnValue=false;
        try {
            connection = DriverManager.getConnection(url, user, password);
            preparedStatement = connection.prepareStatement("delete from \"favouriteThings\" where \"ID\" = ?");
            preparedStatement.setInt(1, id);
            returnValue  =  preparedStatement.execute();
        }catch(SQLException ex){
            ex.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
            return returnValue;
        }
    }
    public boolean insert(FavouriteThing favouriteThing)  {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        boolean returnValue=false;
        try {
            connection = DriverManager.getConnection(url, user, password);
            preparedStatement = connection.prepareStatement("insert into \"favouriteThings\" (\"name\",\"description\",\"rating\") VALUES (?,?,?)");
            preparedStatement.setString(1, favouriteThing.getName());
            preparedStatement.setString(2, favouriteThing.getDescription());
            preparedStatement.setInt(3, favouriteThing.getRating());
            returnValue = preparedStatement.execute();
        }catch(SQLException ex){
            ex.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
            return returnValue;
        }
    }
    public boolean update(int id, FavouriteThing favouriteThing) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        boolean returnValue = false;
        try {
            connection = DriverManager.getConnection(url, user, password);
            preparedStatement = connection.prepareStatement("update \"favouriteThings\" set \"name\" =?, \"description\"=?,\"rating\"=? where \"ID\" = ?");
            preparedStatement.setString(1,favouriteThing.getName());
            preparedStatement.setString(2,favouriteThing.getDescription());
            preparedStatement.setInt(3,favouriteThing.getRating());
            preparedStatement.setInt(4,id);
            returnValue = preparedStatement.execute();
        }catch(SQLException ex){
            ex.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
            return returnValue;
        }
    }
    public FavouriteThing read(int id) {

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        FavouriteThing favouriteThing = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            preparedStatement = connection.prepareStatement("select * from \"favouriteThings\" where \"ID\"=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                favouriteThing = new FavouriteThing(resultSet.getInt("ID"), resultSet.getString("name"), resultSet.getString("description"), resultSet.getInt("rating"));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        } finally {
            //disconect
            try {
                preparedStatement.close();
                connection.close();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
            return favouriteThing;
        }
    }
    public List<FavouriteThing> read() {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        List<FavouriteThing> favouriteThings = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            preparedStatement = connection.prepareStatement("select * from \"favouriteThings\" Order by \"ID\"");
            ResultSet resultSet = preparedStatement.executeQuery();
            favouriteThings = new ArrayList<FavouriteThing>();
            while (resultSet.next()) {
                favouriteThings.add(new FavouriteThing(resultSet.getInt("ID"), resultSet.getString("name"), resultSet.getString("description"), resultSet.getInt("rating")));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
            return favouriteThings;
        }
    }

}
