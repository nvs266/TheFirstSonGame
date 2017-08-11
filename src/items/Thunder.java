package items;

import Utils.Utils;
import bases.Vector2D;
import bases.renderers.Animation;
import physics.Physics;
import platforms.PlatformSprite;
import players.Player;

public class Thunder extends ItemSprite {

    public Thunder() {
        renderer = new Animation(Utils.loadImage("assets/image/thunder/thunder.png"));
        setBoxCollider();
    }

    @Override
    public void run(Vector2D parentPosition) {
        if (this.hitPlayer && isActive()) {
            this.setActive(false);
            Player.instance.hero = true;
            Player.instance.trail = true;
            Player.instance.animationPlayer.setHero(true);
        }
        super.run(parentPosition);
        PlatformSprite platformSprite = Physics.bodyInRectofsuper(position.add(0,1), boxCollider.width, boxCollider.height, PlatformSprite.class);
        if (platformSprite == null){
            position.addUp(0,4);
        }
     }

    @Override
    public void refresh() {
        super.refresh();
        hitPlayer = false;
    }
}
