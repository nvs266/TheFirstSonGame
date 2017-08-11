package bases;

import tklibs.AudioUtils;

import javax.sound.sampled.Clip;

public class Audio {
    public Clip clip;
    public Audio (String url) {
        clip = AudioUtils.loadSound(url);
    }

    public void play() {
        this.clip.setFramePosition(0);
        this.clip.start();
    }
}
