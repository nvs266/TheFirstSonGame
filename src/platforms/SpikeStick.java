package platforms;

import Utils.Utils;
import bases.GameObject;
import bases.renderers.Animation;
import physics.BoxCollider;
import physics.PhysicsBody;

public class SpikeStick extends GameObject implements PhysicsBody{

    public SpikeStick() {
        renderer = new Animation(Utils.loadImage("assets/image/spikeStick/up_0.png"));
        boxCollider = new BoxCollider(renderer.getWidth(), renderer.getHeight());
        this.children.add(boxCollider);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
