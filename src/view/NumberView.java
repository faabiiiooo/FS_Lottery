package view;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Lottery_Model;


import java.lang.reflect.Array;
import java.util.ArrayList;

public class NumberView extends GridPane {

    public static final int NUM_COLUMNS = 6, NUM_ROWS=7;
    private final int MAX_SELECTIONS= 6;
    private int selections = 0;
    private int value = 1;

    private ArrayList<ToggleButton> toggleButtons;

    public NumberView(){


        this.toggleButtons = new ArrayList<>();
        for(int i = 0; i < NUM_ROWS; i++){
            for(int j = 0; j < NUM_COLUMNS; j++){
                ToggleButton number = new ToggleButton(value+"");
                number.selectedProperty().addListener(((observable, oldValue, newValue) -> {

                    if(newValue){
                        selections++;
                        if(selections == MAX_SELECTIONS){
                            for(ToggleButton tb : toggleButtons){
                                if(!tb.isSelected()){
                                    tb.setDisable(true);
                                }
                            }
                        }
                    } else {
                        selections--;
                        for(ToggleButton tb : toggleButtons){
                            if(!tb.isSelected()){
                                tb.setDisable(false



                                );
                            }
                        }
                    }

                }));
                toggleButtons.add(number);
                this.add(number,j,i);
                value++;
            }
        }
    }

    public ArrayList<ToggleButton> getToggleButtons(){
        return this.toggleButtons;
    }


}

