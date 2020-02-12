package ui;


import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new JackPotKingAPP();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
