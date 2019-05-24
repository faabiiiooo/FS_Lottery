package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import model.Lottery_Model;

public class ControlArea extends HBox {

    protected Button btnSimulateLottery, btnResetLottery;
    protected Label lblMoneyAmount;

    public ControlArea(Lottery_Model model){
        btnSimulateLottery = new Button("Simulate!");
        btnResetLottery = new Button("Reset");
        lblMoneyAmount = new Label("0");
        lblMoneyAmount.textProperty().bind(model.getMoney().asStringProperty());
        this.btnSimulateLottery.getStyleClass().add("btn-controlArea");
        this.btnResetLottery.getStyleClass().add("btn-controlArea");

        Region spacer = new Region();


        Label lblMoneyDesc = new Label("Your amount of Money: ");

        this.getChildren().addAll(lblMoneyDesc,lblMoneyAmount,spacer, btnSimulateLottery, btnResetLottery);
        this.setHgrow(spacer, Priority.ALWAYS);
        this.setId("hbox-controlArea");
    }

    public void setLblMoneyAmount(int amount){ lblMoneyAmount.setText(+amount+"");}

    public Button getBtnSimulateLottery(){return this.btnSimulateLottery;}

    public Button getBtnResetLottery(){return this.btnResetLottery; }
}
