package items;

import Utils.Utils;
import bases.Audio;
import bases.FrameCounter;
import bases.Vector2D;
import bases.renderers.Animation;
import physics.BoxCollider;
import physics.Physics;
import platforms.PlatformSprite;
import players.Player;

public class Nipple extends ItemSprite {
    private FrameCounter frameCounter;

    public Nipple() {
        super();
        renderer = new Animation(Utils.loadImage("assets/image/item/item.png"));
        frameCounter = new FrameCounter(100);
        setBoxCollider();
    }

    @Override
    void setBoxCollider() {
        this.boxCollider = new BoxCollider(this.renderer.getWidth() - 5, this.renderer.getHeight());
        this.children.add(this.boxCollider);
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);

        if (hitPlayer && isActive()) {
            Player.instance.totalNipple++;
            this.setActive(false);
            itemAudio = new Audio("assets/music/player/item.wav");
            itemAudio.play();
        }

        PlatformSprite platformSprite = Physics.bodyInRectofsuper(position.add(0,1), boxCollider.width, boxCollider.height, PlatformSprite.class);

        if (platformSprite == null){
            position.addUp(0,4);
        }

        if (frameCounter.run()) {
            this.setActive(false);
        }
    }

    @Override
    public void refresh() {
        super.refresh();
        frameCounter.reset();
        hitPlayer = false;
    }
}
