package players.bullets;

import Utils.Utils;
import bases.GameObjectPool;
import bases.Vector2D;
import bases.renderers.Animation;
import players.Player;


public class ClassicBullet extends PlayerBulletSprite {

    public ClassicBullet() {
        super();
        velocity = new Vector2D(0, 7);
        totalBulletsPerShoot = 1;
    }

    @Override
    void setRenderer() {
        renderer = new Animation(10, false,
                Utils.loadImage("assets/image/player/bullet/classicBullet/tes1.png"),
                Utils.loadImage("assets/image/player/bullet/classicBullet/tes2.png"),
                Utils.loadImage("assets/image/player/bullet/classicBullet/tes3.png")
        );
    }

    @Override
    public void shoot() {
        ClassicBullet bullet = GameObjectPool.recycle(ClassicBullet.class);
        bullet.renderer.reset();
        bullet.position.set(Player.instance.position.add(0, 40));
        bullet.frameCounter.reset();
        bullet.frameCounterHero.reset();
    }
}
