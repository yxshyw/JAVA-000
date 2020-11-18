package jdbc;

import java.sql.*;
import java.util.*;

public class CRUD {

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;

    private void createConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "123456");
            if (connection != null) {
                System.out.println("Connection successful!");
            } else {
                System.out.println("Connection failed!");
            }
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }

    public boolean sql(String sql) {
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            System.out.println("Insert successfully");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void close() throws SQLException {
        preparedStatement.close();
        connection.close();
        System.out.println("Connection close");
    }

    public static void main(String[] args) {
        try {
            CRUD crud = new CRUD();
            crud.createConnection();
            crud.sql("select * from user");
            crud.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}