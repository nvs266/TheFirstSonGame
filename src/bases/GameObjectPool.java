package bases;

import java.util.Vector;

/**
 * Created by cuonghx2709 on 7/31/2017.
 */
public class GameObjectPool  {
    private static Vector<GameObject> pool = new Vector<>();

    public void add(GameObject gameObject){
        pool.add(gameObject);
    }

    public static<T extends  GameObject> T recycle(Class<T> classz){
        for (GameObject gameObject : pool){
            if ( (gameObject.getClass() == classz) && (!gameObject.isActive())){
                gameObject.refresh();
                return (T) gameObject;
            }
        }
        try {
            T gameObject = classz.newInstance();
            GameObject.add(gameObject);
            pool.add(gameObject);
            return gameObject;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void clear() {
        pool.clear();
    }
}
