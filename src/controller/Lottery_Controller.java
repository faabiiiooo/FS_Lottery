package controller;

import app.Lottery;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;
import model.*;
import view.*;

import java.util.Collections;


public class Lottery_Controller {

    private Stage primaryStage;
    private Lottery_Model model;
    private Lottery_View view;
    private AddMoneyView moneyView;
    //private NumberView numView;


    public Lottery_Controller(Stage primaryStage, Lottery_Model model, Lottery_View view){

        this.primaryStage = primaryStage;
        this.model = model;
        this.view = view;


        this.setViewsOnAction();

    }

    private void setViewsOnAction(){

        model.getStoreWins().addListener((ListChangeListener)c -> displayWins());
        this.primaryStage.setOnCloseRequest(e -> {
            model.getMoney().saveToFile();
            Jackpot.saveToFile();
        });



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

        view.getBtnAddGame().setOnAction(e -> addGame());
        view.getBtnRemoveGame().setOnAction(e -> removeGame());

        view.getControlArea().getBtnSimulateLottery().setOnAction(e -> simulateLotto());
        view.getControlArea().getBtnResetLottery().setOnAction(e -> resetLotto());

        view.getMainMenu().getAddMoney().setOnAction(e -> displayAddMoneyWindow());
        view.getMainMenu().getShowJackpot().setOnAction(e -> displayJackpotWindow());
        view.getMainMenu().getShowStats().setOnAction(e -> {
            Statistics stats = new Statistics();
            StatisticsView viewStats = new StatisticsView(stats);
        });


    }

    private void simulateLotto(){

        //generation of winNumbers
        NumberGen generator = new NumberGen();
        //display winNumbers
        view.getWinNumbersView().displayWinNumbers(generator.getWinNumbers(),generator.getWinLuckyTip());
        //model evaluates lotto

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

    private void displayAddMoneyWindow(){
        moneyView = new AddMoneyView();
        moneyView.getBtnAddMoney().setOnAction(e -> addMoney());
    }

    private void addMoney(){
        try {
            double amount = Double.parseDouble(moneyView.getTxtAddMoney().getText());
            if(amount > 0){
                model.getMoney().transferWin(amount);
                moneyView.getStage().close();
            }
        } catch (NumberFormatException e){
            moneyView.getTxtAddMoney().setText("Please enter a Number");
        }
    }

    private void displayJackpotWindow(){
        JackpotView jpView = new JackpotView();
    }
}
