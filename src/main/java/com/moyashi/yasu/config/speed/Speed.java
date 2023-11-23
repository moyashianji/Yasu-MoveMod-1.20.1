package com.moyashi.yasu.config.speed;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Speed {
    public void Config(){

    }
    public static void SPgenerateConfigFile(){
        File modsFolder = new File("mods");
        File modFolder = new File(modsFolder, "yasu");

        System.out.println("Already File Created");

        //モッド名のフォルダが存在しなければ生成する
        if(!modFolder.exists()){
            modFolder.mkdirs();
            System.out.println("Folder Created");
        }

        File configFile = new File(modFolder, "speed.txt");
        System.out.println("Already Speed Created");

        if(!configFile.exists()){
            try{
                configFile.createNewFile();

                FileWriter writer = new FileWriter(configFile);
                writer.write("2.0");
                writer.write("\n" +"2.0");
                writer.write("\n" +"1.0");
                writer.write("\n" +"3.0");
                writer.write("\n" +"1.2");


                writer.close();

            } catch (IOException e) {

                e.printStackTrace();
            }

        }
    }
}
