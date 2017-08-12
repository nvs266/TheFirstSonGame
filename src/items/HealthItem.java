package items;

import Utils.Utils;
import bases.Audio;
import bases.Vector2D;
import bases.renderers.Animation;
import physics.Physics;
import platforms.PlatformSprite;
import players.Player;

public class HealthItem extends ItemSprite{

    public HealthItem() {
        renderer = new Animation(Utils.loadImage("assets/image/item/itemhp.png"));
        setBoxCollider();
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        if (hitPlayer && isActive()) {
            if (Player.instance.life < 4) {
                Player.instance.life++;
            }
            this.setActive(false);
            itemAudio = new Audio("assets/music/player/item.wav");
            itemAudio.play();
        }

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
