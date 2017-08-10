package players;

import Utils.Utils;
import bases.FrameCounter;
import bases.GameObject;
import bases.GameObjectPool;
import bases.Vector2D;
import bases.renderers.Animation;
import bases.renderers.ImageRenderer;
import bases.renderers.Renderer;
import physics.Physics;
import platforms.PlatformSprite;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

/**
 * Created by cuonghx2709 on 8/4/2017.
 */
public class AnimationPlayer implements Renderer{
    private Animation leftAnimation;
    private Animation rightAnimation;
    private Animation straightAnimation;
    private Animation jumpleftAnimation1;
    private Animation jumpleftAnimation2;
    private Animation currentAnimation;
    private Animation jumprightAnimation1;
    private Animation jumprightAnimation2;
    private Animation fallstraight;
    private Animation attack;
    private FrameCounter frameCounterright;
    private FrameCounter frameCounterleft;


    private boolean isAttack;

    public AnimationPlayer(){
        frameCounterright = new FrameCounter(50);
        frameCounterleft = new FrameCounter(50);
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
        jumpleftAnimation1 = new Animation(100,false,
                Utils.loadImage("assets/image/player/7.png")
                );
        jumpleftAnimation2 = new Animation(100,false,
                Utils.loadImage("assets/image/player/8.png")
        );
        jumprightAnimation1 = new Animation(100,false,
                Utils.loadImage("assets/image/player/7 - copy.png")
        );
        jumprightAnimation2 = new Animation(100,false,
                Utils.loadImage("assets/image/player/8 - copy.png")
        );

        fallstraight = new Animation(20, true,
                Utils.loadImage("assets/image/player/14.png")
                );
        attack = new Animation(20,true,
                Utils.loadImage("assets/image/player/15.png"),
                Utils.loadImage("assets/image/player/16.png")
                );

    }
    public void run(){
        boolean left = frameCounterleft.run();
        boolean right = frameCounterright.run();

        if (Player.velocity.y > 0 && Physics.bodyInRectofsuper(Player.instance.position.add(0,1),Player.instance.boxCollider.width, Player.instance.boxCollider.height, PlatformSprite.class) != null){
            EffectLeft effectLeft = GameObjectPool.recycle(EffectLeft.class);
            effectLeft.position.set(Player.instance.position.add(-15,15));
            EffectRight effectRight = GameObjectPool.recycle(EffectRight.class);
            effectRight.position.set(Player.instance.position.add(15,15));

        }
        if (Player.velocity.x > 0){
            if (Player.velocity.y < 0){
                currentAnimation = jumpleftAnimation1;
            }else if (Player.velocity.y > 0){
                currentAnimation = jumpleftAnimation2;
            }else {
                currentAnimation = rightAnimation;
            }
            if (left && Physics.bodyInRectofsuper(Player.instance.position.add(0,1),Player.instance.boxCollider.width, Player.instance.boxCollider.height, PlatformSprite.class) != null){
                EffectLeft effectLeft = GameObjectPool.recycle(EffectLeft.class);
                effectLeft.position.set(Player.instance.position.add(0,15));

                frameCounterleft.reset();
            }
        }else if (Player.velocity.x < 0){
            if (Player.velocity.y < 0){
                currentAnimation = jumprightAnimation1;
            }else if (Player.velocity.y > 0){
                currentAnimation = jumprightAnimation2;
            }else {
                currentAnimation = leftAnimation;
            }
            if (right && Physics.bodyInRectofsuper(Player.instance.position.add(0,1),Player.instance.boxCollider.width, Player.instance.boxCollider.height, PlatformSprite.class) != null){
                EffectRight effectRight = GameObjectPool.recycle(EffectRight.class);
                effectRight.position.set(Player.instance.position.add(0,15));
                frameCounterright.reset();
            }
        }else {
            if (Player.velocity.y != 0){
                currentAnimation = fallstraight;
            }else {
                currentAnimation = straightAnimation;
            }
            if (isAttack){
                currentAnimation = attack;
            }
        }

    }

    public void setActack(boolean attack) {
        isAttack = attack;
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

    @Override
    public void reset() {

    }

    @Override
    public int getIndexImage() {
        return 0;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public BufferedImage getCurrentImage() {
        if (currentAnimation != null) {
            return currentAnimation.getCurrentImage();
        }
        return null;
    }

}
