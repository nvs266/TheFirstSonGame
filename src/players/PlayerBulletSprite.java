package players;

import bases.GameObject;
import bases.GameObjectPool;
import bases.Vector2D;
import enemies.EnemyExplosion;
import enemies.EnemySprite;
import items.Nipple;
import physics.BoxCollider;
import physics.Physics;
import physics.PhysicsBody;
import items.Thunder;

public abstract class PlayerBulletSprite extends GameObject implements PhysicsBody {
    int totalBulletsPerShoot;
    int damge = 1;
    Vector2D velocity;

    public PlayerBulletSprite() {
        super();
        setRenderer();
        setBoxCollider();
    }

    abstract void setRenderer();

    void hitEnemy() {
        EnemySprite enemySprite = Physics.bodyInRectofsuper(this.position, renderer.getWidth(), renderer.getHeight(),EnemySprite.class);
        if (enemySprite != null) {
            enemySprite.health--;
            EnemyExplosion enemyExplosion = GameObjectPool.recycle(EnemyExplosion.class);
            enemyExplosion.position.set(enemySprite.position);
            enemyExplosion.renderer.reset();

            if (enemySprite.health <= 0) {
                enemySprite.setActive(false);
                Nipple nipple = GameObjectPool.recycle(Nipple.class);
                nipple.position.set(enemySprite.position);
            }
            this.setActive(false);


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
