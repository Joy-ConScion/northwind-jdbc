package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/northwind");
        dataSource.setUsername("root");
        dataSource.setPassword("");

        String sql = """
                SELECT productID,
                       ProductName,
                       UnitPrice,
                FROM products;
                """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()){
                int productID = resultSet.getInt("product_id");
                String ProductName = resultSet.getString("product_name");
                int UnitPrice = resultSet.getInt("unit_price");
                System.out.printf(" %d %s %d", productID, ProductName, UnitPrice);
                System.out.println();
            }

        } catch (SQLException e) {
            System.out.println("Failed to retrieve request. Please contact your admin and try again.");
            e.printStackTrace();
        }

    }
}
