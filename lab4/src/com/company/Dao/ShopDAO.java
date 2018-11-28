package com.company.Dao;

import com.company.Entities.Shop;

import java.util.ArrayList;
import java.util.HashMap;

public interface ShopDAO {
    void createShop(int id, String name, String address);
    ArrayList<Shop> getShops();
    void deliverProducts(int id, String product, int number, Double price);
    ArrayList<Integer> shopIdWithCheapestProduct(String name);
    String buySomeProducts(int id, Double money);
    Double buyGoods(int id, HashMap<String, Integer> goods);
    int cheapestGoods(HashMap<String, Integer> goods);
}
