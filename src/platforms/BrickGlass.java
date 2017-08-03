package platforms;

import Utils.Utils;
import bases.GameObject;
import bases.renderers.Animation;

public class BrickGlass extends GameObject {
    public BrickGlass() {
        super();
        renderer = new Animation(Utils.loadImage("assets/image/brick/stone_grass.png"));
    }

}
