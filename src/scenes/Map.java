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
                BrickItem brickItem = new BrickItem();
                brickItem.position.set(j * WIDTH_GRID, i * HEIGHT_GRID);
                GameObject.add(brickItem);
                break;
            case '1':
                BrickGrey brickGrey = new BrickGrey();
                brickGrey.position.set(j * WIDTH_GRID, i * HEIGHT_GRID);
                GameObject.add(brickGrey);
                break;
            case 'C':
                Platform platform = new Platform();
                platform.position.set(j * WIDTH_GRID, i * HEIGHT_GRID);
                GameObject.add(platform);
                break;
            case 'A':
                DirtGlass dirtGlass = new DirtGlass();
                dirtGlass.position.set(j * WIDTH_GRID, i * HEIGHT_GRID);
                GameObject.add(dirtGlass);
                break;
            case '4':
                BatEnemy batEnemy = new BatEnemy();
                batEnemy.position.set(j * WIDTH_GRID, i * HEIGHT_GRID);
                GameObject.add(batEnemy);
                break;
            case '5':
                GumEnemy gumEnemy = new GumEnemy();
                gumEnemy.position.set(j * WIDTH_GRID, i * HEIGHT_GRID);
                GameObject.add(gumEnemy);
                break;
            case '6':
                SnakeEnemy snakeEnemy = new SnakeEnemy();
                snakeEnemy.position.set(j * WIDTH_GRID, i * HEIGHT_GRID);
                GameObject.add(snakeEnemy);
                break;
            case '7':
                GhostEnemy ghostEnemy = new GhostEnemy();
                ghostEnemy.position.set(j * WIDTH_GRID, i * HEIGHT_GRID);
                GameObject.add(ghostEnemy);
                break;
            case '8':
                FrogEnemy frogEnemy = new FrogEnemy();
                frogEnemy.position.set(j * WIDTH_GRID, i * HEIGHT_GRID);
                GameObject.add(frogEnemy);
                break;
            case 'D':
                SpikeStick spikeStick = new SpikeStick();
                spikeStick.position.set(j * WIDTH_GRID, i * HEIGHT_GRID);
                GameObject.add(spikeStick);
                break;
            case '9':
                Item item = GameObjectPool.recycle(Item.class);
                item.position.set(j * WIDTH_GRID, i * HEIGHT_GRID);
                break;
            default:
                break;
        }
    }
}
