package com.hiepscar.pokemongame.panel;

import com.hiepscar.pokemongame.gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by  HoàngHiệp on 11/5/2016.
 */
public class HelpPanel extends BaseComps {
    private JLabel lbBack;
    private MenuPanel menuPanel;
    public void setMenuPanel(MenuPanel menuPanel){
        this.menuPanel=menuPanel;
    }
    @Override
    protected void addComps() {
        add(lbBack);
    }

    @Override
    protected void initComps() {
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
        lbBack.setLocation(800, 600);
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
        drawHelpBackgound(g2d);
    }

    private void drawHelpBackgound(Graphics2D g2d) {
        Image imageMenu = new ImageIcon(getClass().getResource("/resource/help1.png")).getImage();
        g2d.drawImage(imageMenu, 0, 0, GUI.WFRAME, GUI.HFRAME, null);
    }
}
