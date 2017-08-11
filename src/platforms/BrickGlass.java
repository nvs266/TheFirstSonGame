package platforms;

import Utils.Utils;
import bases.GameObject;
import bases.Vector2D;
import bases.renderers.Animation;
import physics.BoxCollider;
import physics.PhysicsBody;

public class BrickGlass extends PlatformSprite {

    public BrickGlass() {
        super();
        renderer = new Animation(Utils.loadImage("assets/image/brick/stone_grass.png"));
        boxCollider = new BoxCollider(renderer.getWidth(), renderer.getHeight());
        this.children.add(boxCollider);
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
