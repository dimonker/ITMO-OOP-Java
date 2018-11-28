package com.company.Dao.DAOFactory;

import com.company.Dao.CSV.CSVProductDAO;
import com.company.Dao.CSV.CSVShopDAO;
import com.company.Dao.ProductDAO;
import com.company.Dao.ShopDAO;

public class CSVDAOFactory extends DAOFactory {

    @Override
    public ProductDAO getProductDAO() {
        return new CSVProductDAO();
    }

    @Override
    public ShopDAO getShopDAO() {
        return new CSVShopDAO();
    }
}
