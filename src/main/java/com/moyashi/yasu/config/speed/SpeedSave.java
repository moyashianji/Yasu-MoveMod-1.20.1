package com.moyashi.yasu.config.speed;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static com.moyashi.yasu.config.MoneyLoad.Money;
import static com.moyashi.yasu.main.Reference.*;


public class SpeedSave {
    public static void onSpeedSave(){
        File modsFolder = new File("mods");
        File modFolder = new File(modsFolder, "yasu");
        File configFile = new File(modFolder, "speed.txt");

        try {
            FileWriter writer = new FileWriter(configFile);
            writer.write((int) ENERSPEED);
            writer.write("\n" +MPHSPEED);
            writer.write("\n" +SYUNSOKUSPEED);
            writer.write("\n" +BIGSPEED);
            writer.write("\n" + SONICSPEED);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
