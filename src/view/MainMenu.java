package view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MainMenu extends MenuBar {

    private final Menu player, about;

    public MainMenu(){

        player = new Menu("Player");
        about = new Menu("About");

        MenuItem addMoney = new MenuItem("Add Money");
        player.getItems().addAll(addMoney);

        this.getMenus().addAll(player);

    }
}
