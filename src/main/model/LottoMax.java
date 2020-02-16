package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class LottoMax {
    private String filename;
    int[][] history = new int[100][7];
    Pair[] pairs = new Pair[51];
    Random rnd = new Random();

    public LottoMax(String dataFile) {
        filename = dataFile;
    }

    public int[][] getHistory() {
        return history;
    }

    class Pair implements Comparable<Pair> {
        public Pair(int num, int count) {
            this.num = num;
            this.count = count;
        }

        int num;
        int count;

        @Override
        public int compareTo(Pair o) {
            return o.count - this.count;
        }
    }

    // REQUIRES: saved files can be found in data
    // EFFECTS: return and print the contents from a file
    public void readFile() {
        List<String> lines = null;
        try {
            lines = Files.readAllLines((new File(filename)).toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        int i = 0;
        for (String line : lines) {
//        Scanner sc = new Scanner(getClass().getClassLoader().getResourceAsStream(filename));
//        for (int i = 0; i < history.length; i++) {
//            String line = sc.nextLine();
            String[] ss = line.split("-");
            for (int j = 0; j < 7; j++) {
                history[i][j] = Integer.parseInt(ss[j]);
            }
            i++;
        }
//        sc.close();
    }


    //REQUIRES: files can be loaded from data directory
    //EFFECTS: return a list of two numbers that represent hot and cold
    public int[] viewStat() {
        int[] all = new int[51];
        for (int i = 0; i < history.length; i++) {
            for (int j = 0; j < 7; j++) {
                all[history[i][j]]++;
            }
        }

        for (int i = 0; i < all.length; i++) {
            pairs[i] = new Pair(i, all[i]);
        }
        Arrays.sort(pairs);
        return new int[]{pairs[0].num,pairs[49].num};

    }


    //REQUIRES: files can be loaded from data directory
    //EFFECTS: return a list of 7 numbers represents prediction
    public int[] doPrediction() {
        int[] x = new int[7];
        for (int i = 49; i >= 0; i--) {
            if (pairs[i].num < 10 && pairs[i].num % 2 == 1) {
                x[0] = pairs[i].num;
                break;
            }
        }

        for (int i = 0; i < 50; i++) {
            if (pairs[i].num >= 10 && pairs[i].num < 20 && pairs[i].num % 2 == 0) {
                x[1] = pairs[i].num;
                break;
            }
        }

        helper1(x);
        helper2(x);
        return x;
    }

    //REQUIRES: files can be loaded from data
    //MODIFIES: this
    //EFFECTS: help doPrediction method continuing creating numbers
    private void helper1(int[] x) {

        for (int i = 0; i < 50; i++) {
            if (pairs[i].num >= 20 && pairs[i].num < 28 && pairs[i].num % 2 == 0) {
                x[2] = pairs[i].num;
                break;
            }
        }

        x[3] = 28 + rnd.nextInt(5);
        for (int i = 49; i >= 0; i--) {
            if (pairs[i].num >= 33 && pairs[i].num < 40 && pairs[i].num % 2 == 1) {
                x[4] = pairs[i].num;
                break;
            }
        }

    }

    //REQUIRES: files can be loaded from data
    //MODIFIES: this
    //EFFECTS: help doPrediction method continuing creating numbers
    private void helper2(int[] x) {
        int a = rnd.nextInt(11);
        int b = rnd.nextInt(11);
        while (b == a) {
            b = rnd.nextInt(11);
        }
        if (a > b) {
            int c = a;
            a = b;
            b = c;
        }
        x[5] = 40 + a;
        x[6] = 40 + b;
    }


    //REQUIRES: list is not empty, and bot list int are positive under 50
    //EFFECTS: return true if item in list equals to the int, false otherwise
    private boolean isIn(int[] x, int n) {
        for (int i = 0; i < x.length; i++) {
            if (x[i] == n) {
                return true;
            }
        }
        return false;
    }

    //REQUIRES: the int must be bounded under 50
    //EFFECTS: return a random number list as a result  of lottery
    private int[] getResult() {
        int[] result = new int[7];
        int i = 0;
        while (i < 7) {
            int n = 1 + rnd.nextInt(50);
            if (!isIn(result, n)) {
                result[i++] = n;
            }
        }
        Arrays.sort(result);
        return result;
    }

    //REQUIRES: the input value should be 7 different positive numbers under 50
    //MODIFIES: this
    //EFFECTS: return a list of two numbers representing num of total match and matched numbers

    public int[] doSimulator(int[] input) {
        int[] result = getResult();
        int count = 0;
        for (int num : input) {
            if (isIn(result, num)) {
                count++;
            }
        }

        int[] ret = new int[count + 1];
        ret[0] = count;
        int pos = 1;
        for (int num : input) {
            if (isIn(result, num)) {
                ret[pos++] = num;
            }
        }
        return ret;
    }

}
