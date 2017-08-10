import bases.GameObject;
import bases.GameObjectPool;
import bases.Setting;
import enemies.*;
import enemies.boss.Boss;
import inputs.InputManager;
import platforms.*;
import players.Player;
import scenes.Background;
import scenes.Camera;
import scenes.Map;

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
    Map map;

    public GameWindow() throws IOException {
        setUpgameWindow();
        setupInputs();
        loadMap();
        addPlayer();
        this.setVisible(true);
    }

    private void loadMap() throws IOException {
        map = new Map(30, 280, "assets/map/map4.txt");
    }

    private void addPlayer() {
        Player player = GameObjectPool.recycle(Player.class);
        Camera.instance.setFollowGameObject(player);
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
        map.readMap(Player.instance);
        GameObject.removeAll();
    }
}
