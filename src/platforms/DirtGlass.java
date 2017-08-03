package platforms;

import Utils.Utils;
import bases.GameObject;
import bases.renderers.Animation;

public class DirtGlass extends GameObject {
    public DirtGlass() {
        renderer = new Animation(Utils.loadImage("assets/image/land/dirt_grass.png"));
    }
}
