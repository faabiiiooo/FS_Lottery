package model;

import app.Lottery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Statistics {

    private File winNumbersFile;
    private final ArrayList<Integer> winNumbers = new ArrayList<>();
    private final ArrayList<Integer> numbers = new ArrayList<>();
    private int gameCounter;

    private final ObservableList<StatisticNumber> statistics = FXCollections.observableArrayList();

    public Statistics(){

        for(int i = 1; i <= Lottery.HIGHEST_TIP; i++){
            numbers.add(i);
        }
        gameCounter = 0;
        winNumbersFile = new File("./src/resources/winNumbersFile.txt");
        this.createStats();

    }

    private void createStats(){ //create stats based on file.
        DecimalFormat fmt = new DecimalFormat("00.0#%");
        if(winNumbersFile.exists()){
            this.importData();
            HashSet<Integer> hashSet = new HashSet<>(winNumbers);

            for(int i : hashSet){
                double tempStat = (Collections.frequency(winNumbers,i) / (double) gameCounter);
                statistics.add(new StatisticNumber(i, Collections.frequency(winNumbers,i), fmt.format(tempStat)));

            }
        } else {
            for(int i = 1; i <= Lottery.HIGHEST_TIP; i++){
                statistics.add(new StatisticNumber(i,0,"no Data available, play first!"));
            }
        }






    }

    private void importData(){ //imports Data from file

        try(BufferedReader reader = new BufferedReader(new FileReader(winNumbersFile))){

            String line;
            String[] tempNumbers;
            while((line = reader.readLine()) != null){

                tempNumbers = line.split(";");
                for(int i = 0; i < tempNumbers.length-2; i++){
                    winNumbers.add(Integer.parseInt(tempNumbers[i]));
                }
                gameCounter++;
            }

        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public ObservableList<StatisticNumber> getStatistics(){ return this.statistics; }

    public int getGameCounter(){return this.gameCounter; }
}
