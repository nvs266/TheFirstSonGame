package scenes;

import Utils.Utils;
import bases.GameObject;
import bases.GameObjectPool;
import players.Player;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Level1Scenes extends Scene{
    private Map map;

    @Override
    public void init() throws IOException {
        GameObject.clear();
        addBackground();
        loadMap();
        addPlayer();
        Icon icon = new Icon();
    }

    @Override
    public void run() {

    }

    @Override
    public void render(Graphics2D graphics2D) {

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
