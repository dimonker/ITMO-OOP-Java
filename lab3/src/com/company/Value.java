package com.company;


public class Value {
    private Object _value;
    private Class _clazz;

    public Value(Object value, Class clazz) {
        _value = value;
        _clazz = clazz;
    }

    public <T> T getValue() {
        return (T) _value;
    }

    public Class getClazz() {
        return _clazz;
    }
}
