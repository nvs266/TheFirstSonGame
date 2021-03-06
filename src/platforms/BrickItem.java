package platforms;

import Utils.Utils;
import bases.GameObject;
import bases.renderers.Animation;
import physics.BoxCollider;
import physics.PhysicsBody;

public class BrickItem extends PlatformSprite {

    public BrickItem() {
        renderer = new Animation(Utils.loadImage("assets/image/brick/stone_iron.png"));
        boxCollider = new BoxCollider(renderer.getWidth(), renderer.getHeight());
        children.add(boxCollider);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
