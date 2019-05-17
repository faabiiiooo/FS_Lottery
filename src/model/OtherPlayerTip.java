package model;

import app.Lottery;

import java.util.ArrayList;

public class OtherPlayerTip {

    private final ArrayList<Integer> tipsSelected = new ArrayList<>();
    private final int luckyTip;

    private WinType winEval;

    public OtherPlayerTip(int ... tips){

        for(int i = 0; i < Lottery.MAX_TIPS; i++){
            tipsSelected.add(tips[i]);
        }

        luckyTip = tips[tips.length-1];

    }

    public void setWinEval(WinType winEval){ this.winEval = winEval; }

    public ArrayList<Integer> getTipsSelected(){return this.tipsSelected;}

    public int getLuckyTip(){ return this.luckyTip; }

}
