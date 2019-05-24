package model;

import app.Lottery;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class NumberGen {

    private final ArrayList<Integer> allNumbers = new ArrayList<>();
    private final ArrayList<Integer> allLuckyTips = new ArrayList<>();

    private final ArrayList<Integer> winNumbers = new ArrayList<>();
    private int winLuckyTip;

    private File winNumbersFile;

    public NumberGen(){

        winNumbersFile = new File("./src/resources/winNumbersFile.txt");

        for(int i = 1; i <= Lottery.HIGHEST_TIP;i++){
            allNumbers.add(i);
        }

        for(int i = 1; i <= Lottery.HIGHEST_LUCKY_TIP; i++){
            allLuckyTips.add(i);
        }

        generateWinNumbers();

    }

    private void generateWinNumbers(){

        //first shuffle both collections.
        Collections.shuffle(allNumbers);
        Collections.shuffle(allLuckyTips);

        //get the first 6 numbers
        for(int i = 0; i < Lottery.MAX_TIPS; i++){
            winNumbers.add(allNumbers.get(i));
        }

        //get the first lucky tip
        winLuckyTip = allLuckyTips.get(0);
        this.saveWinNumbersToFile();

        WinType.setWinNumbers(this.winNumbers, this.winLuckyTip);
    }

    private void saveWinNumbersToFile(){

        try(FileWriter writer = new FileWriter(winNumbersFile, true)){
            if(!winNumbersFile.exists()) winNumbersFile.createNewFile();

            writer.write(this.convertToString());
            writer.flush();

        } catch (IOException e){
            e.printStackTrace();
        }

    }

    private String convertToString(){
        String converted = "";
        for(int i : winNumbers){
            converted += i +";";
        }
        converted += winLuckyTip +"\n";

        return converted;
    }

    public ArrayList<Integer> getWinNumbers(){return this.winNumbers;}

    public int getWinLuckyTip(){return this.winLuckyTip;}
}
