import bases.Setting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by cuonghx2709 on 7/31/2017.
 */
public class GameWindow extends JFrame implements Setting{
    public GameWindow(){
        this.setSize(widthScreen, heightScreen);
        this.setTitle("the first son");
        this.setResizable(false);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.black);
        g2d.fillRect(0,0,600,800);
    }
}
