package scenes;

import bases.GameObject;

import java.io.IOException;

public abstract class Scene {
    public abstract void init() throws IOException;
    public void deInit() {
        GameObject.clear();
    }
}
