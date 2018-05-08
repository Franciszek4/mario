/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mariogra;

import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.ImageIcon;

enum StanMario{
        SKACZE,
        STOI,
        IDZIE
    }

public class Mario{
    
    private Image       stoi_prawo;
    private Image       idzie_prawo_1;
    private Image       idzie_prawo_2;
    private Image       skacze_prawo;
    private Image       skacze_lewo;
    private Image       stoi_lewo;
    private Image       idzie_lewo_1;
    private Image       idzie_lewo_2;    
    public StanMario    coRobiMario;
    public boolean      czySkok;
    public boolean      czyPrawo;
    private boolean     wGore;
    private boolean     zmianaKroku;
    private int         wysokoscSkoku;
    private int         wysokoscPodloza;
    private int         wysokoscBlokadyXPrawo;
    private int         wysokoscBlokadyXLewo;
    private int         wspX;
    private int         wspY;
    private int         przesuniecie;
    private int         height;
    private int         width;
    private int         licznik_zyc;
    
    Mario(){
        
        stoi_prawo              = new ImageIcon("obrazki/mario_stoi_prawo.png","30").getImage();
        idzie_prawo_1           = new ImageIcon("obrazki/mario_krok1_prawo.png").getImage();
        idzie_prawo_2           = new ImageIcon("obrazki/mario_krok2_prawo.png").getImage();
        skacze_prawo            = new ImageIcon("obrazki/mario_skok_prawo.png").getImage();
        stoi_lewo               = new ImageIcon("obrazki/mario_stoi_lewo.png").getImage();
        idzie_lewo_1            = new ImageIcon("obrazki/mario_krok1_lewo.png").getImage();
        idzie_lewo_2            = new ImageIcon("obrazki/mario_krok2_lewo.png").getImage();
        skacze_lewo             = new ImageIcon("obrazki/mario_skok_lewo.png").getImage(); 
        czySkok                 = false;
        wGore                   = false;
        czyPrawo                = true;
        zmianaKroku             = false;
        wysokoscSkoku           = 40;
        wysokoscPodloza         = 535;
        wysokoscBlokadyXPrawo   = 0;
        wysokoscBlokadyXLewo    = 0;
        wspX                    = 20;
        wspY                    = 500;
        height                  = 35;
        width                   = 30;
        coRobiMario             = StanMario.STOI;
        przesuniecie            = 0;
        licznik_zyc             = 3;
    }
    
    public void uaktualnijWspolrzedne(boolean gora, boolean dol, boolean lewo, boolean prawo)
    {
        if(lewo)
            {
                if(!czySkok && wspY+height!=wysokoscPodloza) 
                {
                    czySkok         = true;
                    coRobiMario     = StanMario.SKACZE;
                    wGore           = false;
                    wysokoscSkoku   = 0;
                    wspX            -= 10;
                }                        
                else
                {
                    if(!czySkok) coRobiMario = StanMario.IDZIE;
                    
                    if(wspX==wysokoscBlokadyXLewo) wspX = wysokoscBlokadyXLewo;
                    else wspX -= 10;
                    
                    if(zmianaKroku) zmianaKroku = false;
                    else zmianaKroku = true;
                }
                
            }
        if(prawo)
            {
                if(!czySkok && wspY+height<wysokoscPodloza) 
                {
                    czySkok         = true;
                    coRobiMario     = StanMario.SKACZE;
                    wGore           = false;
                    wysokoscSkoku   = 0;
                    wspX            += 10;
                }
                else
                {
                    if(!czySkok) coRobiMario = StanMario.IDZIE;
                
                    if(wspX+width==wysokoscBlokadyXPrawo) wspX = wysokoscBlokadyXPrawo-width;
                    else wspX += 10;
                
                    if(zmianaKroku) zmianaKroku = false;
                    else zmianaKroku = true;
                }
            }
        
        if(gora)
            {
                if(!czySkok)
                {
                    coRobiMario     = StanMario.SKACZE;
                    czySkok         = true;
                    wGore           = true;
                    wysokoscSkoku   = 40;
                }
            }
            
        if(czySkok)
            {                 
                wspY = skok(wspY);
            }
        
        wspX = (wspX<przesuniecie)?przesuniecie:wspX;
        if(wspY > wysokoscPodloza-height)
           {
               wspY         = wysokoscPodloza-height;
               coRobiMario  = StanMario.STOI;
               czySkok      = false;
           }
    }
    
    public void setWspX(int wspolrzednaX)
    {
        wspX = wspolrzednaX;
    }
    
    public void setWspY(int wspolrzednaY)
    {
        wspY = wspolrzednaY;
    }
    
    public void setWysokoscPodloza(int wspY)
    {
        wysokoscPodloza = wspY;
    }
    
    public void setBlokadaXPrawo(int wspX)
    {
        wysokoscBlokadyXPrawo = wspX;
    }
    
    public void setBlokadaXLewo(int wspX)
    {
        wysokoscBlokadyXLewo = wspX;
    }
    
    public int skok(int wspY)
    {
        if(wysokoscSkoku == 0 && wGore) 
        {            
            wGore = false;
        }
        
        if(wGore)
        {
            wysokoscSkoku   -= 5;
            wspY            -= wysokoscSkoku;
        }
        else
        {
            wysokoscSkoku   += 5;
            wspY            += wysokoscSkoku;
        }
        
        return wspY;
    }
    
    public void setPrzesuniecie(int n)
    {
        przesuniecie = n;
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
    
    public boolean getCzyPrawo()
    {
        return czyPrawo;
    }
    
    public int liczbaZyc()
    {
        return licznik_zyc;
    }
    
    public void zmienLiczbeZyc(int i)
    {
        licznik_zyc += i;
    }
    
    public Image rysowanieMario()
    {
        switch(coRobiMario)
        {
            case STOI:
                if(czyPrawo)
                {
                    return stoi_prawo;
                }
                else
                {
                    return stoi_lewo;
                }
            case SKACZE:
                if(czyPrawo)
                {
                    return skacze_prawo;
                }
                else
                {
                    return skacze_lewo;
                } 
            case IDZIE:
                if(czyPrawo)
                {
                    if(zmianaKroku) return stoi_prawo;
                    else return idzie_prawo_2;
                }
                else
                {
                    if(zmianaKroku) return stoi_lewo;
                    else return idzie_lewo_2;
                }
        }
        return null;
    }
}
