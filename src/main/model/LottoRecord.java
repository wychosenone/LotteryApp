package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoRecord {
    private List<TicketNo> ticketNoList = new ArrayList<>();

    public void add(TicketNo ticketNo) {
        ticketNoList.add(ticketNo);
    }

    public List<TicketNo> getTicketNoList() {
        return Collections.unmodifiableList(ticketNoList);
    }
}
