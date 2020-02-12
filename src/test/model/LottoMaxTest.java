package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class LottoMaxTest {
    private LottoMax lottoMax;

    @BeforeEach
    void runBefore() throws FileNotFoundException {
        lottoMax = new LottoMax("/Users/aaronwang/Desktop/cpsc 210/project_v9z2b/test_data/historyInfo.txt");
        lottoMax.readFile();
        lottoMax.viewStat();
    }

    @Test
    public void testReadFile() throws FileNotFoundException {
            lottoMax.readFile();

    }

    @Test
    public void testViewStat() {
        int [] x = lottoMax.viewStat();
        assertEquals(11,x[0]);
        assertEquals(17,x[1]);
    }

    @Test
    public void testPrediction(){
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
        int[] ret = lottoMax.doSimulator(new int[]{3,12,21,29,34,37,42});
        for (int i : ret){
            System.out.println(i);
        }
    }
}