package com.company;

public class Main {

    public static void main(String[] args)  {
        SetOfFraction setOfFraction = new SetOfFraction();

        setOfFraction.addFraction(new Fraction(1, 3));
        setOfFraction.addFraction(new Fraction( 6, 4));
        setOfFraction.addFraction(new Fraction(5,9));
        setOfFraction.addFraction(new Fraction(6,2));
        setOfFraction.addFraction(new Fraction(3, 4));
        setOfFraction.PrintSet();


        System.out.println("Min: " + setOfFraction.getMin());
        System.out.println("Max: " + setOfFraction.getMax());
        Fraction f = new Fraction(4, 3);
        System.out.println("Number of elements higher than " + f + ": " + setOfFraction.numberHigher(f));
        System.out.println("Number of elements lower than " + f + ": " + setOfFraction.numberLower(f));

        Polynomial p1 = new Polynomial(setOfFraction);
        Polynomial p2 = new Polynomial();
        p2.addCoefficient(new Fraction(1, 2));
        p2.addCoefficient(new Fraction(3, 4));
        Polynomial p3 = Polynomial.addition(p1, p2);
        System.out.println("\nPolynomial");
        p3.printPolynomial();

    }
}
