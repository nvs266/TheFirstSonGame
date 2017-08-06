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
        renderer = new Animation(Utils.loadImage("assets/image/item/item.png"));
        boxCollider = new BoxCollider(renderer.getWidth(), renderer.getHeight());
        children.add(boxCollider);
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        if (Physics.bodyInRectofsuper(position.add(0,2), boxCollider.width, boxCollider.height, PlatformSprite.class) == null){
            position.addUp(0,2);
            System.out.println("added");
        }
     }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
