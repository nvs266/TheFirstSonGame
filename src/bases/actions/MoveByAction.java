package bases.actions;

import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;

import java.util.Vector;

public class MoveByAction implements Action {

    private Vector2D velocity;
    private FrameCounter frameCounter;

    public MoveByAction(Vector2D velocity, int countMax) {
        this.velocity = velocity;
        this.frameCounter = new FrameCounter(countMax);
    }

    @Override
    public boolean run(GameObject gameObject) {
        if (frameCounter.run()) {
            return true;
        }

        gameObject.position.addUp(velocity);
        return false;
    }

    @Override
    public void reset() {
        frameCounter.reset();
    }
}
