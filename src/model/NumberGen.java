package model;

import app.Lottery;

import java.util.ArrayList;
import java.util.Collections;

public class NumberGen {

    private final ArrayList<Integer> allNumbers = new ArrayList<>();
    private final ArrayList<Integer> allLuckyTips = new ArrayList<>();

    private final ArrayList<Integer> winNumbers = new ArrayList<>();
    private int winLuckyTip;

    public NumberGen(){

        for(int i = 1; i <= Lottery.HIGHEST_TIP;i++){
            allNumbers.add(i);
        }

        for(int i = 1; i <= Lottery.HIGHEST_LUCKY_TIP; i++){
            allLuckyTips.add(i);
        }

        generateWinNumbers();

    }

    private void generateWinNumbers(){

        //first shuffle both collections.
        Collections.shuffle(allNumbers);
        Collections.shuffle(allLuckyTips);

        //get the first 6 numbers
        for(int i = 0; i < Lottery.MAX_TIPS; i++){
            winNumbers.add(allNumbers.get(i));
        }

        //get the first lucky tip
        winLuckyTip = allLuckyTips.get(0);

        WinType.setWinNumbers(this.winNumbers, this.winLuckyTip);
    }

    public ArrayList<Integer> getWinNumbers(){return this.winNumbers;}

    public int getWinLuckyTip(){return this.winLuckyTip;}
}
