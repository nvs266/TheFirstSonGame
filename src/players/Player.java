package players;

import bases.*;
import bases.actions.Action;
import bases.actions.RepeatForeverAction;
import bases.actions.SequenceAction;
import bases.actions.WaitAction;
import inputs.InputManager;
import physics.BoxCollider;
import physics.Physics;
import physics.PhysicsBody;
import platforms.Item;
import platforms.PlatformSprite;

import java.awt.*;

public class Player extends GameObject implements Setting, PhysicsBody {
    public static Player instance;
    public  static  Vector2D velocity = new Vector2D();
    private FrameCounter frameCounter;
    private boolean shootEnable;
    private int bullets;
    private int totalBullets;
    private AnimationPlayer animationPlayer;
    private PlayerBulletSprite bulletSprite;
    public int life;
    public boolean immortal; // bat tu
    private boolean added;
    private boolean hero;
    private FrameCounter immortalCounter;
    private Action immortalAction;

    public Player(){
        super();
        position.set(100, 100);
        life = START_LIFE;
        instance = this;
        bullets = totalBullets = START_TOTAL_BULLETS;
        bulletSprite = new ClassicBullet();
        this.frameCounter = new FrameCounter(COOLDOWN);

        this.animationPlayer = new AnimationPlayer();
        this.renderer = animationPlayer;

        boxCollider = new BoxCollider(40, 50);
        children.add(boxCollider);

        immortal = false;
        immortalCounter = new FrameCounter(300);
        immortalAction = new RepeatForeverAction(
          new SequenceAction(
                  new PlayerAction(),
                  new WaitAction(5)
          )
        );
    }

    @Override
    public void run(Vector2D parentPosition) {


        if (renderer != null && renderer.getCurrentImage() != null && hero) {
            Trail trail = GameObjectPool.recycle(Trail.class);
            trail.setTrail(this.position, 0.02f, renderer.getCurrentImage());
        }

        if (immortal) {
            hero = false;
            if (!added){
                addAction(immortalAction);
                added = true;
            }
            if (immortalCounter.run()) {
                immortal = false;
                immortalCounter.reset();
                remoteAction(immortalAction);
                added = false;
                renderer = animationPlayer;
            }

        }
        super.run(parentPosition);
        makeBullet();
        move();
        this.position.addUp(velocity);
        updateAnimation();
        checkItem();
    }

    private void checkItem() {
        Item item = Physics.bodyInRect(position.add(2,2),boxCollider.width, boxCollider.height,Item.class);
        if (item == null){
            item = Physics.bodyInRect(position.add(-2,-2),boxCollider.width, boxCollider.height,Item.class);
        }
        if (item != null){
            item.setActive(false);
            hero = true;
        }
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
            if (frameCounter.run() && shootEnable && bullets > 0){
                bulletSprite.shoot();
                velocity.y = 0;
                bullets -= bulletSprite.totalBulletsPerShoot;
                frameCounter.reset();
                animationPlayer.setActack(true);
            }
        }else {
            animationPlayer.setActack(false);
        }
    }

    private void move() {

        if (InputManager.instance.leftPressed) {
            velocity.x -= SPEED_PLAYER;
        }

        if (InputManager.instance.rightPressed) {
            velocity.x += SPEED_PLAYER;
        }


        if (Physics.bodyInRectofsuper(position.add(0, 1), boxCollider.width, boxCollider.height, PlatformSprite.class) != null) {
            bullets = totalBullets;
            if (InputManager.instance.spacePressed) {
                velocity.y = SPEED_JUMP_PLAYER;
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
    public void add(Vector2D position){
        position.addUp(position);
    }
}
