package players;

import Utils.Utils;
import bases.GameObject;
import bases.renderers.Animation;

import java.awt.*;

/**
 * Created by cuonghx2709 on 8/9/2017.
 */
public class EffectLeft extends GameObject {
    public EffectLeft(){
        super();
        this.renderer = new Animation(20, false,
                Utils.loadImage("assets/image/New folder/left1.png"),
                Utils.loadImage("assets/image/New folder/left2.png"),
                Utils.loadImage("assets/image/New folder/left3.png"),
                Utils.loadImage("assets/image/New folder/left4.png"),
                Utils.loadImage("assets/image/New folder/left5.png"),
                Utils.loadImage("assets/image/New folder/left6.png"),
                Utils.loadImage("assets/image/New folder/left7.png")
        );
    }

    @Override
    public void render(Graphics2D g2d) {
        super.render(g2d);
        if (renderer.isFinished()){
            this.setActive(false);
            renderer.reset();
        }
    }
}
