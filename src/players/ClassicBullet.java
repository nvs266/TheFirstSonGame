package players;

import Utils.Utils;
import bases.Setting;
import bases.Vector2D;
import bases.renderers.Animation;
import enemies.EnemySprite;
import physics.BoxCollider;
import physics.Physics;
import physics.PhysicsBody;

public class ClassicBullet extends PlayerBulletSprite {

    public ClassicBullet() {
        super();
        Player.velocity.y = Setting.SPEED_JUMPP_HIT_ENEMY;
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
        this.position.y += 2;
        if (this.position.y - Player.instance.position.y > 400) this.setActive(false);
    }

}
