package players;

import bases.FrameCounter;
import bases.GameObject;
import bases.GameObjectPool;
import enemies.EnemyExplosion;
import enemies.EnemySprite;
import physics.BoxCollider;
import physics.Physics;
import physics.PhysicsBody;

public abstract class PlayerBulletSprite extends GameObject implements PhysicsBody {
    int totalBulletsPerShoot;

    public PlayerBulletSprite() {
        super();
        setRenderer();
        setBoxCollider();
    }


    abstract void setRenderer();

    void hitEnemy() {
        EnemySprite enemySprite = Physics.bodyInRectofsuper(this.position, renderer.getWidth(), renderer.getHeight(),EnemySprite.class);
        if (enemySprite != null) {
            this.setActive(false);
            enemySprite.setActive(false);

            EnemyExplosion enemyExplosion = GameObjectPool.recycle(EnemyExplosion.class);
            enemyExplosion.position.set(enemySprite.position);
            enemyExplosion.renderer.reset();
        }
    }

    void setBoxCollider() {
        boxCollider = new BoxCollider(renderer.getWidth(), renderer.getHeight());
        this.children.add(boxCollider);
    }

    void addCartouche() {
        Cartouche cartouche = GameObjectPool.recycle(Cartouche.class);
        cartouche.setCartouche(Player.instance.position);
    }

    abstract void move();

    abstract void shoot();

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}
