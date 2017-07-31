import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by cuonghx2709 on 7/31/2017.
 */
public class GameWindow extends JFrame{
    public GameWindow(){
        this.setResizable(false);
        this.setSize(600,600);
        this.setLocation(100, 50);
        this.setTitle("the first son");
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setVisible(true);
    }
}
