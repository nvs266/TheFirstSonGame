package players.bullets;

import Utils.Utils;
import bases.GameObjectPool;
import bases.Setting;
import bases.Vector2D;
import bases.renderers.Animation;
import players.Player;

import java.awt.*;

public class ThreeRayBullet extends PlayerBulletSprite {

    public ThreeRayBullet() {
        super();
        totalBulletsPerShoot = 3;
    }

    @Override
    void setRenderer() {
    }

    @Override
    public void run(Vector2D parentPosition) {

    }

    @Override
    public void render(Graphics2D g2d) {
    }

    @Override
    void setBoxCollider() {
    }

    @Override
    public void shoot() {
        ClassicBullet bullet = GameObjectPool.recycle(ClassicBullet.class);
        bullet.renderer.reset();
        bullet.position.set(Player.instance.position.add(0, 40));
        bullet.frameCounter.reset();
        bullet.frameCounterHero.reset();

        ClassicBullet bullet1 = GameObjectPool.recycle(ClassicBullet.class);
        bullet1.renderer.reset();
        bullet1.position.set(Player.instance.position.add(15, 40));
        bullet1.frameCounter.reset();
        bullet1.frameCounterHero.reset();

        ClassicBullet bullet2 = GameObjectPool.recycle(ClassicBullet.class);
        bullet2.renderer.reset();
        bullet2.position.set(Player.instance.position.add(-15, 40));
        bullet2.frameCounter.reset();
        bullet2.frameCounterHero.reset();
    }
}
