package model;

import app.Lottery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;


public class Lottery_Model {


    private final ObservableList<Game> games = FXCollections.observableArrayList(); //All Games
    private final ObservableList<String> storeWins = FXCollections.observableArrayList(); //WinnEvals
    private static int gameIdTemplate = 0;
    private final Money money;
    protected static int count61;



    public Lottery_Model() {

        count61 = 0;
        money = new Money();
        //Create two Games on First run or reset.
        for(int i = 0; i < Lottery.MIN_TIP_FIELDS; i++){
            games.add(new Game(i));
            money.reduceMoney();
            gameIdTemplate = i+1;
        }



    }

    public void playLotto(){

        storeWins.clear();

        for(int i = 0; i < games.size(); i++){
            money.reduceMoney();
        }

        for(Game g : games){ //get the tips from every Game. and evaluate them.
            ArrayList<Integer> tempTips = new ArrayList<>();
            for(int i : g.getTipsSelected()){
                tempTips.add(i);
            }
            WinType winType = WinType.evaluateWinType(tempTips,g.getLuckyTip());
            if(!winType.equals(WinType.six1)){
                money.transferWin(Jackpot.checkJackpotWon(winType));
            } else {
                count61++;
            }
            storeWins.add("Game "+(g.GAME_ID+1)+" :\t"+WinType.asString(winType));
        }
        if(count61 == 0){
            Jackpot.increaseJackpot();
        }

        if(count61 > 0){
            double jackpot = Jackpot.jackpot / count61;
            Jackpot.resetJackpot();
            money.transferWin(jackpot);
        }

        money.saveToFile(); //all transactions are done, save account to file.

    }


    public void addGame(){
        games.add(new Game(gameIdTemplate));
        gameIdTemplate++;
    }

    public void removeGame(Game game){
        games.remove(game);
    }

    public ObservableList<Game> getGames() { return this.games; }

    public ObservableList<String> getStoreWins() { return this.storeWins; }

    public Money getMoney(){return this.money;}




}
