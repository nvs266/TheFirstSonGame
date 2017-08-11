package scenes;

import bases.GameObject;
import bases.Setting;
import bases.Vector2D;
import enemies.boss.Boss;
import players.Player;

public class Camera implements Setting{
    public Vector2D position;
    private boolean isLock;
    private int count = 0;
    private int coutMax = 0;
    private GameObject followGameObject;

    public static Camera instance = new Camera();

    public Camera(Vector2D position) {
        this.position = position;
        instance = this;
    }

    public Camera() {
        this(new Vector2D());
    }

    public void setFollowGameObject(GameObject followGameObject) {
        this.followGameObject = followGameObject;
    }


    public Vector2D getPosition() {
        return position;
    }

    public void setPosition() {
        if (followGameObject.getClass() == Player.class) {
            if (followGameObject.position.x < 250 || followGameObject.position.x > 610){
                this.position.y = followGameObject.position.y -  HEIGHT_SCREEN / 2;
                this.position.x = followGameObject.position.x -  WIDTH_SCREEN / 2;
            }
            else {
                if (this.position.x <= 240) this.position.x+= 3;
                else if (this.position.x >= 256) this.position.x--;
                this.position.y = followGameObject.position.y -  HEIGHT_SCREEN / 2;
            }
        } else if (followGameObject.getClass() == Boss.class) {
            this.position.x = 255;
            this.position.y = followGameObject.position.y - HEIGHT_SCREEN / 2 ;
        }

    }

    public Vector2D posInCamera(GameObject gameObject,Vector2D position) {
        if (followGameObject.getClass() != gameObject.getClass()) {
            return position.substract(this.position);
        } else {
            if (followGameObject.getClass() == Player.class) {
                return position.substract(this.position).add(Player.velocity);
            } else {
                return position.substract(this.position).add(0, gameObject.renderer.getHeight() / 2 + 50);
            }
        }
    }

    public GameObject getFollowGameObject() {
        return followGameObject;
    }
}
