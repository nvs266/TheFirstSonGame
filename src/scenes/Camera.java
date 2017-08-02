package scenes;

import bases.GameObject;
import bases.Setting;
import bases.Vector2D;
import players.Player;

public class Camera implements Setting{
    private Vector2D position;

    public static Camera instance = new Camera();

    public Camera(Vector2D position) {
        this.position = position;
        instance = this;
    }

    public Camera() {
        this(new Vector2D());
    }


    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(GameObject gameObject) {
        if (gameObject.position.y - HEIGHT_SCREEN / 2 > this.position.y) {
            this.position.y = gameObject.position.y -  HEIGHT_SCREEN / 2;
        }
    }
}
