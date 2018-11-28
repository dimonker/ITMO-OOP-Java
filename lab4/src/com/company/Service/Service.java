package com.company.Service;

import com.company.Dao.DAOFactory.DAOFactory;
import com.company.Dao.ShopDAO;
import com.company.Entities.Shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;


public class Service {
    private DAOFactory daoFactory;

    public Service(){
        daoFactory = DAOFactory.getDAOFactory();
    }

    //1
    public void createShop(String name, String address){
        ShopDAO shopDAO = daoFactory.getShopDAO();
        HashSet<Integer> shopsId = new HashSet<>();
        shopDAO.getShops().forEach(x -> shopsId.add(x.getId()));
        Random rand = new Random();
        int newId = rand.nextInt();
        do{
            newId = rand.nextInt(100) + 1;
        }while (shopsId.contains(newId));
        shopDAO.createShop(newId, name, address);
    }

    //2
    public void createProduct(String name){
        daoFactory.getProductDAO().createProduct(name);
    }


    private Shop findShopByName(String nameShop) throws Exception {
        ShopDAO shopDAO = daoFactory.getShopDAO();
        ArrayList<Shop> shops = shopDAO.getShops();
        Shop shop = null;
        for (Shop  s: shops){
            if (s.getName().equals(nameShop)){
                return s;
            }
        }

        throw new Exception("Нет такого магазина");
    }
    //3
    public void deliverProductsToShop(String shopName, String product, int number, Double price) throws Exception {
        ShopDAO shopDAO = daoFactory.getShopDAO();
        int id = findShopByName(shopName).getId();
        daoFactory.getShopDAO().deliverProducts(id, product, number, price);
    }

    //4
    public ArrayList<Shop> shopsWithCheapestProduct(String name){
        ShopDAO shopDAO = daoFactory.getShopDAO();
        ArrayList<Integer> ids = shopDAO.shopIdWithCheapestProduct(name);
        ArrayList<Shop> shops = new ArrayList<>();
        for (Shop s : shopDAO.getShops()){
            if (ids.contains(s.getId()))
                shops.add(s);
        }
        return shops;
    }

    //5
    public String buySomeProducts(String nameShop, double money) throws Exception {
        ShopDAO shopDAO = daoFactory.getShopDAO();
        Shop shop = null;

        shop = findShopByName(nameShop);

        return shopDAO.buySomeProducts(shop.getId(), money);
    }

    //6
    public double buyGoods(String nameShop, HashMap<String, Integer> goods){
        ShopDAO shopDAO = daoFactory.getShopDAO();
        Shop shop = null;
        try {
            shop = findShopByName(nameShop);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return shopDAO.buyGoods(shop.getId(), goods);
    }

    //7
    public Shop cheapestGoods(HashMap<String, Integer> goods){
        ShopDAO shopDAO = daoFactory.getShopDAO();
        int id = shopDAO.cheapestGoods(goods);
        Shop shop = null;
        for (Shop s : shopDAO.getShops()){
            if (s.getId() == id){
                shop = s;
                break;
            }
        }
        return shop;
    }
}
