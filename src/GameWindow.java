import bases.GameObject;
import bases.Setting;
import inputs.InputManager;
import javafx.scene.media.MediaPlayer;
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

    public GameWindow() throws IOException {
        setUpgameWindow();
        setAudio();
        setupInputs();
        setUpStartupScene();
        this.setVisible(true);
    }

    private void setAudio() {
        AudioUtils.initialize();
        mediaPlayer = AudioUtils.playMedia("assets/music/gameplay/soundtrack.mp3");
        mediaPlayer.setVolume(0.1d);
        mediaPlayer.play();
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
        }
        if (SceneManager.instance != null && SceneManager.instance.getCurrentScene() == null) {
            introScene.render(buffBackgroundGraphics2d);
        }

        if (SceneManager.instance != null && SceneManager.instance.getCurrentScene() != null && SceneManager.instance.getCurrentScene().getClass() == Victory.class) {
            SceneManager.instance.getCurrentScene().render(buffBackgroundGraphics2d);
        }

        GameObject.renderAll(buffBackgroundGraphics2d);
        Graphics2D graphics2D = (Graphics2D) this.getGraphics();
        graphics2D.drawImage(buffBackground, WIDTH_SCREEN, 0, null);

        if (Icon.instance != null) {
            Icon.instance.render(buffBackgroundGraphics2dLeft);
        }
        graphics2D.drawImage(buffBackgroundLeft, 0, 0, null);

        graphics2D.drawImage(buffBackgroundRight, WIDTH_SCREEN * 2, 0, null);
    }

    private void run() throws IOException {
        if (inputManager.xPressed){
            pause = true;
        }
        if (inputManager.pPressed){
            pause = false;
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
