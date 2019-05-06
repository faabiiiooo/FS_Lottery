package view;


import app.Lottery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class SelectedTips extends HBox {

    private final Label lblLuckyTip;

    private final ObservableList<Label> lblSelectedTips = FXCollections.observableArrayList();
   // private final ObservableList<HBox> tip = FXCollections.observableArrayList();

    public SelectedTips(){

        for(int i = 0; i < Lottery.MAX_TIPS; i++){
            lblSelectedTips.add(new Label());
            this.getChildren().add(lblSelectedTips.get(i));
        }
        lblLuckyTip = new Label();
        lblLuckyTip.setId("lbl-luckytip");
        this.getChildren().add(lblLuckyTip);
        //this.tip.add(this);
    }

    public void displaySelectedTip(int tip){
        boolean displayed = false;
        boolean found = false;

        int index = 0;
        for(int i = 0; i < lblSelectedTips.size();i++){
            if(!lblSelectedTips.get(i).getText().equals("")) {
                int value = Integer.parseInt(lblSelectedTips.get(i).getText()); //check if value is already displayed
                if (value == tip) {
                    index = i;
                    found = true;
                }
            }
        }

        if(found){
            lblSelectedTips.get(index).setText(""); //if value is displayed then hide value
        } else {
            for(int i = 0; i < lblSelectedTips.size() && !displayed;i++){  //if not, display the new value
                if(lblSelectedTips.get(i).getText().equals("")){
                    lblSelectedTips.get(i).setText(tip+"");
                    displayed = true;
                }
            }
        }


    }

    public void setLblLuckyTip(int tip){
        if(lblLuckyTip.getText().equals("")){
            lblLuckyTip.setText(tip+"");
        } else {
            lblLuckyTip.setText("");
        }
    }

    public ObservableList<Label> getLblSelectedTips(){ return this.lblSelectedTips; }

    //public ObservableList<HBox> getTip(){ return this.tip; }

    public HBox getSelectedTips(){
        return this;
    }
}
