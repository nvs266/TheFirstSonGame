package players.bullets;

import Utils.Utils;
import bases.GameObjectPool;
import bases.Vector2D;
import bases.renderers.Animation;
import items.Thunder;
import physics.Physics;
import physics.PhysicsBody;
import platforms.BrickGrey;
import platforms.BrickItem;
import platforms.DirtGlass;
import platforms.PlatformSprite;
import players.Player;

import java.awt.*;

public class Classic3Bullet extends PlayerBulletSprite {
    public Classic3Bullet() {
        super();
        velocity = new Vector2D();
        totalBulletsPerShoot = 3;
    }

    @Override
    void setRenderer() {
        renderer = new Animation(
                Utils.loadImage("assets/image/player/bullet/classicBullet/test3.png"),
                Utils.loadImage("assets/image/player/bullet/classicBullet/test4.png"),
                Utils.loadImage("assets/image/player/bullet/classicBullet/test5.png"),
                Utils.loadImage("assets/image/player/bullet/classicBullet/test6.png"),
                Utils.loadImage("assets/image/player/bullet/classicBullet/test7.png"),
                Utils.loadImage("assets/image/player/bullet/classicBullet/test8.png"),
                Utils.loadImage("assets/image/player/bullet/classicBullet/test9.png")
        );
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        move();
        hitEnemy();
    }

    @Override
    public void move() {
        this.position.addUp(velocity);
        PhysicsBody physicsBody = Physics.bodyInRectofsuper(position.add(0,1),boxCollider.width, boxCollider.height, PlatformSprite.class);
        if (physicsBody != null){
            if (physicsBody.getClass() == BrickGrey.class){
                setActive(false);
            }
            else if (physicsBody.getClass() == BrickItem.class){
                BrickItem brickItem = (BrickItem) physicsBody;
                brickItem.setActive(false);
                Thunder thunder = GameObjectPool.recycle(Thunder.class);
                thunder.position.set(position);
                setActive(false);
            }
            else if (physicsBody.getClass() == DirtGlass.class){
                DirtGlass dirtGlass = (DirtGlass) physicsBody;
                dirtGlass.setActive(false);
                setActive(false);
            }
        }
        if (this.position.y - Player.instance.position.y > 200 && !Player.instance.hero){
            this.setActive(false);
        }
        else if (this.position.y - Player.instance.position.y > 400) {
            this.setActive(false);
        }
    }

    @Override
    public void shoot() {
        Classic3Bullet classic3Bullet1 = GameObjectPool.recycle(Classic3Bullet.class);
        classic3Bullet1.position.set(Player.instance.position);
        classic3Bullet1.renderer.reset();
        classic3Bullet1.velocity.set(0, 5);

        Classic3Bullet classic3Bullet2 = GameObjectPool.recycle(Classic3Bullet.class);
        classic3Bullet2.position.set(Player.instance.position);
        classic3Bullet2.renderer.reset();
        classic3Bullet2.velocity.set(2,5);

        Classic3Bullet classic3Bullet3 = GameObjectPool.recycle(Classic3Bullet.class);
        classic3Bullet3.position.set(Player.instance.position);
        classic3Bullet3.renderer.reset();
        classic3Bullet3.velocity.set(-2, 5);
    }

    @Override
    public void render(Graphics2D g2d) {
        super.render(g2d);
        if (renderer.isFinished()) {
            this.setActive(false);
        }
    }
}
