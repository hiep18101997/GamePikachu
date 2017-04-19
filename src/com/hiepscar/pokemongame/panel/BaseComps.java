package com.hiepscar.pokemongame.panel;

import javax.swing.*;

/**
 * Created by  HoàngHiệp on 10/22/2016.
 */
public abstract class BaseComps extends JPanel {
    public BaseComps() {
        initPanel();
        initComps();
        addComps();
    }

    protected abstract void addComps();

    protected abstract void initComps();

    protected abstract void initPanel();
}
