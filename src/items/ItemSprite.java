package items;

import bases.GameObject;
import physics.BoxCollider;
import physics.PhysicsBody;

public abstract class ItemSprite extends GameObject implements PhysicsBody {
    public boolean hitPlayer = false;

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

    void setBoxCollider() {
        boxCollider = new BoxCollider(renderer.getWidth(), renderer.getHeight());
        children.add(boxCollider);
    }
}
