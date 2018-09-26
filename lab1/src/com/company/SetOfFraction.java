package com.company;

import java.io.File;
import java.util.*;

public class SetOfFraction {
    private ArrayList<Fraction> fractions = new ArrayList<>();
    private boolean maxIsCached = false;
    private boolean minIsCached = false;
    private Fraction maxValue;
    private Fraction minValue;

    private TreeMap<Fraction, Integer> cacheLower = new TreeMap<>(new ComparatorFraction());
    private TreeMap<Fraction, Integer> cacheHigher = new TreeMap<>(new ComparatorFraction());

    public SetOfFraction() {
    }

    public void addFraction(Fraction... fraction) {
        for (Fraction n : fraction){
            try {
                if (maxValue != null && maxValue.value() < n.value()) {
                    maxValue = n.clone();
                    maxIsCached = true;
                }
                if (minValue != null && minValue.value() > n.value()) {
                    minValue = n.clone();
                    minIsCached = true;
                }
                cacheLower.clear();
                cacheHigher.clear();
                fractions.add(n);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }

    }

    public ArrayList<Fraction> getFractions() {
        return (ArrayList<Fraction>) fractions.clone();
    }

    public int numberHigher(Fraction f) {
        Integer cache = cacheHigher.get(f);
        if (cache != null) {
            return cache;
        }
        int number = 0;
        for (int i = 0; i < fractions.size(); i++) {
            if (f.value() < fractions.get(i).value())
                number++;
        }
        cacheHigher.put(f, number);
        return number;
    }

    public int numberLower(Fraction f) {
        Integer cache = cacheLower.get(f);
        if (cache != null) {
            return cache;
        }
        int number = 0;
        for (int i = 0; i < fractions.size(); i++) {
            if (f.value() > fractions.get(i).value())
                number++;
        }
        cacheLower.put(f, number);
        return number;
    }

    public Fraction getMax() {
        try {
            if (maxIsCached) return maxValue.clone();
            if (fractions.size() == 0) throw new Exception();
            maxValue = fractions.get(0);
            for (int i = 0; i < fractions.size(); i++) {
                if (maxValue.value() < fractions.get(i).value())
                    maxValue = fractions.get(i).clone();
            }
            maxIsCached = true;
            return maxValue.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Fraction getMin() {
        try {
            if (minIsCached) return minValue.clone();
            if (fractions.size() == 0) throw new Exception();
            minValue = fractions.get(0);
            for (int i = 0; i < fractions.size(); i++) {
                if (minValue.value() > fractions.get(i).value())
                    minValue = fractions.get(i).clone();
            }
            minIsCached = true;
            return minValue.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void PrintSet() {
        System.out.println(fractions.size());
        for (int i = 0; i < fractions.size(); i++) {
            System.out.println(i + ": " + fractions.get(i));
        }
        System.out.println();
    }

    public void ReadFile(String fileName) {
        try {
            Scanner in = new Scanner(new File(fileName));
            int m, n;
            while (in.hasNextInt()) {
                m = in.nextInt();
                n = in.nextInt();
                addFraction(new Fraction(m, n));
            }
            in.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

}
