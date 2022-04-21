package org.product.app.manager;

import org.product.app.App;
import org.product.app.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductManager {
    public static void insert(Product product) throws SQLException {
        try (Connection c = App.getConnection()){
            String sql = "INSERT INTO PRODUCT(Title, ProductType, ArticleNumber, Description, Image, ProductionPersonCount, ProductionWorkshopNumber, MinCostForAgent) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, product.getTitle());
            ps.setString(2, product.getProductType());
            ps.setInt(3, product.getArticleNumber());
            ps.setString(4, product.getDescription());
            ps.setString(5, product.getImage());
            ps.setInt(6, product.getProductionPersonCount());
            ps.setInt(7, product.getProductionWorkshopNumber());
            ps.setInt(8, product.getMinCostForAgent());
            ps.executeUpdate();

            ResultSet key = ps.getGeneratedKeys();
            if (key.next()){
                product.setId(key.getInt(1));
                return;
            }
            throw new SQLException("ошибка генерации id");
        }
    }

    public static void update(Product product) throws SQLException {
        try (Connection c = App.getConnection()){
            String sql = "UPDATE PRODUCT SET Title=?, ProductType=?, ArticleNumber=?, Description=?, Image=?, ProductionPersonCount=?, ProductionWorkshopNumber=?, MinCostForAgent=? WHERE ID=?";
            PreparedStatement ps = c.prepareStatement(sql);

            ps.setString(1, product.getTitle());
            ps.setString(2, product.getProductType());
            ps.setInt(3, product.getArticleNumber());
            ps.setString(4, product.getDescription());
            ps.setString(5, product.getImage());
            ps.setInt(6, product.getProductionPersonCount());
            ps.setInt(7, product.getProductionWorkshopNumber());
            ps.setInt(8, product.getMinCostForAgent());
            ps.setInt(9, product.getId());
            ps.executeUpdate();
        }
    }


    public static List<Product> selectAll() throws SQLException {
        try (Connection c = App.getConnection()){
            String sql = "SELECT * FROM PRODUCT";
            Statement statement = c.createStatement();
            statement.executeQuery(sql);

            List<Product> list = new ArrayList<>();
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()){
                list.add(new Product(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getInt(7),
                        resultSet.getInt(8),
                        resultSet.getInt(9)));
            }
            return list;
        }
    }

    public static void deletebyid(int id) throws SQLException {
        try (Connection c = App.getConnection()){
            String sql = "DELETE FROM PRODUCT WHERE ID = ?";
            PreparedStatement ps = c.prepareStatement(sql);

            ps.setInt(1, id);
            ps.executeUpdate();
        }

    }
    public static void delete(Product product) throws SQLException {
        deletebyid(product.getId());
    }
}
