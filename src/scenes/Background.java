package scenes;

import Utils.Utils;
import bases.GameObject;
import bases.GameObjectPool;
import bases.Vector2D;
import bases.renderers.Animation;
import players.AnimationPlayer;
import players.Player;

public class Background extends GameObject{
    public Background(){
        super();
        renderer = new Animation(999,true,
                Utils.loadImage("assets/image/New folder/background.png")
        );
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        position = Player.instance.position;
    }
}
