package model;

import java.util.Arrays;
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

    public void readFile(){
        Scanner sc = new Scanner(getClass().getClassLoader().getResourceAsStream(filename));
        for (int i = 0; i < history.length; i++) {
            String line = sc.nextLine();
            String[] ss = line.split("-");
            for (int j = 0; j < 7; j++) {
                history[i][j] = Integer.parseInt(ss[j]);
            }
        }
        sc.close();
    }

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


    private boolean isIn(int[] x, int n) {
        for (int i = 0; i < x.length; i++) {
            if (x[i] == n) {
                return true;
            }
        }
        return false;
    }

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
