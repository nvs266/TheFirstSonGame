package enemies;

import Utils.Utils;
import bases.GameObject;
import bases.renderers.Animation;

public class GumEnemy extends EnemySprite {
    public GumEnemy() {
        super();
    }

    @Override
    protected void setRenderer() {
        renderer = new Animation(10, true,
                Utils.loadImage("assets/image/enemy/enemy2/0.png"),
                Utils.loadImage("assets/image/enemy/enemy2/1.png"),
                Utils.loadImage("assets/image/enemy/enemy2/2.png"),
                Utils.loadImage("assets/image/enemy/enemy2/3.png")
        );
    }

    @Override
    protected void move() {

    }
}
