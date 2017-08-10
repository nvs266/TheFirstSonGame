package scenes;

import Utils.Utils;
import bases.GameObject;
import bases.renderers.Animation;
import inputs.InputListener;
import inputs.InputManager;

public class StartingScene extends Scene implements InputListener{
    @Override
    public void init() {
        GameObject introImage = new GameObject();
        introImage.renderer = new Animation(Utils.loadImage("assets/image/newimages/clouds.png"));
        introImage.position.set(200, 400);
        GameObject.add(introImage);
        InputManager.instance.register(this);
    }

    @Override
    public boolean onKeyPressed(int keyCode) {
        return false;
    }

    @Override
    public boolean onKeyReleased(int keyCode) {
        SceneManager.instance.requestChangeScene(new Level1Scenes());
        return true;
    }
}
