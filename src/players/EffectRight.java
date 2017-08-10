package players;

import Utils.Utils;
import bases.GameObject;
import bases.renderers.Animation;

import java.awt.*;

/**
 * Created by cuonghx2709 on 8/9/2017.
 */
public class EffectRight extends GameObject {
    public EffectRight(){
        super();
        renderer = new Animation(20, false,
                Utils.loadImage("assets/image/New folder/right1.png"),
                Utils.loadImage("assets/image/New folder/right2.png"),
                Utils.loadImage("assets/image/New folder/right3.png"),
                Utils.loadImage("assets/image/New folder/right4.png"),
                Utils.loadImage("assets/image/New folder/right5.png"),
                Utils.loadImage("assets/image/New folder/right6.png"),
                Utils.loadImage("assets/image/New folder/right7.png")
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
