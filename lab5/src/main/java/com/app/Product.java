package com.app;

import com.app.annotations.XmlAttribute;
import com.app.annotations.XmlObject;
import com.app.annotations.XmlTag;

@XmlObject
public class Product {

    double price;

    @XmlAttribute
    String name;

    public Product(String name, double price){
        this.name = name;
        this.price = price;
    }

    @XmlTag
    public double getPrice(){
        return price;
    }
}

