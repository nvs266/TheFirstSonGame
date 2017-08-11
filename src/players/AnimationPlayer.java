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
    private Animation leftAnimationHero;
    private Animation rightAnimationHero;
    private Animation straightAnimationHero;
    private Animation jumpleftAnimation1Hero;
    private Animation jumpleftAnimation2Hero;
    private Animation currentAnimationHero;
    private Animation jumprightAnimation1Hero;
    private Animation jumprightAnimation2Hero;
    private Animation fallstraightHero;
    private Animation attackHero;


    private FrameCounter frameCounterright;
    private FrameCounter frameCounterleft;


    private boolean isAttack;
    private boolean hero;

    public AnimationPlayer(){
        loadImageNormal();
        loadImageHero();
        frameCounterright = new FrameCounter(9);
        frameCounterleft = new FrameCounter(9);



    }

    private void loadImageHero() {
        straightAnimationHero = new Animation(25,true,
                Utils.loadImage("assets/image/player/new Image/straight0.png"),
                Utils.loadImage("assets/image/player/new Image/straight1.png"),
                Utils.loadImage("assets/image/player/new Image/straight2.png"),
                Utils.loadImage("assets/image/player/new Image/straight1.png")


        );
        rightAnimationHero = new Animation(4,true,
                Utils.loadImage("assets/image/player/new Image/4.png"),
                Utils.loadImage("assets/image/player/new Image/5.png"),
                Utils.loadImage("assets/image/player/new Image/9.png"),
                Utils.loadImage("assets/image/player/new Image/10.png")
        );
        leftAnimationHero = new Animation(4,true,
                Utils.loadImage("assets/image/player/new Image/4copy.png"),
                Utils.loadImage("assets/image/player/new Image/5copy.png"),
                Utils.loadImage("assets/image/player/new Image/9copy.png"),
                Utils.loadImage("assets/image/player/new Image/10copy.png")
        );
        jumprightAnimation1Hero = new Animation(18,false,
                Utils.loadImage("assets/image/player/new Image/7copy.png")
        );
        jumprightAnimation2Hero = new Animation(18,false,
                Utils.loadImage("assets/image/player/new Image/3copy.png")
        );
        jumpleftAnimation1Hero = new Animation(18,false,
                Utils.loadImage("assets/image/player/new Image/7.png")
        );
        jumpleftAnimation2Hero = new Animation(18,false,
                Utils.loadImage("assets/image/player/new Image/3.png")
        );

        fallstraightHero = new Animation(4, true,
                Utils.loadImage("assets/image/player/new Image/19.png"),
                Utils.loadImage("assets/image/player/new Image/20.png")
        );
        attackHero = new Animation(4,true,
                Utils.loadImage("assets/image/player/new Image/3.png")
        );

    }

    private void loadImageNormal() {
        straightAnimation = new Animation(9,true,
                Utils.loadImage("assets/image/player/9.png"),
                Utils.loadImage("assets/image/player/10.png")
        );
        rightAnimation = new Animation(4,true,
                Utils.loadImage("assets/image/player/1.png"),
                Utils.loadImage("assets/image/player/2.png"),
                Utils.loadImage("assets/image/player/3.png"),
                Utils.loadImage("assets/image/player/4.png"),
                Utils.loadImage("assets/image/player/5.png"),
                Utils.loadImage("assets/image/player/6.png")
        );
        leftAnimation = new Animation(4,true,
                Utils.loadImage("assets/image/player/1 - copy.png"),
                Utils.loadImage("assets/image/player/2 - copy.png"),
                Utils.loadImage("assets/image/player/3 - copy.png"),
                Utils.loadImage("assets/image/player/4 - copy.png"),
                Utils.loadImage("assets/image/player/5 - copy.png"),
                Utils.loadImage("assets/image/player/6 - copy.png")
        );
        jumpleftAnimation1 = new Animation(18,false,
                Utils.loadImage("assets/image/player/7.png")
        );
        jumpleftAnimation2 = new Animation(18,false,
                Utils.loadImage("assets/image/player/8.png")
        );
        jumprightAnimation1 = new Animation(18,false,
                Utils.loadImage("assets/image/player/7 - copy.png")
        );
        jumprightAnimation2 = new Animation(18,false,
                Utils.loadImage("assets/image/player/8 - copy.png")
        );

        fallstraight = new Animation(4, true,
                Utils.loadImage("assets/image/player/14.png")
        );
        attack = new Animation(4,true,
                Utils.loadImage("assets/image/player/15.png"),
                Utils.loadImage("assets/image/player/16.png")
        );
    }

    public void run(){
        if (Player.velocity.y > 0 && Physics.bodyInRectofsuper(Player.instance.position.add(0,1),Player.instance.boxCollider.width, Player.instance.boxCollider.height, PlatformSprite.class) != null){
            EffectLeft effectLeft = GameObjectPool.recycle(EffectLeft.class);
            effectLeft.position.set(Player.instance.position.add(-15,15));
            EffectRight effectRight = GameObjectPool.recycle(EffectRight.class);
            effectRight.position.set(Player.instance.position.add(15,15));

        }
        if (hero){
            hero();
        }else{
            animationNomarl();
        }
 
    }

    private void hero() {
        boolean left = frameCounterleft.run();
        boolean right = frameCounterright.run();
        Player.instance.trail = true;

        if (Player.velocity.x > 0){
            if (Player.velocity.y < 0){
                currentAnimation = jumpleftAnimation1Hero;
            }else if (Player.velocity.y > 0){
                currentAnimation = jumpleftAnimation2Hero;
            }else {
                currentAnimation = rightAnimationHero;
            }
            if (left && Physics.bodyInRectofsuper(Player.instance.position.add(0,1),Player.instance.boxCollider.width, Player.instance.boxCollider.height, PlatformSprite.class) != null){
                EffectLeft effectLeft = GameObjectPool.recycle(EffectLeft.class);
                effectLeft.position.set(Player.instance.position.add(0,15));

                frameCounterleft.reset();
            }
        }else if (Player.velocity.x < 0){
            if (Player.velocity.y < 0){
                currentAnimation = jumprightAnimation1Hero;
            }else if (Player.velocity.y > 0){
                currentAnimation = jumprightAnimation2Hero;
            }else {
                currentAnimation = leftAnimationHero;
            }
            if (right && Physics.bodyInRectofsuper(Player.instance.position.add(0,1),Player.instance.boxCollider.width, Player.instance.boxCollider.height, PlatformSprite.class) != null){
                EffectRight effectRight = GameObjectPool.recycle(EffectRight.class);
                effectRight.position.set(Player.instance.position.add(0,15));
                frameCounterright.reset();
            }
        }else {
            if (Player.velocity.y != 0){
                currentAnimation = fallstraightHero;
            }else {
                Player.instance.trail = false;
                currentAnimation = straightAnimationHero;
            }
            if (isAttack){
                currentAnimation = attackHero;
            }

        }

    }

    private void animationNomarl() {
        boolean left = frameCounterleft.run();
        boolean right = frameCounterright.run();

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

    public void setHero(boolean hero) {
        this.hero = hero;
    }
}
