package enemies.boss;

import Utils.Utils;
import bases.GameObject;
import bases.Setting;
import bases.Vector2D;
import bases.actions.*;
import bases.renderers.Animation;
import physics.BoxCollider;
import physics.PhysicsBody;


public class Boss extends GameObject implements PhysicsBody, Setting{
    public static Boss instance = null;

    public Boss() {
        super();
        renderer = new Animation(Utils.loadImage("assets/image/boss/boss1.png"));

        boxCollider = new BoxCollider(renderer.getWidth(), renderer.getHeight());
        this.children.add(boxCollider);
        Action action = new RepeatForeverAction(
                new SequenceAction(
                        new RepeatnAction(5, new MoveByAction(new Vector2D(0, 0.1f),1)),
                        new WaitAction(1000)
//                        new SkillOne(this.position)
                )
        );
        this.addAction(action);

        instance = this;
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}
