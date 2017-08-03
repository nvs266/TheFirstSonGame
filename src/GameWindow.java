import bases.GameObject;
import bases.GameObjectPool;
import bases.Setting;
import enemies.BatEnemy;
import inputs.InputManager;
import players.Player;
import platforms.Brick;

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
        FileInputStream fis = new FileInputStream("assets/map/map.txt");

        for (int i = 0; i < 28 * 2; i++) {
            for (int j = 0; j < 12; j++) {
                int c = fis.read();
                if (c == ' ') j--;

                switch(c) {
                    case '1':
                        addBrick(j,i);
                        break;
                    case '2':
                        addRedBat(j,i);
                        break;
                    default:
                        break;
                }
            }
            System.out.println();
        }
        fis.close();
    }

    private void addRedBat(int x, int y) {
        BatEnemy redBat = GameObjectPool.recycle(BatEnemy.class);
        redBat.position.set(x * redBat.renderer.getWidth() + 16, y * redBat.renderer.getHeight() + 30);
//        System.out.println(redBat.screenPosition);
    }

    private void addBrick(int x, int y) {
        Brick brick = GameObjectPool.recycle(Brick.class);
        brick.position.set(x * brick.renderer.getWidth() + 16, y * brick.renderer.getHeight() + 30);
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
    }
}
