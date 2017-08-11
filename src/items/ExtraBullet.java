package items;

import Utils.Utils;
import bases.Audio;
import bases.Vector2D;
import bases.renderers.Animation;
import physics.Physics;
import platforms.PlatformSprite;
import players.Player;

public class ExtraBullet extends ItemSprite{
    public ExtraBullet() {
        renderer = new Animation(Utils.loadImage("assets/image/iconmenu/icon.png"));
        setBoxCollider();
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        if (hitPlayer && isActive()) {
            Player.instance.totalBullets += 3;
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
