package platforms;

import Utils.Utils;
import bases.GameObject;
import bases.renderers.Animation;
import physics.BoxCollider;
import physics.PhysicsBody;

public class DirtGlass extends PlatformSprite{

    public DirtGlass() {
        renderer = new Animation(Utils.loadImage("assets/image/land/dirt_grass.png"));
        boxCollider = new BoxCollider(renderer.getWidth(), renderer.getHeight());
        children.add(boxCollider);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
