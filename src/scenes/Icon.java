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

    public static Icon instance;

    public Icon() {
        bullet = new Animation(Utils.loadImage("assets/image/iconmenu/icon.png"));
        health = new Animation(Utils.loadImage("assets/image/item/itemhp.png"));
        instance = this;
    }

    @Override
    public void render(Graphics2D g2d) {
        if (Player.instance != null) {
            for (int i = 1; i <= Player.instance.bullets; i++) {
                bullet.render(g2d, new Vector2D(20, 22 * i + 20));
            }

            for (int i = 1; i <= Player.instance.life; i++) {
                health.render(g2d, new Vector2D(50, 22 * i + 20));
            }
        }
    }
}
