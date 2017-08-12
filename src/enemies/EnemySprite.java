package enemies;

import bases.*;
import inputs.InputManager;
import items.Nipple;
import physics.BoxCollider;
import physics.Physics;
import physics.PhysicsBody;
import items.Thunder;
import players.Player;

public abstract class EnemySprite extends GameObject implements PhysicsBody, Setting {
    public int health;

    public EnemySprite() {
        super();
        setRenderer();
        setBoxCollider();
        health = 1;
    }

    protected abstract void setRenderer();

    void hitEnemy() {
        Player player = Physics.bodyInRect(position.add(0, 0), boxCollider.width, boxCollider.height, Player.class);
        if (player != null) {
            float botPlayer = 0;
            float topthis = 0;
            if (player.renderer != null) {
                botPlayer = player.position.y + player.renderer.getHeight() / 2;
                topthis = position.y - renderer.getHeight() / 2;
            }
            if (botPlayer < topthis && this.getClass() != GumEnemy.class) {
                Player.instance.velocity.y = SPEED_JUMPP_HIT_ENEMY;
                setActive(false);

                EnemyExplosionSmoke enemyExplosionSmoke = GameObjectPool.recycle(EnemyExplosionSmoke.class);
                EnemyExplosionSmoke.enemyExplosionSmokeAudio.play();
                enemyExplosionSmoke.position.set(this.position);
                enemyExplosionSmoke.renderer.reset();

                Player.instance.resetBullet();

                Nipple nipple = GameObjectPool.recycle(Nipple.class);
                nipple.position.set(this.position);
            } else {
                if (!Player.instance.immortal) {
                    if (this.getClass() == GumEnemy.class) {
                        Player.instance.velocity.y = SPEED_JUMPP_HIT_ENEMY;
                    }
                    if (!InputManager.instance.immotal){
                        Player.instance.life--;
                    }

                    Player.cryAudio = new Audio("assets/music/player/cry.wav");
                    Player.cryAudio.play();
                    Player.instance.immortal = true;
                }
            }
        }
    }

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

    protected abstract void move();

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}
