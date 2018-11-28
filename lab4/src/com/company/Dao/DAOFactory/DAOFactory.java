package com.company.Dao.DAOFactory;

import com.company.Dao.ProductDAO;
import com.company.Dao.ShopDAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class DAOFactory {

    public abstract ProductDAO getProductDAO();
    public abstract ShopDAO getShopDAO();

    public static DAOFactory getDAOFactory(){
        Properties config = new Properties();
        try {
            config.load(new FileInputStream(System.getProperty("user.dir") + "\\config.properties"));
            switch (config.getProperty("storage")){
                case "db":
                    return new SQLDAOFactory();
                case "csv":
                    return new CSVDAOFactory();
                default:
                    return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
