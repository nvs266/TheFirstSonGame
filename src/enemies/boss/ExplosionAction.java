package enemies.boss;

import bases.GameObject;
import bases.GameObjectPool;
import bases.Vector2D;
import bases.actions.Action;
import enemies.EnemyExplosion;

/**
 * Created by cuonghx2709 on 8/12/2017.
 */
public class ExplosionAction implements Action {

    Vector2D position;

    public ExplosionAction(Vector2D position){
        this.position = position;
    }
    @Override
    public boolean run(GameObject gameObject) {
        Explosion explosion = GameObjectPool.recycle(Explosion.class);
        explosion.position.set(position.add(0,-300));
        Explosion explosion2 = GameObjectPool.recycle(Explosion.class);
        explosion2.position.set(position.add(-100,-200));
        Explosion explosion3 =GameObjectPool.recycle(Explosion.class);
        explosion3.position.set(position.add(100,-100));
        EnemyExplosion enemyExplosion = GameObjectPool.recycle(EnemyExplosion.class);
        enemyExplosion.position.set(position.add(0,-400));
//        EnemyExplosion enemyExplosion2 = GameObjectPool.recycle(EnemyExplosion.class);
//        enemyExplosion2.position.set(position.add(0,-100));
//        EnemyExplosion enemyExplosion3 = GameObjectPool.recycle(EnemyExplosion.class);
//        enemyExplosion3.position.set(position.add(0,-200));
        return true;
    }

    @Override
    public void reset() {

    }
}
