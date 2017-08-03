package players;

import Utils.Utils;
import bases.FrameCounter;
import bases.GameObject;
import bases.Setting;
import bases.Vector2D;
import bases.renderers.Animation;
import inputs.InputManager;

import java.awt.*;

public class Player extends GameObject implements Setting {
    public static Player instance;
    private InputManager inputManager;
    private Vector2D velocity;
    private Vector2D vUp;
    private boolean bulletDisable;
    private boolean goUp;
    private FrameCounter frameCounter = new FrameCounter(10);

    public Player(){
        super();
        this.renderer = new Animation(Utils.loadImage("assets/image/player/1.png"));
        this.velocity = new Vector2D();
        instance = this;
        goUp = false;
        vUp = new Vector2D(0,-3);
        position.set(100, 400);
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        move();
        makeBullet();
    }

    private void makeBullet() {
        if (!bulletDisable){
            if (inputManager.spacePressed){
                //todo

            }
        }
    }

    private void move() {
        velocity.set(0,0);
        if (inputManager.spacePressed && !goUp){
            goUp = true;
        }
        if (inputManager.rightPressed){
            velocity.addUp(SPEED_PLAYER,0);
        }
        if (inputManager.leftPressed){
            velocity.addUp(-SPEED_PLAYER,0);
        }
        if (inputManager.upPressed){
            velocity.addUp(0,-SPEED_PLAYER);
        }
        if (inputManager.downPressed){
            velocity.addUp(0,SPEED_PLAYER);
        }
        goUp();
        position.addUp(velocity);
    }

    private void goUp() {
        if (goUp){

            if (frameCounter.run()){
                frameCounter.reset();
                vUp.addUp(0,0.5f);
            }

            velocity.addUp(vUp);

            if (screenPosition.y >= 400 ){
                goUp = false;
                vUp.set(0,-3);
            }
        }

    }


    public void setInputManager(InputManager inputManager) {
        this.inputManager = inputManager;
    }
}
