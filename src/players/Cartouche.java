package players;

import Utils.Utils;
import bases.GameObject;
import bases.Vector2D;
import bases.actions.*;
import bases.renderers.Animation;

public class Cartouche extends GameObject {

    public Cartouche() {
        renderer = new Animation(Utils.loadImage("assets/image/New folder/1.png"));
    }

    public void setCartouche(Vector2D position) {
        this.position = position.clone();
        for (Action action: actions) {
            action.reset();
        }
        Action action = new SequenceAction(
                new RepeatnAction(
                        20,
                        new MoveByAction(new Vector2D(1, -1f), 1)
                ),
                new RepeatnAction(
                        30,
                        new MoveByAction(new Vector2D(1, 1),1)
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
