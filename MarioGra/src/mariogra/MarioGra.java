package mariogra;

import java.awt.*;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MarioGra extends JFrame{
    
    private Image               tlo;
    private Image               koniec;
    private Image               puchar;
    private boolean             klawisze[];
    private boolean             zatrzask;
    private boolean             czy_przegrana;
    private boolean             czy_wygrana;
    private Timer               zegar;
    Mario                       mario;
    private ArrayList<Podloze>  podloza;
    private ArrayList<Polka>    polki;
    private ArrayList<Potworek> potworki;
    private ArrayList<Strzal>   strzaly;
    private ArrayList<Serce>    serca;
    private ArrayList<Schodek>  schody;
    private int                 temp;
    private int                 tempXPrawo;
    private int                 tempXLewo;
    private int                 przesuniecie;
    private int                 x;   
    
    class Zadanie extends TimerTask{

        public void run()
        {
            wyznaczWspYPodloza();
            wyznaczWspXPrawo();
            wyznaczWspXLewo();
            usunStrzaly();
            
            if(mario.getX() > (800+2*przesuniecie)/2 && przesuniecie<=2605 && klawisze[3]) 
                przesuniecie += 10;
            
            else if (mario.getX() < (800+2*przesuniecie)/2 && przesuniecie>0 && klawisze[2]) 
                przesuniecie -= 10;
            
            for(int i=0; i<potworki.size(); i++) 
                potworki.get(i).uaktualnijWspolrzedne();
            
            for(int i=0; i<strzaly.size(); i++) 
                strzaly.get(i).uaktualnijWspolrzedne();
            
            mario.setPrzesuniecie(przesuniecie);
            mario.uaktualnijWspolrzedne(klawisze[0], klawisze[1], klawisze[2], klawisze[3]);
            
            czyPrzegrana();
            czyWygrana();
            repaint();
        }
  }
    
    public void usunStrzaly()
    {
        for(int i=0; i<strzaly.size(); i++)
        {
            if(strzaly.get(i).getZasieg() == 0) strzaly.remove(i); 
        }
    }
    
    public void start()
    {
        klawisze        = new boolean[4];
        podloza         = new ArrayList<Podloze>();
        polki           = new ArrayList<Polka>();
        potworki        = new ArrayList<Potworek>();
        strzaly         = new ArrayList<Strzal>();
        serca           = new ArrayList<Serce>();
        schody          = new ArrayList<Schodek>();
        tlo             = new ImageIcon("obrazki/tlo.png").getImage();
        mario           = new Mario();
        puchar          = new ImageIcon("obrazki/trophy.png").getImage();
        koniec          = new ImageIcon("obrazki/koniec.png").getImage();
        przesuniecie    = 0;
        zatrzask        = false;
        czy_wygrana     = false;
        czy_przegrana   = false;
        tempXPrawo      = 3410;
        tempXLewo       = 0;
        
        mario.setWspX(20);
        mario.setWspY(500);
        
        podloza.add(new Podloze(0,535,70,600,"podloga"));
        podloza.add(new Podloze(600,535,70,600,"podloga"));
        podloza.add(new Podloze(2190,535,70,600,"podloga"));
        podloza.add(new Podloze(3000,535,70,600,"podloga"));
        polki.add(new Polka(420,280,20,100,"polka1"));
        polki.add(new Polka(260,170,20,100,"polka1"));
        polki.add(new Polka(100,290,20,100,"polka1"));
        polki.add(new Polka(0,150,20,100,"polka1"));
        polki.add(new Polka(1200,430,20,100,"polka1"));
        polki.add(new Polka(1150,180,20,100,"polka1"));
        polki.add(new Polka(920,270,20,200,"polka2"));
        polki.add(new Polka(1330,350,20,100,"polka1"));
        polki.add(new Polka(1350,205,20,100,"polka1"));
        polki.add(new Polka(1490,280,20,100,"polka1"));
        polki.add(new Polka(1640,180,20,200,"polka2"));
        polki.add(new Polka(1900,400,20,200,"polka2"));
        polki.add(new Polka(1900,280,20,100,"polka1"));
        polki.add(new Polka(2150,250,20,100,"polka1"));
        polki.add(new Polka(2380,200,20,200,"polka2"));
        polki.add(new Polka(2700,310,20,200,"polka2"));
        potworki.add(new Potworek(300,490,35,30,450));
        potworki.add(new Potworek(930,220,35,30,1110));
        potworki.add(new Potworek(780,490,35,30,1150));
        potworki.add(new Potworek(1650,135,35,30,1800));
        potworki.add(new Potworek(1910,355,35,30,2060));
        potworki.add(new Potworek(2200,490,35,30,2460));
        potworki.add(new Potworek(2700,270,35,30,2850));
        serca.add(new Serce(30,120,20,15));
        serca.add(new Serce(790,500,20,15));
        serca.add(new Serce(940,215,20,15));
        serca.add(new Serce(2430,500,20,15));
        serca.add(new Serce(2750,455,20,15));
        serca.add(new Serce(3380,500,20,15));
        
        for(int i=9; i>3; i--)
        {
            schody.add(new Schodek(500+(-i+9)*30,505-(-i+9)*30,30,i*30,""+i));
        }
        
        for(int i=9; i>3; i--)
        {
            schody.add(new Schodek(3100+(-i+9)*30,505-(-i+9)*30,30,i*30,""+i));
        }
        
        for(int i=3; i<10; i++)
        {
            schody.add(new Schodek(2500,505-(9-i)*30,30,i*30,""+i));
        }
    }
    
    MarioGra(){
        super("Mario");
        setBounds(50,50,800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        createBufferStrategy(2);
        
        start();
        
        zegar = new Timer();
        zegar.scheduleAtFixedRate(new Zadanie(),0,45);

        this.addKeyListener(new KeyListener(){

            public void keyPressed(KeyEvent e){
                switch(e.getKeyCode()){
                    case KeyEvent.VK_P:       
                        przesuniecie = 2500; 
                        mario.setWspX(3000); 
                        break;
                    case KeyEvent.VK_R:       start(); break;
                    case KeyEvent.VK_UP:      klawisze[0] = true; break;
                    case KeyEvent.VK_DOWN:    klawisze[1] = true; break;
                    case KeyEvent.VK_LEFT:    
                        klawisze[2] = true; 
                        mario.czyPrawo = false; 
                        break;
                    case KeyEvent.VK_RIGHT:   
                        klawisze[3] = true; 
                        mario.czyPrawo = true; 
                        break;
                    case KeyEvent.VK_SPACE:   
                        strzaly.add(new Strzal(mario.getX(),mario.getY()+10,mario.getCzyPrawo())); 
                        break;
                }
            }

            public void keyReleased(KeyEvent e){
                switch(e.getKeyCode()){
                    case KeyEvent.VK_UP:      klawisze[0] = false; break;
                    case KeyEvent.VK_DOWN:    klawisze[1] = false; break;
                    case KeyEvent.VK_LEFT:    
                        klawisze[2] = false; 
                        if(!mario.czySkok) mario.coRobiMario = StanMario.STOI; 
                        break;
                    case KeyEvent.VK_RIGHT:   
                        klawisze[3] = false; 
                        if(!mario.czySkok) mario.coRobiMario = StanMario.STOI; 
                        break;
                }
            }

            public void keyTyped(KeyEvent e){
            }
        }
    );
  }
    
    public void wyznaczWspXPrawo()
    {
        int temp_odleglosc = 3500;
        for(int i=0; i<schody.size(); i++)
        {
            if(mario.getX() + mario.getWidth() == schody.get(i).getX()
                        && mario.getY() + mario.getHeight() >= schody.get(i).getY() - (mario.getHeight() -schody.get(i).getHeight())
                            && schody.get(i).getX() - mario.getX() < temp_odleglosc)
            {
               tempXPrawo = schody.get(i).getX();
               break;
            }
            else tempXPrawo = 3410;
        }                        
        
        mario.setBlokadaXPrawo(tempXPrawo);
    }
    
    public void wyznaczWspXLewo()
    {
        int temp_odleglosc = 100;
        for(int i=0; i<schody.size(); i++)
        {
            if(mario.getX() == schody.get(i).getX() + schody.get(i).getWidth()
                        && mario.getY() + mario.getHeight() >= schody.get(i).getY() - (mario.getHeight() -schody.get(i).getHeight())
                            && mario.getX()-(schody.get(i).getX()+schody.get(i).getWidth()) < temp_odleglosc)
            {
               tempXLewo = schody.get(i).getX()+schody.get(i).getWidth();
               break;
            }
            else tempXLewo = 0;
        }                        
        
        mario.setBlokadaXLewo(tempXLewo);
    }
    
    public void wyznaczWspYPodloza()
    {
        temp = 635;
        
        for(int i=0; i<podloza.size(); i++)
        {
            if(mario.getX() + mario.getWidth() > podloza.get(i).getX() && 
                    mario.getX() < podloza.get(i).getX() + podloza.get(i).getWidth() &&
                        mario.getY() + mario.getHeight() <= podloza.get(i).getY())
                if(podloza.get(i).getY() < temp) temp = podloza.get(i).getY();                           
        }
        
        for(int i=0; i<polki.size(); i++)
        {
            if(((mario.getX() + mario.getWidth()) > polki.get(i).getX()) && 
                    (mario.getX() < (polki.get(i).getX() + polki.get(i).getWidth())) &&
                        ((mario.getY() + mario.getHeight()) <= polki.get(i).getY()))
                if(polki.get(i).getY() < temp) temp = polki.get(i).getY();                            
        }
        
        for(int i=0; i<schody.size(); i++)
        {
            if(((mario.getX() + mario.getWidth()) > schody.get(i).getX()) && 
                    (mario.getX() < (schody.get(i).getX() + schody.get(i).getWidth())) &&
                        ((mario.getY() + mario.getHeight()) <= schody.get(i).getY()))
                if(schody.get(i).getY() < temp) temp = schody.get(i).getY();                            
        }
        
        mario.setWysokoscPodloza(temp);
    }
    
    public void czyKolizja()
    {
        Rectangle2D marioProstokat = new Rectangle2D.Float(mario.getX(),mario.getY(),mario.getWidth(),mario.getHeight());
        
        if(!marioProstokat.intersects(potworki.get(x).getX(), potworki.get(x).getY(), potworki.get(x).getWidth(), potworki.get(x).getHeight()))
            zatrzask = false;
        
        for(int i=0; i<potworki.size(); i++)
        {
            if(marioProstokat.intersects(potworki.get(i).getX(), potworki.get(i).getY(), potworki.get(i).getWidth(), potworki.get(i).getHeight()) && !zatrzask)
            {
                mario.zmienLiczbeZyc(-1);
                x = i;
                zatrzask = true;
            }
        }
        
        for(int i=0; i<serca.size(); i++)
        {
            if(marioProstokat.intersects(serca.get(i).getX(), serca.get(i).getY(), 20, 20))
            {
                serca.remove(i);
                mario.zmienLiczbeZyc(1);
            }
        }
        
        for(int i=0; i<strzaly.size(); i++)
        {
            Rectangle2D strzalProstokat = new Rectangle2D.Float(strzaly.get(i).getX(),strzaly.get(i).getY(),10,10);
            for(int j=0; j<potworki.size(); j++)
            {
                if(strzalProstokat.intersects(potworki.get(j).getX(), potworki.get(j).getY(), potworki.get(j).getWidth(), potworki.get(j).getHeight()))
                {
                    strzaly.remove(i);
                    potworki.remove(j);
                }
            }
        }
    }
    
    public void czyPrzegrana()
    {
        czyKolizja();
        if(mario.getY() > 550 || mario.liczbaZyc()==0) start();
    }
    
    public void czyWygrana()
    {
        Rectangle2D marioProstokat = new Rectangle2D.Float(mario.getX(),mario.getY(),mario.getWidth(),mario.getHeight());
        
        if(marioProstokat.intersects(3300, 295, 50, 50))
            czy_wygrana = true;
    }
    
    public static void main(String[] args)
    {
        new MarioGra();
    }
    
    public void paint(Graphics g)
    {
        try{
        BufferStrategy bstrategy = this.getBufferStrategy();
        Graphics2D g2d = (Graphics2D)bstrategy.getDrawGraphics();
        g2d.drawImage(tlo, 0-przesuniecie,0,null);
        
        for(int i=0; i<podloza.size(); i++)
        {
            g2d.drawImage(podloza.get(i).getPodloze(), podloza.get(i).getX()-przesuniecie, podloza.get(i).getY(), podloza.get(i).getWidth(), podloza.get(i).getHeight(), null);
        }
        
        for(int i=0; i<polki.size(); i++)
        {
            g2d.drawImage(polki.get(i).getPolka(), polki.get(i).getX()-przesuniecie, polki.get(i).getY(), polki.get(i).getWidth(), polki.get(i).getHeight(), null);
        }
        
        for(int i=0; i<potworki.size(); i++)
        {
            g2d.drawImage(potworki.get(i).getPotworek(), potworki.get(i).getX()-przesuniecie, potworki.get(i).getY(), potworki.get(i).getWidth(), potworki.get(i).getHeight(), null);
        }
        
        for(int i=0; i<strzaly.size(); i++)
        {
            g2d.drawImage(strzaly.get(i).getStrzal(), strzaly.get(i).getX()-przesuniecie, strzaly.get(i).getY()-5, 20, 20, null);
        }
        
        for(int i=0; i<serca.size(); i++)
        {
            g2d.drawImage(serca.get(i).getSerce(), serca.get(i).getX()-przesuniecie, serca.get(i).getY(), 20, 20, null);
        }
        
        for(int i=0; i<schody.size(); i++)
        {
            g2d.drawImage(schody.get(i).getSchodek(), schody.get(i).getX()-przesuniecie, schody.get(i).getY(), null);
        }
        
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Corbel",Font.BOLD,30));
        
        for(int i=0; i<mario.liczbaZyc(); i++)
        {
           g2d.drawImage(new Serce().getSerce(), 500+i*40, 50, 30, 30, null); 
        }
        
        g2d.drawImage(puchar, 3290-przesuniecie, 295, 70, 70, null);
        g2d.drawImage(mario.rysowanieMario(), mario.getX()-przesuniecie, mario.getY(), mario.getWidth(), mario.getHeight(), null);
        
        if(czy_wygrana) 
        {
            g2d.drawString("WYGRANA!     GRATULACJE!", 200, 150);
            g2d.drawString("r - jeszcze raz", 300, 400);
        }
        
        g2d.dispose();
        bstrategy.show();
        }
        catch(NullPointerException npe){};
    }
}