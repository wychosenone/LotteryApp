package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        assertEquals(16,x[1]);
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
}