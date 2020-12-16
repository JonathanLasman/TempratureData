package com.jonathan;

import com.google.gson.Gson;
import java.io.Console;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;


public class UserConsole implements Runnable {
    //validates input from user
    private static Integer tryParse(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return null;
        }
    }
    private static Scanner in = new Scanner(System.in);
    private static String localPath = System.getProperty("user.dir")+"/Measurement/";
    @Override
    public void run() {
        while (true)
        {
            final String INSTRUCTIONS = "Welcome Yochevet!"+"\n"+"Please type in the sensor ID you'd like to get information about, 0 for general stats, or -1 to exit the program.";
            System.out.println(INSTRUCTIONS);
            String input = in.nextLine();
            int intInput= tryParse(input);
            if (Integer.parseInt(input)==0) {
            System.out.println("Max global temp sampled: "+GlobalData.getGlobalMax());
            System.out.println("Min global temp sampled: "+GlobalData.getGlobalMin());
            System.out.println("Avg global temp: "+GlobalData.getGlobalAvg());
            }
            else
                if (intInput!=-1)
            {
                // JSON file to Java object
                Gson gson = new Gson();
                SensorData sensorData = null;
                try {
                    sensorData = gson.fromJson(new FileReader(localPath+input+".json"), SensorData.class);
                    System.out.println("Max temp sampled: "+sensorData.getMaxTemp());
                    System.out.println("Min temp sampled: "+sensorData.getMinTemp());
                    System.out.println("Avg temp: "+sensorData.getAvgTemp());
                } catch (FileNotFoundException e) {
                    System.out.println("No data for the sensor");
                }

            }
                else
                    System.exit(1);
        }
    }


}
