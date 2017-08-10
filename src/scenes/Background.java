package scenes;

import Utils.Utils;
import bases.GameObject;
import bases.GameObjectPool;
import bases.Vector2D;
import bases.renderers.Animation;
import players.AnimationPlayer;
import players.Player;

import java.awt.*;

public class Background extends GameObject{
    public Background(){
        super();
        this.position = new Vector2D();
        renderer = new Animation(
                Utils.loadImage("assets/image/New folder/background.png")
        );
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.drawImage(Utils.loadImage("assets/image/New folder/background.png"), 0, 0, null);
    }
}
