package com.company.Dao.SQL;

import com.company.Dao.ProductDAO;
import com.company.Entities.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SQLProductDAO implements ProductDAO {


    @Override
    public void createProduct(String name) {
        try(Statement statement = SQLConnection.getConnection().createStatement()){
            String sql = String.format("INSERT INTO Product VALUES ('%s')", name);
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Product> getProducts() {
        ArrayList<Product> result = new ArrayList<>();
        try(Statement statement = SQLConnection.getConnection().createStatement()){
            String sql = String.format("SELECT * FROM product");
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                Product product = new Product();
                product.setName(rs.getString(1));
                result.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
