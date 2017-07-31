import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.security.PublicKey;

/**
 * Created by cuonghx2709 on 7/31/2017.
 */
public class GameWindow extends JFrame{
    public GameWindow(){
        this.setSize(800,600);
        this.setResizable(false);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setVisible(true);
    }
}
