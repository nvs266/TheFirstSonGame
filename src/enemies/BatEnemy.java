package enemies;

import Utils.Utils;
import bases.GameObject;
import bases.Setting;
import bases.Vector2D;
import bases.renderers.Animation;
import physics.Physics;
import players.Player;


public class BatEnemy extends EnemySprite {
    private Vector2D velocity;
    private Vector2D target;

    public BatEnemy() {
        super();
        this.velocity = new Vector2D();
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
        if (this.position.distance(Player.instance.position) > Setting.SIZE_ENEMY_ACTIVE - 100) {
            velocity = target.substract(position).normalize().multiply((float) 0.3);
        } else if (this.position.distance(Player.instance.position) <= Setting.SIZE_ENEMY_ACTIVE - 100) {
            velocity = target.substract(position).normalize().multiply((float) 0.6);
        }

        position.addUp(velocity);
    }
}
