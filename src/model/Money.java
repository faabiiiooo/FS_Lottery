package model;

import app.Lottery;
import javafx.beans.property.SimpleStringProperty;

public class Money {

    public static final double MIN_INSET = Lottery.MIN_TIP_FIELDS * Game.PRICE;

    private final SimpleStringProperty asString = new SimpleStringProperty();

    private double amount;

    public Money(double amount){
        this.amount = amount;
        this.asString.set(amount+"");
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
}
