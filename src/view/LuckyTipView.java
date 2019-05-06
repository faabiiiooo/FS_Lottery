package view;

import app.Lottery;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class LuckyTipView extends HBox {

    private final ArrayList<ToggleButton> toggleButtons = new ArrayList<>();

    public LuckyTipView(){

        ToggleGroup luckyGroup = new ToggleGroup();
        for(int i = 1; i <= Lottery.HIGHEST_LUCKY_TIP; i++ ){
            ToggleButton tb = new ToggleButton(i+"");
            tb.setToggleGroup(luckyGroup);
            tb.getStyleClass().add("tb-luckytip");
            toggleButtons.add(tb);
            this.getChildren().add(tb);
        }

    }

    public ArrayList<ToggleButton> getToggleButtons(){return this.toggleButtons;}
}
