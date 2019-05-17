package model;

import app.Lottery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;


public class Game {

    public final static double PRICE = 2.5;

    public final int GAME_ID;

    private final ArrayList<Integer> tipNumbers;

    private final ObservableList<Integer> tipsSelected = FXCollections.observableArrayList();

    private int luckyTip= -1;

    private boolean replay = false;

    private int numReplay;

    private boolean gameFilled;

    public Game(int gameId){
        GAME_ID = gameId;
        tipNumbers = new ArrayList<>();
        for(int i = 0; i < Lottery.HIGHEST_TIP; i++){
            tipNumbers.add(i);
        }
    }

    public ArrayList<Integer> getTipNumbers(){ return this.tipNumbers; }

    public ObservableList<Integer> getTipsSelected(){ return this.tipsSelected; }

    public void addSelectedTip(int tip){

        if(!tipsSelected.contains(tip)) {
            tipsSelected.add(tip);

        }else{
            removeSelectedTip(tip);}
    }

    public void removeSelectedTip(int tip){
        tipsSelected.remove(Integer.valueOf(tip));
    }

    public void setLuckyTip(int luckyTip){
        this.luckyTip = luckyTip;
    }

    public void resetLuckyTip(){
        this.luckyTip = -1;
    }

    public int getLuckyTip(){return this.luckyTip;}

    public void setReplay(boolean replay){ this.replay = replay;}

    public boolean getReplay(){return this.replay;}

    public String toString(){
        return "Game Nr.: "+(GAME_ID+1);
    }

    public void setNumReplay(int numReplay){ this.numReplay = numReplay; }

}
