package model;

import java.util.ArrayList;
import java.util.List;

public class TicketNo {
    private int[] numbers = new int[7];

    public TicketNo(int[] numbers) {
        this.numbers = numbers;
    }

    public int[] getNumbers() {
        return numbers;
    }

    @Override
    public String toString() {
        String s = "";
        for (int n : numbers) {
            s = s + n + "-";
        }
        return s.substring(0, s.length() - 1);
    }






}
