package enemies.boss;

import Utils.Utils;
import bases.Audio;
import bases.GameObject;
import bases.GameObjectPool;
import bases.Setting;
import bases.Vector2D;
import bases.actions.*;
import bases.renderers.Animation;
import enemies.EnemyExplosion;
import inputs.InputManager;
import physics.BoxCollider;
import physics.Physics;
import physics.PhysicsBody;
import players.Player;
import scenes.Camera;
import scenes.SceneManager;
import scenes.Victory;

import java.awt.*;
import java.util.Random;


public class Boss extends GameObject implements PhysicsBody, Setting{
    public static Boss instance = new Boss();
    public int hp = 300;
    public static Audio bossAudio;
    public Animation attack;
    private Animation normal;
    private boolean attackMode;

    public Boss() {
        super();
        normal = new Animation(50, true,
                Utils.loadImage("assets/image/boss/boss2.png")
                );
        attack = new Animation(20,false,
                Utils.loadImage("assets/image/boss/boss.png"),
                Utils.loadImage("assets/image/boss/boss1.png")
                );
        renderer = normal;
        boxCollider = new BoxCollider(300, 300);
        this.children.add(boxCollider);
        Action action = new RepeatForeverAction(
                new SequenceAction(
                        new RepeatnAction(3, new SequenceAction(
                                    new SkillOne(this.position),
                                    new WaitAction(150))
                        ),
                        new SkillSecond(this.position)
                )
        );
        this.addAction(action);
        Camera.instance.setFollowGameObject(this);
        instance = this;
    }


    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);

        if (Camera.instance.getFollowGameObject() == this) {
            Camera.instance.setPosition();
        }
        Player player = Physics.bodyInRect(position, boxCollider.width, boxCollider.height, Player.class);
        if (player!= null && !InputManager.instance.immotal && !player.immortal){
            player.life--;
            player.immortal = true;
        }
        if (hp <= 0){
            Player.instance.victory = true;
            Random random = new Random();
            for (int i = 0;i < 10;i++){
                Explosion explosion = GameObjectPool.recycle(Explosion.class);
                explosion.position.set(position.add(random.nextInt(600)-300,random.nextInt(600)-300));
                EnemyExplosion enemyExplosion3 = GameObjectPool.recycle(EnemyExplosion.class);
                enemyExplosion3.position.set(position.add(random.nextInt(600)-300,random.nextInt(600)-300));
            }


            setActive(false);
        }
    }

    public void setAttackMode(boolean attackMode) {
        this.attackMode = attackMode;
    }

    @Override
    public void render(Graphics2D g2d) {
        if (attackMode){
            renderer = attack;
        }else {
            renderer = normal;
        }
        super.render(g2d);
        if (renderer.isFinished() && attackMode){
            attackMode = false;
        }
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

    @Override
    public void refresh() {
        super.refresh();
        hp = 300;
        this.setActive(true);
    }
}
