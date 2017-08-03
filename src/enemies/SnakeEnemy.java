package enemies;

import Utils.Utils;
import bases.GameObject;
import bases.renderers.Animation;

public class SnakeEnemy extends GameObject {
    public SnakeEnemy() {
        renderer = new Animation(40, true,
                Utils.loadImage("assets/image/enemy/enemy3/0.png"),
                Utils.loadImage("assets/image/enemy/enemy3/1.png"),
                Utils.loadImage("assets/image/enemy/enemy3/2.png"),
                Utils.loadImage("assets/image/enemy/enemy3/3.png"),
                Utils.loadImage("assets/image/enemy/enemy3/4.png"),
                Utils.loadImage("assets/image/enemy/enemy3/5.png"),
                Utils.loadImage("assets/image/enemy/enemy3/6.png")
        );
    }
}
