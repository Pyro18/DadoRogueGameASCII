package dev.dado.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class ResponsabileRisorse {

    private static BufferedReader reader;


    public static ArrayList<String> readFloorFile(String fileName) {
        System.out.println("[ResponsabileRisorse]: Lettura di " + fileName);

        ArrayList<String> strings = new ArrayList<String>();

        try {
            reader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("[ResponsabileRisorse] [ERRORE]: file " + fileName + " non trovato!");
        }

        try {
            String str = reader.readLine();
            strings.add(str);

            while (str != null) {
                str = reader.readLine();
                strings.add(str);
            }

        } catch (IOException e) {
            System.out.println("[ResponsabileRisorse] [ERRORE]: IOException");
        }

        return strings;
    }
}
