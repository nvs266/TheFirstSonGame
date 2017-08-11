package scenes;

import Utils.Utils;
import bases.Audio;
import bases.GameObject;
import bases.Setting;
import bases.Vector2D;
import bases.renderers.Animation;
import javafx.scene.media.MediaPlayer;
import players.Player;
import tklibs.AudioUtils;

import java.awt.*;
import java.io.IOException;

public class Victory extends Scene {
    Animation playerAnimation;
    MediaPlayer victoryMedia;

    @Override
    public void init() throws IOException {
        Player.instance = null;
        GameObject.clear();
        addBackground();
        playerAnimation = new Animation(
                25,true,
                Utils.loadImage("assets/image/player/new Image/straight0.png"),
                Utils.loadImage("assets/image/player/new Image/straight1.png"),
                Utils.loadImage("assets/image/player/new Image/straight2.png"),
                Utils.loadImage("assets/image/player/new Image/straight1.png")
        );
        setVictoryMedia();
    }

    private void addBackground() {
        background = Utils.loadImage("assets/image/New folder/background.png");
    }

    public void setVictoryMedia() {
        AudioUtils.initialize();
        victoryMedia = AudioUtils.playMedia("assets/music/gameplay/victory.mp3");
        victoryMedia.setVolume(0.15d);
    }

    @Override
    public void run() {

    }

    @Override
    public void render(Graphics2D graphics2D) {
        graphics2D.setColor(Color.WHITE);
        graphics2D.setFont(new Font("serif", Font.BOLD, 40));
        graphics2D.drawString("VICTORY", 100, 200 );
        playerAnimation.render(graphics2D, new Vector2D(Setting.WIDTH_SCREEN / 2, Setting.HEIGHT_SCREEN / 2));
    }
}
