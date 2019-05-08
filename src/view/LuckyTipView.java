package view;

import app.Lottery;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class LuckyTipView extends HBox {

    private final ArrayList<ToggleButton> toggleButtons = new ArrayList<>();

    private int selections = 0;
    private final int MAX_SELECTIONS = 1;

    public LuckyTipView(){

        ToggleGroup luckyGroup = new ToggleGroup();
        for(int i = 1; i <= Lottery.HIGHEST_LUCKY_TIP; i++ ){
            ToggleButton number = new ToggleButton(i+"");
            number.setPrefSize(40,40);
            number.selectedProperty().addListener(((observable, oldValue, newValue) -> {

                if(newValue){
                    selections++;
                    if(selections == MAX_SELECTIONS){
                        for(ToggleButton tb : toggleButtons){
                            if(!tb.isSelected()){
                                tb.setDisable(true);
                            }
                        }
                    }
                } else {
                    selections--;
                    for(ToggleButton tb : toggleButtons){
                        if(!tb.isSelected()){
                            tb.setDisable(false);
                        }
                    }
                }

            }));
            number.getStyleClass().add("tb-luckytip");
            toggleButtons.add(number);
            this.getChildren().add(number);
        }

    }

    public ArrayList<ToggleButton> getToggleButtons(){return this.toggleButtons;}
}
