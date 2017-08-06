package players;

import bases.GameObject;
import bases.actions.Action;
import bases.renderers.Renderer;

/**
 * Created by cuonghx2709 on 8/6/2017.
 */
public class PlayerAction implements Action {
    int sw = 0;
    Renderer currentRender;
    @Override
    public boolean run(GameObject gameObject) {
        if (sw ==0 ){
            sw = 1;
            currentRender = gameObject.renderer;
            gameObject.renderer = null;
        }else {
            sw = 0;
            gameObject.renderer = currentRender;
        }
        return true;
    }

    @Override
    public void reset() {

    }
}
