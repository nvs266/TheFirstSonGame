import bases.GameObject;
import bases.Setting;
import enemies.boss.Boss;
import inputs.InputManager;
import javafx.scene.media.MediaPlayer;
import players.Player;
import players.bullets.ThreeRayBullet;
import scenes.*;
import scenes.Icon;
import tklibs.AudioUtils;


import javax.swing.*;
import javax.xml.validation.SchemaFactoryConfigurationError;
import java.awt.*;
import java.awt.Color;
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
    MediaPlayer mediaPlayer;
    BufferedImage buffBackground;
    BufferedImage buffBackgroundLeft;
    BufferedImage buffBackgroundRight;
    Graphics2D buffBackgroundGraphics2d;
    Graphics2D buffBackgroundGraphics2dLeft;
    Graphics2D buffBackgroundGraphics2dRight;

    InputManager inputManager = InputManager.instance;
    Scene introScene;
    public boolean pause;
    MenuScene shoppingScene;

    public GameWindow() throws IOException {
        setUpgameWindow();
        setupInputs();
        setUpStartupScene();
        this.setVisible(true);
        shoppingScene = new MenuScene("Three Rays - 10 Nipples", "Health - 10 Nipples", "Extra Bullet - 20 Nipples","Superman - 30 Nipples");
    }

    private void setUpStartupScene() throws IOException {
        introScene = new IntroScene();
        introScene.init();
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
        this.setSize(WIDTH_SCREEN * 3, HEIGHT_SCREEN);
        this.setLocation(100, 50);
        this.setTitle("the first son");
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        buffBackground = new BufferedImage(WIDTH_SCREEN, HEIGHT_SCREEN, BufferedImage.TYPE_INT_ARGB);
        buffBackgroundLeft = new BufferedImage(WIDTH_SCREEN, HEIGHT_SCREEN, BufferedImage.TYPE_INT_ARGB);
        buffBackgroundRight = new BufferedImage(WIDTH_SCREEN, HEIGHT_SCREEN, BufferedImage.TYPE_INT_ARGB);

        buffBackgroundGraphics2d = (Graphics2D) buffBackground.getGraphics();
        buffBackgroundGraphics2dLeft = (Graphics2D) buffBackgroundLeft.getGraphics();
        buffBackgroundGraphics2dRight = (Graphics2D) buffBackgroundRight.getGraphics();
    }

    long lastUpdateTime = -1;
    public void loop() throws IOException {
        while (true) {
            {
                long currentTime = System.nanoTime();
                if (currentTime - lastUpdateTime > Setting.DELAY){
                    lastUpdateTime = System.nanoTime();
                    run();
                    render();
                }
            }
        }
    }

    private void render() throws IOException {
        buffBackgroundGraphics2d.setColor(Color.BLACK);
        buffBackgroundGraphics2d.fillRect(0,0, WIDTH_SCREEN, HEIGHT_SCREEN);
        buffBackgroundGraphics2dLeft.setColor(Color.BLACK);
        buffBackgroundGraphics2dLeft.fillRect(0,0, WIDTH_SCREEN, HEIGHT_SCREEN);
        buffBackgroundGraphics2dRight.setColor(Color.BLACK);
        buffBackgroundGraphics2dRight.fillRect(0,0, WIDTH_SCREEN, HEIGHT_SCREEN);

        if (Scene.background != null) {
            buffBackgroundGraphics2d.drawImage(Scene.background, 0, 0, null);
            buffBackgroundGraphics2dLeft.drawImage(Scene.background, 0, 0, null);
            buffBackgroundGraphics2dRight.drawImage(Scene.background, 0, 0, null);
//            if (Boss.instance.position.x >8000){
//                buffBackgroundGraphics2dRight.setColor(Color.GREEN);
//                buffBackgroundGraphics2dRight.drawString(String.format("%s", Boss.instance.hp),100,100);
//            }
        }
        if (SceneManager.instance != null && SceneManager.instance.getCurrentScene() == null) {
            introScene.render(buffBackgroundGraphics2d);
        }

        if (SceneManager.instance != null && SceneManager.instance.getCurrentScene() != null && SceneManager.instance.getCurrentScene().getClass() == Victory.class) {
            SceneManager.instance.getCurrentScene().render(buffBackgroundGraphics2d);
        }

        if (!pause) {
            GameObject.renderAll(buffBackgroundGraphics2d);
        } else {
            buffBackgroundGraphics2d.drawImage(Scene.background, 0, 0, null);
            shoppingScene.render(buffBackgroundGraphics2d);
        }
        Graphics2D graphics2D = (Graphics2D) this.getGraphics();
        graphics2D.drawImage(buffBackground, WIDTH_SCREEN, 0, null);

        if (Icon.instance != null) {
            Icon.instance.render(buffBackgroundGraphics2dLeft);
        }
        graphics2D.drawImage(buffBackgroundLeft, 0, 0, null);

        if (Player.instance != null && Player.instance.totalNipple >= 10) {
            buffBackgroundGraphics2dRight.setColor(Color.RED);
            buffBackgroundGraphics2dRight.setFont(new Font("serif", Font.BOLD, 20));
            buffBackgroundGraphics2dRight.drawString("Press Escape To Shop", 30, 150 );
            buffBackgroundGraphics2dRight.drawString("Press Enter To Buy and Resume game", 30, 170 );
        }

        graphics2D.drawImage(buffBackgroundRight, WIDTH_SCREEN * 2, 0, null);
    }

    private void run() throws IOException {
       if (inputManager.escapePressed) {
           pause = true;
           if (Level1Scenes.lv1Audio != null) {
               Level1Scenes.lv1Audio.pause();
           }
       }

       if (pause && inputManager.enterPressed && Player.instance != null) {
           switch (shoppingScene.currentIndex) {
               case 0:
                   if (Player.instance.totalNipple >= 10 && Player.instance.bulletSprite.getClass() != ThreeRayBullet.class) {
                       Player.instance.bulletSprite = new ThreeRayBullet();
                        Player.instance.totalNipple -= 10;
                   }
                   break;
               case 1:
                   if (Player.instance.totalNipple >= 10 && Player.instance.life < 4) {
                       Player.instance.life++;
                       Player.instance.totalNipple -= 10;
                   }
                   break;
               case 2:
                   if (Player.instance.totalNipple >= 20) {
                       Player.instance.totalBullets += 3;
                       Player.instance.totalNipple -= 20;
                   }
                   break;
               case 3:
                   if (!Player.instance.hero && Player.instance.totalNipple >= 30) {
                       Player.instance.hero = true;
                       Player.instance.totalNipple -= 30;
                   }
                   break;
               default:
                   break;
           }
           pause = false;
           if (Level1Scenes.lv1Audio != null) {
               Level1Scenes.lv1Audio.play();
           }
       }

        if (!pause){
           GameObject.runAll();
           GameObject.runAllAction();
           GameObject.removeAll();
        }
        SceneManager.instance.changeSceneIfNeeded();

    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }
}
