import bases.GameObject;
import bases.GameObjectPool;
import bases.Setting;
import enemies.RedBat;
import inputs.InputManager;
import players.Player;
import scenes.Brick;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by cuonghx2709 on 7/31/2017.
 */
public class GameWindow extends JFrame implements Setting{

    BufferedImage buffBackground;
    Graphics2D buffBackgroundGraphics2d;
    InputManager inputManager = new InputManager();

    public GameWindow() throws IOException {
        setUpgameWindow();
        setupInputs();
        loadMap();
//        addPlayer();
        this.setVisible(true);
    }

    private void loadMap() throws IOException {
        FileInputStream fis = new FileInputStream("assets/map/map.txt");

        for (int i = 0; i < 28; i++) {
            for (int j = 0; j < 12; j++) {
                int c = fis.read();
                char f = (char) c;
                System.out.print(f);
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
        RedBat redBat = GameObjectPool.recycle(RedBat.class);
        redBat.position.set(x * 32 + 16, y * redBat.renderer.getHeight() + redBat.renderer.getHeight() / 2);
//        System.out.println(redBat.screenPosition);
    }

    private void addBrick(int x, int y) {
        Brick brick = GameObjectPool.recycle(Brick.class);
        brick.position.set(x * brick.renderer.getWidth() + brick.renderer.getWidth() / 2, y * brick.renderer.getHeight());
    }

    private void addPlayer() {
        Player player = GameObjectPool.recycle(Player.class);
        player.setInputManager(this.inputManager);
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
