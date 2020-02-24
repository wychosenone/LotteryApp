package model;

import java.util.ArrayList;
import java.util.List;

public class TicketNo {
    public TicketNo(int[] numbers) {
        this.numbers = numbers;
    }

    public int[] getNumbers() {
        return numbers;
    }

    private int[] numbers = new int[7];
    private List<String> matchNumbers = new ArrayList<>();



}
