/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mariogra;

import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

/**
 *
 * @author guziec
 */
public class Potworek{
    private Image       prawo;
    private Image       lewo;
    private int         wspX;
    private int         wspY;
    private int         width;
    private int         height;
    private int         ograniczenieXPrawe;
    private int         ograniczenieXLewe;
    private boolean     czyPrawo;
    
    Potworek(int wspX, int wspY, int height, int width, int ograniczenieXPrawe)
    {
        this.wspX                   = wspX;
        this.wspY                   = wspY;
        this.height                 = height;
        this.width                  = width;
        this.ograniczenieXPrawe     = ograniczenieXPrawe;
        this.ograniczenieXLewe      = wspX;
        
        prawo       = new ImageIcon("obrazki/enemy1_prawo.png").getImage();
        lewo        = new ImageIcon("obrazki/enemy1_lewo.png").getImage();
        czyPrawo    = true;
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
    
    public Image getPotworek()
    {
        if(czyPrawo) return prawo;
        else return lewo;
    }
    
    void uaktualnijWspolrzedne()
    {
        if(wspX == ograniczenieXPrawe)  czyPrawo=false;
        if(wspX == ograniczenieXLewe)   czyPrawo=true;
        if(czyPrawo)
        {
            wspX += 5;
        }
        else
        {
            wspX -= 5;
        }
    }
}
