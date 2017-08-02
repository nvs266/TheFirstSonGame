package bases.actions;

import bases.GameObject;

import java.util.Arrays;
import java.util.List;

public class SequenceAction implements Action {

    private List<Action> actions;
    private int currentActionIndex;

    public SequenceAction(Action ...actions) {
        this.actions = Arrays.asList(actions);
        currentActionIndex = 0;
    }

    @Override
    public boolean run(GameObject gameObject) {
        if (currentActionIndex >= actions.size()) {
            return true;
        }

        Action currentAction = actions.get(currentActionIndex);
        if (currentAction.run(gameObject)) {
            currentActionIndex++;
        }
        return false;
    }

    @Override
    public void reset() {
        currentActionIndex = 0;
        for (Action action : actions) {
            action.reset();
        }
    }
}
