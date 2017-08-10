package enemies;

import Utils.Utils;
import bases.GameObject;
import bases.Setting;
import bases.Vector2D;
import bases.renderers.Animation;
import physics.BoxCollider;
import physics.Physics;
import players.Player;

public class GhostEnemy extends EnemySprite {

    private Vector2D velocity;
    private Vector2D target;

    public GhostEnemy() {
        super();
        this.velocity = new Vector2D();
        this.boxCollider = new BoxCollider(renderer.getWidth(), renderer.getHeight());
        children.add(boxCollider);
    }

    @Override
    protected void setRenderer() {
        renderer = new Animation(10, true,
                Utils.loadImage("assets/image/enemy/enemy4/0.png"),
                Utils.loadImage("assets/image/enemy/enemy4/1.png"),
                Utils.loadImage("assets/image/enemy/enemy4/2.png")
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
            velocity = target.substract(position).normalize().multiply((float) 2);
        } else if (this.position.distance(Player.instance.position) <= Setting.SIZE_ENEMY_ACTIVE /2) {
            velocity = target.substract(position).normalize().multiply((float) 2);
        }

        position.addUp(velocity);
    }
}
