package persistence;

import model.LottoRecord;
import model.TicketNo;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WriterTest {
    @Test
    public void testWriter() throws IOException {
        List<String> records = Reader.readFile("./data/historyInfo_test.txt");
        List<TicketNo> ticketNoList = Reader.parseRecord(records);
        LottoRecord record = new LottoRecord(ticketNoList);
        int numOfRecord = ticketNoList.size();
        record.add(new TicketNo(new int[]{1, 2, 3, 4, 5, 6, 7}));
        Writer.write("./data/historyInfo_test.txt", record);

        records = Reader.readFile("./data/historyInfo_test.txt");
        ticketNoList = Reader.parseRecord(records);
        assertEquals(numOfRecord + 1, ticketNoList.size());

    }

}
