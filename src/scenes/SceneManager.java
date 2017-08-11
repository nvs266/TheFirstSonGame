package scenes;

import java.io.IOException;

public class SceneManager {
    private Scene currentScene;
    private Scene nextScene;

    public static final SceneManager instance = new SceneManager();

    private SceneManager() {

    }

    public void changeSceneIfNeeded() throws IOException {
        if (nextScene != null) {
            if (currentScene != null) {
                currentScene.deInit();
            }
            nextScene.init();
            currentScene = nextScene;
            nextScene = null;
        }
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public Scene getNextScene() {
        return nextScene;
    }

    public void requestChangeScene(Scene scene) {
        nextScene = scene;
    }
}
