package platforms;

import Utils.Utils;
import bases.GameObject;
import bases.renderers.Animation;
import physics.BoxCollider;
import physics.PhysicsBody;

public class GravelDirt extends PlatformSprite{

    public GravelDirt() {
        renderer = new Animation(Utils.loadImage("assets/image/land/gravel_dirt.png"));
        boxCollider = new BoxCollider(renderer.getWidth(), renderer.getHeight());
        children.add(boxCollider);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
