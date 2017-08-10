package enemies;

import Utils.Utils;
import bases.GameObject;
import bases.Vector2D;
import bases.renderers.Animation;

import java.awt.*;

/**
 * Created by cuonghx2709 on 8/10/2017.
 */
public class EnemyExplosionSmoke extends GameObject{
    public EnemyExplosionSmoke(){
        this.renderer = new Animation(5,false,
                Utils.loadImage("assets/image/enemy/explosion/exp1.png"),
                Utils.loadImage("assets/image/enemy/explosion/exp2.png"),
                Utils.loadImage("assets/image/enemy/explosion/exp3.png"),
                Utils.loadImage("assets/image/enemy/explosion/exp4.png"),
                Utils.loadImage("assets/image/enemy/explosion/exp5.png"),
                Utils.loadImage("assets/image/enemy/explosion/exp6.png"),
                Utils.loadImage("assets/image/enemy/explosion/exp7.png"),
                Utils.loadImage("assets/image/enemy/explosion/exp8.png"),
                Utils.loadImage("assets/image/enemy/explosion/exp9.png"),
                Utils.loadImage("assets/image/enemy/explosion/exp10.png"),
                Utils.loadImage("assets/image/enemy/explosion/exp11.png"),
                Utils.loadImage("assets/image/enemy/explosion/exp12.png"),
                Utils.loadImage("assets/image/enemy/explosion/exp13.png")
        );
    }

    @Override
    public void render(Graphics2D g2d) {
        super.render(g2d);
        if (renderer.isFinished()){
            this.setActive(false);
        }
    }
}
