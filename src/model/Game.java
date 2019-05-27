package model;

import app.Lottery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;


public class Game {

    public final static double PRICE = 2.5; //price per game

    public final int GAME_ID; // unique GameID

    private final ArrayList<Integer> tipNumbers; //Numbers from 1 to 42

    private final ObservableList<Integer> tipsSelected = FXCollections.observableArrayList(); //selected Numbers

    private int luckyTip= -1; //selected Luckytip, if none specified = -1


    public Game(int gameId){ //Generate the numbers from 1  to 42
        GAME_ID = gameId;
        tipNumbers = new ArrayList<>();
        for(int i = 0; i < Lottery.HIGHEST_TIP; i++){
            tipNumbers.add(i);
        }
    }

    public ArrayList<Integer> getTipNumbers(){ return this.tipNumbers; }

    public ObservableList<Integer> getTipsSelected(){ return this.tipsSelected; }

    public void addSelectedTip(int tip){ //gets Called when a tip gets Selected

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

    public String toString(){
        return "Game Nr.: "+(GAME_ID+1);
    }



}
