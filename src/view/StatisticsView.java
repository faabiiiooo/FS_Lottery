package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.StatisticNumber;
import model.Statistics;

import java.text.DecimalFormat;

public class StatisticsView {

    private final Label lblNumOfGames = new Label();
    private final Button resetStats = new Button("Reset Statistics");
    private TableView<StatisticNumber> tblStats;
    private Statistics stats;

    public StatisticsView(Statistics stats){

        this.stats = stats;

        this.createTblStats();
        lblNumOfGames.setText("Games Played: "+stats.getGameCounter()+"");
        lblNumOfGames.setId("lbl-statNumGames");
        VBox root = new VBox();

        root.getChildren().addAll(lblNumOfGames,tblStats);

        lblNumOfGames.prefWidthProperty().bind(tblStats.widthProperty());

        tblStats.prefHeightProperty().bind(root.heightProperty());
        tblStats.prefWidthProperty().bind(root.widthProperty());

        tblStats.setId("tbl-stats");
        tblStats.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tblStats.setSelectionModel(null); //makes TableView unselectable


        Stage stage = new Stage();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("Lottery.css").toExternalForm());
        root.prefWidthProperty().bind(scene.widthProperty());
        root.prefHeightProperty().bind(scene.heightProperty());
        stage.setScene(scene);
        stage.setHeight(400);
        stage.setWidth(300);
        stage.setTitle("Statistics");
        stage.getIcons().add(new Image("./resources/logo.png"));
        stage.show();

    }

    private void createTblStats(){

        tblStats = new TableView<>();

        TableColumn<StatisticNumber, Integer> colNumber = new TableColumn<>("Number");
        colNumber.setCellValueFactory(n -> n.getValue().numberProperty().asObject());

        TableColumn<StatisticNumber, Integer> colFrequency = new TableColumn<>("Frequency");
        colFrequency.setCellValueFactory(f -> f.getValue().frequencyProperty().asObject());

        TableColumn<StatisticNumber, String> colStatEval = new TableColumn<>("Chance");
        colStatEval.setCellValueFactory(s -> s.getValue().statEvalProperty());

        tblStats.getColumns().addAll(colNumber,colFrequency,colStatEval);
        tblStats.setItems(stats.getStatistics());

    }
}
