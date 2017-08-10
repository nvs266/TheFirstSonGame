package scenes;

import bases.GameObject;
import bases.GameObjectPool;
import bases.Setting;
import enemies.*;
import platforms.*;

import java.io.*;

public class Map implements Setting{
    private char map[][];
    private int width;
    private int height;
    private int currentHeight;
    private GameObject followGameObject;
    public static Map instance;

    public Map(int width, int height, String fileName) throws IOException {
        this.width = width;
        this.height = height;
        map = new char[width][height];

        currentHeight = 0;

        FileReader fr = new FileReader(new File(fileName));
        BufferedReader br = new BufferedReader(fr);

        for (int i = 0; i < height; i++) {
            String line = br.readLine();
            int k = 0;
            int j = 0;
            while (k != line.length()) {
                char c = line.charAt(k);
                k++;
                if (c != ',') {
                    map[j][i] = c;
                    j++;
                }
            }
        }

        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < width; j++) {
                addObject(map[j][i], i, j);
            }
        }
        currentHeight = 29;
        instance = this;
    }

    public void setFollowGameObject(GameObject gameObject) {
        this.followGameObject = gameObject;
    }

    public GameObject getFollowGameObject() {
        return followGameObject;
    }

    public void printMap() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(map[j][i]);
            }
            System.out.println();
        }
    }

    public void readMap(GameObject gameObject) {
        int posHeightObject = (int) gameObject.position.y / HEIGHT_GRID;
        while (currentHeight - posHeightObject < 30) {
            for (int j = 0; j < this.width; j++) {
                if (currentHeight < height) {
                    addObject(map[j][currentHeight], currentHeight, j);
                }
            }
            currentHeight++;
            if (currentHeight >= height) break;
        }
    }

    private void addObject(char c, int i, int j) {
        switch(c) {
            case '3':
                BrickItem brickItem = GameObjectPool.recycle(BrickItem.class);
                brickItem.position.set(j * WIDTH_GRID, i * HEIGHT_GRID);
                break;
            case '1':
                BrickGrey brickGrey = GameObjectPool.recycle(BrickGrey.class);
                brickGrey.position.set(j * WIDTH_GRID, i * HEIGHT_GRID);
                break;
            case 'C':
                Platform platform = GameObjectPool.recycle(Platform.class);
                platform.position.set(j * WIDTH_GRID, i * HEIGHT_GRID);
                break;
            case 'A':
                DirtGlass dirtGlass = GameObjectPool.recycle(DirtGlass.class);
                dirtGlass.position.set(j * WIDTH_GRID, i * HEIGHT_GRID);
                break;
            case '4':
                BatEnemy batEnemy = GameObjectPool.recycle(BatEnemy.class);
                batEnemy.position.set(j * WIDTH_GRID, i * HEIGHT_GRID);
                break;
            case '5':
                GumEnemy gumEnemy = GameObjectPool.recycle(GumEnemy.class);
                gumEnemy.position.set(j * WIDTH_GRID, i * HEIGHT_GRID);
                break;
            case '6':
                SnakeEnemy snakeEnemy = GameObjectPool.recycle(SnakeEnemy.class);
                snakeEnemy.position.set(j * WIDTH_GRID, i * HEIGHT_GRID - 32);
                break;
            case '7':
                GhostEnemy ghostEnemy = GameObjectPool.recycle(GhostEnemy.class);
                ghostEnemy.position.set(j * WIDTH_GRID, i * HEIGHT_GRID);
                break;
            case '8':
                FrogEnemy frogEnemy = GameObjectPool.recycle(FrogEnemy.class);
                frogEnemy.position.set(j * WIDTH_GRID, i * HEIGHT_GRID);
                break;
            case 'D':
                SpikeStick spikeStick = GameObjectPool.recycle(SpikeStick.class);
                spikeStick.position.set(j * WIDTH_GRID, i * HEIGHT_GRID);
                break;
            case '9':
                Thunder thunder = GameObjectPool.recycle(Thunder.class);
                thunder.position.set(j * WIDTH_GRID, i * HEIGHT_GRID - 32);
                break;
            default:
                break;
        }
    }
}
