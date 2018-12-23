package com.app;

import com.app.annotations.XmlAttribute;
import com.app.annotations.XmlObject;
import com.app.annotations.XmlTag;

@XmlObject
public class Person {
    @XmlTag(name = "fullname")
    private final String name;

    @XmlAttribute(tag = "fullname")
    private final String lang;

    @XmlTag
    Comp comp;

    private final int age;

    public Person(String name, String lang, int age) {
        this.name = name;
        this.lang = lang;
        this.age = age;
    }


    public void setComp(Comp comp) {
        this.comp = comp;
    }



    public int getAge() {
        return age;
    }
}