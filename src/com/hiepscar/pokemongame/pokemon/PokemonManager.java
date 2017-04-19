package com.hiepscar.pokemongame.pokemon;

import com.hiepscar.pokemongame.gui.GUI;
import com.hiepscar.pokemongame.sound.WavEffect;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by hoanghiep on 11/1/16.
 */
public class PokemonManager {
    public static final int COL=14;
    public static final int ROW=10;
    public static final int SIZE=50;
    public static final int PADDING=50;
    private List<Pokemon> pokemonList;
    private List<Point> twoPoints=new ArrayList<>();
    private Random random;
    private Image bg;
    private int matrix[][];
    public PokemonManager(){
        createMatrix();
        printMatrix();
        initListPokemon();
    }
    private void printMatrix() {
        for (int i=0;i<ROW+2;i++){
            for (int j=0;j<COL+2;j++){
                System.out.print(matrix[j][i]+"\t");
            }
            System.out.println();
        }
    }
    private void createMatrix() {
        random=new Random();
         matrix= new  int[COL+2][ROW+2];
        for(int i=0;i<COL+2;i++){
            matrix[i][0]=matrix[i][ROW+1]=0;
        }
        for (int i=0;i<ROW+2;i++){
            matrix[0][i]=matrix[COL+1][i]=0;
        }
        int imgNumber=48;
        ArrayList<Point> listPoint = new ArrayList<Point>();
        for (int i = 1; i <COL+1; i++) {
            for (int j = 1; j < ROW+1; j++) {
                listPoint.add(new Point(i, j));
            }
        }
        int i=0;
        do {
            int imgIndex = random.nextInt(imgNumber) + 1;
            for (int j = 0; j < 2; j++) {
                int size = listPoint.size();
                int pointIndex = random.nextInt(size);
                matrix[listPoint.get(pointIndex).x][listPoint.get(pointIndex).y] = imgIndex;
                listPoint.remove(pointIndex);
            }
            i++;
        } while (i < ROW*COL/ 2);
    }
    private void initListPokemon() {
        pokemonList=new ArrayList<>();
        random = new Random();
        bg = new ImageIcon(getClass().getResource("/resource/bg"+(random.nextInt(12)+1)+".png")).getImage();

        String imgSquareName = "square_1.png";
        for (int i = 1; i < COL+1; i++) {
            for (int j = 1; j < ROW+1; j++) {
                int type=matrix[i][j];
                int x = i * SIZE + PADDING;
                int y = j * SIZE + PADDING;
                String imgPikachuName = "p_" +type+ "_cap1.png";
                Pokemon pokemon = new Pokemon(x, y,SIZE,type, imgPikachuName,imgSquareName);
                pokemonList.add(pokemon);
            }
        }
    }
    public void drawAllPokemon(Graphics2D g2d) {
        g2d.drawImage(bg, 0, 0, GUI.WFRAME, GUI.HFRAME, null);
        int size=pokemonList.size();
        for (int i=0;i<size;i++) {
            pokemonList.get(i).draw(g2d);
        }
    }

    public void deletePokemon(int x, int y) {
        matrix[x][y]=0;
        for (int i=0;i<pokemonList.size();i++) {
            if(pokemonList.get(i).getX()==x * SIZE + PADDING &&pokemonList.get(i).getY()==y* SIZE + PADDING){
                pokemonList.remove(i);
            }
        }
        //printMatrix();
    }
    public boolean saveTwoLocationClick(int x,int y){
        if (twoPoints.size()==2){
            twoPoints.clear();
        }
        twoPoints.add(new Point(x,y));
        WavEffect effect;
        if(twoPoints.size()==1){
            effect=new WavEffect("effect_1");
            effect.play();
        }else {
            effect=new WavEffect("effect_2");
            effect.play();
        }

        if (twoPoints.size()==2){
            if(matrix[twoPoints.get(0).x][twoPoints.get(0).y]==matrix[twoPoints.get(1).x][twoPoints.get(1).y]&&checkTwoPoint(twoPoints.get(0),twoPoints.get(1))&&twoPoints.get(0).equals(twoPoints.get(1))==false){
                deletePokemon(twoPoints.get(0).x,twoPoints.get(0).y);
                deletePokemon(twoPoints.get(1).x,twoPoints.get(1).y);
                effect=new WavEffect("score");
                effect.play();
                return true;
            }else {
                effect=new WavEffect("effect_wrong");
                effect.play();
            }
        }
        return false;
    }

    public List<Pokemon> getPokemonList() {
        return pokemonList;
    }

