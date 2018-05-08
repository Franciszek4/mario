/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mariogra;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author guziec
 */
public class Serce {
    
    private int         wspX;
    private int         wspY;
    private Image       serce;
    
    Serce()
    {
        serce     = new ImageIcon("obrazki/serce.png").getImage();
    }
    
    Serce(int wspX,int wspY, int height, int width)
    {
        this.wspX   = wspX;
        this.wspY   = wspY;
        
        serce     = new ImageIcon("obrazki/serce.png").getImage();
    }
    
    public int getX()
    {
        return wspX;
    }
    
    public int getY()
    {
        return wspY;
    }
    
    public Image getSerce()
    {
        return serce;
    }
    
    
}
