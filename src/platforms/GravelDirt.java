package platforms;

import Utils.Utils;
import bases.GameObject;
import bases.renderers.Animation;

public class GravelDirt extends GameObject {
    public GravelDirt() {
        renderer = new Animation(Utils.loadImage("assets/image/land/gravel_dirt.png"));
    }
}
