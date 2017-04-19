package com.hiepscar.pokemongame.panel;

import com.hiepscar.pokemongame.gui.GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by  HoàngHiệp on 11/3/2016.
 */
public class SavePanel extends BaseComps {
    private JLabel lbSaveName;
    private JTextField tfSaveName;
    @Override
    protected void addComps() {
//        add(lbSaveName);
//        add(tfSaveName);

    }

    @Override
    protected void initComps() {
        lbSaveName=new JLabel("Mời bạn nhập tên");
        lbSaveName.setFont(new Font("Tahoma",Font.BOLD,18));
        lbSaveName.setLocation(30,220);
        lbSaveName.setSize(160,50);
        lbSaveName.setForeground(Color.green);

        tfSaveName=new JTextField();
        tfSaveName.setFont(new Font("Tahoma",Font.BOLD,15));
        tfSaveName.setForeground(Color.black);
        tfSaveName.setSize(200,50);
        tfSaveName.setLocation(lbSaveName.getX()+170,lbSaveName.getY());
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
        drawBackGroundSavePanel(g2d);
    }

    private void drawBackGroundSavePanel(Graphics2D g2d) {
        Image imageSavePanel = new ImageIcon(getClass().getResource("/resource/background_gameover.jpg")).getImage();
        g2d.drawImage(imageSavePanel, 0, 0, GUI.WFRAME, GUI.HFRAME, null);
    }
}
