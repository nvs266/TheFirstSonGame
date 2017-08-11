package enemies.boss;

import Utils.Utils;
import bases.GameObject;
import bases.Setting;
import bases.Vector2D;
import bases.actions.*;
import bases.renderers.Animation;
import inputs.InputManager;
import physics.BoxCollider;
import physics.Physics;
import physics.PhysicsBody;
import players.Player;
import scenes.Camera;

import java.awt.*;


public class Boss extends GameObject implements PhysicsBody, Setting{
    public static Boss instance = new Boss();
    public int hp = 20;

    public Boss() {
        super();
        renderer = new Animation(50, true,
                Utils.loadImage("assets/image/boss/boss.png"),
                Utils.loadImage("assets/image/boss/boss1.png"),
                Utils.loadImage("assets/image/boss/boss2.png")
                );

        boxCollider = new BoxCollider(300, 300);
        this.children.add(boxCollider);
        Action action = new RepeatForeverAction(
                new SequenceAction(
                        new RepeatnAction(5, new SequenceAction(
                                    new SkillOne(this.position),
                                    new WaitAction(200))
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
            Player.instance.vitory = true;
        }
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}
