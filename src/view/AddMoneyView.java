package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AddMoneyView {

    private final Stage stage;
    private final TextField txtAddMoney;
    private final Label lblAddMoney;
    private final Button btnAddMoney;

    public AddMoneyView(){

        HBox root = new HBox();

        stage = new Stage();
        txtAddMoney = new TextField();
        lblAddMoney = new Label("Amount Of Money to Add: ");
        btnAddMoney = new Button("Pay-In");
        btnAddMoney.setDisable(true);

        lblAddMoney.setId("lbl-addMoney");
        this.btnAddMoney.getStyleClass().add("btn-controlArea");

        txtAddMoney.textProperty().addListener(((observable, oldValue, newValue) -> {
            if(!newValue.matches("\\d+\\.\\d+") && !newValue.matches("\\d+")){
                txtAddMoney.setStyle("-fx-text-fill: red");
                btnAddMoney.setDisable(true);
            } else {
                txtAddMoney.setStyle("-fx-text-fill: green");
                btnAddMoney.setDisable(false);
            }
        }));

        root.getChildren().addAll(lblAddMoney,txtAddMoney,btnAddMoney);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("Lottery.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Pay-In");
        stage.getIcons().add(new Image("./resources/logo.png"));
        stage.setAlwaysOnTop(true);
        stage.show();
    }

    public Stage getStage() {
        return stage;
    }

    public TextField getTxtAddMoney() {
        return txtAddMoney;
    }

    public Button getBtnAddMoney(){
        return btnAddMoney;
    }
}
