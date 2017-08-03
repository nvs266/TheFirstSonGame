package platforms;

import Utils.Utils;
import bases.GameObject;
import bases.renderers.Animation;

public class Platform extends GameObject {
    public Platform() {
        renderer = new Animation(Utils.loadImage("assets/image/platform/1.png"));
    }
}
