package enemies;

import Utils.Utils;
import bases.GameObject;
import bases.Vector2D;
import bases.renderers.Animation;
import physics.BoxCollider;
import physics.PhysicsBody;

import javax.swing.*;

public class SnakeEnemy extends GameObject implements PhysicsBody{
    private BoxCollider boxCollider;
    private Vector2D velocity;

    public SnakeEnemy() {
        super();
        renderer = new Animation(40, true,
                Utils.loadImage("assets/image/enemy/enemy3/0.png"),
                Utils.loadImage("assets/image/enemy/enemy3/1.png"),
                Utils.loadImage("assets/image/enemy/enemy3/2.png"),
                Utils.loadImage("assets/image/enemy/enemy3/3.png"),
                Utils.loadImage("assets/image/enemy/enemy3/4.png"),
                Utils.loadImage("assets/image/enemy/enemy3/5.png"),
                Utils.loadImage("assets/image/enemy/enemy3/6.png")
        );
        this.boxCollider = new BoxCollider(renderer.getWidth(), renderer.getHeight());
        children.add(boxCollider);
        this.velocity = new Vector2D();
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        move();
        hitPlayer();
    }

    private void hitPlayer() {

    }

    private void move() {
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
