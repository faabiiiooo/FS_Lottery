package model;

import app.Lottery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Lottery_Model {


    private final ObservableList<Game> games = FXCollections.observableArrayList();
    private static int gameIdTemplate = 0;
    private final Money money;



    public Lottery_Model() {

        money = new Money(20.0);

        for(int i = 0; i < Lottery.MIN_TIP_FIELDS; i++){
            games.add(new Game(i));
            money.reduceMoney();
            gameIdTemplate = i+1;
        }



    }

    public void addGame(){
        games.add(new Game(gameIdTemplate));
        gameIdTemplate++;
    }

    public void removeGame(Game game){
        games.remove(game);
    }

    public int getGameId(Game game){
        return games.indexOf(game);
    }

    public ObservableList<Game> getGames() { return this.games; }

    public Money getMoney(){return this.money;}



}
