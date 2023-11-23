package com.moyashi.yasu.config.speed;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static com.moyashi.yasu.main.Reference.*;

public class SpeedLoad {

    public static int Money;
    public static String MoneyValue = "";


    public static void main(String[] args) {

    }

    public static void SPextractDoubleFromTextFile() {
        File modsFolder = new File("mods");
        File modFolder = new File(modsFolder, "yasu");
        File configFile = new File(modFolder, "speed.txt");

        if (configFile.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(configFile));
                String line;

                // ファイルから読み取ったテキストを整数に変換


                int lineNumber = 1;

                while ((line = reader.readLine()) != null) {
                    if (lineNumber == 1) {
                        ENERSPEED = Double.parseDouble(line);

                    } else if (lineNumber == 2) {
                        MPHSPEED = Double.parseDouble(line);
                        System.out.println(MPHSPEED);
                    } else if (lineNumber == 3) {
                        SYUNSOKUSPEED = Double.parseDouble(line);
                        System.out.println(SYUNSOKUSPEED);
                    } else if (lineNumber == 4) {
                        BIGSPEED = Double.parseDouble(line);
                        System.out.println(BIGSPEED);
                    } else if (lineNumber == 5) {
                        SONICSPEED = Double.parseDouble(line);
                        System.out.println(BIGSPEED);

                        break; // 最後の行を読み込んだらループを終了
                    }
                    lineNumber++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
