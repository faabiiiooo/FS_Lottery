package model;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;


public class WinTypeTest {

    private static int[] winNums = {1,32,5,41,7,19};
    private static int winLucky = 4;

    private static int[] threeNums = {1,32,5,33,8,20};
    private static int three1Lucky = 4;

    private static int[] fourNums = {1,32,5,33,8,41};

    private static int falseLucky = 1;


    private static ArrayList<Integer> winNumbers;
    private static ArrayList<Integer> threeNumbers;
    private static ArrayList<Integer> fourNumbers;

    @Before
    public void init(){

        winNumbers = new ArrayList<>();
        threeNumbers = new ArrayList<>();
        fourNumbers = new ArrayList<>();

        for(int i : winNums){
            winNumbers.add(i);
        }
        for(int i : threeNums){
            threeNumbers.add(i);
        }
        for(int i : fourNums){
            fourNumbers.add(i);
        }
        WinType.setWinNumbers(winNumbers,winLucky);
    }

    @Test
    public void testEvaluateWinType(){
        //test 3
        assertEquals(WinType.three,WinType.evaluateWinType(threeNumbers,falseLucky));

        //test 3+1
        assertEquals(WinType.three1, WinType.evaluateWinType(threeNumbers,three1Lucky));

        //test4
        assertEquals(WinType.four, WinType.evaluateWinType(fourNumbers,falseLucky));

    }


/*
    @Test
    public void isThree(){
        assertTrue(WinType.isThree(threeNumbers));
    }

    @Test
    public void isThree1(){
        assertTrue(WinType.isThree1(threeNumbers,three1Lucky));
    }*/
}
