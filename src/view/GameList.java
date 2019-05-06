package view;

import javafx.scene.control.ListView;
import model.Game;
import model.Lottery_Model;

public class GameList extends ListView<Game> {

    public GameList(Lottery_Model model){

        this.setItems(model.getGames());
        this.getSelectionModel().selectFirst();

    }
}
