package enemies;

import Utils.Utils;
import bases.GameObject;
import bases.renderers.Animation;

import java.awt.*;

public class EnemyExplosion extends GameObject {
    public EnemyExplosion() {
        renderer = new Animation(20, false,
                Utils.loadImage("assets/image/enemy/explosion/0.png"),
                Utils.loadImage("assets/image/enemy/explosion/1.png"),
                Utils.loadImage("assets/image/enemy/explosion/2.png"),
                Utils.loadImage("assets/image/enemy/explosion/3.png"),
                Utils.loadImage("assets/image/enemy/explosion/4.png"),
                Utils.loadImage("assets/image/enemy/explosion/5.png"),
                Utils.loadImage("assets/image/enemy/explosion/6.png"),
                Utils.loadImage("assets/image/enemy/explosion/7.png")
                );
    }

    @Override
    public void render(Graphics2D g2d) {
        super.render(g2d);
        if (renderer.isFinished()) this.setActive(false);
    }
}