    public void setPokemonList(List<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    //Bắt đầu sang thuật toán ăn Pokemon
    private boolean checkRow(int x1,int x2,int y){
        //Ăn theo 1 hàng chiều ngang
        int max=Math.max(x1,x2);
        int min=Math.min(x1,x2);
        for (int x=min+1;x<max;x++){
            if(matrix[x][y]>0){
                return false;
            }
        }
        return true;
    }
    private boolean checkColum(int y1,int y2,int x){
        //Ăn theo 1 hàng chiều dọc
        int max=Math.max(y1,y2);
        int min=Math.min(y1,y2);
        for (int y=min+1;y<max;y++){
             if(matrix[x][y]>0){
                return false;
            }
        }
        return true;
    }
    private boolean  checkRectLeftToRight(Point p1,Point p2){
        //Ăn hình chữ Z chiều ngang
        Point pointMinX=p1;
        Point pointMaxX=p2;
        if(p1.x>p2.x){
            pointMaxX=p1;
            pointMinX=p2;
        }
        for(int x=pointMinX.x;x<pointMaxX.x+1;x++){
            if (x > pointMinX.x && matrix[x][pointMinX.y] > 0) {
                return false;
            }
            if ((matrix[x][pointMaxX.y] == 0 || x-pointMaxX.x==0)
                    && checkColum(pointMinX.y, pointMaxX.y, x)
                    && checkRow(x, pointMaxX.x, pointMaxX.y)) {
                return true;
            }
        }
    return false;
    }
    private boolean checkRectUptoDown(Point p1,Point p2){
        //Ăn hình chữ Z chiều dọc
        Point pointMinY=p1;
        Point pointMaxY=p2;
        if(p1.y>p2.y){
            pointMaxY=p1;
            pointMinY=p2;
        }
        for(int y=pointMinY.y;y<pointMaxY.y+1;y++){
            if (y > pointMinY.y && matrix[pointMinY.x][y] > 0) {
                return false;
            }
            if ((matrix[pointMaxY.x][y] == 0 || y- pointMaxY.y==0)
                    && checkRow(pointMinY.x, pointMaxY.x, y)
                    && checkColum(y, pointMaxY.y, pointMaxY.x)) {
                return true;
            }
        }
        return false;
    }
    private boolean checkMoreRowUp(Point p1, Point p2){
        //Ăn theo hình chữ L hoặc chữ U theo chiều dọc (Mở rộng lên phía trên)
        Point pointMaxY=p1;
        Point pointMinY=p2;
        if(p1.y<p2.y){
            pointMaxY=p2;
            pointMinY=p1;
        }
        int y=pointMinY.y-1;
        if((matrix[pointMaxY.x][pointMaxY.y] == 0 || pointMinY.y == pointMaxY.y)
                && checkColum(pointMinY.y, pointMaxY.y, pointMaxY.x)){
            while (matrix[pointMaxY.x][y]==0&&matrix[pointMinY.x][y]==0){
                if(checkRow(pointMaxY.x,pointMinY.x,y)){
                    return true;
                }
                y--;
            }
        }
        return false;
    }
    private boolean checkMoreRowDown(Point p1, Point p2){
        //Ăn theo hình chữ L hoặc chữ U theo chiều dọc(Mở rông xuống phía dưới)
        Point pointMaxY=p1;
        Point pointMinY=p2;
        if(p1.y<p2.y){
            pointMaxY=p2;
            pointMinY=p1;
        }
        int y=pointMaxY.y+1;
        if((matrix[pointMinY.x][pointMaxY.y] == 0 || pointMaxY.y == pointMinY.y)
                && checkColum(pointMaxY.y, pointMinY.y, pointMinY.x)){
            while (matrix[pointMinY.x][y]==0&&matrix[pointMaxY.x][y]==0){
                if(checkRow(pointMinY.x,pointMaxY.x,y)){
                    return true;
                }
                y++;
            }
        }
        return false;
    }
    private boolean checkMoreColumRight(Point p1, Point p2){
        //Ăn theo hình chữ L hoặc chữ U theo chiều ngang(Mở rộng sang bên phải)
        Point pointMaxX=p1;
        Point pointMinX=p2;
        if(p1.x<p2.x){
            pointMaxX=p2;
            pointMinX=p1;
        }
        int x=pointMaxX.x+1;
        if(checkRow(pointMinX.x,x,pointMinX.y)){
            while (matrix[x][pointMinX.y]==0&&matrix[x][pointMaxX.y]==0){
                if(checkColum(pointMinX.y,pointMaxX.y,x)){
                    return true;
                }
                x++;
            }
        }
        return false;
    }
    private boolean checkMoreColumLeft(Point p1, Point p2){
        //Ăn theo hình chữ L hoặc chữ U theo chiều ngang(Mở rộng sang bên trái)
        Point pointMaxX=p1;
        Point pointMinX=p2;
        if(p1.x<p2.x){
            pointMaxX=p2;
            pointMinX=p1;
        }
        int x=pointMinX.x-1;
        if(checkRow(pointMaxX.x,x,pointMaxX.y)){
            while (matrix[x][pointMaxX.y]==0&&matrix[x][pointMinX.y]==0){
                if(checkColum(pointMaxX.y,pointMinX.y,x)){
                    return true;
                }
                x--;
            }
        }
        return false;
    }
    public boolean checkTwoPoint(Point p1,Point p2){
       if(checkRow(p1.x,p2.x,p1.y)&&p1.y==p2.y){
           System.out.println("Row Check");
           return true;
       }
       if (checkColum(p1.y,p2.y,p1.x)&&p1.x==p2.x){
           System.out.println("Colum Check");
           return true;
       }
       if (checkRectLeftToRight(p1,p2)){
           System.out.println("RectLeftToRight Check");
           return true;
       }
       if (checkRectUptoDown(p1,p2)){
           System.out.println("RectUptoDown Check");
           return true;
       }
       if(checkMoreColumRight(p1,p2)){
           System.out.println("MoreColumRight Check");
           return true;
       }
       if(checkMoreColumLeft(p1,p2)){
           System.out.println("MoreColumLeft Check");
           return true;
       }
       if (checkMoreRowUp(p1,p2)){
           System.out.println("MoreRowUp Check");
           return true;
       }
       if (checkMoreRowDown(p1,p2)){
           System.out.println("MoreRowDown Check");
           return true;
       }
        return false;
    }
}
