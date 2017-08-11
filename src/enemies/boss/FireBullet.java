package enemies.boss;

import Utils.Utils;
import bases.Audio;
import bases.GameObject;
import bases.Vector2D;
import bases.renderers.Animation;
import inputs.InputManager;
import physics.BoxCollider;
import physics.Physics;
import physics.PhysicsBody;
import platforms.BrickGrey;
import platforms.PlatformSprite;
import players.Player;

import java.awt.*;

public class FireBullet extends GameObject implements PhysicsBody {
    private Vector2D velocity;

    public FireBullet() {
        renderer = new Animation(2, true,
                Utils.loadImage("assets/image/player/bullet/classicBullet/test1.png"),
                Utils.loadImage("assets/image/player/bullet/classicBullet/test2.png"),
                Utils.loadImage("assets/image/player/bullet/classicBullet/test3.png"),
                Utils.loadImage("assets/image/player/bullet/classicBullet/test4.png"),
                Utils.loadImage("assets/image/player/bullet/classicBullet/test5.png"),
                Utils.loadImage("assets/image/player/bullet/classicBullet/test6.png"),
                Utils.loadImage("assets/image/player/bullet/classicBullet/test7.png"),
                Utils.loadImage("assets/image/player/bullet/classicBullet/test8.png"),
                Utils.loadImage("assets/image/player/bullet/classicBullet/test9.png")
        );
        Boss.bossAudio = new Audio("assets/music/enmemies/boss/boss.wav");
        Boss.bossAudio.play();

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
        if (position.x < 200 || position.x >1000||position.y < Player.instance.position.y-400){
            this.setActive(false);
        }
        if (Physics.bodyInRect(boxCollider, BrickGrey.class) != null && position.y <8300){
            this.setActive(false);
        }
        Player player = Physics.bodyInRect(boxCollider,Player.class);
        if (player!= null && !player.immortal&& !InputManager.instance.immotal){
            player.life--;
            player.immortal = true;
        }
        this.position.set(position.substract(velocity));
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

}
