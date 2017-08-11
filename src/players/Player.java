package players;

import bases.*;
import bases.actions.Action;
import bases.actions.RepeatForeverAction;
import bases.actions.SequenceAction;
import bases.actions.WaitAction;
import enemies.boss.Boss;
import inputs.InputManager;
import items.ItemSprite;
import physics.BoxCollider;
import physics.Physics;
import physics.PhysicsBody;
import platforms.PlatformSprite;
import players.bullets.ClassicBullet;
import players.bullets.PlayerBulletSprite;
import scenes.*;

public class Player extends GameObject implements Setting, PhysicsBody {
    public static Player instance;
    public  static  Vector2D velocity = new Vector2D();
    private FrameCounter frameCounter;
    private boolean shootEnable;
    public int bullets;
    public int totalBullets;
    public AnimationPlayer animationPlayer;
    private PlayerBulletSprite bulletSprite;
    public int life;
    public boolean immortal; // bat tu
    private boolean added;
    public boolean hero;
    public boolean trail;
    private FrameCounter frameCounterTrails;
    private FrameCounter immortalCounter;
    private Action immortalAction;
    private boolean boss;
    public int totalNipple;
    public Audio shootAudio;
    public static Audio cryAudio;
    public boolean vitory;

    public Player() {
        super();
        position.set(27 * 32 / 2, -500);
        life = START_LIFE;
        instance = this;
        bullets = totalBullets = START_TOTAL_BULLETS;
        bulletSprite = new ClassicBullet();
        this.frameCounter = new FrameCounter(COOLDOWN);

        this.animationPlayer = new AnimationPlayer();
        this.renderer = animationPlayer;

        boxCollider = new BoxCollider(20, 50);
        children.add(boxCollider);

        immortal = false;
        immortalCounter = new FrameCounter(100);
        hero = false;
        frameCounterTrails = new FrameCounter(3);
        immortalAction = new RepeatForeverAction(
                new SequenceAction(
                        new PlayerAction(),
                        new WaitAction(3)
                )
        );
        Camera.instance.position = this.position.add(-WIDTH_SCREEN/2, -HEIGHT_SCREEN / 2);
        totalNipple = 0;
    }

    @Override
    public void run(Vector2D parentPosition) {
         if (frameCounterTrails.run()&& renderer != null &&renderer.getCurrentImage() != null &&  hero) {
            if (trail){
                Trail trail = GameObjectPool.recycle(Trail.class);
                trail.setTrail(this.position, 0.06f, renderer.getCurrentImage());
                frameCounterTrails.reset();
            }
        }

        if (immortal) {
            hero = false;
            trail = false;
            animationPlayer.setHero(false);
            if (!added){
                addAction(immortalAction);
                added = true;
            }
            if (immortalCounter.run()) {
                immortal = false;
                immortalCounter.reset();
                removeAction(immortalAction);
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


        if (Camera.instance.getFollowGameObject() == this) {
            Camera.instance.setPosition();
        }

        if (Map.instance != null && Map.instance.getFollowGameObject() == this) {
            Map.instance.readMap(this);
        }
        if (position.y > 8100 && ! boss) {
            GameObject.add(Boss.instance);
            Boss.instance.position.set(255 + Boss.instance.renderer.getWidth() / 2, this.position.y + 200);
            Camera.instance.setFollowGameObject(Boss.instance);
            boss = true;
        }


        if (vitory) {
            SceneManager.instance.requestChangeScene(new Victory());
        }
    }

    private void checkItem() {
        ItemSprite itemSprite = Physics.bodyInRectofsuper(position.add(2,2),boxCollider.width, boxCollider.height, ItemSprite.class);
        if (itemSprite == null){
            itemSprite = Physics.bodyInRect(position.add(-2,-2),boxCollider.width, boxCollider.height, ItemSprite.class);
        }
        if (itemSprite != null){
            itemSprite.hitPlayer = true;
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
                bulletSprite.addCartouche();
                this.shootAudio = new Audio("assets/music/player/shoot.wav");
                shootAudio.play();
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
//        System.out.println(position);

        if (InputManager.instance.leftPressed) {
            velocity.x -= SPEED_PLAYER;
        }

        if (InputManager.instance.rightPressed) {
            velocity.x += SPEED_PLAYER;
        }


        if (Physics.bodyInRectofsuper(position.add(0, 1), boxCollider.width, boxCollider.height, PlatformSprite.class) != null) {
            resetBullet();
            if (InputManager.instance.spacePressed) {
                velocity.y = SPEED_JUMP_PLAYER;
                EffectLeft effectLeft = GameObjectPool.recycle(EffectLeft.class);
                effectLeft.position.set(Player.instance.position.add(-15,15));
                EffectRight effectRight = GameObjectPool.recycle(EffectRight.class);
                effectRight.position.set(Player.instance.position.add(15,15));
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

    public void resetBullet() {
        bullets = totalBullets;
    }

}
