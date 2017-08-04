package players;

import bases.GameObject;
import bases.GameObjectPool;
import bases.Setting;
import bases.Vector2D;
import inputs.InputManager;
import physics.BoxCollider;
import physics.Physics;
import physics.PhysicsBody;
// <<<<<<< sonfix
// import platforms.BrickGrey;
// =======
// >>>>>>> master


public class Player extends GameObject implements Setting, PhysicsBody {
    public static Player instance;
    public  static  Vector2D velocity = new Vector2D();
    private boolean bulletDisable;
// <<<<<<< sonfix
//     private AnimationPlayer animationPlayer;
// =======

//     private AnimationPlayer animationPlayer;

// >>>>>>> master

    public Player(){
        super();
        instance = this;
        position.set(100, 100);
        this.animationPlayer = new AnimationPlayer();
        this.renderer = animationPlayer;
        boxCollider = new BoxCollider(40, 50);
        children.add(boxCollider);
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        move();
        updateAnimation();
        makeBullet();
    }

    private void updateAnimation() {
        animationPlayer.run();
    }

    private void makeBullet() {
            if (InputManager.instance.spacePressed){
                ClassicBullet classicBullet = GameObjectPool.recycle(ClassicBullet.class);
                classicBullet.position.set(this.position.add(0, this.renderer.getHeight()));
            }
    }

    private void move() {

        velocity.y += GRAVITY_PLAYER;
        velocity.x = 0;

        if (InputManager.instance.leftPressed) {
            velocity.x -= SPEED_PLAYER;
        }

        if (InputManager.instance.rightPressed) {
            velocity.x += SPEED_PLAYER;
        }

        if (InputManager.instance.upPressed) {
            if (Physics.bodyInRectofPlatform(position.add(0, 1), boxCollider.width, boxCollider.height) != null) {
                velocity.y = SPEED_JUMP_PLAYER;
            }
        }

        moveVertical();
        moveHorizontal();

        this.position.addUp(velocity);
    }

    private void moveHorizontal() {
        float deltaX = velocity.x > 0 ? 1 : -1;
        PhysicsBody body = Physics.bodyInRectofPlatform(position.add(velocity.x, 0), boxCollider.width, boxCollider.height);
        if (body != null){

            while (Physics.bodyInRectofPlatform(position.add(deltaX, 0), boxCollider.width, boxCollider.height) == null){
                position.addUp(deltaX,0);
            }
            velocity.x = 0;
        }
    }

    private void moveVertical() {
        float deltaY = velocity.y > 0 ? 1: -1;
        PhysicsBody body = Physics.bodyInRectofPlatform(position.add(0, velocity.y), boxCollider.width, boxCollider.height);
        if (body != null) {
            while(Physics.bodyInRectofPlatform(position.add(0, deltaY), boxCollider.width, boxCollider.height) == null) {
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
