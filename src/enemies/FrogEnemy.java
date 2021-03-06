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
    private Animation jumpAnimation;
    private Animation jumpAnimationleft;
    private Animation animation;
    private Animation animationleft;
    private Animation currentAnimation;

    public FrogEnemy() {
        super();
        this.velocity = new Vector2D();
        frameCounter = new FrameCounter(117);//~= 2s to start jump
    }

    @Override
    void setBoxCollider() {
        boxCollider = new BoxCollider(renderer.getWidth() - 20, renderer.getHeight()-2);
        this.children.add(boxCollider);
    }

    @Override
    protected void setRenderer() {
        jumpAnimation = new Animation(12, true,
                Utils.loadImage("assets/image/enemy/enemy5/1.png")
        );
        animation = new Animation(12, true,
                Utils.loadImage("assets/image/enemy/enemy5/0.png")
                );
        jumpAnimationleft = new Animation(12, true,
                Utils.loadImage("assets/image/enemy/enemy5/left1.png")
        );
        animationleft = new Animation(12, true,
                Utils.loadImage("assets/image/enemy/enemy5/left0.png")
        );
        renderer = animation;
    }

    @Override
    protected void move() {
        velocity.y += GRAVITY_PLAYER;
        if (frameCounter.run() && this.position.y - Player.instance.position.y <= 200 && !jump){
            jump = true;
            velocity.y = SPEED_JUMP_PLAYER;
            if (Player.instance.position.x > this.position.x){
                direction = 4f;
            }else {
                direction = -4f;
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
        if (velocity.y < 0){
            if (direction < 0){
                currentAnimation = jumpAnimationleft;
            }else {
                currentAnimation = jumpAnimation;
            }
        }else {
           if (direction < 0){
                currentAnimation = animationleft;
            }else {
                currentAnimation = animation;
            }
        }
        renderer = currentAnimation;
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
