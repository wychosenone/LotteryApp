package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.Reader;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReaderTest {


    @Test
    public void testparseRecord() throws IOException {
        List<String> records = Reader.readFile("./data/historyInfo.txt");
        List<TicketNo> ticketNoList = Reader.parseRecord(records);
        assertEquals(100, ticketNoList.size());
    }

}
