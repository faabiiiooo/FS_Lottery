package view;

import app.Lottery;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.util.ArrayList;

public class WinNumbersView extends HBox {

    private final ArrayList<Label> winLabels = new ArrayList<>();
    private final Label lblwinLuckyTip = new Label();

    public WinNumbersView(){

        for(int i = 0; i < Lottery.MAX_TIPS; i++){
            Label lbl = new Label();
            lbl.getStyleClass().add("lbl-winNumber");
            winLabels.add(lbl);
            this.getChildren().add(lbl);
        }

        lblwinLuckyTip.setId("lbl-winLuckyTipNumber");
        this.getChildren().addAll(lblwinLuckyTip);
        this.setSpacing(10);

    }

    public void displayWinNumbers(ArrayList<Integer> winNumbers, int winLuckyNum){

        if(winNumbers.size()== Lottery.MAX_TIPS){
            SequentialTransition sequence = new SequentialTransition();
            for(int i = 0; i < winNumbers.size(); i++){

                RotateTransition rotateLbl = new RotateTransition(Duration.millis(500),winLabels.get(i));
                rotateLbl.setAutoReverse(true);
                rotateLbl.setByAngle(360);
                sequence.getChildren().add(rotateLbl);

                winLabels.get(i).setText(Integer.toString(winNumbers.get(i)));
            }
            RotateTransition rotateLuckyLbl = new RotateTransition(Duration.millis(500),lblwinLuckyTip);
            rotateLuckyLbl.setByAngle(360);
            sequence.getChildren().add(rotateLuckyLbl);
            lblwinLuckyTip.setText(Integer.toString(winLuckyNum));
            sequence.play();
        }

    }
}
