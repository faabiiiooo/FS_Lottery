package view;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import model.Lottery_Model;

public class TipView extends VBox {

    protected NumberView tips;
    protected LuckyTipView luckyTip;

    public TipView(){

        tips = new NumberView();
        luckyTip = new LuckyTipView();

        this.getChildren().addAll(tips,luckyTip);


    }

    public NumberView getTips(){
        return this.tips;
    }

    public LuckyTipView getLuckyTip(){ return this.luckyTip;}

}
