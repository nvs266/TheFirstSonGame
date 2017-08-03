package platforms;

import Utils.Utils;
import bases.GameObject;
import bases.renderers.Animation;

public class Item extends GameObject {
    public Item() {
        renderer = new Animation(Utils.loadImage("assets/image/item/item.png"));
    }
}
