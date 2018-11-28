package com.company.Dao.SQL;

import com.company.Dao.ShopDAO;
import com.company.Entities.Shop;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class SQLShopDAO implements ShopDAO {

    @Override
    public void createShop(int id, String name, String address) {
        try (Statement statement = SQLConnection.getConnection().createStatement()) {
            String sql = String.format("INSERT INTO shop VALUES (%d, '%s', '%s')", id, name, address);
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Shop> getShops() {
        ArrayList<Shop> result = new ArrayList<>();
        try (Statement statement = SQLConnection.getConnection().createStatement()) {
            String sql = String.format("SELECT * FROM shop");
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Shop shop = new Shop();
                shop.setId(rs.getInt(1));
                shop.setName(rs.getString(2));
                shop.setAddress(rs.getString(3));
                result.add(shop);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void update(String sql) {
        try (Statement statement = SQLConnection.getConnection().createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deliverProducts(int id, String product, int number, Double price) {
        String sql = String.format("INSERT INTO Product_In_Shop VALUES (%d,'%s',%d, %s)", id, product, number, price.toString());
        update(sql);
    }

    private ResultSet get(String queryString){
        ResultSet rs = null;
        Statement statement;
        try {
            statement = SQLConnection.getConnection().createStatement();
            rs = statement.executeQuery(queryString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    @Override
    public ArrayList<Integer> shopIdWithCheapestProduct(String name) {
        ArrayList<Integer> result = new ArrayList<>();
        String sql = String.format("select [shop id] from Product_In_Shop where price = (select min(price) from Product_In_Shop where [product name] = '%s')", name);
        ResultSet rs = get(sql);
        try{
            while (rs.next()) {
                result.add(rs.getInt(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public String buySomeProducts(int id, Double money) {
        String sql = String.format("select [product name],number,price from product_in_shop where [shop id] = %s", id);
        ResultSet rs = get(sql);

        StringBuilder str = new StringBuilder();
        double sum = 0;
        try{
            while (rs.next()){
                String name = rs.getString(1);
                Integer number = rs.getInt(2);
                double price = rs.getDouble(3);

                //количество товара которое можно купить на оставшуюся сумму
                int min = (int) ((money - sum) / price);
                //если результат больше количество товара в магазине
                if (min > number)
                    min = number;

                if (min != 0){
                    sum += min * price;
                    str.append(name).append(" ").append(min).append(", ");
                }

            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return str.toString();
    }

    @Override
    public Double buyGoods(int id, HashMap<String, Integer> goods) {
        String sql = String.format("select [product name],number,price from product_in_shop where [shop id] = %s", id);
        ResultSet rs = get(sql);

        double sum = 0;
        ArrayList<String> bought = new ArrayList<>();
        try{
            while (rs.next()){

                String product = rs.getString(1);
                if (!goods.containsKey(product)) //если товара нет в данном поле
                    continue;

                Integer requiredNumber = goods.get(product);
                Integer numberInShop = rs.getInt(2);
                double price = rs.getDouble(3);
                if (numberInShop >= requiredNumber){
                    sum += price * requiredNumber;
                    bought.add(product);
                }
                else {
                    return -1.0; //если недостаточно товара
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        if(!bought.containsAll(goods.keySet()))
            sum = 0;
        return sum == 0 ? -1.0 : sum;
    }

    @Override
    public int cheapestGoods(HashMap<String, Integer> goods) {
        String sql = String.format("select distinct [shop id] from product_in_shop");
        ResultSet rs = get(sql);
        int minId = -1;

        try {
            double min = Double.MAX_VALUE;

            while (rs.next()){
                int id = rs.getInt(1);
                double current = buyGoods(id, goods);
                if (current != -1.0 && current < min){
                    min = current;
                    minId = rs.getInt(1);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return minId;
    }
}
