package com.company.Dao.CSV;

import com.company.Dao.ProductDAO;
import com.company.Entities.Product;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class CSVProductDAO implements ProductDAO {
    @Override
    public void createProduct(String name) {
        try {
            Path path = Paths.get("products.csv");
            List<String> lines = Files.readAllLines(path, UTF_8);

            if(lines.stream().skip(1).anyMatch(x -> x.split(",")[0].equals(name)))
                throw new Exception("Такой продукт уже существует");

            lines.add(name + ",");
            Files.write(path, lines, UTF_8);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Product> getProducts() {

        return null;
    }
}
