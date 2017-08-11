package enemies.boss;

import bases.GameObject;
import bases.GameObjectPool;
import bases.Vector2D;
import bases.actions.Action;
import physics.BoxCollider;
import physics.PhysicsBody;
import players.Player;
import scenes.Camera;

public class SkillOne extends GameObject implements PhysicsBody, Action {

    public SkillOne(Vector2D bossPos) {
        FireBullet fireBulletMid = GameObjectPool.recycle(FireBullet.class);
        Vector2D velocity = bossPos.substract(Player.instance.position).normalize().multiply(1.5f);
        fireBulletMid.set(velocity, bossPos);

        FireBullet fireBuletLeft = GameObjectPool.recycle(FireBullet.class);
        fireBuletLeft.set(velocity.makeAlpha((float) (-Math.PI / 4)), bossPos);

        FireBullet fireBuletRight = GameObjectPool.recycle(FireBullet.class);
        fireBuletRight.set(velocity.makeAlpha((float) (Math.PI / 4)), bossPos);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return null;
    }

    @Override
    public boolean run(GameObject gameObject) {
        return true;
    }

    @Override
    public void reset() {
        new SkillOne(Boss.instance.position.clone());
    }
}
