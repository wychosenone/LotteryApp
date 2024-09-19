package model;

import exception.InvalidTicketNoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

class LottoMaxTest {
    private LottoMax lottoMax;

    @BeforeEach
    void runBefore() throws IOException {
        lottoMax = new LottoMax("./data/historyInfo_test.txt");

    }
    @Test
    void testGetHistory() {
        assertTrue(lottoMax.getHistory().getTicketNoList().size() > 0);
    }



    @Test
    void testViewStat() {
        int [] x = lottoMax.viewStat();
        assertEquals(11,x[0]);
        assertEquals(17,x[1]);
    }

    @Test
    void testPrediction(){
        int[] x = lottoMax.doPrediction();
        assertEquals(3,x[0]);
        assertEquals(12,x[1]);
        assertEquals(22,x[2]);
        assertTrue(x[3] >= 28 && x[3] <= 33 );
        assertEquals(35,x[4]);
        assertTrue(x[5] >= 40 && x[5] <= 50 );
        assertTrue(x[6] >= 40 && x[6] <= 50);
        assertTrue( x[5] != x[6]);

    }


    @Test
    void testSimulator() {
        int[] ret1 = lottoMax.doSimulator(new int[]{3,12,21,29,34,37,42});
        for (int i : ret1){
            System.out.println(i);
        }

        int[] ret2 = lottoMax.doSimulator(new int[]{4,19,23,24,35,39,43});
        for (int i : ret2) {
            System.out.println(i);
        }
    }

    @Test
    public void testAddNewRecord() throws FileNotFoundException, UnsupportedEncodingException {
        int originalNum = lottoMax.getHistory().getTicketNoList().size();
        lottoMax.addNewRecord(new int[]{8,12,19,22,25,32,42});
        assertEquals(originalNum + 1,lottoMax.getHistory().getTicketNoList().size());
    }

    @Test
    public void testParseNo() {
        try {
            LottoMax.parseNo("er 123");
            fail("should throw exception");
        } catch (InvalidTicketNoException e) {
            //
        }
    }

    @Test
    void testOutOfBoundary() {
        try {
            LottoMax.parseNo("1 2 3 4 5 6 7 8");
            fail("should throw exception");
        } catch (InvalidTicketNoException e) {
            //
        }
    }

    @Test
    public void testParseNoNoError() throws InvalidTicketNoException {
        try {
            int [] result = LottoMax.parseNo("1 2 3 4 5 6 7");
            assertEquals(7,result.length);
            assertEquals(1,result[0]);
            assertEquals(7,result[6]);
        } catch (NumberFormatException e) {
            fail("should not throw exception");
        }
    }
}