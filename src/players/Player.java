package players;

import Utils.Utils;
import bases.GameObject;
import bases.Setting;
import bases.Vector2D;
import bases.renderers.Animation;
import bases.renderers.Renderer;
import inputs.InputManager;
import physics.BoxCollider;
import physics.Physics;
import physics.PhysicsBody;
import platforms.BrickGrey;
import platforms.PlatformSprite;


public class Player extends GameObject implements Setting, PhysicsBody {
    public static Player instance;
    public  static  Vector2D velocity = new Vector2D();
    private boolean bulletDisable;
<<<<<<< sonfix
=======
    private BoxCollider boxCollider;
    private float gravity = 0.01f;
    private AnimationPlayer animationPlayer;
>>>>>>> master

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
        if (!bulletDisable){
            if (InputManager.instance.spacePressed){
                //todo

            }
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
            if (Physics.bodyInRect(position.add(0, 1), boxCollider.width, boxCollider.height, BrickGrey.class) != null) {
                velocity.y = SPEED_JUMP_PLAYER;
            }
        }

        moveVertical();
        moveHorizontal();

        this.position.addUp(velocity);
    }

    private void moveHorizontal() {
        float deltaX = velocity.x > 0 ? 1 : -1;
        PhysicsBody body = Physics.bodyInRect(position.add(velocity.x, 0), boxCollider.width, boxCollider.height, BrickGrey.class);
        if (body != null){
            while (Physics.bodyInRect(position.add(deltaX, 0), boxCollider.width, boxCollider.height, BrickGrey.class) == null){
                position.addUp(deltaX,0);
            }
            velocity.x = 0;
        }
    }

    private void moveVertical() {
        float deltaY = velocity.y > 0 ? 1: -1;
        PhysicsBody body = Physics.bodyInRect(position.add(0, velocity.y), boxCollider.width, boxCollider.height,BrickGrey.class);
        if (body != null) {
            while(Physics.bodyInRect(position.add(0, deltaY), boxCollider.width, boxCollider.height, BrickGrey.class) == null) {
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
