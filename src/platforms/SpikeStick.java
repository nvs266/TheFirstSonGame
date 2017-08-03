package platforms;

import Utils.Utils;
import bases.GameObject;
import bases.renderers.Animation;

public class SpikeStick extends GameObject {
    public SpikeStick() {
        renderer = new Animation(Utils.loadImage("assets/image/spikeStick/up_0.png"));
    }
}
