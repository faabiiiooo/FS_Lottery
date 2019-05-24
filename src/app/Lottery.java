package app;

import controller.Lottery_Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Jackpot;
import model.Lottery_Model;
import view.Lottery_View;

public class Lottery extends Application {

    public static final int HIGHEST_TIP = 42;
    public static final int HIGHEST_LUCKY_TIP=6;
    public static final int LOWEST_TIP= 1;
    public static final int MIN_TIP_FIELDS=2;
    public static final int MAX_TIPS = 6;
    public static final int MAX_GAMES = 14;
    public static final int MAX_REPLAYS = 3;

    public static void main(String[] args){
        launch(args);
    }


    public void start(Stage primaryStage){

        Jackpot.importJackpot();
        Lottery_Model model = new Lottery_Model();
        Lottery_View view = new Lottery_View(primaryStage, model);
        Lottery_Controller controller = new Lottery_Controller(primaryStage,model,view);
    }
}
