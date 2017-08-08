package bases;

import bases.actions.Action;
import bases.renderers.Renderer;
import physics.BoxCollider;
import physics.Physics;
import physics.PhysicsBody;
import players.Player;
import players.Trail;
import scenes.Camera;

import java.awt.*;
import java.util.*;

public class GameObject {
    public Vector2D position;
    public Vector2D screenPosition;

    private static Vector<GameObject> gameObjects = new Vector<>();
    private static Vector<GameObject> newGameObject = new Vector<>();
    private static Vector<GameObject> gameObjectsRemove = new Vector<>();
    private Vector<Action> actions;
    private java.util.List<Action> newAction;
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
        this.actions = new Vector<>();
        this.newAction = new Vector<>();
    }
    public void runAction(){
        Iterator<Action> iterator = actions.iterator();
        while (iterator.hasNext()){
            Action action = iterator.next();
            if (action.run(this)){
                iterator.remove();
            }
        }
        this.actions.addAll(this.newAction);
        newAction.clear();
    }
    public static void runAllAction(){
        for (GameObject gameObject : gameObjects){
            if (gameObject.isActive()){
                gameObject.runAction();
            }
        }

    }

    public void addAction(Action action){
        newAction.add(action);
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
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("serif", Font.BOLD, 10));
        g2d.drawString("LIFE: " + Player.instance.life, 50, 50 );
        if (Player.instance.life == 0) {
            Player.instance.setActive(false);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.05f));
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("serif", Font.BOLD, 40));
            g2d.drawString("LOOSED", 100, 300 );
        }
    }

    public void remove() {
        gameObjectsRemove.add(this);
        for (GameObject chil: this.children) {
            chil.remove();
        }
    }

    public static void removeAll() {
        for (GameObject gameObject: gameObjects) {
            if (gameObject.isActive && Player.instance.position.y - gameObject.position.y > 500) {
                gameObject.remove();
            }
        }
        gameObjects.removeAll(gameObjectsRemove);
        gameObjectsRemove.clear();
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
