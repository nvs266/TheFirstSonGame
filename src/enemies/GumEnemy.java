package enemies;

import Utils.Utils;
import bases.GameObject;
import bases.renderers.Animation;

public class GumEnemy extends GameObject {
    public GumEnemy() {
        renderer = new Animation(50, true,
                Utils.loadImage("assets/image/enemy/enemy2/0.png"),
                Utils.loadImage("assets/image/enemy/enemy2/1.png"),
                Utils.loadImage("assets/image/enemy/enemy2/2.png"),
                Utils.loadImage("assets/image/enemy/enemy2/3.png")
                );
    }
}
