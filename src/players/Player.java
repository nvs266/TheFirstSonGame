package players;

import Utils.Utils;
import bases.GameObject;
import bases.Setting;
import bases.Vector2D;
import bases.renderers.Animation;
import inputs.InputManager;
import physics.BoxCollider;


public class Player extends GameObject implements Setting {
    public static Player instance;
    public static Vector2D velocity = new Vector2D();
    private boolean bulletDisable;
    private BoxCollider boxCollider;
    private float gravity = 0.01f;

    public Player(){
        super();
        this.renderer = new Animation(Utils.loadImage("assets/image/player/1.png"));
        instance = this;
        position.set(100, 100);
        boxCollider = new BoxCollider(renderer.getWidth(), renderer.getHeight());
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
//        velocity.addUp(0, gravity);
        velocity.set(0, 0);
        if (InputManager.instance.rightPressed){
            velocity.addUp(SPEED_PLAYER,0);
        }
        if (InputManager.instance.leftPressed){
            velocity.addUp(-SPEED_PLAYER,0);
        }
        if (InputManager.instance.upPressed){
            velocity.addUp(0,-SPEED_PLAYER);
        }
        if (InputManager.instance.downPressed){
            velocity.addUp(0,SPEED_PLAYER);
        }
        position.addUp(velocity);
    }
}
