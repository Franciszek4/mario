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
public class Strzal {
    
    private int     wspX;
    private int     wspY;
    private int     zasieg;
    private boolean czyPrawo;
    private Image   strzal;
    
    Strzal(int wspX, int wspY, boolean czyPrawo)
    {
        zasieg = 200;
        this.wspX   = wspX;
        this.wspY   = wspY;
        this.czyPrawo = czyPrawo;
        strzal = new ImageIcon("obrazki/strzal.png").getImage(); ;
    }
    
    public void uaktualnijWspolrzedne()
    {
        if(czyPrawo) wspX += 20;
        else wspX -= 20;
        zasieg -= 20;
    }
    
    public int getX()
    {
        return wspX;
    }
    
    public int getZasieg()
    {
        return zasieg;
    }
    
    public int getY()
    {
        return wspY;
    }
    
    public Image getStrzal()
    {
        return strzal;
    }
}
