package enemies;

import Utils.Utils;

import bases.renderers.Animation;

public class BatEnemy extends EnemySprite {

    public BatEnemy() {
        super();
    }

    @Override
    protected void setRenderer() {
        renderer = new Animation(45 , true,
                Utils.loadImage("assets/image/enemy/enemy1/0.png"),
                Utils.loadImage("assets/image/enemy/enemy1/1.png"),
                Utils.loadImage("assets/image/enemy/enemy1/2.png")
        );
    }

    @Override
    protected void move() {

    }
}
