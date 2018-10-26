package com.company;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        String fileName = System.getProperty("user.dir") + "/src/conf.ini";
        ConfigurationHandler c = new ConfigurationHandler();
        c.Run(new File(fileName));

        String i = c.getValue(String.class, "Driver", "ADC_DEV");
        System.out.println(i);


    }
}
