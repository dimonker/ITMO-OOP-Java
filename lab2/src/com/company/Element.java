package com.company;

public abstract class Element {
    protected String name;

    public Element(String name){
        this.name = name;
    }

    String getName(){
        return this.getClass().getSimpleName() + ": " + name;
    }

    abstract String getInfo();

    abstract void addElement(Element element);

    protected static void addReference(Element e1, Element e2){
        e1.addElement(e2);
        e2.addElement(e1);
    }

    @Override
    public String toString() {
        return getName();
    }
}
