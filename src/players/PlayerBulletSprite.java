package players;

import bases.FrameCounter;
import bases.GameObject;
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
        }
    }

    void setBoxCollider() {
        boxCollider = new BoxCollider(renderer.getWidth(), renderer.getHeight());
        this.children.add(boxCollider);
    }

    abstract void move();

    abstract void shoot();

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}
