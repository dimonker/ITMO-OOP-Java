package com.company;


import com.company.Service.Service;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        try {


            Service service = new Service();
            service.deliverProductsToShop("Магнит", "Телевизор Philips", 1, 20000.0);


            System.out.println(service.shopsWithCheapestProduct("Шоколад `Аленка`"));
            System.out.println(service.buySomeProducts("Магнит", 100.0));

            HashMap<String, Integer> goods = new HashMap<>();
            goods.put("Шоколад `Аленка`", 10);
            goods.put("Молоко", 20);

            System.out.println(service.buyGoods("Магнит", goods));
            System.out.println(service.cheapestGoods(goods));


        } catch (Exception e) {
            System.out.println(e);
        }

    }

}