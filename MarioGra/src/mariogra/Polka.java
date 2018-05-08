
package mariogra;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Polka extends Podloze{
    
    private Image       polka;
    private int         wspX;
    private int         wspY;
    private int         height;
    private int         width;
    
    Polka(int wspX, int wspY, int height, int width, String nazwa)
    {
        super(wspX,wspY,height,width,nazwa);
        this.wspX   = wspX;
        this.wspY   = wspY;
        this.height = height;
        this.width  = width;
        
        polka     = new ImageIcon("obrazki/" + nazwa + ".png").getImage();
    }
    
    public Image getPolka()
    {
        return polka;
    }
    
}
