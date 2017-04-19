package com.hiepscar.pokemongame.panel;

import com.hiepscar.pokemongame.gui.GUI;
import com.hiepscar.pokemongame.highscore.HighScore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by  HoàngHiệp on 11/5/2016.
 */
public class HighScorePanel extends BaseComps {
    private JLabel lbBack;
    private MenuPanel menuPanel;
    private HighScore highScore;
    public void setMenuPanel(MenuPanel menuPanel){
        this.menuPanel=menuPanel;
    }
    @Override
    protected void addComps() {
        add(lbBack);
    }

    @Override
    protected void initComps() {
        highScore=new HighScore();
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                lbBack.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resource/button_back.png")).getImage()));
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                lbBack.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resource/buttonc_back.png")).getImage()));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                doMouseClicked();
            }
        };
        lbBack = new JLabel();
        lbBack.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resource/buttonc_back.png")).getImage()));
        lbBack.setLocation(785, 585);
        lbBack.setSize(80, 80);
        lbBack.addMouseListener(mouseAdapter);

    }

    private void doMouseClicked() {
        Container parent = getParent();
        CardLayout layoutParent = (CardLayout) parent.getLayout();
        layoutParent.show(parent, GUI.KEY_PANEL_MENU);
        menuPanel.requestFocus();
    }

    @Override
    protected void initPanel() {
    setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d=(Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        drawHighScoreBackgound(g2d);
        drawHighScoreList(g2d);
    }

    private void drawHighScoreList(Graphics2D g2d) {
        ArrayList<String> list=highScore.readScoreFromFile();
        g2d.setColor(Color.black);
        g2d.setFont(new Font("Tahoma",Font.BOLD,30));
        for(int i=0;i<list.size();i++){
            if(i==0){
                g2d.drawString(list.get(0),180,261-10);
            }
            if(i==1){
                g2d.drawString(list.get(1),180,332-10);
            }
            if(i==2){
                g2d.drawString(list.get(2),180,403-10);
            }
            if(i==3){
                g2d.drawString(list.get(3),180,474-10);
            }
            if(i==4){
                g2d.drawString(list.get(4),490,261-10);
            }
            if(i==5){
                g2d.drawString(list.get(5),490,332-10);
            }
            if(i==6){
                g2d.drawString(list.get(6),490,403-10);
            }
            if(i==7){
                g2d.drawString(list.get(7),490,474-10);
            }

        }
    }

    private void drawHighScoreBackgound(Graphics2D g2d) {
        Image imageMenu = new ImageIcon(getClass().getResource("/resource/highscore.png")).getImage();
        g2d.drawImage(imageMenu, 0, 0, GUI.WFRAME, GUI.HFRAME, null);
    }
}
