package platforms;

import bases.GameObject;
import physics.BoxCollider;
import physics.Physics;
import physics.PhysicsBody;

public abstract class ItemSprite extends GameObject implements PhysicsBody{
    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

    void setBoxCollider() {
        boxCollider = new BoxCollider(renderer.getWidth(), renderer.getHeight());
        children.add(boxCollider);
    }
}
