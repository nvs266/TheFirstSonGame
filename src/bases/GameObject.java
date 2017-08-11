package bases;

import bases.actions.Action;
import bases.renderers.Renderer;
import physics.BoxCollider;
import physics.Physics;
import physics.PhysicsBody;
import players.Player;
import scenes.*;

import java.awt.*;
import java.util.*;

public class GameObject {
    public Vector2D position;
    public Vector2D screenPosition;

    private static Vector<GameObject> gameObjects = new Vector<>();
    private static Vector<GameObject> newGameObject = new Vector<>();
    private static Vector<GameObject> gameObjectsRemove = new Vector<>();
    public Vector<Action> actions;
    private java.util.List<Action> newAction;
    public Vector<GameObject> children ;
    private boolean isActive;
    public Renderer renderer;
    private static boolean lost;

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

    public static void add(GameObject gameObject){
        newGameObject.add(gameObject);
        if (gameObject instanceof PhysicsBody){
            Physics.add((PhysicsBody) gameObject);
        }
    }
    public void removeAction(Action action){
        actions.remove(action);
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

    public void render(Graphics2D g2d){
        if (renderer != null){
            if (camera.getFollowGameObject() != null) {
                renderer.render(g2d, camera.posInCamera(this ,screenPosition));
                        g2d.setColor(Color.RED);
                if (boxCollider != null) {
                    Vector2D newVetor = camera.posInCamera(this, screenPosition);
                    g2d.drawRect((int) (newVetor.x - boxCollider.width *0.5), (int) (newVetor.y - boxCollider.height * 0.5), (int) boxCollider.width, (int) boxCollider.height);
                }
            } else renderer.render(g2d, screenPosition);
        }
    }
    public static long start;
    public static long end;
    public static void renderAll(Graphics2D g2d){
        for (GameObject gameObject : gameObjects){
            if (gameObject.isActive){
                gameObject.render(g2d);
            }
        }


        Scene scene = SceneManager.instance.getCurrentScene();

        if (scene != null && scene.getClass() != IntroScene.class && Player.instance != null) {
            end = System.nanoTime();
            if (end - start >0) {
                g2d.drawString("fps: " + 1000000000/(end - start), 50, 60);
            }
            start = System.nanoTime();

            if (Player.instance.immortal) {
                g2d.setColor(Color.RED);
                g2d.setFont(new Font("serif", Font.BOLD, 20));
                g2d.drawString("-1", Player.instance.position.x - Camera.instance.getPosition().x + 20, Player.instance.position.y - Camera.instance.getPosition().y - 20);
            }
            if (Player.instance.life == 0) {
                lost = true;
            }
            if (lost){
                Player.instance.setActive(false);
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.05f));
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font("serif", Font.BOLD, 40));
                g2d.drawString("LOST", 100, 300 );
            }

        }

    }

    public void remove() {
        gameObjectsRemove.add(this);
        for (GameObject chil: this.children) {
            chil.remove();
        }
    }

    public static void removeAll() {
        Scene scene = SceneManager.instance.getCurrentScene();
        if (scene == null || scene.getClass() == IntroScene.class) return;

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

    public static void clear() {
        gameObjects.clear();
        GameObjectPool.clear();
        Physics.clear();
    }
}
