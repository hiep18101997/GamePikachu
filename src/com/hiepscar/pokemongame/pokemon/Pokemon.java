package com.hiepscar.pokemongame.pokemon;

import javax.swing.*;
import java.awt.*;

/**
 * Created by  HoàngHiệp on 10/26/2016.
 */
public class Pokemon {
    private int x, y, size, type;
    private Image imgPokemon, imgSquare;

    public Pokemon(int x, int y, int size, int type, String imgPokemonName, String imgSquareName) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.type = type;
        imgPokemon = new ImageIcon(getClass().getResource("/resource/" + imgPokemonName)).getImage();
        imgSquare = new ImageIcon(getClass().getResource("/resource/" + imgSquareName)).getImage();
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(imgSquare, x, y, size, size, null);
        g2d.drawImage(imgPokemon, x, y, size, size, null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }

    public int getType() {
        return type;
    }

    public Image getImgPokemon() {
        return imgPokemon;
    }

    public Image getImgSquare() {
        return imgSquare;
    }
}
