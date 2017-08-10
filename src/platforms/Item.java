package platforms;

import Utils.Utils;
import bases.GameObject;
import bases.Vector2D;
import bases.renderers.Animation;
import physics.BoxCollider;
import physics.Physics;
import physics.PhysicsBody;

public class Item extends PlatformSprite {

    public Item() {
        renderer = new Animation(Utils.loadImage("assets/image/thunder/thunder.png"));
        boxCollider = new BoxCollider(renderer.getWidth(), renderer.getHeight());
        children.add(boxCollider);
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        PlatformSprite platformSprite = Physics.bodyInRectofsuper(position.add(0,1), boxCollider.width, boxCollider.height, PlatformSprite.class);
        if (platformSprite == null || platformSprite.getClass() == Item.class){
            position.addUp(0,1);
        }
     }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
