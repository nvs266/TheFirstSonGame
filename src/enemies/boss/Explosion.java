package enemies.boss;

import Utils.Utils;
import bases.GameObject;
import bases.renderers.Animation;

import java.awt.*;

/**
 * Created by cuonghx2709 on 8/12/2017.
 */
public class Explosion extends GameObject {
    public Explosion(){
        super();
        renderer = new Animation(2, true,
                Utils.loadImage("assets/image/boss/explosion/1.png"),
                Utils.loadImage("assets/image/boss/explosion/2.png")
                );
    }
}
