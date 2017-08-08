package enemies;

import Utils.Utils;
import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import bases.renderers.Animation;
import physics.BoxCollider;
import physics.Physics;
import physics.PhysicsBody;
import platforms.PlatformSprite;
import players.Player;

public class FrogEnemy extends EnemySprite {
    FrameCounter frameCounter ;
    Vector2D velocity;
    private boolean jump;
    private float direction;

    public FrogEnemy() {
        super();
        this.velocity = new Vector2D();
        frameCounter = new FrameCounter(500);//~= 3s to start jump
    }

    @Override
    void setBoxCollider() {
        boxCollider = new BoxCollider(renderer.getWidth() - 20, renderer.getHeight());
        this.children.add(boxCollider);
    }

    @Override
    protected void setRenderer() {
        renderer = new Animation(50, true,
                Utils.loadImage("assets/image/enemy/enemy5/0.png"),
                Utils.loadImage("assets/image/enemy/enemy5/1.png"),
                Utils.loadImage("assets/image/enemy/enemy5/2.png")
        );
    }

    @Override
    protected void move() {
        velocity.y += GRAVITY_PLAYER;
        if (frameCounter.run() && this.position.y - Player.instance.position.y <= 200 && !jump){
            jump = true;
            velocity.y = SPEED_JUMP_PLAYER;
            if (Player.instance.position.x > this.position.x){
                direction = 1f;
            }else {
                direction = -1f;
            }
        }
        if (jump) {
            jumpAction();
            if (Physics.bodyInRectofsuper(position.add(0,1), boxCollider.width, boxCollider.height, PlatformSprite.class) != null){
                jump = false;
                frameCounter.reset();
            }
        }
        moveVertical();
        moveHorizontal();
        position.addUp(velocity);
    }

    private void moveHorizontal() {
        float deltaX = velocity.x > 0 ? 1 : -1;
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
            velocity.x = 0;
        }
    }

    private void jumpAction() {

        velocity.x += direction;
    }
}
