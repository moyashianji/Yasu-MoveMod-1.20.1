package com.moyashi.yasu.config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static com.moyashi.yasu.config.MoneyLoad.Money;
import static com.moyashi.yasu.main.Reference.*;


public class MoneySave {
    public static void onSave(){
        File modsFolder = new File("mods");
        File modFolder = new File(modsFolder, "yasu");
        File configFile = new File(modFolder, "config.txt");

        try {
            FileWriter writer = new FileWriter(configFile);
            writer.write(Integer.toString(Money));
            writer.write("\n" +Integer.toString((int) NetX));
            writer.write("\n" +Integer.toString((int) NetY));
            writer.write("\n" +Integer.toString((int) NetZ));
            writer.write("\n" +Integer.toString((int) NewX));
            writer.write("\n" +Integer.toString((int) NewY));
            writer.write("\n" +Integer.toString((int) NewZ));
            writer.write("\n" +Integer.toString((int) OveX));
            writer.write("\n" +Integer.toString((int) OveY));
            writer.write("\n" +Integer.toString((int) OveZ));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
