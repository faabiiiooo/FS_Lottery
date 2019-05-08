package model;

import java.util.ArrayList;

public enum WinType {


    lower,three,three1,four,four1,five,five1,six,six1;

    private static ArrayList<Integer> winNumbers;
    private static int winLuckyNum;

    public static void setWinNumbers(ArrayList<Integer> numbers, int luckynumber){  //give the eval the winning numbers
        winNumbers = numbers;
        winLuckyNum = luckynumber;
    }

    public static WinType evaluateWinType(ArrayList<Integer> tippedNum, int tippedLucky){
        int count = 0;
        boolean luckyFound = false;
        WinType eval = lower;

        for(int i : tippedNum){
            if(winNumbers.contains(i)) count++;  //count how many numbers the tippedNum contains
        }
        if(tippedLucky == winLuckyNum) luckyFound = true;  //check if lucky numbers match

        switch (count){  //check if lucky number and which wintype match
            case 3: eval = (luckyFound) ? three1 : three;
            break;
            case 4: eval = (luckyFound) ? four1 : four;
            break;
            case 5: eval = (luckyFound) ? five1 : five;
            break;
            case 6: eval = (luckyFound) ? six1 : six;
        }

        return eval;
    }

    public static String asString(WinType winType){
        String winTypeString = "";

        switch (winType){
            case lower: winTypeString ="lower";
            break;
            case three: winTypeString="3";
            break;
            case three1: winTypeString="3+1";
            break;
            case four: winTypeString="4";
            break;
            case four1: winTypeString="4+1";
            break;
            case five: winTypeString="5";
            break;
            case five1: winTypeString="5+1";
            break;
            case six: winTypeString="6";
            break;
            case six1: winTypeString="6+1";
        }

        return winTypeString;
    }


}
