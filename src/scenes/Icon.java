package scenes;

import Utils.Utils;
import bases.GameObject;
import bases.Vector2D;
import bases.renderers.Animation;
import players.Player;

import java.awt.*;

public class Icon extends GameObject {
    Animation bullet;
    Animation health;
    Animation nipple;

    public static Icon instance;

    public Icon() {
        bullet = new Animation(Utils.loadImage("assets/image/iconmenu/icon.png"));
        health = new Animation(Utils.loadImage("assets/image/item/itemhp.png"));
        nipple = new Animation(Utils.loadImage("assets/image/item/item.png"));
        instance = this;
    }

    @Override
    public void render(Graphics2D g2d) {
        if (Player.instance != null) {
            for (int i = 1; i <= Player.instance.bullets; i++) {
                bullet.render(g2d, new Vector2D(20, 30 * i + 20));
            }

            for (int i = 1; i <= Player.instance.life; i++) {
                health.render(g2d, new Vector2D(70, 30 * i + 20));
            }

            nipple.render(g2d, new Vector2D(120, 42));

            g2d.setColor(Color.RED);
            g2d.setFont(new Font("serif", Font.BOLD, 20));
            g2d.drawString("" + Player.instance.totalNipple, 150, 40 );
        }
    }
}
