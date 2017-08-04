package platforms;

import Utils.Utils;
import bases.GameObject;
import bases.renderers.Animation;
import physics.BoxCollider;
import physics.PhysicsBody;

public class BrickGrey extends GameObject implements PhysicsBody{

    public BrickGrey() {
        super();
        renderer = new Animation(Utils.loadImage("assets/image/brick/brick_grey.png"));
        boxCollider = new BoxCollider(32, 32);
        children.add(boxCollider);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
