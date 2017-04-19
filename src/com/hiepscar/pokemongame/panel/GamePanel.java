package com.hiepscar.pokemongame.panel;

import com.hiepscar.pokemongame.gui.GUI;
import com.hiepscar.pokemongame.highscore.HighScore;
import com.hiepscar.pokemongame.pokemon.Pokemon;
import com.hiepscar.pokemongame.pokemon.PokemonManager;
import com.hiepscar.pokemongame.sound.WavEffect;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by  HoàngHiệp on 10/26/2016.
 */
public class GamePanel extends BaseComps implements Runnable {
    private PokemonManager pokemonManager;
    private JLabel lbScore, lbSwap, lbMap, lbTimeStart, lbTimeEnd, lbKetQua;
    private Thread thread;
    private int score = 0;
    private SavePanel savePanel;
    private boolean timeStart=false;
    private WavEffect effect;
    private HighScore highScore;
    public void setSavePanel(SavePanel savePanel) {
        this.savePanel = savePanel;
    }

    protected void addComps() {
        add(lbTimeStart);
        add(lbTimeEnd);
        add(lbKetQua);
        add(lbScore);
        //add(lbMap);
        add(lbSwap);
    }

    @Override
    protected void initComps() {
        highScore=new HighScore();
        pokemonManager = new PokemonManager();
        lbScore = new JLabel();
        String pathScoreLabel = getClass().getResource("/resource/stage_score.png").getPath();
        Image iconScoreLabel = new ImageIcon(pathScoreLabel).getImage();
        lbScore.setIcon(new ImageIcon(iconScoreLabel));
        lbScore.setLocation(700, 0);
        lbScore.setSize(200, 50);

        lbKetQua = new JLabel();
        lbKetQua.setFont(new Font("Tahoma", Font.BOLD, 20));
        lbKetQua.setText(score + "");
        lbKetQua.setLocation(780, 5);
        lbKetQua.setSize(100, 50);
        lbKetQua.setForeground(Color.black);

        lbMap = new JLabel();
        String pathlbMap = getClass().getResource("/resource/stage_level.png").getPath();
        Image iconlbMap = new ImageIcon(pathlbMap).getImage();
        lbMap.setIcon(new ImageIcon(iconlbMap));
        lbMap.setLocation(0, -5);
        lbMap.setSize(70, 50);

        lbSwap = new JLabel();
        String pathlbSwap = getClass().getResource("/resource/button_1_1.png").getPath();
        Image iconlbSwap = new ImageIcon(pathlbSwap).getImage();
        lbSwap.setIcon(new ImageIcon(iconlbSwap));
        lbSwap.setLocation(820, 200);
        lbSwap.setSize(70, 70);

        lbTimeStart = new JLabel();
        String pathlbTimeStart = getClass().getResource("/resource/time_1.png").getPath();
        Image iconlbTimeStart = new ImageIcon(pathlbTimeStart).getImage();
        lbTimeStart.setIcon(new ImageIcon(iconlbTimeStart));
        lbTimeStart.setLocation(160, 0);
        lbTimeStart.setSize(380, 50);

        lbTimeEnd = new JLabel();
        String pathlbTimeEnd = getClass().getResource("/resource/time_0.png").getPath();
        Image iconlbTimeEnd = new ImageIcon(pathlbTimeEnd).getImage();
        lbTimeEnd.setIcon(new ImageIcon(iconlbTimeEnd));
        lbTimeEnd.setLocation(160, 0);
        lbTimeEnd.setSize(400, 50);
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = (e.getX() - PokemonManager.PADDING) / PokemonManager.SIZE;
                int y = (e.getY() - PokemonManager.PADDING) / PokemonManager.SIZE;
                if (x > 0 && y > 0 && x < PokemonManager.COL + 1 && y < PokemonManager.ROW + 1) {
                    System.out.println(x + "," + y);
                    if (pokemonManager.saveTwoLocationClick(x, y)) {
                        score += 10;
                        lbKetQua.setText(score + "");
                    }
                    repaint();
                }

            }
        };
        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lbSwap.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resource/button_1_2.png")).getImage()));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lbSwap.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resource/button_1_1.png")).getImage()));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                doMouseClick();
            }
        };
        addMouseListener(mouseAdapter);
        lbSwap.addMouseListener(adapter);
        thread = new Thread(this);
        thread.start();
    }

    private void doMouseClick() {
        score-=20;
        lbKetQua.setText(score+"");
        int arr[][] = pokemonManager.getMatrix();
        ArrayList<Integer> integers=new ArrayList<>();
        Random random=new Random();
        for (int i = 1; i < PokemonManager.COL + 1; i++) {
            for (int j = 1; j < PokemonManager.ROW + 1; j++) {
                integers.add(arr[i][j]);
            }
        }
        for (int i = 1; i < PokemonManager.COL + 1; i++) {
            for (int j = 1; j < PokemonManager.ROW + 1; j++) {
                int index=random.nextInt(integers.size());
                arr[i][j]=integers.get(index);
                integers.remove(index);
            }
        }
        pokemonManager.setMatrix(arr);
        List<Pokemon> list = new ArrayList<>();
        String imgSquareName = "square_1.png";
        for (int i = 1; i < PokemonManager.COL + 1; i++) {
            for (int j = 1; j < PokemonManager.ROW + 1; j++) {
                if(arr[i][j]!=0){
                    int type = arr[i][j];
                    int x = i * PokemonManager.SIZE + PokemonManager.PADDING;
                    int y = j * PokemonManager.SIZE + PokemonManager.PADDING;
                    String imgPikachuName = "p_" + type + "_cap1.png";
                    Pokemon pokemon = new Pokemon(x, y, PokemonManager.SIZE, type, imgPikachuName, imgSquareName);
                    list.add(pokemon);
                }
            }
        }
        pokemonManager.setPokemonList(list);
    }
    @Override
    protected void initPanel() {
        setBackground(Color.red);
        setLayout(null);
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        pokemonManager.drawAllPokemon(g2d);

    }

    @Override
    public void run() {
        int size = 380;
        boolean check = false;
        while (true) {
            if(timeStart){
                if(size==380){
                    effect=new WavEffect("caripokemon");
                    effect.loop(Clip.LOOP_CONTINUOUSLY);
                    effect.play();
                }
               lbTimeStart.setSize(size--, 50);
           }
            if (pokemonManager.getPokemonList().size() == 0) {
                JOptionPane.showMessageDialog(this, "Bạn thắng rồi!\n Điểm của bạn là: "+score);
                check = true;
            }
            if (size == 0) {
                JOptionPane.showMessageDialog(this, "Hết thời gian.Bạn thua rồi!\n Điểm của bạn là: "+score);
                check = true;
            }
            if (check == true) {
                String name=JOptionPane.showInputDialog(this,"Mời bạn nhập tên (1 chữ thôi nha :D)");
                highScore.saveToFile(score,name);
                Container parent = getParent();
                CardLayout layoutParent = (CardLayout) parent.getLayout();
                layoutParent.show(parent, GUI.KEY_PANEL_SAVE);
                savePanel.requestFocus();
                effect.stop();
                WavEffect effect=new WavEffect("gameover");
                effect.loop(Clip.LOOP_CONTINUOUSLY);
                effect.play();
                break;
            }
            try {
                Thread.sleep(1200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();
        }
    }

    public void setTimeStart(boolean timeStart) {
        this.timeStart = timeStart;
    }
}
