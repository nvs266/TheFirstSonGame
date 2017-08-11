package players.bullets;

import Utils.Utils;
import bases.GameObject;
import bases.Vector2D;
import bases.actions.*;
import bases.renderers.Animation;

public class Cartouche extends GameObject {

    public Cartouche() {
        renderer = new Animation(3, true,
                Utils.loadImage("assets/image/New folder/1.png"),
                Utils.loadImage("assets/image/New folder/2.png"),
                Utils.loadImage("assets/image/New folder/3.png"),
                Utils.loadImage("assets/image/New folder/4.png"),
                Utils.loadImage("assets/image/New folder/5.png"),
                Utils.loadImage("assets/image/New folder/6.png"),
                Utils.loadImage("assets/image/New folder/7.png"),
                Utils.loadImage("assets/image/New folder/8.png")
        );

    }

    public void setCartouche(Vector2D position) {
        this.position = position.clone();
        for (Action action: actions) {
            action.reset();
        }
        Action action = new SequenceAction(
                new RepeatnAction(
                        4,
                        new MoveByAction(new Vector2D(4, -4f), 1)
                ),
                new RepeatnAction(
                        6,
                        new MoveByAction(new Vector2D(4, 4),1)
                )
        );
        actions.add(action);
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        if (this.actions.size() == 0) {
            this.setActive(false);
        }
    }
}
