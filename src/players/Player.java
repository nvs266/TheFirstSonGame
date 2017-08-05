package players;

import bases.*;
import inputs.InputManager;
import physics.BoxCollider;
import physics.Physics;
import physics.PhysicsBody;
import platforms.PlatformSprite;

public class Player extends GameObject implements Setting, PhysicsBody {
    public static Player instance;
    public  static  Vector2D velocity = new Vector2D();
// <<<<<<< danghai1996
//     private boolean bulletDisable;
// // <<<<<<< sonfix
// //     private AnimationPlayer animationPlayer;
// // =======

//      private AnimationPlayer animationPlayer;

// // >>>>>>> master
// =======
    private FrameCounter frameCounter;
    public boolean shootEnable;
    private AnimationPlayer animationPlayer;
    private float posYBeforeShoot;
// >>>>>>> master

    public Player(){
        super();
        instance = this;
        position.set(100, 100);
        this.animationPlayer = new AnimationPlayer();
        this.renderer = animationPlayer;
        boxCollider = new BoxCollider(40, 50);
        children.add(boxCollider);
        this.frameCounter = new FrameCounter(30);
    }

    @Override
    public void run(Vector2D parentPosition) {

        super.run(parentPosition);
        makeBullet();
        move();
        this.position.addUp(velocity);
        updateAnimation();
    }

    private void updateAnimation() {
        animationPlayer.run();
    }

    private void makeBullet() {
        if (velocity.y > 0) {
            shootEnable = true;
        } else {
            shootEnable = false;
        }

        velocity.y += GRAVITY_PLAYER;
        velocity.x = 0;

            if (InputManager.instance.spacePressed){
                if (frameCounter.run() && shootEnable){
                    ClassicBullet classicBullet = GameObjectPool.recycle(ClassicBullet.class);
                    classicBullet.position.set(this.position.add(0, this.renderer.getHeight()));
                    velocity.y = 0;
                    frameCounter.reset();
                }
            }
    }

    private void move() {



        if (InputManager.instance.leftPressed) {
            velocity.x -= SPEED_PLAYER;
        }

        if (InputManager.instance.rightPressed) {
            velocity.x += SPEED_PLAYER;
        }


        if (InputManager.instance.spacePressed) {
            if (Physics.bodyInRectofsuper(position.add(0, 1), boxCollider.width, boxCollider.height, PlatformSprite.class) != null) {
                velocity.y = SPEED_JUMP_PLAYER;
                shootEnable = false;
            }
        }

        moveVertical();
        moveHorizontal();


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
        }
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
