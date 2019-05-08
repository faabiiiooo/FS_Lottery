package view;

import app.Lottery;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class WinNumbersView extends HBox {

    private final ArrayList<Label> winLabels = new ArrayList<>();
    private final Label lblwinLuckyTip = new Label();
    private final Label lblShowWinType = new Label();

    public WinNumbersView(){

        for(int i = 0; i < Lottery.MAX_TIPS; i++){
            Label lbl = new Label();
            lbl.getStyleClass().add("lbl-winNumber");
            winLabels.add(lbl);
            this.getChildren().add(lbl);
        }

        lblwinLuckyTip.setId("lbl-winLuckyTipNumber");
        lblShowWinType.setId("lbl-showWinType");
        this.getChildren().addAll(lblwinLuckyTip,lblShowWinType);

    }

    public void displayWinNumbers(ArrayList<Integer> winNumbers, int winLuckyNum){

        if(winNumbers.size()== Lottery.MAX_TIPS){
            for(int i = 0; i < winNumbers.size(); i++){
                winLabels.get(i).setText(Integer.toString(winNumbers.get(i)));
            }
            lblwinLuckyTip.setText(Integer.toString(winLuckyNum));
        }

    }

    public void displayWinType(String winType){
        lblShowWinType.setText(winType);
    }
}
