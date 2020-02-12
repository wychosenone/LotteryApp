package ui;



import model.LottoMax;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class JackPotKingAPP {

    private Scanner input;
    private LottoMax lottoMax;

    // EFFECTS: runs the JackPotKing application
    public JackPotKingAPP() throws FileNotFoundException {
        lottoMax = new LottoMax("/Users/aaronwang/Desktop/cpsc 210/project_v9z2b/data/historyInfo.txt");
        lottoMax.readFile();
        lottoMax.viewStat();
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
        System.out.println("\tq -> quit");
    }

    // REQUIRES: .txt file can be found
    // EFFECTS: get access to the .txt file
    private void historyInfo() {
        for (int[] row : lottoMax.getHistory()) {
            for (int cell : row) {
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

    // REQUIRES: data can be loaded from .txt file
    // EFFECTS: return a list of numbers
    private void doPrediction() {
        int[] r = lottoMax.doPrediction();
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
