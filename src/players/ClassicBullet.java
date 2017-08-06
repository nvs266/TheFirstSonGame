package players;

import Utils.Utils;
import bases.GameObjectPool;
import bases.Vector2D;
import bases.renderers.Animation;

public class ClassicBullet extends PlayerBulletSprite {

    public ClassicBullet() {
        super();
        totalBulletsPerShoot = 1;
    }

    @Override
    void setRenderer() {
        renderer = new Animation(10, true,
                Utils.loadImage("assets/image/player/bullet/classicBullet/0.png"),
                Utils.loadImage("assets/image/player/bullet/classicBullet/1.png"),
                Utils.loadImage("assets/image/player/bullet/classicBullet/2.png"),
                Utils.loadImage("assets/image/player/bullet/classicBullet/3.png")
        );
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        move();
        hitEnemy();
    }


    @Override
    void move() {
        this.position.y += 4;
        if (this.position.y - Player.instance.position.y > 200) this.setActive(false);
    }

    @Override
    void shoot() {
        ClassicBullet bullet = GameObjectPool.recycle(ClassicBullet.class);
        bullet.position.set(Player.instance.position);
    }

}
