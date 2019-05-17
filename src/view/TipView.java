package view;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.Lottery_Model;



public class TipView extends VBox {

    protected NumberView tips;
    protected LuckyTipView luckyTip;
    protected CheckBox replay;
    protected TextField numOfReplays;
    protected Button saveReplay;

    public TipView(){

        tips = new NumberView();
        luckyTip = new LuckyTipView();
        replay = new CheckBox("Replay?");
        numOfReplays = new TextField();
        numOfReplays.setDisable(true);
        saveReplay = new Button("Save");
        saveReplay.setDisable(true);



        this.getChildren().addAll(tips,luckyTip, replay, numOfReplays, saveReplay);
        this.setSpacing(5);


    }

    public NumberView getTips(){
        return this.tips;
    }

    public LuckyTipView getLuckyTip(){ return this.luckyTip;}

    public TextField getNumOfReplays(){ return this.numOfReplays; }

    public CheckBox getReplay(){ return this.replay; }

    public Button getSaveReplay() {return  this.saveReplay; }

}
