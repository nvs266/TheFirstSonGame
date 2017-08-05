package enemies;

import Utils.Utils;
import bases.GameObject;
import bases.Setting;
import bases.Vector2D;
import bases.renderers.Animation;
import physics.BoxCollider;
import physics.Physics;
import players.Player;

public class BatEnemy extends EnemySprite {
    private Vector2D velocity;
    private Vector2D target;

    public BatEnemy() {
        super();
        this.velocity = new Vector2D();
        this.boxCollider = new BoxCollider(renderer.getWidth(), renderer.getHeight());
        children.add(boxCollider);
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
        if (this.position.distance(Player.instance.position) < Setting.SIZE_ENEMY_ACTIVE) {
            movetoPlayer();
        }
    }

    private void movetoPlayer() {
        GameObject player = Physics.find(Player.class);
        if (player != null) {
            this.target = player.position;
        }
        if (this.position.distance(target) > Setting.SIZE_ENEMY_ACTIVE / 2) {
            velocity = target.substract(position).normalize().multiply((float) 0.3);
        } else if (this.position.distance(target) <= Setting.SIZE_ENEMY_ACTIVE /2) {
            velocity = target.substract(position).normalize().multiply((float) 0.5);
        }

        position.addUp(velocity);
    }
}
