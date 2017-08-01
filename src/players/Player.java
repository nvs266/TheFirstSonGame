package players;

import Utils.Utils;
import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import bases.renderers.ImageRenderer;
import inputs.InputManager;

public class Player extends GameObject{

    private static Player instence;

    InputManager inputManager;
    Vector2D velocity;
    Vector2D vUp;

    private boolean bulletIsDisable;
    private boolean goUp;
    FrameCounter frameCounter = new FrameCounter(10);

    public Player(){
        super();
        this.renderer = new ImageRenderer(Utils.loadImage("assets/image/player/1.png"));
        this.velocity = new Vector2D();
        instence = this;
        vUp = new Vector2D(0,-4);
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        move();
        makeBullet();
    }

    private void makeBullet() {
        if (!bulletIsDisable){
            if (inputManager.spacePressed){
                //todo

            }
        }
    }

    private void move() {
        velocity.set(0,0);
        if (inputManager.spacePressed){
            goUp = true;

        }
        if (inputManager.rightPressed){
            velocity.addUp(2,0);
        }
        if (inputManager.leftPressed){
            velocity.addUp(-2,0);
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
            if (screenPosition.y >= 400){
                goUp = false;
                vUp.set(0,-4);
            }
        }
    }

    public void setInputManager(InputManager inputManager) {
        this.inputManager = inputManager;
    }
}
