package model;

import app.Lottery;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class Money {

    public static final double MIN_INSET = Lottery.MIN_TIP_FIELDS * Game.PRICE;

    private final SimpleStringProperty asString = new SimpleStringProperty();

    private double amount;

    private File moneyFile;

    public Money(){


        moneyFile = new File("./src/resources/moneyFile.txt");

        if(moneyFile.exists()) { //get money from file if one exists else set default amount of 50
            try (BufferedReader reader = new BufferedReader(new FileReader(moneyFile))) {

                String line;

                while ((line = reader.readLine()) != null) {
                    amount = Double.parseDouble(line);
                    this.asString.set(amount+"");
                }
            } catch (Exception e) {

            }
        } else {
            this.amount = 50;
            this.asString.set(amount + "");

        }

    }

    public boolean reduceMoney(){ //reduce Money per Game plaed

        boolean enoughMoney = false;

        if((amount - Game.PRICE) < 0){

        } else {
            setAmountOfMoney((amount-Game.PRICE));
            enoughMoney = true;
        }

        return enoughMoney;

    }

    public void payBack(){
        setAmountOfMoney(amount+Game.PRICE);
    } //used for Payback if user delets a Game befor simulating

    public void transferWin(double win){
        setAmountOfMoney(amount+win);
    }

    public void setAmountOfMoney(double amount){
        this.amount = amount;
        this.asString.set(amount+"");
    }

    public SimpleStringProperty asStringProperty() {
        return asString;
    }

    public void saveToFile(){ //save money to .txt file.

        File moneyFile = new File("./src/resources/moneyFile.txt");

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(moneyFile))) {

            moneyFile.createNewFile();
            writer.write(this.amount+"");

        } catch (Exception e){

        }

    }
}
