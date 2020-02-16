package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LottoMaxTest {
    private LottoMax lottoMax;

    @BeforeEach
    void runBefore()  {
        lottoMax = new LottoMax("./data/historyInfo.txt");
        lottoMax.readFile();
        lottoMax.viewStat();
    }
    @Test
    void testGetHistory() {
        assertEquals(100,lottoMax.getHistory().length);
    }

    @Test
    void testReadFile() {
            lottoMax.readFile();

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
}