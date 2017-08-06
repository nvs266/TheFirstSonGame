package bases.actions;

import bases.GameObject;

/**
 * Created by cuonghx2709 on 8/6/2017.
 */
public class RepeatnAction implements Action{
    private int count = 0;
    private int countMax ;
    private Action action;
    public RepeatnAction(int count, Action action){
        this.countMax = count;
        this.action = action;
    }
    @Override
    public boolean run(GameObject gameObject) {
        if (count >= countMax){
            count = 0;
            action.reset();
            return true;
        }else {
            if (action.run(gameObject)){
                action.reset();
                count--;
            }
            return false;
        }
    }

    @Override
    public void reset() {
    }
}
