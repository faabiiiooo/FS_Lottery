package view;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.Lottery_Model;



public class TipView extends VBox {

    protected NumberView tips;
    protected LuckyTipView luckyTip;


    public TipView(){

        tips = new NumberView();
        luckyTip = new LuckyTipView();


        this.getChildren().addAll(tips,luckyTip);
        this.setSpacing(5);


    }

    public NumberView getTips(){
        return this.tips;
    }

    public LuckyTipView getLuckyTip(){ return this.luckyTip;}


}
