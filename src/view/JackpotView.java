package view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Jackpot;
import model.WinType;

import java.text.DecimalFormat;

public class JackpotView {

    private final GridPane root;
    private final Stage stage;


    public JackpotView(){

        DecimalFormat fmt = new DecimalFormat("###,##,###,### CHF");

        stage = new Stage();
        root = new GridPane();
        root.setId("grid-jackpotView");
        WinType[] winTypes = WinType.values();
        for(int i = 1; i < WinType.values().length; i++){
            root.add(new Label(WinType.asString(winTypes[i])),0,i);
        }
        for(int i = 0; i < Jackpot.getJackpotsAsList().size(); i++ ){
            root.add(new Label((fmt.format(Jackpot.getJackpotsAsList().get(i))+"")),1,(i+1));
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("Lottery.css").toExternalForm());
        stage.setTitle("Jackpots");
        stage.getIcons().add(new Image("./resources/logo.png"));
        stage.show();



    }
}
