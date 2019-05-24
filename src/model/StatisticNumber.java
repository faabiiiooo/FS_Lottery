package model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class StatisticNumber {

    private final SimpleIntegerProperty number = new SimpleIntegerProperty();
    private final SimpleIntegerProperty frequency = new SimpleIntegerProperty();
    private final SimpleStringProperty statEval = new SimpleStringProperty();

    public StatisticNumber(int number, int frequency, String statEval){
        this.number.set(number);
        this.frequency.set(frequency);
        this.statEval.set(statEval);
    }

    public int getNumber() {
        return number.get();
    }

    public SimpleIntegerProperty numberProperty() {
        return number;
    }

    public int getFrequency() {
        return frequency.get();
    }

    public SimpleIntegerProperty frequencyProperty() {
        return frequency;
    }

    public String getStatEval() {
        return statEval.get();
    }

    public SimpleStringProperty statEvalProperty() {
        return statEval;
    }

    public String toString(){
        return "Num: "+this.getNumber()+"\n Freq: "+this.getFrequency()+"\n Eval: "+this.getStatEval();
    }
}
