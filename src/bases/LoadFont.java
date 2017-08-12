package bases;


import javafx.scene.text.*;

import java.awt.*;

import java.awt.Font;
import java.io.IOException;

/**
 * Created by cuonghx2709 on 8/12/2017.
 */
public class LoadFont {
    public static Font instance;
    public static Font loadFont(String fontPath, float size) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, new java.io.File(fontPath))
                    .deriveFont(size)
                    .deriveFont(Font.BOLD);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
