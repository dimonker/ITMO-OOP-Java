package com.company;

import java.util.Comparator;

class ComparatorFraction implements Comparator<Fraction> {

    @Override
    public int compare(Fraction o1, Fraction o2) {
        return Double.compare(o1.value(), o2.value());
    }
}

public class Fraction implements Cloneable {
    private int n;
    private int m = 1;

    public Fraction(int n, int m) {
        this.n = n;
        this.m = m;
    }

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setM(int m) {
        this.m = m;
    }

    public double value() {
        return (double) n / m;
    }

    @Override
    public String toString() {
        return n + "/" + m;
    }

    public Fraction clone() throws CloneNotSupportedException {
        return (Fraction) super.clone();
    }
}