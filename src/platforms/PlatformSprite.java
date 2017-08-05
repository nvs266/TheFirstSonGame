package platforms;

import bases.GameObject;
import physics.BoxCollider;
import physics.PhysicsBody;

/**
 * Created by cuonghx2709 on 8/4/2017.
 */
public abstract class PlatformSprite extends GameObject implements PhysicsBody{

    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
