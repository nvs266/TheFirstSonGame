package platforms;

import Utils.Utils;
import bases.Vector2D;
import bases.renderers.Animation;
import physics.BoxCollider;
import physics.Physics;

public class Thunder extends ItemSprite {

    public Thunder() {
        renderer = new Animation(Utils.loadImage("assets/image/thunder/thunder.png"));
        setBoxCollider();
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        PlatformSprite platformSprite = Physics.bodyInRectofsuper(position.add(0,1), boxCollider.width, boxCollider.height, PlatformSprite.class);
        if (platformSprite == null){
            position.addUp(0,4);
        }
     }

}
