package ui;


import exception.InvalidTicketNoException;
import model.LottoMax;
import model.TicketNo;
import org.omg.CORBA.DynAnyPackage.Invalid;
import persistence.Writer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;1
import java.util.Scanner;

public class JackPotKingAPP {

    private Scanner input;
    private LottoMax lottoMax;
    private static final String HISTORY_FILE = "./data/historyInfo.txt";

    // EFFECTS: runs the JackPotKing application
    public JackPotKingAPP() throws IOException {
        lottoMax = new LottoMax(HISTORY_FILE);
        runJackPot();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runJackPot() {
        boolean goAhead = true;
        String mission = null;
        input = new Scanner(System.in);


        while (goAhead) {
            displayMenu();
            mission = input.next();
            mission = mission.toLowerCase();

            if (mission.equals("q")) {
                goAhead = false;
            } else {
                processMission(mission);
            }
        }

        System.out.println("\nCya");
    }


    // MODIFIES: this
    // EFFECTS: processes user command
    private void processMission(String mission) {
        if (mission.equals("h")) {
            historyInfo();
        } else if (mission.equals("v")) {
            viewStat();
        } else if (mission.equals("p")) {
            doPrediction();
        } else if (mission.equals("t")) {
            doSimulator();
        } else if (mission.equals(("a"))) {
            addNewRecord();
        } else {
            System.out.println("Selected invalid command");
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nWelcome to JackPotKing");
        System.out.println("\nSelect from:");
        System.out.println("\th -> historyInfo");
        System.out.println("\tv -> viewStat");
        System.out.println("\tp -> doPrediction");
        System.out.println("\tt -> doSimulator");
        System.out.println("\ta -> addNewRecord");
        System.out.println("\tq -> quit");
    }

    // REQUIRES: .txt file can be found
    // EFFECTS: get access to the .txt file
    private void historyInfo() {
        for (TicketNo row : lottoMax.getHistory().getTicketNoList()) {
            for (int cell : row.getNumbers()) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    // REQUIRES: data can be accessed from .txt file
    // EFFECTS: return a hot and a cold number
    private void viewStat() {
        int[] ret = lottoMax.viewStat();
        System.out.println("Hot: " + ret[0]);
        System.out.println("Cold: " + ret[1]);
    }

    private void addNewRecord() {

        System.out.println("Please input 7 different numbers,space by one blank:");
        input.nextLine();
        String s = input.nextLine();
        int[] data = new int[7];
        int p = 0;
        for (String d : s.split(" ")) {
            data[p++] = Integer.parseInt(d);
        }
        try {
            lottoMax.addNewRecord(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }


    // REQUIRES: data can be loaded from .txt file
    // EFFECTS: return a list of numbers
    private void doPrediction() {
        int[] r = lottoMax.doPrediction();
        System.out.println("The numbers below are the suggestions for you:");
        for (int i : r) {
            System.out.print(i + " ");
        }

    }

    // REQUIRES: input numbers should not be equal&& numbers are less than 50
    // EFFECTS: return how many numbers match and what are these numbers

    private void doSimulator() {
        System.out.println("Please input 7 different numbers,space by one blank:");
        String s = input.nextLine();
        s = input.nextLine();
        int[] data = new int[7];
        int p = 0;
        for (String d : s.split(" ")) {
            data[p++] = Integer.parseInt(d);
        }

        int[] ret = lottoMax.doSimulator(data);
        System.out.print("you match " + ret[0] + " numbers,");
        String t = "";
        if (ret[0] > 0) {
            for (int i = 1; i <= ret[0]; i++) {
                t = t + ret[i] + " ";
            }
            System.out.println("They are: " + t);
        }


    }
}
