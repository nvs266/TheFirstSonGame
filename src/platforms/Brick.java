package platforms;

import Utils.Utils;
import bases.GameObject;
import bases.renderers.Animation;
import physics.BoxCollider;
import physics.PhysicsBody;

public class Brick extends GameObject implements PhysicsBody{

    BoxCollider boxCollider;

    public Brick() {
        super();
        renderer = new Animation(Utils.loadImage("assets/image/brick/brick_grey.png"));
        this.boxCollider = new BoxCollider(32, 32);
        children.add(boxCollider);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
