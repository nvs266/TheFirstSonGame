package bases.actions;

import bases.FrameCounter;
import bases.GameObject;

public class WaitAction implements Action {

    private FrameCounter frameCounter;

    public WaitAction(int countMax) {
        frameCounter = new FrameCounter(countMax);
    }

    @Override
    public boolean run(GameObject gameObject) {
        return frameCounter.run();
    }

    @Override
    public void reset() {
        frameCounter.reset();
    }
}
