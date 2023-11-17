package com.moyashi.yasu.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import com.moyashi.yasu.main.Reference;

import static com.moyashi.yasu.main.Reference.*;

public class MoneyLoad {

    public static int Money;
    public static String MoneyValue = "";


    public static void main(String[] args) {

    }

    public static void extractDoubleFromTextFile() {
        File modsFolder = new File("mods");
        File modFolder = new File(modsFolder, "yasu");
        File configFile = new File(modFolder, "config.txt");

        if (configFile.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(configFile));
                String line;

                // ファイルから読み取ったテキストを整数に変換


                int lineNumber = 1;

                while ((line = reader.readLine()) != null) {
                    if (lineNumber == 1) {
                        Money = Integer.parseInt(line);

                    } else if (lineNumber == 2) {
                        NetX = Integer.parseInt(line);
                        System.out.println(NetX);
                    } else if (lineNumber == 3) {
                        NetY = Integer.parseInt(line);
                        System.out.println(NetY);
                    } else if (lineNumber == 4) {
                        NetZ = Integer.parseInt(line);
                        System.out.println(NetZ);
                    } else if (lineNumber == 5) {
                        NewX = Integer.parseInt(line);
                        System.out.println(NewX);
                    } else if (lineNumber == 6) {
                        NewY = Integer.parseInt(line);
                        System.out.println(NewY);
                    } else if (lineNumber == 7) {
                        NewZ = Integer.parseInt(line);
                        System.out.println(NewZ);
                    } else if (lineNumber == 8) {
                        OveX = Integer.parseInt(line);
                        System.out.println(OveX);
                    } else if (lineNumber == 9) {
                        OveY = Integer.parseInt(line);
                        System.out.println(OveY);
                    } else if (lineNumber == 10) {
                        OveZ = Integer.parseInt(line);
                        System.out.println(OveZ);
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
