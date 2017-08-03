package players;

import Utils.Utils;
import bases.GameObject;
import bases.Setting;
import bases.Vector2D;
import bases.renderers.Animation;
import inputs.InputManager;
import physics.BoxCollider;
import physics.Physics;
import physics.PhysicsBody;
import platforms.BrickGrey;


public class Player extends GameObject implements Setting, PhysicsBody {
    public static Player instance;
    public  static  Vector2D velocity = new Vector2D();
    private boolean bulletDisable;
    private BoxCollider boxCollider;
    private float gravity = 0.01f;

    public Player(){
        super();
        this.renderer = new Animation(Utils.loadImage("assets/image/player/1.png"));
        instance = this;
        position.set(100, 100);
        boxCollider = new BoxCollider(renderer.getWidth(), renderer.getHeight());
        children.add(boxCollider);
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        move();

        makeBullet();
    }

    private void makeBullet() {
        if (!bulletDisable){
            if (InputManager.instance.spacePressed){
                //todo

            }
        }
    }

    private void move() {

        this.velocity.y += gravity;
        this.velocity.x = 0;

        if (InputManager.instance.leftPressed) {
            this.velocity.x -= 0.5f;
        }

        if (InputManager.instance.rightPressed) {
            this.velocity.x += 0.5f;
        }

        if (InputManager.instance.upPressed) {
            if (Physics.bodyInRect(position.add(0, 1), boxCollider.width, boxCollider.height, BrickGrey.class) != null) {
                this.velocity.y = -1.5f;
            }
        }

        moveVertical();
        moveHorizontal();

        this.position.addUp(this.velocity);
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
            this.velocity.y = 0;
        }
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
