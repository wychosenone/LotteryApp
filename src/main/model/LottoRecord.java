package model;

import persistence.Writable;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoRecord implements Writable {
    private List<TicketNo> ticketNoList = new ArrayList<>();

    public LottoRecord() {

    }

    public LottoRecord(List<TicketNo> ticketNoList) {
        this.ticketNoList = ticketNoList;
    }

    public void add(TicketNo ticketNo) {
        ticketNoList.add(ticketNo);
    }

    public List<TicketNo> getTicketNoList() {
        return Collections.unmodifiableList(ticketNoList);
    }

    @Override
    public void write(Writer writer) throws IOException {
        for (TicketNo ticket: ticketNoList) {
            writer.write(ticket.toString() + "\n");
        }
    }
}
