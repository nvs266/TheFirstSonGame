package enemies;

import Utils.Utils;
import bases.GameObject;
import bases.renderers.Animation;

import java.awt.*;

public class GumEnemy extends EnemySprite {
    public Animation left;
    public Animation right;

    public GumEnemy() {
        super();
        health = 2;
    }

    @Override
    protected void setRenderer() {
        left = new Animation(10, true,
                Utils.loadImage("assets/image/enemy/enemy2/0.png"),
                Utils.loadImage("assets/image/enemy/enemy2/1.png"),
                Utils.loadImage("assets/image/enemy/enemy2/2.png"),
                Utils.loadImage("assets/image/enemy/enemy2/3.png")
        );
        right = new Animation(10, true,
                Utils.loadImage("assets/image/enemy/enemy2/0right.png"),
                Utils.loadImage("assets/image/enemy/enemy2/1right.png"),
                Utils.loadImage("assets/image/enemy/enemy2/2right.png"),
                Utils.loadImage("assets/image/enemy/enemy2/3right.png")
        );
        renderer = left;
    }

    @Override
    protected void move() {

    }
}
