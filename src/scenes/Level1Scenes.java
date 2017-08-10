package scenes;

import bases.GameObject;
import bases.GameObjectPool;
import players.Player;

import java.io.IOException;

public class Level1Scenes extends Scene{
    private Map map;

    @Override
    public void init() throws IOException {
        GameObject.clear();
        loadMap();
        addPlayer();
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
