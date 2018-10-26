package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ConfigurationHandler {
    private HashMap<String, HashMap<String, Value>> hashMap = new HashMap<>();

    public ConfigurationHandler() {
    }

    public <T> T getValue(Class type, String parameter, String section) {
        HashMap<String, Value> inner = hashMap.get(section);
        if (inner == null)
            throw new IllegalArgumentException("Section doesn't exists");

        Value v = inner.get(parameter);
        if (v == null)
            throw new IllegalArgumentException("Parameter doesn't exist");

        if (!(type == v.getClazz()))
            throw new IllegalArgumentException("Invalid parameter type");

        return v.getValue();
    }

    public void Run(File file) {
        try {
            Scanner in = new Scanner(file);

            Pattern fileExtension = Pattern.compile("\\.ini$");
            if (!fileExtension.matcher(file.toString()).find())
                throw new FileNotFoundException(file + " (Неверный формат файла)");

            String currentSection = null;
            while (in.hasNext()) {
                String line = in.nextLine().split(";")[0].trim();
                if (line.isEmpty()) {
                    continue;
                }
                if (line.charAt(0) == '[') {
                    currentSection = line.substring(1, line.length() - 1);
                    continue;
                }

                String str1 = line.split("=")[0].trim();
                String str2 = line.split("=")[1].trim();
                Pattern integerPattern = Pattern.compile("[\\d]+");
                Pattern doublePattern = Pattern.compile("[\\d]+\\.[\\d]+");

                Value v;
                if (integerPattern.matcher(str2).matches()) {
                    Integer i = Integer.parseInt(str2);
                    v = new Value(i, Integer.class);
                } else if (doublePattern.matcher(str2).matches()) {
                    Double d = Double.parseDouble(str2);
                    v = new Value(d, Double.class);
                } else {
                    v = new Value(str2, String.class);
                }

                HashMap<String, Value> inner = hashMap.get(currentSection);
                if (inner == null) {
                    inner = new HashMap<>();
                    hashMap.put(currentSection, inner);
                }
                inner.put(str1.trim(), v);

            }

        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
