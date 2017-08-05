package enemies;

import Utils.Utils;
import bases.GameObject;
import bases.Setting;
import bases.Vector2D;
import bases.renderers.Animation;
import physics.BoxCollider;
import physics.Physics;
import physics.PhysicsBody;
import players.Player;

import javax.swing.*;

public class SnakeEnemy extends EnemySprite{
    private Vector2D velocity;
    private Vector2D target;

    public SnakeEnemy() {
        super();
        this.velocity = new Vector2D();
        this.boxCollider = new BoxCollider(renderer.getWidth(), renderer.getHeight() / 2);
        children.add(boxCollider);
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
            movetoPlayer();
    }

    private void movetoPlayer() {
        GameObject player = Physics.find(Player.class);
        if (player != null) {
            this.target = player.position;
        }
        if (Math.abs(this.position.y  - Player.instance.position.y)  < 20
                    && Math.abs(this.position.x - target.x) <= Setting.SIZE_ENEMY_ACTIVE) {
                velocity.set((float) ((target.x - this.position.x) / (Math.abs(this.position.x - target.x)) *  0.1), 0);
        } else velocity.set(0 , 0);
        this.position.addUp(velocity);
    }
}
