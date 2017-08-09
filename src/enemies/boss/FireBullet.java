package enemies.boss;

import Utils.Utils;
import bases.GameObject;
import bases.Vector2D;
import bases.renderers.Animation;
import physics.BoxCollider;
import physics.PhysicsBody;

import java.awt.*;

public class FireBullet extends GameObject implements PhysicsBody {
    private Vector2D velocity;

    public FireBullet() {
        renderer = new Animation(Utils.loadImage("assets/image/player/bullet/classicBullet/0.png"));

        boxCollider = new BoxCollider(renderer.getWidth(), renderer.getHeight());
        this.children.add(boxCollider);
    }

    public void set(Vector2D velocity, Vector2D position) {
        this.velocity = velocity;
        this.position = new Vector2D();
        this.position.set(position);
    }

    @Override
    public void render(Graphics2D g2d) {
//        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        super.render(g2d);
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        this.position.set(position.substract(velocity));
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

}
