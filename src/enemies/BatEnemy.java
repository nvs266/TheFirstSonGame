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

import java.util.Set;

public class BatEnemy extends GameObject implements PhysicsBody {
    private BoxCollider boxCollider;
    private Vector2D velocity;
    private Vector2D target;

    public BatEnemy() {
        super();
        renderer = new Animation(45 , true,
                Utils.loadImage("assets/image/enemy/enemy1/0.png"),
                Utils.loadImage("assets/image/enemy/enemy1/1.png"),
                Utils.loadImage("assets/image/enemy/enemy1/2.png")
        );
        this.boxCollider = new BoxCollider(renderer.getWidth(),renderer.getHeight());
        children.add(boxCollider);
        this.velocity = new Vector2D();
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        move();
        hitPlayer();
    }

    private void move() {
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

    private void hitPlayer() {
        Player player = Physics.bodyInRect(position.add(0,20), boxCollider.width, boxCollider.height,Player.class);
        if (player!= null){
            float botPlayer = player.position.y + player.renderer.getHeight()/2;
            if (botPlayer <= position.y + 5 ){
                setActive(false);
            }else {
                player.setActive(false);
            }
        }
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
