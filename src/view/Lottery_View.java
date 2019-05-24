package view;

import app.Lottery;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Game;
import model.Lottery_Model;
import sun.applet.Main;


public class Lottery_View extends BorderPane {

    protected Stage primaryStage;
    protected Lottery_Model model;
    protected GameList gameList;
    protected GameView gameView;
    protected WinNumbersView winNumbersView;
    protected ControlArea controlArea;
    protected MainMenu mainMenu;

    private final ObservableList<GameView> gameViews = FXCollections.observableArrayList();
    private final Button btnAddGame, btnRemoveGame;
    private final Label lblWinEval = new Label();


    public Lottery_View(Stage primaryStage, Lottery_Model model){
        this.primaryStage = primaryStage;

        btnAddGame = new Button("+");
        btnAddGame.getStyleClass().add("btn-controls");
        btnRemoveGame = new Button("-");
        btnRemoveGame.getStyleClass().add("btn-controls");
        gameList = new GameList(model);
        winNumbersView = new WinNumbersView();
        mainMenu = new MainMenu();

        lblWinEval.setId("lbl-winEval");

        for(int i = 0; i < Lottery.MIN_TIP_FIELDS; i++){
            gameViews.add(new GameView());
        }

        HBox buttons = new HBox();
        buttons.getChildren().addAll(btnAddGame,btnRemoveGame);
        buttons.setSpacing(5);


        VBox controlList = new VBox();
        controlList.getChildren().addAll(gameList,buttons);

        VBox winPart = new VBox();
        winPart.getChildren().addAll(winNumbersView,lblWinEval);
        winPart.setStyle("-fx-alignment: center");

        controlArea = new ControlArea(model);


        this.setCenter(gameViews.get(0));
        this.setLeft(controlList);
        this.setRight(winPart);
        this.setBottom(controlArea);
        this.setTop(mainMenu);


        Scene scene = new Scene(this);
        scene.getStylesheets().add(getClass().getResource("Lottery.css").toExternalForm());
        this.primaryStage.setResizable(false);
        this.primaryStage.setScene(scene);
        this.primaryStage.setTitle("Swiss Lotto");
        this.primaryStage.getIcons().add(new Image("./resources/logo.png"));
        this.primaryStage.show();

    }

    public void disableToggleButtons(){

        for(int i = 0; i < gameViews.size(); i++){
            for(ToggleButton tb : gameViews.get(i).getTipView().getTips().getToggleButtons()){
                tb.setDisable(true);
            }
        }

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

    public WinNumbersView getWinNumbersView(){return this.winNumbersView; }

    public ControlArea getControlArea(){return this.controlArea; }

    public void showOtherGame(int id){ this.setCenter(gameViews.get(id));  }

    public void showWinEval(String winEval){this.lblWinEval.setText(winEval);}

    public void setModel(Lottery_Model model){this.model = model;}

    public MainMenu getMainMenu(){ return this.mainMenu; }
}
