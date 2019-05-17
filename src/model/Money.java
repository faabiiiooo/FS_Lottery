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
        System.out.println(moneyFile.getAbsolutePath());
        System.out.println(moneyFile.exists());

        if(moneyFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(moneyFile))) {

                String line;
                String amountOfMoney;
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

    public boolean reduceMoney(){

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
    }

    public void transferWin(double win){
        setAmountOfMoney(amount+win);
    }

    public void setAmountOfMoney(double amount){
        this.amount = amount;
        this.asString.set(amount+"");
    }

    public double getAmountOfMoney(){ return this.amount;}

    public String getAsString() {
        return asString.get();
    }

    public SimpleStringProperty asStringProperty() {
        return asString;
    }

    public void setAsString(String asString) {
        this.asString.set(asString);
    }

    public void saveToFile(){

        File moneyFile = new File("./src/resources/moneyFile.txt");

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(moneyFile))) {

            moneyFile.createNewFile();
            writer.write(this.amount+"");

        } catch (Exception e){

        }

    }
}
