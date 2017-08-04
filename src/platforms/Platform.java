package platforms;

import Utils.Utils;
import bases.GameObject;
import bases.renderers.Animation;
import physics.BoxCollider;
import physics.PhysicsBody;

public class Platform extends PlatformSprite {

    public Platform() {
        renderer = new Animation(Utils.loadImage("assets/image/platform/1.png"));
        boxCollider = new BoxCollider(renderer.getWidth(), renderer.getHeight());
        children.add(boxCollider);
    }


    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
