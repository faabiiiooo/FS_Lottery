package view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SimulateProgress {

    private ProgressBar progressBar;

    public SimulateProgress(){

        Stage stage = new Stage();

        HBox root = new HBox();
        Label lbl = new Label("Progress: ");
        progressBar = new ProgressBar();

        root.getChildren().addAll(lbl,progressBar);


        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setAlwaysOnTop(true);
        stage.show();

    }

    public ProgressBar getProgressBar(){ return this.progressBar; }

}
