import Utils.Utils;
import bases.GameObject;
import bases.Setting;
import bases.renderers.ImageRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

/**
 * Created by cuonghx2709 on 7/31/2017.
 */
public class GameWindow extends JFrame implements Setting{

    BufferedImage buffBackground;
    Graphics2D buffBackgroundGraphics2d;

    public GameWindow(){
        setUpgameWindow();
        GameObject gameObject = new GameObject();
        gameObject.renderer = new ImageRenderer(Utils.Loadimage("assets/image/enemy/enemy1/0.png"));
        gameObject.position.set(200,200);
        gameObject.add(gameObject);

        this.setVisible(true);
    }

    private void setUpgameWindow() {
        this.setResizable(false);
        this.setSize(widthScreen,heightScreen);
        this.setTitle("the first son");
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        buffBackground = new BufferedImage(widthScreen, heightScreen, BufferedImage.TYPE_INT_ARGB);
        buffBackgroundGraphics2d = (Graphics2D) buffBackground.getGraphics();
    }

    long lastUpdateTime = -1;
    public void loop(){
        while (true) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastUpdateTime > Setting.Delay){
                run();
                render();
            }
        }
    }

    private void render() {
        buffBackgroundGraphics2d.setColor(Color.BLACK);
        buffBackgroundGraphics2d.fillRect(0,0,widthScreen, heightScreen);

        GameObject.renderAll(buffBackgroundGraphics2d);

        Graphics2D graphics2D = (Graphics2D) this.getGraphics();
        graphics2D.drawImage(buffBackground, 0, 0, null);
    }

    private void run(){
        GameObject.runAll();
    }
}
