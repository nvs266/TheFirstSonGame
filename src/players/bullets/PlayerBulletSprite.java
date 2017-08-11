package players.bullets;

import bases.*;
import enemies.EnemyExplosion;
import enemies.EnemySprite;
import enemies.boss.Boss;
import items.Nipple;
import items.Thunder;
import physics.BoxCollider;
import physics.Physics;
import physics.PhysicsBody;
import platforms.*;
import players.Player;

public abstract class PlayerBulletSprite extends GameObject implements PhysicsBody {
    public int totalBulletsPerShoot;
    public Vector2D velocity;
    public FrameCounter frameCounter;
    public FrameCounter frameCounterHero;
    public Audio itemAudio;

    public PlayerBulletSprite() {
        super();
        frameCounter = new FrameCounter(30);
        frameCounterHero = new FrameCounter(45);
        setRenderer();
        setBoxCollider();
    }

    abstract void setRenderer();

    void setBoxCollider() {
        boxCollider = new BoxCollider(renderer.getWidth(), renderer.getHeight());
        this.children.add(boxCollider);
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        move();
        hitEnemy();
    }

    void hitEnemy() {
        EnemySprite enemySprite = Physics.bodyInRectofsuper(this.position, renderer.getWidth(), renderer.getHeight(),EnemySprite.class);

        if (enemySprite != null) {
            enemySprite.health--;
            EnemyExplosion enemyExplosion = GameObjectPool.recycle(EnemyExplosion.class);
            enemyExplosion.position.set(enemySprite.position);
            EnemyExplosion.enemyExplosionAudio.play();
            enemyExplosion.renderer.reset();

            if (enemySprite.health <= 0) {
                enemySprite.setActive(false);
                Nipple nipple = GameObjectPool.recycle(Nipple.class);
                nipple.position.set(enemySprite.position);
            }

            this.setActive(false);
        }
        Boss boss = Physics.bodyInRect(this.position, renderer.getWidth(), renderer.getHeight(),Boss.class);
        if (boss!= null){
            boss.hp--;
        }
    }

    public void addCartouche() {
        for (int i = 0; i < totalBulletsPerShoot; i++) {
            Cartouche cartouche = GameObjectPool.recycle(Cartouche.class);
            cartouche.setCartouche(Player.instance.position);
        }
    }

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
            } else if (physicsBody.getClass() == GravelDirt.class) {
                GravelDirt gravelDirt = (GravelDirt) physicsBody;
                gravelDirt.setActive(false);
                setActive(false);
            }
        }

        if (Player.instance.hero){
            if (frameCounterHero.run()) {
                this.setActive(false);
            }
        } else {
            if (frameCounter.run()) {
                this.setActive(false);
            }
        }
    }

    abstract public void shoot();

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}
