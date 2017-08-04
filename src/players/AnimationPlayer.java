package players;

import Utils.Utils;
import bases.Vector2D;
import bases.renderers.Animation;
import bases.renderers.Renderer;

import java.awt.*;

/**
 * Created by cuonghx2709 on 8/4/2017.
 */
public class AnimationPlayer implements Renderer{
    private Animation leftAnimation;
    private Animation rightAnimation;
    private Animation straightAnimation;
    private Animation jumpleftAnimation;
    private Animation currentAnimation;
    public boolean jump;
    public AnimationPlayer(){
        straightAnimation = new Animation(50,true,
                Utils.loadImage("assets/image/player/9.png"),
                Utils.loadImage("assets/image/player/10.png")
                );
        rightAnimation = new Animation(20,true,
                Utils.loadImage("assets/image/player/1.png"),
                Utils.loadImage("assets/image/player/2.png"),
                Utils.loadImage("assets/image/player/3.png"),
                Utils.loadImage("assets/image/player/4.png"),
                Utils.loadImage("assets/image/player/5.png"),
                Utils.loadImage("assets/image/player/6.png")
                );
        leftAnimation = new Animation(20,true,
                Utils.loadImage("assets/image/player/1 - copy.png"),
                Utils.loadImage("assets/image/player/2 - copy.png"),
                Utils.loadImage("assets/image/player/3 - copy.png"),
                Utils.loadImage("assets/image/player/4 - copy.png"),
                Utils.loadImage("assets/image/player/5 - copy.png"),
                Utils.loadImage("assets/image/player/6 - copy.png")
        );
        jumpleftAnimation = new Animation(100,false,
                Utils.loadImage("assets/image/player/7.png"),
                Utils.loadImage("assets/image/player/8.png")
                );

    }
    public void run(){
        if (Player.velocity.x > 0){
            currentAnimation  = rightAnimation;
        }else if (Player.velocity.x < 0){
            currentAnimation = leftAnimation;
        }else {
            currentAnimation = straightAnimation;
        }
    }

    @Override
    public void render(Graphics2D g2d, Vector2D position) {
        if (currentAnimation != null){
            currentAnimation.render(g2d, position.add(0,0));
        }
    }

    @Override
    public float getWidth() {
        return 0;
    }

    @Override
    public float getHeight() {
        return 0;
    }
}
