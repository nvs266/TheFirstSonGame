package platforms;

import Utils.Utils;
import bases.GameObject;
import bases.renderers.Animation;

public class BrickItem extends GameObject {
    public BrickItem() {
        renderer = new Animation(Utils.loadImage("assets/image/brick/stone_iron.png"));
    }
}
