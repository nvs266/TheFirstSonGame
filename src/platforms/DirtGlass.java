package platforms;

import Utils.Utils;
import bases.GameObject;
import bases.renderers.Animation;
import physics.BoxCollider;
import physics.PhysicsBody;

public class DirtGlass extends GameObject implements PhysicsBody{

    public DirtGlass() {
        renderer = new Animation(Utils.loadImage("assets/image/land/dirt_grass.png"));
        boxCollider = new BoxCollider(renderer.getWidth(), renderer.getHeight());
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
