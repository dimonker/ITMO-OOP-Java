package com.app;



import java.util.HashSet;

public class Main {
    public static void main(String[] args){
        Comp product = new Comp("MSI", 200.0, "Black");
        Person person1 = new Person("Sergey", "RUS", 32);
        person1.setComp(product);
        Person person2 = new Person("Alexey", "RUS", 31);
        HashSet<Object> objects = new HashSet<>();
        objects.add(person1);
        objects.add(person2);
        objects.add(product);
        Serializer serializer = new Serializer();
        try {
            serializer.serialize(objects);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
