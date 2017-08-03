package scenes;

import bases.GameObject;
import bases.Setting;
import bases.Vector2D;
import players.Player;
import tklibs.Mathx;

public class Camera implements Setting{
    private Vector2D position;
    private boolean isLock;
    private int count = 0;
    private int coutMax = 0;

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
//        if (gameObject.position.y - HEIGHT_SCREEN / 2 > this.position.y) {
//        }


        if (gameObject.position.x < 250 || gameObject.position.x > 610){
            this.position.y = gameObject.position.y -  HEIGHT_SCREEN / 2;
            this.position.x = gameObject.position.x -  WIDTH_SCREEN / 2;
        }
        else {
            if (this.position.x <= 254) this.position.x++;
            else if (this.position.x >= 256) this.position.x--;
            this.position.y = gameObject.position.y -  HEIGHT_SCREEN / 2;
        }

//        if (gameObject.position.x > 250 && !isLock && gameObject.position.x < 610){
//            position.x += count++;
//            if (position.x > 255 ){
//                this.position.x = 255;
//                coutMax = count;
//                count = 0;
//                isLock = true;
//            }
//        }else if (gameObject.position.x < 250 && isLock){
//            if (position.x - count >= gameObject.position.x -  WIDTH_SCREEN / 2){
//                position.x -= count++;
//            }else {
//                count = 0;
//                isLock = false;
//            }
//        } else if (gameObject.position.x > 610 && isLock) {
//            isLock = false;
//            this.position.y = gameObject.position.y -  HEIGHT_SCREEN / 2;
//            this.position.x = gameObject.position.x -  WIDTH_SCREEN / 2;
//        }

    }

}
