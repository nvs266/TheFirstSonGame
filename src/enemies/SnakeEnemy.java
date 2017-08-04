package enemies;

import Utils.Utils;
import bases.GameObject;
import bases.Vector2D;
import bases.renderers.Animation;
import physics.BoxCollider;
import physics.PhysicsBody;

import javax.swing.*;

public class SnakeEnemy extends EnemySprite{
    private Vector2D velocity;

    public SnakeEnemy() {
        super();
        this.velocity = new Vector2D();
    }

    @Override
    protected void setRenderer() {
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

    @Override
    protected void move() {

    }
}
