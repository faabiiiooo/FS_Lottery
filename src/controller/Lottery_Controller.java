package controller;

import app.Lottery;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;
import model.Game;
import model.Lottery_Model;
import model.NumberGen;
import view.GameView;
import view.Lottery_View;
import view.NumberView;
import view.SimulateProgress;

import java.util.Collections;


public class Lottery_Controller {

    private Stage primaryStage;
    private Lottery_Model model;
    private Lottery_View view;
    //private NumberView numView;


    public Lottery_Controller(Stage primaryStage, Lottery_Model model, Lottery_View view){

        this.primaryStage = primaryStage;
        this.model = model;
        this.view = view;


        this.setViewsOnAction();

    }

    private void setViewsOnAction(){

        model.getStoreWins().addListener((ListChangeListener)c -> displayWins());



        view.getGameList().getSelectionModel().selectedIndexProperty().addListener(((observable, oldValue, newValue) -> {
            view.showOtherGame((int)newValue);
        }));

        for(int i = 0; i < view.getGameViews().size();i++){
            for(ToggleButton tb : view.getGameViews().get(i).getTipView().getTips().getToggleButtons()){
                tb.setOnAction(e -> tipSelected(e));
            }
            for(ToggleButton tb : view.getGameViews().get(i).getTipView().getLuckyTip().getToggleButtons()){
                tb.setOnAction(e -> luckyTipSelected(e));
            }

        }


        for(GameView gv : view.getGameViews()){
            gv.getTipView().getNumOfReplays().textProperty().addListener(((observable, oldValue, newValue) -> {
                if(!newValue.matches("\\d{0,2}")|| newValue.isEmpty()){
                    gv.getTipView().getNumOfReplays().setStyle("-fx-text-fill: red");
                    view.getControlArea().getBtnSimulateLottery().setDisable(true);
                } else {
                    gv.getTipView().getNumOfReplays().setStyle("-fx-text-fill: green");
                    view.getControlArea().getBtnSimulateLottery().setDisable(false);
                }
            }));

            gv.getTipView().getReplay().selectedProperty().addListener(((observable, oldValue, newValue) -> {
                if(!newValue){
                    gv.getTipView().getNumOfReplays().setDisable(true);
                    gv.getTipView().getSaveReplay().setDisable(true);
                } else {
                    gv.getTipView().getNumOfReplays().setDisable(false);
                    gv.getTipView().getSaveReplay().setDisable(false);
                }
            }));

            gv.getTipView().getSaveReplay().setOnAction(e -> {
                model.getGames().get(view.getGameViews().indexOf(gv)).setReplay(true);
                model.getGames().get(view.getGameViews().indexOf(gv)).setNumReplay(
                        Integer.parseInt(gv.getTipView().getNumOfReplays().getText()));
                gv.getTipView().getSaveReplay().setDisable(true);
                gv.getTipView().getNumOfReplays().setDisable(true);
                gv.getTipView().getReplay().setDisable(true);
            });
        }

        view.getBtnAddGame().setOnAction(e -> addGame());
        view.getBtnRemoveGame().setOnAction(e -> removeGame());
        view.getControlArea().getBtnSimulateLottery().setOnAction(e -> simulateLotto());
        view.getControlArea().getBtnResetLottery().setOnAction(e -> resetLotto());


    }

    private void simulateLotto(){

        //generation of winNumbers
        NumberGen generator = new NumberGen();
        //display winNumbers
        view.getWinNumbersView().displayWinNumbers(generator.getWinNumbers(),generator.getWinLuckyTip());
        //model evaluates lotto
        SimulateProgress progressWindow = new SimulateProgress();
        progressWindow.getProgressBar().progressProperty().bind(model.getOtherPlayer().progressProperty());
        model.playLotto();

        //view.disableToggleButtons();

    }

    private void resetLotto(){



        model = new Lottery_Model();
        view = new Lottery_View(primaryStage,model);
        this.setViewsOnAction();

    }

    private void displayWins(){
        String toSubmit = "Your Evals: \n";
        for(String s : model.getStoreWins()){
            toSubmit+= s+"\n";
        }
        view.showWinEval(toSubmit);

    }


    private void addGame(){

        if(model.getGames().size()< Lottery.MAX_GAMES){

            if(model.getMoney().reduceMoney()) {
                model.addGame();
                view.addGame();

                setViewsOnAction();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Not enough money -- increase it first!");
                alert.showAndWait();
            }
        }

    }

    private void removeGame(){
        if(model.getGames().size() <= Lottery.MIN_TIP_FIELDS){

        } else {
            Game g = view.getGameList().getSelectionModel().getSelectedItem();
            GameView gv = view.getGameViews().get(g.GAME_ID);

            model.removeGame(g);
            view.removeGame(gv);
            model.getMoney().payBack();
        }

    }

    private void tipSelected(Event e){  //this method gets called when one of the toggle buttons gets selected.
        ToggleButton tb = (ToggleButton) e.getSource();
        int selectedGame = view.getGameList().getSelectionModel().getSelectedIndex();
        Game g = view.getGameList().getSelectionModel().getSelectedItem();
        GameView gv = view.getGameViews().get(selectedGame);

        try{
            int tip = Integer.parseInt(tb.getText());
            if(tb.isSelected()) {  //handle the selection of a togglebutton
                g.addSelectedTip(tip);
                gv.getSelectedTips().displaySelectedTip(tip);
            } else {
                if(g.getTipsSelected().size() != 0) { //handle deselection of a toggle button
                    g.removeSelectedTip(tip);
                    gv.getSelectedTips().displaySelectedTip(tip);
                }
            }
        } catch(NumberFormatException exception){
                System.out.println("Error!");
        }

    }

    private void luckyTipSelected(Event e){
        ToggleButton tb = (ToggleButton) e.getSource();
        int selectedGame = view.getGameList().getSelectionModel().getSelectedIndex();
        Game g = view.getGameList().getSelectionModel().getSelectedItem();
        GameView gv = view.getGameViews().get(selectedGame);

        try{
            int luckyTip = Integer.parseInt(tb.getText());
            if(tb.isSelected()) {  //handle the selection of a togglebutton
                g.setLuckyTip(luckyTip);
                gv.getSelectedTips().setLblLuckyTip(luckyTip);
            } else {
                    g.resetLuckyTip();
                    gv.getSelectedTips().setLblLuckyTip(luckyTip);
            }
        } catch(NumberFormatException exception){
            System.out.println("Error!");
        }

    }
}
