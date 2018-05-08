/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mariogra;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author guziec
 */
public class Podloze{
    
    private int         wspX;
    private int         wspY;
    private int         height;
    private int         width;
    private Image       podloga;
    
    Podloze(int wspX, int wspY, int height, int width, String nazwa)
    {
        this.wspX   = wspX;
        this.wspY   = wspY;
        this.height = height;
        this.width  = width;
        
        podloga     = new ImageIcon("obrazki/" + nazwa + ".png").getImage(); 
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
    public Image getPodloze()
    {
        return podloga;
    }
    
}
