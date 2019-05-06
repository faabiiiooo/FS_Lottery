package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import model.Lottery_Model;

public class ControlArea extends HBox {

    protected Button btnSimulateLottery;
    protected Label lblMoneyAmount;

    public ControlArea(Lottery_Model model){
        btnSimulateLottery = new Button("Simulate!");
        lblMoneyAmount = new Label("0");
        lblMoneyAmount.textProperty().bind(model.getMoney().asStringProperty());

        this.getChildren().addAll(lblMoneyAmount, btnSimulateLottery);
    }

    public void setLblMoneyAmount(int amount){ lblMoneyAmount.setText(amount+"");}

    public Button getBtnSimulateLottery(){return this.btnSimulateLottery;}
}
