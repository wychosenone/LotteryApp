package persistence;

import model.TicketNo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    public static List<String> readFile(String fileName) throws IOException {
        return Files.readAllLines((new File(fileName)).toPath());
    }

    public static List<TicketNo> parseRecord(List<String> lines) {
        List<TicketNo> ticketNoList = new ArrayList<>();
        for (String line : lines) {
            String[] ss = line.split("-");
            int[] record = new int[7];
            for (int j = 0; j < 7; j++) {
                record[j] = Integer.parseInt(ss[j]);
            }
            ticketNoList.add(new TicketNo(record));
        }
        return ticketNoList;
    }


}
