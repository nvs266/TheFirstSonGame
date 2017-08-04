package enemies;

import Utils.Utils;
import bases.GameObject;
import bases.renderers.Animation;

public class FrogEnemy extends EnemySprite {

    public FrogEnemy() {
        super();
    }

    @Override
    protected void setRenderer() {
        renderer = new Animation(50, true,
                Utils.loadImage("assets/image/enemy/enemy5/0.png"),
                Utils.loadImage("assets/image/enemy/enemy5/1.png"),
                Utils.loadImage("assets/image/enemy/enemy5/2.png")
        );
    }

    @Override
    protected void move() {

    }
}
