package enemies.boss;

import Utils.Utils;
import bases.*;
import bases.actions.Action;
import bases.renderers.Animation;
import physics.BoxCollider;
import physics.PhysicsBody;
import players.Player;

import java.util.Random;

/**
 * Created by cuonghx2709 on 8/12/2017.
 */
public class SkillSecond extends GameObject implements  Action {

    private int angle ;
    private Vector2D bossPos;
    FrameCounter frameCounter;

    public SkillSecond(Vector2D bossPos){
        super();
        this.bossPos = bossPos;
        this.angle = 0;
        this.frameCounter = new FrameCounter(10);
    }

    @Override
    public boolean run(GameObject gameObject) {
        if (frameCounter.run()){
            frameCounter.reset();
            FireBullet2 fireBullet2 = GameObjectPool.recycle(FireBullet2.class);
            Vector2D velocity =new Vector2D(-2.5f,0);
            fireBullet2.set(velocity.makeAlpha((float) (Math.PI)/angle*180), bossPos.add(-75,70));
            FireBullet2 fireBullet2right = GameObjectPool.recycle(FireBullet2.class);
            fireBullet2right.set(velocity.makeAlpha((float) (Math.PI)/angle*180), bossPos.add(75,70));
            angle += 8;
        }
        if (angle > 180){
            return true;
        }
        return false;
    }

    @Override
    public void reset() {
        angle = 0;
    }
}
