package com.app;

import com.app.annotations.XmlObject;
import com.app.annotations.XmlTag;


@XmlObject
public class Comp extends Product{

    @XmlTag
    private String color;

    Comp(String name, double price, String color){
        super(name, price);
        this.color = color;
    }

    @XmlTag
    public String getColorMethod(){
        return color;
    }


    @Override
    public String toString() {
        return "toString()";
    }
}
