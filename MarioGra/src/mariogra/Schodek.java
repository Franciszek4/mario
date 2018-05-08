/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mariogra;

import java.awt.Image;
import javax.swing.ImageIcon;


public class Schodek{
    
    private int         wspX;
    private int         wspY;
    private int         height;
    private int         width;
    private Image       schody;
    
    Schodek(int wspX, int wspY, int height, int width, String nazwa)
    {
        this.wspX   = wspX;
        this.wspY   = wspY;
        this.height = height;
        this.width  = width;
        schody = new ImageIcon("obrazki/schodek" + nazwa + ".png").getImage();
    }
    
    public int getX()
    {
        return wspX;
    }
    
    public int getY()
    {
        return wspY;
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public int getHeight()
    {
        return height;
    }
    
    public Image getSchodek()
    {
        return schody;
    }
    
}
