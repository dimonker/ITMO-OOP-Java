package com.company.Dao.CSV;

import com.company.Dao.ShopDAO;
import com.company.Entities.Shop;
import javafx.util.Pair;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.charset.StandardCharsets.UTF_8;

public class CSVShopDAO implements ShopDAO {
    @Override
    public void createShop(int id, String name, String address) {
        try {
            Path path = Paths.get("shops.csv");
            List<String> lines = Files.readAllLines(path, UTF_8);

            if (lines.stream().skip(1).anyMatch(x -> x.split(",")[0].equals(Integer.toString(id))))
                throw new Exception("Такой магазин уже существует");

            lines.add(String.format("%d,%s", id, name));
            Files.write(path, lines, UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Shop> getShops() {
        List<Shop> shops = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get("shops.csv"))) {
            shops = stream.skip(1).map(x -> {
                String[] str = x.split(",");
                return new Shop(Integer.parseInt(str[0]), str[1]);
            }).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>(shops);
    }


    @Override
    public void deliverProducts(int id, String product, int number, Double price) {
        try {
            Path path = Paths.get("products.csv");
            List<String> lines = Files.readAllLines(path, UTF_8);

            int l = 0;
            for (String line : lines) {
                if (line.split(",")[0].trim().equals(product))
                    break;
                l++;
            }
            String line = lines.get(l);
            String[] oldLine = lines.get(l).split(",");
            List<String> newLine = Arrays.asList(lines.get(l).split(","));
            boolean productExisted = false;
            for (int i = 1; i < oldLine.length; i += 3) {
                Integer currentId = Integer.parseInt(oldLine[i]);
                int currentNumber = Integer.parseInt(oldLine[i + 1]);
                double currnetPrice = Double.parseDouble(oldLine[i + 2]);

                if (currentId == id) {
                    newLine.set(i + 1, Integer.toString(currentNumber + number));
                    newLine.set(i + 2, price.toString());
                    productExisted = true;
                    break;
                }
            }
            if (productExisted){
                lines.set(l, String.join(",", newLine));
            }else {
                lines.set(l, line
                        + (line.charAt(line.length() - 1) == ',' ? "" : ",")
                        + String.format("%d,%d,%s", id, number, price.toString()));
            }


            Files.write(path, lines, UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public ArrayList<Integer> shopIdWithCheapestProduct(String name) {
        ArrayList<Integer> result = new ArrayList<>();
        try {
            Path path = Paths.get("products.csv");
            List<String> lines = Files.readAllLines(path, UTF_8);

            String[] product = null;
            for (String line : lines) {
                if (line.split(",")[0].trim().equals(name)) {
                    product = line.split(",");
                    break;
                }
            }

            if (product == null)
                throw new Exception("Нет такого товара");

            double minPrice = Double.MAX_VALUE;
            for (int i = 1; i < product.length; i += 3) {
                int id = Integer.parseInt(product[i]);
                double price = Double.parseDouble(product[i + 2]);

                if (price == minPrice)
                    result.add(id);
                else if (price < minPrice) {
                    result.clear();
                    result.add(id);
                    minPrice = price;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private HashMap<String, Pair<Integer, Double>> getAllProductsInShop(int id) {
        HashMap<String, Pair<Integer, Double>> result = new HashMap<>();
        Path path = Paths.get("products.csv");
        try {
            List<String> lines = Files.readAllLines(path, UTF_8);
            lines.remove(0);
            for (String line : lines) {
                String[] shop = line.split(",");
                for (int i = 1; i < shop.length; i += 3) {
                    int shopId = Integer.parseInt(shop[i]);
                    if (shopId == id) {
                        int number = Integer.parseInt(shop[i + 1]);
                        double price = Double.parseDouble(shop[i + 2]);
                        result.put(shop[0], new Pair<>(number, price));
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public String buySomeProducts(int id, Double money) {
        HashMap<String, Pair<Integer, Double>> products = getAllProductsInShop(id);
        Double sum = 0.0;
        StringBuilder str = new StringBuilder();
        for (String name : products.keySet()) {
            int number = products.get(name).getKey();
            Double price = products.get(name).getValue();
            int k = (int) ((money - sum) / price);
            if (k > number)
                k = number;

            if (k != 0) {
                sum += k * price;
                str.append(name).append(" ").append(k).append(", ");
            }
        }
        return str.toString();
    }

    @Override
    public Double buyGoods(int id, HashMap<String, Integer> goods) {
        HashMap<String, Pair<Integer, Double>> products = getAllProductsInShop(id);

        double sum = 0.0;
        ArrayList<String> bought = new ArrayList<>();
        for (String name : products.keySet()) {
            if (!goods.containsKey(name))
                continue;

            int requiredNumber = goods.get(name);
            int numberInShop = products.get(name).getKey();
            double price = products.get(name).getValue();
            if (numberInShop >= requiredNumber) {
                sum += requiredNumber * price;
                bought.add(name);
            } else return -1.0;//если недостаточно товара
        }
        if (!bought.containsAll(goods.keySet()))
            sum = 0;
        return sum == 0 ? -1.0 : sum;
    }

    @Override
    public int cheapestGoods(HashMap<String, Integer> goods) {
        int minId = -1;
        ArrayList<Shop> shops = getShops();

        double min = Double.MAX_VALUE;

        for (Shop shop : shops) {
            int id = shop.getId();
            double currentSum = buyGoods(id, goods);
            if (currentSum != -1.0 && currentSum < min) {
                min = currentSum;
                minId = id;
            }
        }
        return minId;
    }


}
