package enemies;

import Utils.Utils;
import bases.GameObject;
import bases.Setting;
import bases.Vector2D;
import bases.renderers.Animation;
import physics.BoxCollider;
import physics.Physics;
import physics.PhysicsBody;
import platforms.PlatformSprite;
import players.Player;

import javax.swing.*;

public class SnakeEnemy extends EnemySprite{
    private Vector2D velocity;
    private Vector2D target;
    private float x;

    public SnakeEnemy() {
        super();
        this.velocity = new Vector2D();
        x = -0.1f;
        this.boxCollider = new BoxCollider(renderer.getWidth(), renderer.getHeight());
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
        velocity.y += GRAVITY_PLAYER;
        velocity.x = 0;
        moveVertical();
        if (velocity.y == 0) {
            velocity.x = x;
            moveHorizontal();
            if (velocity.x == 0) {
                x = -x;
            } else {
                Vector2D currentPos = this.position.clone();
                position.addUp(500 * x, 0);
                velocity.y += GRAVITY_PLAYER;
                moveVertical();
                if (velocity.y != 0) {
                    x = -x;
                }
                position.set(currentPos);
            }
            velocity.y = 0;
        }
        position.addUp(velocity);
    }

    private void moveHorizontal() {
        float deltaX = velocity.x > 0 ? 0.1f : -0.1f;
        PhysicsBody body = Physics.bodyInRectofsuper(position.add(velocity.x, 0), boxCollider.width, boxCollider.height, PlatformSprite.class);
        if (body != null){

            while (Physics.bodyInRectofsuper(position.add(deltaX, 0), boxCollider.width, boxCollider.height, PlatformSprite.class) == null){
                position.addUp(deltaX,0);
            }
            velocity.x = 0;
        }
    }

    private void moveVertical() {
        float deltaY = velocity.y > 0 ? 1: -1;
        PhysicsBody body = Physics.bodyInRectofsuper(position.add(0, velocity.y), boxCollider.width, boxCollider.height, PlatformSprite.class);
        if (body != null) {
            while(Physics.bodyInRectofsuper(position.add(0, deltaY), boxCollider.width, boxCollider.height, PlatformSprite.class) == null) {
                position.addUp(0, deltaY);
            }
            velocity.y = 0;
        }
    }

}
