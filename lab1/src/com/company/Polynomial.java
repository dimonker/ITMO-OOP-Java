package com.company;

import java.util.ArrayList;

public class Polynomial {
    private ArrayList<Fraction> coefficients;

    public Polynomial() {
        coefficients = new ArrayList<>();
    }

    public Polynomial(ArrayList<Fraction> f) {
        coefficients = f;
    }

    public Polynomial(SetOfFraction setOfFraction) {
        coefficients = setOfFraction.getFractions();
    }

    public int size() {
        return coefficients.size();
    }

    public void addCoefficient(Fraction f) {
        coefficients.add(f);
    }

    public ArrayList<Fraction> getCoefficients(){
        return (ArrayList<Fraction>) coefficients.clone();
    }

    public static Polynomial addition(Polynomial p1, Polynomial p2) {
        ArrayList<Fraction> result = new ArrayList<>();
        int p1Size = p1.size();
        int p2Size = p2.size();

        for (int i = 0; i < Math.min(p1Size, p2Size); i++) {
            int n1 = p1.coefficients.get(i).getN();
            int m1 = p1.coefficients.get(i).getM();
            int n2 = p2.coefficients.get(i).getN();
            int m2 = p2.coefficients.get(i).getM();
            result.add(new Fraction(n1 * m2 + m1 * n2, m1 * m2));
        }

        Polynomial maxPolynomial = p1Size > p2Size ? p1 : p2;
        for (int i = Math.min(p1Size, p2Size); i < Math.max(p1Size, p2Size); i++) {
            try {
                result.add(maxPolynomial.coefficients.get(i).clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }

        return new Polynomial(result);
    }

    public void printPolynomial() {
        int i = 0;
        for (Fraction f : coefficients) {
            System.out.println(i++ + " " + f);
        }
    }
}
