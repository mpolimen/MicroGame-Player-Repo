

import java.awt.*;
import javax.swing.*;
public class Flappy extends MiniGame implements Runnable
{
    private Thread main = new Thread(this);
    private int x = 1300;
    private int y = 50;
    private int var = 10;
    private int yBreak;
    private boolean hit = false;
    public Flappy()
    {
        main.start();   
        yBreak = (int)(Math.random()*400)+100;
    }

    public void draw(Graphics2D g2, JPanel panel)
    {
        g2.setColor(Color.green);
        if(!hit)
            g2.fillRect(50, y, 40, 40);
        g2.setColor(Color.darkGray);
        g2.fillRect(x, 0, 100, yBreak);
        g2.fillRect(x, yBreak+175, 100, 700);
    }

    public boolean isComplete()
    {
        return !hit;
    }

    public void up()
    {
    }

    public void down(){}

    public void right()
    {

    }

    public void left()
    {

    }

    public void space()
    {
        var=20;
    }

    public void click(int mousex, int mousey){}

    public void drag(int mousex, int mousey){}

    public void mouseUpdate(int mousex, int mousey){}

    public String getDescription()
    {
        return "FLAP (Use Space)";
    }

    public void run()
    {
        while(true)
        {
            Rectangle top = new Rectangle(x, 0, 100, yBreak);
            Rectangle bottom = new Rectangle(x, yBreak+175, 100, 700);
            Rectangle bird = new Rectangle(50, y, 40, 40);
            var-=3;
            y-=var;
            x-=25;
            if(bird.intersects(top) ||
            bird.intersects(bottom))
                hit=true;	
            if(x<-100)
            {
                yBreak = (int)(Math.random()*400)+100;
                x=1200;
            }
            if(y>800 || y<0)
                hit=true;
            try{main.sleep(25);} catch(Exception e) {}
        }
    }
}