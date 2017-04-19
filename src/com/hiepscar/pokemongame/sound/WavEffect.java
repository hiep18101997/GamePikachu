package com.hiepscar.pokemongame.sound;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class WavEffect {
    private Clip clip;

    public WavEffect(String name) {
        try {
            URL url = getClass().getResource("/resource/" + name + ".wav");
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(url);

            clip = AudioSystem.getClip();
            clip.open(inputStream);
        } catch (UnsupportedAudioFileException
                | IOException
                | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip.isOpen() && !clip.isRunning()) {
            clip.start();
        }
    }

    public void stop() {
        if (clip.isRunning()) {
            clip.stop();
        }
    }

    public void loop(int count) {
        clip.loop(count);
    }

}