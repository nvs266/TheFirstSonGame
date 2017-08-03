package enemies;

import Utils.Utils;
import bases.GameObject;
import bases.Vector2D;
import bases.renderers.Animation;
import physics.BoxCollider;
import physics.Physics;
import physics.PhysicsBody;
import players.Player;

public class BatEnemy extends GameObject implements PhysicsBody {
    private BoxCollider boxCollider;
    private Vector2D velocity;

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
