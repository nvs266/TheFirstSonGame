package platforms;

import Utils.Utils;
import bases.GameObject;
import bases.renderers.Animation;

public class BrickGrey extends GameObject {
    public BrickGrey() {
        super();
        renderer = new Animation(Utils.loadImage("assets/image/brick/brick_grey.png"));
    }
}
