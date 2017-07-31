import bases.Setting;
import scenes.Background;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import static sun.misc.PostVMInitHook.run;

/**
 * Created by cuonghx2709 on 7/31/2017.
 */
public class GameWindow extends JFrame implements Setting{
    BufferedImage backBufferedImage;
    Background background;

    public GameWindow(){
        setupWindow();
        background = new Background();
    }

    private void setupWindow() {
        this.setResizable(false);
        this.setSize(600,600);
        this.setLocation(400, 50);
        this.setTitle("The First Son made by SHC");
        this.getContentPane().setBackground(Color.black);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setVisible(true);
    }

    public void gameLoop() {
        while (true) {
            try {
                Thread.sleep(17);
                run();
                render();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void run() {

    }

    private void render() {
    }
}
