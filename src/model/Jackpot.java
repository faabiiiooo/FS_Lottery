package model;

import java.io.*;
import java.util.ArrayList;

public class Jackpot {

    public static double jackpot;
    private final static double jack6 = 1000000;
    private final static double jack51 = 10000;
    private final static double jack5 = 1000;
    private final static double jack41 = 150;
    private final static double jack4 = 100;
    private final static double jack31 = 25;
    private final static double jack3 = 10;

    private final static double jackLower = 0;

    private static File jackpotFile;

    public static void importJackpot(){
        jackpotFile = new File("./src/resources/jackpotFile.txt");

        if(jackpotFile.exists()){
            try(BufferedReader reader = new BufferedReader(new FileReader(jackpotFile))){

                String line;
                while ((line = reader.readLine()) != null) {
                    jackpot = Double.parseDouble(line);
                }


            } catch (IOException e){
                e.printStackTrace();
            }
        } else {
            jackpot = 1000000;
        }

    }

    public static void saveToFile(){
        jackpotFile = new File("./src/resources/jackpotFile.txt");

        try(FileWriter writer = new FileWriter(jackpotFile)){

            jackpotFile.createNewFile();
            writer.write(jackpot+"");
            writer.flush();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static double checkJackpotWon(WinType winType){

        double jack = 0.0;
        boolean is61 = false;

        switch (winType) {
            case six1:
                jack = jackpot;
                is61 = true;
            break;
            case six:
                jack= jack6;
            break;
            case five1:
                jack= jack51;
            break;
            case five:
                jack= jack5;
            break;
            case four1:
                jack= jack41;
            break;
            case four:
                jack= jack4;
            break;
            case three1:
                jack= jack31;
            break;
            case three:
                jack= jack3;
            break;
            case lower:
                jack= jackLower;
            break;
        }

        if(is61) resetJackpot();
        if(!is61) increaseJackpot();

        return jack;
    }

    public static void resetJackpot(){
        jackpot = 1000000;
    }

    public static ArrayList<Double> getJackpotsAsList(){
        ArrayList<Double> jackpots = new ArrayList<>();
        jackpots.add(jack3);
        jackpots.add(jack31);
        jackpots.add(jack4);
        jackpots.add(jack41);
        jackpots.add(jack5);
        jackpots.add(jack51);
        jackpots.add(jack6);
        jackpots.add(jackpot);

        return jackpots;
    }

    private static void increaseJackpot(){
        jackpot += 1000000;
    }



}
