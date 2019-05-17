package model;

import app.Lottery;
import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.Collections;

public class OtherPlayer extends Task<Void> {

    private final int numPlayers = 1000000;

    private final ArrayList<Integer> tipNumbers = new ArrayList<>();
    private final ArrayList<Integer> luckyNumbers = new ArrayList<>();
    private final ArrayList<OtherPlayerTip> otherPlayerTips = new ArrayList<>();

    public OtherPlayer(){

        for(int i = 1; i <= Lottery.HIGHEST_TIP; i++){
            tipNumbers.add(i);
        }
        for(int i = 1; i <= Lottery.HIGHEST_LUCKY_TIP; i++){
            luckyNumbers.add(i);
        }
    }

    public Void call(){

        for(int i = 0; i < 1000000; i++){
            Collections.shuffle(tipNumbers);
            Collections.shuffle(luckyNumbers);
            int[] tipsSelected = new int[Lottery.MAX_TIPS+1];
            for(int j = 0; j <Lottery.MAX_TIPS; j++){
                tipsSelected[j] = tipNumbers.get(j);
            }

            tipsSelected[tipsSelected.length-1] = luckyNumbers.get(0);
            otherPlayerTips.add(new OtherPlayerTip(tipsSelected));

            WinType winType = WinType.evaluateWinType(otherPlayerTips.get(i).getTipsSelected(),
                    otherPlayerTips.get(i).getLuckyTip());
            otherPlayerTips.get(i).setWinEval(winType);
            if(winType.equals(WinType.six1)){
                Lottery_Model.count61++;
            }
            this.updateProgress(i,numPlayers);

        }

        return null;
    }

}
