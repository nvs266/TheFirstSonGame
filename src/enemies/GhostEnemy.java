package enemies;

import Utils.Utils;
import bases.GameObject;
import bases.renderers.Animation;

public class GhostEnemy extends GameObject {
    public GhostEnemy() {
        renderer = new Animation(50, true,
                Utils.loadImage("assets/image/enemy/enemy4/0.png"),
                Utils.loadImage("assets/image/enemy/enemy4/1.png"),
                Utils.loadImage("assets/image/enemy/enemy4/2.png")
        );
    }
}
