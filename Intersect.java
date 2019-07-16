

import java.awt.*;
import javax.swing.*;
public class Intersect extends MiniGame implements Runnable
{
    private Thread main = new Thread(this);
    private int y;
    private int y2 = 300;
    private int good = 0;
    private boolean detonate = false;
    private int var;
    private int wait = 0;
    private int turn = (int)(Math.random()*30)+5;
    public Intersect()
    {
        main.start();
    }

    public void draw(Graphics2D g2, JPanel panel)
    {
        g2.setStroke(new BasicStroke(6));
        g2.setColor(Color.cyan);
        if(good != 1)
            g2.drawRect(580, y2, 100, 100);
        if(good == 0)
            g2.setColor(Color.green);
        else
            g2.setColor(Color.red);
        if(good != 1)
            g2.fillRect(600, y, 30, 30);
    }

    public boolean isComplete()
    {
        return good == 1;
    }

    public void up(){}

    public void down(){}

    public void right()
    {

    }

    public void left()
    {

    }

    public void space()
    {
        detonate = true;
    }

    public void click(int mousex, int mousey){}

    public void drag(int mousex, int mousey){}

    public void mouseUpdate(int mousex, int mousey){}

    public void run()
    {
        while(true)
        {
            Rectangle gap = new Rectangle(580, y2, 100, 100);
            Rectangle thing = new Rectangle(600, y, 30, 30);
            if(good == 0)
                y+=7;
            if(detonate && good == 0)
            {
                if(thing.intersects(gap))
                    good = 1;
                else
                    good = 2;
            }
            if(y2 - y <= 30 && wait == 0)
            {
               var = 15;
            }
            y2 += var;
            if(var != 0)
                wait++;
            if(wait>=turn && wait<1000)
            {
                var*=-1;
                var-=7;
                wait = 1001;
            }
            try{main.sleep(20);} catch(Exception e) {}
        }
    }

    public String getDescription()
    {
        return "PRESS WHEN INSIDE (Use Space)";
    }
}