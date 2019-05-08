package controller;

import app.Lottery;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleButton;
import model.Game;
import model.Lottery_Model;
import model.NumberGen;
import view.GameView;
import view.Lottery_View;
import view.NumberView;




public class Lottery_Controller {

    private Lottery_Model model;
    private Lottery_View view;
    private NumberView numView;


    public Lottery_Controller(Lottery_Model model, Lottery_View view){

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

        view.getBtnAddGame().setOnAction(e -> addGame());
        view.getBtnRemoveGame().setOnAction(e -> removeGame());
        view.getControlArea().getBtnSimulateLottery().setOnAction(e -> simulateLotto());


    }

    private void simulateLotto(){
        //generation of winNumbers
        NumberGen generator = new NumberGen();
        //display winNumbers
        view.getWinNumbersView().displayWinNumbers(generator.getWinNumbers(),generator.getWinLuckyTip());
        //model evaluates lotto
        model.playLotto();

    }

    private void displayWins(){
        String toSubmit = "Your Evals: \n";
        for(String s : model.getStoreWins()){
            toSubmit+= s+"\n";
        }
        view.getWinNumbersView().displayWinType(toSubmit);
        System.out.println(toSubmit);

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
