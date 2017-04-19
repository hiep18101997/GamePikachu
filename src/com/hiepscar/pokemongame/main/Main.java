package com.hiepscar.pokemongame.main;

import com.hiepscar.pokemongame.gui.GUI;
import com.hiepscar.pokemongame.highscore.HighScore;

/**
 * Created by  HoàngHiệp on 10/26/2016.
 */
public class Main {
    public static void main(String[] args) {
        new GUI().setVisible(true);
        HighScore highScore=new HighScore();
    }
}
