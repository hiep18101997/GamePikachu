package com.hiepscar.pokemongame.highscore;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by  HoàngHiệp on 11/1/2016.
 */
public class HighScore {
    File file = new File("E:/HighScore.txt");
    ArrayList<String> scores = readScoreFromFile();
    public void sortScore() {
        Comparator<String> compareDown = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int score1=Integer.parseInt(o1.substring(o1.indexOf(":")+1));
                int score2=Integer.parseInt(o2.substring(o2.indexOf(":")+1));

                return -(score1-score2);
            }
        };
        Collections.sort(scores,compareDown);
    }

    public ArrayList<String> readScoreFromFile() {

        ArrayList<String> list = new ArrayList<>();
        try {
            if (file.exists() == false) {
                file.createNewFile();
            }
            RandomAccessFile reader=new RandomAccessFile(file,"rw");
            String line = reader.readLine();
            while (line != null) {
                list.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void saveToFile(int score,String name) {
        try {
                String str=name+":"+score;
                scores.add(str);
                sortScore();
                FileOutputStream writer=new FileOutputStream(file);
                String s="";
              if(scores.size()<8){
                  for(int i=0;i<scores.size();i++){
                      s+=scores.get(i)+"\r\n";
                  }
                  writer.write(s.getBytes());
              }else {
                  for (int i = 0; i < 8; i++) {
                      s+= scores.get(i)+"\r\n";
                  }
                  writer.write(s.getBytes());
              }
              scores.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
