package com.company.Dao.DAOFactory;

import com.company.Dao.ProductDAO;
import com.company.Dao.SQL.SQLProductDAO;
import com.company.Dao.SQL.SQLShopDAO;
import com.company.Dao.ShopDAO;

public class SQLDAOFactory extends DAOFactory {

    @Override
    public ProductDAO getProductDAO() {
        return new SQLProductDAO();
    }

    @Override
    public ShopDAO getShopDAO() {
        return new SQLShopDAO();
    }
}
