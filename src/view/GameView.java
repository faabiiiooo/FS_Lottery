package view;

import javafx.scene.layout.HBox;

import java.awt.*;

public class GameView extends HBox {

    protected SelectedTips selectedTips;
    protected TipView tipView;
    protected Button btnAddGame;

    public GameView(){

        selectedTips = new SelectedTips();
        tipView = new TipView();
        btnAddGame = new Button("+");

        this.getChildren().addAll(tipView,selectedTips);
        this.setId("hbox-gameView");

    }

    public SelectedTips getSelectedTips(){
        return this.selectedTips;
    }

    public TipView getTipView(){
        return this.tipView;
    }
}
