package com.hiepscar.pokemongame.gui;

import com.hiepscar.pokemongame.panel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by  HoàngHiệp on 10/22/2016.
 */
public class GUI extends JFrame {
    public static final int WFRAME = 900;
    public static final int HFRAME = 700;
    public static final String KEY_PANEL_GAME = "GAME";
    public static final String KEY_PANEL_MENU = "MENU";
    public static final String KEY_PANEL_SAVE = "SAVE";
    public static final String KEY_PANEL_HELP = "HELP";
    public static final String KEY_PANEL_HIGHTSCORE = "HIGHTSCORE";
    private MenuPanel menuPanel;
    private GamePanel gamePanel;
    private SavePanel savePanel;
    private HelpPanel helpPanel;
    private HighScorePanel highScorePanel;

    public GUI() {
        initGUI();
        initComps();
        addComps();
    }

    private void addComps() {
        add(menuPanel, KEY_PANEL_MENU);
        add(gamePanel, KEY_PANEL_GAME);
        add(savePanel, KEY_PANEL_SAVE);
        add(helpPanel,KEY_PANEL_HELP);
        add(highScorePanel,KEY_PANEL_HIGHTSCORE);
    }

    private void initComps() {
        menuPanel = new MenuPanel();
        gamePanel = new GamePanel();
        savePanel = new SavePanel();
        helpPanel = new HelpPanel();
        highScorePanel =new HighScorePanel();
        menuPanel.setGamePanel(gamePanel);
        menuPanel.setHelpPanel(helpPanel);
        menuPanel.setHighScorePanel(highScorePanel);
        gamePanel.setSavePanel(savePanel);
        helpPanel.setMenuPanel(menuPanel);
        highScorePanel.setMenuPanel(menuPanel);

    }

    private void initGUI() {
        setTitle("Đinh Nguyễn Hoàng Hiệp - UET - LAND1608E");
        setSize(WFRAME, HFRAME);
        setLayout(new CardLayout());
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        WindowAdapter windowAdapter = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                showMessage();
            }
        };
        addWindowListener(windowAdapter);
    }

    private void showMessage() {
        int result = JOptionPane.showConfirmDialog(this, "Bạn có muốn thoát không ?");
        if (result == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
