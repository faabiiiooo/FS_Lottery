package view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MainMenu extends MenuBar {

    private final Menu player, about;
    private final MenuItem addMoney, showJackpot, showStats;

    public MainMenu(){

        player = new Menu("Player");
        about = new Menu("About");

        addMoney = new MenuItem("Add Money");
        player.getItems().addAll(addMoney);

        showJackpot = new MenuItem("Show Jackpot");
        showStats = new MenuItem("Show Statistics");
        about.getItems().addAll(showJackpot, showStats);

        this.setId("menuBar-main");

        this.getMenus().addAll(player, about);

    }

    public MenuItem getAddMoney(){ return  this.addMoney; }

    public MenuItem getShowJackpot() { return this.showJackpot; }

    public MenuItem getShowStats(){ return this.showStats; }
}
