package enemies.boss;

import Utils.Utils;
import bases.GameObject;
import bases.Setting;
import bases.Vector2D;
import bases.actions.*;
import bases.renderers.Animation;
import physics.BoxCollider;
import physics.PhysicsBody;
import scenes.Camera;

import java.awt.*;


public class Boss extends GameObject implements PhysicsBody, Setting{
    public static Boss instance = null;

    public Boss() {
        super();
        renderer = new Animation(50, true,
                Utils.loadImage("assets/image/boss/boss.png"),
                Utils.loadImage("assets/image/boss/boss1.png"),
                Utils.loadImage("assets/image/boss/boss2.png")
                );

        boxCollider = new BoxCollider(renderer.getWidth(), renderer.getHeight());
        this.children.add(boxCollider);
        Action action = new RepeatForeverAction(
                new SequenceAction(
                        new RepeatnAction(300,new MoveByAction(new Vector2D(0, 1), 1)),
                        new RepeatnAction(5,
                            new SequenceAction(
                                    new WaitAction(300),
                                    new SkillOne(this.position)
                            )
                                )
                )
        );
        this.addAction(action);

        instance = this;
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        super.render(g2d);
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);

        if (Camera.instance.getFollowGameObject() == this) {
            Camera.instance.setPosition();
        }
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}
