package enemies;

import Utils.Utils;
import bases.Audio;
import bases.GameObject;
import bases.renderers.Animation;

import java.awt.*;

public class EnemyExplosion extends GameObject {
    public static Audio enemyExplosionAudio;
    public EnemyExplosion() {
        renderer = new Animation(1, false,
                Utils.loadImage("assets/image/enemy/explosion/1.png"),
                Utils.loadImage("assets/image/enemy/explosion/2.png"),
                Utils.loadImage("assets/image/enemy/explosion/3.png"),
                Utils.loadImage("assets/image/enemy/explosion/4.png"),
                Utils.loadImage("assets/image/enemy/explosion/5.png"),
                Utils.loadImage("assets/image/enemy/explosion/6.png"),
                Utils.loadImage("assets/image/enemy/explosion/7.png"),
                Utils.loadImage("assets/image/enemy/explosion/8.png"),
                Utils.loadImage("assets/image/enemy/explosion/9.png"),
                Utils.loadImage("assets/image/enemy/explosion/10.png"),
                Utils.loadImage("assets/image/enemy/explosion/11.png"),
                Utils.loadImage("assets/image/enemy/explosion/12.png"),
                Utils.loadImage("assets/image/enemy/explosion/13.png"),
                Utils.loadImage("assets/image/enemy/explosion/14.png"),
                Utils.loadImage("assets/image/enemy/explosion/15.png"),
                Utils.loadImage("assets/image/enemy/explosion/16.png"),
                Utils.loadImage("assets/image/enemy/explosion/17.png"),
                Utils.loadImage("assets/image/enemy/explosion/18.png"),
                Utils.loadImage("assets/image/enemy/explosion/19.png"),
                Utils.loadImage("assets/image/enemy/explosion/20.png"),
                Utils.loadImage("assets/image/enemy/explosion/21.png"),
                Utils.loadImage("assets/image/enemy/explosion/22.png"),
                Utils.loadImage("assets/image/enemy/explosion/23.png"),
                Utils.loadImage("assets/image/enemy/explosion/24.png"),
                Utils.loadImage("assets/image/enemy/explosion/25.png"),
                Utils.loadImage("assets/image/enemy/explosion/26.png"),
                Utils.loadImage("assets/image/enemy/explosion/27.png"),
                Utils.loadImage("assets/image/enemy/explosion/28.png"),
                Utils.loadImage("assets/image/enemy/explosion/29.png"),
                Utils.loadImage("assets/image/enemy/explosion/30.png"),
                Utils.loadImage("assets/image/enemy/explosion/31.png"),
                Utils.loadImage("assets/image/enemy/explosion/32.png"),
                Utils.loadImage("assets/image/enemy/explosion/33.png"),
                Utils.loadImage("assets/image/enemy/explosion/34.png"),
                Utils.loadImage("assets/image/enemy/explosion/35.png"),
                Utils.loadImage("assets/image/enemy/explosion/36.png")
                );
        enemyExplosionAudio = new Audio("assets/music/enmemies/explosion.wav");
    }

    @Override
    public void render(Graphics2D g2d) {
        super.render(g2d);
        if (renderer.isFinished()) this.setActive(false);
    }
}
