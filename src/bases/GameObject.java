package bases;

import bases.renderers.Renderer;

import java.awt.*;
import java.util.Vector;

public class GameObject {
    public Vector2D position;
    public Vector2D screenPosition;

    private static Vector<GameObject> gameObjects = new Vector<>();
    private static Vector<GameObject> newGameObject = new Vector<>();
    private Vector<GameObject> children ;

    private boolean isActive;
    public Renderer renderer;

    public GameObject(){
        this.position = new Vector2D();
        this.screenPosition = new Vector2D();
        this.isActive = true;
        this.children = new Vector<>();
        this.renderer = null;
    }
    public static void  add(GameObject gameObject){
        newGameObject.add(gameObject);
    }
    public void render(Graphics2D g2d){
        if (renderer != null){
            renderer.render(g2d, screenPosition);
        }
    }
    public static void runAll(){
        for (GameObject gameObject : gameObjects){
            if (gameObject.isActive){
                gameObject.run(Vector2D.ZERO);
            }
        }
        gameObjects.addAll(newGameObject);
        newGameObject.clear();
    }
    public void run(Vector2D parentPosition){
        screenPosition = parentPosition.add(position);
        for (GameObject child : children){
            child.run(screenPosition);
        }

    }
    public static void renderAll(Graphics2D g2d){
        for (GameObject gameObject : gameObjects){
            if (gameObject.isActive){
                gameObject.render(g2d);
            }
        }
    }
    public void refresh(){
        this.isActive = true;
    }
    public boolean isActive(){
        return isActive;
    }
}
