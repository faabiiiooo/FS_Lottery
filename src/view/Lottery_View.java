package view;

import app.Lottery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Game;
import model.Lottery_Model;



public class Lottery_View extends BorderPane {

    protected Stage primaryStage;
    protected Lottery_Model model;
    protected GameList gameList;
    protected GameView gameView;

    private final ObservableList<GameView> gameViews = FXCollections.observableArrayList();
    private final Button btnAddGame, btnRemoveGame;


    public Lottery_View(Stage primaryStage, Lottery_Model model){
        this.primaryStage = primaryStage;

        btnAddGame = new Button("+");
        btnRemoveGame = new Button("-");
        gameList = new GameList(model);

        for(int i = 0; i < Lottery.MIN_TIP_FIELDS; i++){
            gameViews.add(new GameView());
        }

        HBox buttons = new HBox();
        buttons.getChildren().addAll(btnAddGame,btnRemoveGame);


        VBox controlList = new VBox();
        controlList.getChildren().addAll(gameList,buttons);

        ControlArea controlArea = new ControlArea(model);


        this.setCenter(gameViews.get(0));
        this.setLeft(controlList);
        this.setBottom(controlArea);


        Scene scene = new Scene(this);
        this.primaryStage.setScene(scene);
        this.primaryStage.setTitle("Swiss Lotto");
        this.primaryStage.show();

    }

    public void addGame(){
        gameViews.add(new GameView());
    }

    public void removeGame(GameView gv){
        gameViews.remove(gv);
    }

    public GameList getGameList(){ return this.gameList; }

    public ObservableList<GameView> getGameViews(){ return this.gameViews; }

    public Button getBtnAddGame(){return this.btnAddGame;}

    public Button getBtnRemoveGame(){ return  this.btnRemoveGame; }

    public void showOtherGame(int id){ this.setCenter(gameViews.get(id));  }
}
