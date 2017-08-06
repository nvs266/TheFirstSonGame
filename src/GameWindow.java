import bases.GameObject;
import bases.GameObjectPool;
import bases.Setting;
import enemies.*;
import inputs.InputManager;
import platforms.*;
import players.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by cuonghx2709 on 7/31/2017.
 */
public class GameWindow extends JFrame implements Setting{

    BufferedImage buffBackground;
    Graphics2D buffBackgroundGraphics2d;
    //InputManager inputManager = new InputManager();
    InputManager inputManager = InputManager.instance;

    public GameWindow() throws IOException {
        setUpgameWindow();
        setupInputs();
        loadMap();
        addPlayer();
        this.setVisible(true);
    }

    private void loadMap() throws IOException {
        FileInputStream fis = new FileInputStream("assets/map/newmap.txt");

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 30; j++) {
                int c = fis.read();
                if (c == ',') j--;
                char f = (char) c;
                System.out.print(f);
                switch(c) {
                    case '2':
                        BrickItem brickItem = new BrickItem();
                        brickItem.position.set(j * WIDTH_GRID, i * HEIGHT_GRID);
                        GameObject.add(brickItem);
                        break;
                    case '3':
                        BrickGrey brickGrey = new BrickGrey();
                        brickGrey.position.set(j * WIDTH_GRID, i * HEIGHT_GRID);
                        GameObject.add(brickGrey);
                        break;
                    case '4':
                        Platform platform = new Platform();
                        platform.position.set(j * WIDTH_GRID, i * HEIGHT_GRID);
                        GameObject.add(platform);
                        break;
                    case '5':
                        DirtGlass dirtGlass = new DirtGlass();
                        dirtGlass.position.set(j * WIDTH_GRID, i * HEIGHT_GRID);
                        GameObject.add(dirtGlass);
                        break;
                    case '7':
                        BatEnemy batEnemy = new BatEnemy();
                        batEnemy.position.set(j * WIDTH_GRID, i * HEIGHT_GRID);
                        GameObject.add(batEnemy);
                        break;
                    case '8':
                        GumEnemy gumEnemy = new GumEnemy();
                        gumEnemy.position.set(j * WIDTH_GRID, i * HEIGHT_GRID);
                        GameObject.add(gumEnemy);
                        break;
                    case '9':
                        SnakeEnemy snakeEnemy = new SnakeEnemy();
                        snakeEnemy.position.set(j * WIDTH_GRID, i * HEIGHT_GRID);
                        GameObject.add(snakeEnemy);
                        break;
                    case 'A':
                        GhostEnemy ghostEnemy = new GhostEnemy();
                        ghostEnemy.position.set(j * WIDTH_GRID, i * HEIGHT_GRID);
                        GameObject.add(ghostEnemy);
                        break;
                    case 'B':
                        FrogEnemy frogEnemy = new FrogEnemy();
                        frogEnemy.position.set(j * WIDTH_GRID, i * HEIGHT_GRID);
                        GameObject.add(frogEnemy);
                        break;
                    case 'C':
                        SpikeStick spikeStick = new SpikeStick();
                        spikeStick.position.set(j * WIDTH_GRID, i * HEIGHT_GRID);
                        GameObject.add(spikeStick);
                        break;
                    case 'D':
                        Item item = GameObjectPool.recycle(Item.class);
                        item.position.set(j * WIDTH_GRID, i * HEIGHT_GRID);
                        break;
                    default:
                        break;
                }
            }
            System.out.println();
        }
        fis.close();
    }

    private void addPlayer() {
        Player player = GameObjectPool.recycle(Player.class);
    }

    private void setupInputs() {
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                inputManager.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                inputManager.keyRealeased(e);
            }
        });
    }

    private void setUpgameWindow() {
        this.setResizable(false);
        this.setSize(WIDTH_SCREEN, HEIGHT_SCREEN);
        this.setLocation(400, 50);
        this.setTitle("the first son");
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        buffBackground = new BufferedImage(WIDTH_SCREEN, HEIGHT_SCREEN, BufferedImage.TYPE_INT_ARGB);
        buffBackgroundGraphics2d = (Graphics2D) buffBackground.getGraphics();
    }

    long lastUpdateTime = -1;
    public void loop(){
        while (true) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastUpdateTime > Setting.DELAY){
                run();
                render();
            }
        }
    }

    private void render() {
        buffBackgroundGraphics2d.setColor(Color.BLACK);
        buffBackgroundGraphics2d.fillRect(0,0, WIDTH_SCREEN, HEIGHT_SCREEN);

        GameObject.renderAll(buffBackgroundGraphics2d);
        Graphics2D graphics2D = (Graphics2D) this.getGraphics();
        graphics2D.drawImage(buffBackground, 0, 0, null);
    }

    private void run(){
        GameObject.runAll();
        GameObject.runAllAction();
    }
}
