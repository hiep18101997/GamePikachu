package com.hiepscar.pokemongame.panel;

import com.hiepscar.pokemongame.gui.GUI;
import com.hiepscar.pokemongame.sound.WavEffect;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

/**
 * Created by  HoàngHiệp on 10/22/2016.
 */
public class MenuPanel extends BaseComps {
    private JLabel lbPlayGame, lbHighScore,lbHelp;
    private GamePanel gamePanel;
    private HelpPanel helpPanel;
    private HighScorePanel highScorePanel;
    private File file;
    private WavEffect effect;
    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    public void setHelpPanel(HelpPanel helpPanel){
        this.helpPanel=helpPanel;
    }
    public void setHighScorePanel(HighScorePanel highScorePanel){
        this.highScorePanel =highScorePanel;
    }
    @Override
    protected void addComps() {
        add(lbHelp);
        add(lbPlayGame);
        add(lbHighScore);

    }

    @Override
    protected void initComps() {
        effect=new WavEffect("main");
        effect.loop(Clip.LOOP_CONTINUOUSLY);
        effect.play();
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                lbPlayGame.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resource/buttonu_0_2.png")).getImage()));
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                lbPlayGame.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resource/buttonu_0_1.png")).getImage()));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                doMouseClicked();
            }
        };
        MouseAdapter adapter=new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                lbHighScore.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resource/buttonu_1_2.png")).getImage()));
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                lbHighScore.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resource/buttonu_1_1.png")).getImage()));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                showHightScorePanel();
            }
        };
        MouseAdapter adapter1=new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showHelpPanel();
            }
        };
        lbPlayGame = new JLabel();
        lbPlayGame.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resource/buttonu_0_1.png")).getImage()));
        lbPlayGame.setLocation(310, 357);
        lbPlayGame.setSize(270, 100);
        lbPlayGame.addMouseListener(mouseAdapter);

        lbHighScore = new JLabel();
        lbHighScore.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resource/buttonu_1_1.png")).getImage()));
        lbHighScore.setLocation(340, 450);
        lbHighScore.setSize(210, 100);
        lbHighScore.addMouseListener(adapter);

        lbHelp = new JLabel();
        lbHelp.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resource/buttonhelp.png")).getImage()));
        lbHelp.setLocation(600,365);
        lbHelp.setSize(70, 70);
        lbHelp.addMouseListener(adapter1);
    }

    private void showHelpPanel() {
        Container parent = getParent();
        CardLayout layoutParent = (CardLayout) parent.getLayout();
        layoutParent.show(parent, GUI.KEY_PANEL_HELP);
        helpPanel.requestFocus();
    }

    private void showHightScorePanel() {
        Container parent = getParent();
        CardLayout layoutParent = (CardLayout) parent.getLayout();
        layoutParent.show(parent, GUI.KEY_PANEL_HIGHTSCORE);
        highScorePanel.requestFocus();
    }

    private void doMouseClicked() {
        Container parent = getParent();
        CardLayout layoutParent = (CardLayout) parent.getLayout();
        layoutParent.show(parent, GUI.KEY_PANEL_GAME);
        gamePanel.requestFocus();
        gamePanel.setTimeStart(true);
        effect.stop();
    }

    @Override
    protected void initPanel() {
        setLayout(null);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        drawBackGroundMenu(g2d);
    }

    private void drawBackGroundMenu(Graphics2D g2d) {
        Image imageMenu = new ImageIcon(getClass().getResource("/resource/background_main.png")).getImage();
        g2d.drawImage(imageMenu, 0, 0, GUI.WFRAME, GUI.HFRAME, null);
    }
}
