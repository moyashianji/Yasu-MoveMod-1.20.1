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
            writer.write("\n" +NetX);
            writer.write("\n" +NetY);
            writer.write("\n" +NetZ);
            writer.write("\n" + NewX);
            writer.write("\n" + NewY);
            writer.write("\n" + NewZ);
            writer.write("\n" + OveX);
            writer.write("\n" +OveY);
            writer.write("\n" +OveZ);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
