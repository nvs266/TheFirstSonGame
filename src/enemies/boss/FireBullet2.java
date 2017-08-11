package enemies.boss;

import Utils.Utils;
import bases.GameObject;
import bases.Vector2D;
import bases.renderers.Animation;
import inputs.InputManager;
import physics.BoxCollider;
import physics.Physics;
import physics.PhysicsBody;
import platforms.BrickGrey;
import players.Player;

import java.awt.*;

/**
 * Created by cuonghx2709 on 8/12/2017.
 */
public class FireBullet2 extends GameObject implements PhysicsBody {


    private Vector2D velocity;

    public FireBullet2() {
        this.renderer = new Animation(5,true,
                Utils.loadImage("assets/image/boss/bullet/1.png"),
                Utils.loadImage("assets/image/boss/bullet/2.png")
        );

        boxCollider = new BoxCollider(renderer.getWidth(), renderer.getHeight());
        this.children.add(boxCollider);
    }

    public void set(Vector2D velocity, Vector2D position) {
        this.velocity = velocity;
        this.position = new Vector2D();
        this.position.set(position);
    }


    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        if (position.x < 200 || position.x >1000||position.y < Player.instance.position.y-400){
            this.setActive(false);
        }
        if (Physics.bodyInRect(boxCollider, BrickGrey.class) != null && position.y <8350){
            this.setActive(false);
        }
        Player player = Physics.bodyInRect(boxCollider,Player.class);
        if (player!= null && !player.immortal && !InputManager.instance.immotal){
            player.life--;
            player.immortal = true;
            this.setActive(false);
        }
        this.position.set(position.substract(velocity));
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
