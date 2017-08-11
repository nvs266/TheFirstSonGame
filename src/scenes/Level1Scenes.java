package scenes;

import Utils.Utils;
import bases.GameObject;
import bases.GameObjectPool;
import javafx.scene.media.MediaPlayer;
import players.Player;
import tklibs.AudioUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Level1Scenes extends Scene{
    private Map map;
    public static MediaPlayer lv1Audio;

    @Override
    public void init() throws IOException {
        GameObject.clear();
        addBackground();
        loadMap();
        addPlayer();
        Icon icon = new Icon();
        setLv1Audio();
    }

    @Override
    public void run() {

    }

    @Override
    public void render(Graphics2D graphics2D) {

    }

    public void setLv1Audio() {
        AudioUtils.initialize();
        this.lv1Audio = AudioUtils.playMedia("assets/music/gameplay/soundtrack.mp3");
        this.lv1Audio.setVolume(0.1d);
    }

    private void addBackground() {
        background = Utils.loadImage("assets/image/New folder/background.png");
    }

    private void loadMap() throws IOException {
        map = new Map(30, 280, "assets/map/map4.txt");
    }

    private void addPlayer() {
        Player player = GameObjectPool.recycle(Player.class);
        Camera.instance.setFollowGameObject(player);
        Map.instance.setFollowGameObject(player);
    }

}
