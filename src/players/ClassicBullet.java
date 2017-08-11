package players;

import Utils.Utils;
import bases.GameObjectPool;
import bases.Vector2D;
import bases.renderers.Animation;
import items.Thunder;
import physics.Physics;
import physics.PhysicsBody;
import platforms.*;

import java.awt.*;

public class ClassicBullet extends PlayerBulletSprite {

    public ClassicBullet() {
        super();
        totalBulletsPerShoot = 1;
    }

    @Override
    void setRenderer() {
        renderer = new Animation(30, false,
                Utils.loadImage("assets/image/player/bullet/classicBullet/tes1.png"),
                Utils.loadImage("assets/image/player/bullet/classicBullet/tes2.png"),
                Utils.loadImage("assets/image/player/bullet/classicBullet/tes3.png")
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
        this.position.y += 10;
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
    void shoot() {
        ClassicBullet bullet = GameObjectPool.recycle(ClassicBullet.class);
        bullet.renderer.reset();
        bullet.position.set(Player.instance.position);
    }

    @Override
    public void render(Graphics2D g2d) {
        super.render(g2d);
        if (renderer.isFinished()) {
            this.setActive(false);
        }
    }
}
