package bases;

import bases.renderers.Renderer;
import physics.BoxCollider;
import physics.Physics;
import physics.PhysicsBody;
import players.Player;
import scenes.Camera;

import java.awt.*;
import java.util.Vector;

public class GameObject {
    public Vector2D position;
    public Vector2D screenPosition;

    private static Vector<GameObject> gameObjects = new Vector<>();
    private static Vector<GameObject> newGameObject = new Vector<>();
    public Vector<GameObject> children ;

    private boolean isActive;
    public Renderer renderer;

    public static Camera camera = new Camera();

    public BoxCollider boxCollider;
    public GameObject(){
        this.position = new Vector2D();
        this.screenPosition = new Vector2D();
        this.isActive = true;
        this.children = new Vector<>();
        this.renderer = null;
    }
    public static void  add(GameObject gameObject){
        newGameObject.add(gameObject);
        if (gameObject instanceof PhysicsBody){
            Physics.add((PhysicsBody) gameObject);
        }
    }

    public static void runAll(){
        for (GameObject gameObject : gameObjects){
            if (gameObject.isActive){
                gameObject.run(Vector2D.ZERO);
                if (gameObject.getClass() == Player.class) {
                    camera.setPosition(gameObject);
                }
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

    public void render(Graphics2D g2d){
        if (renderer != null && this.getClass() != Player.class){
            renderer.render(g2d, screenPosition.substract(camera.getPosition()));
        } else if (renderer != null) {
            renderer.render(g2d, screenPosition.substract(camera.getPosition()).add(Player.velocity));
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

    public void setActive(boolean active) {
        isActive = active;
    }
}
