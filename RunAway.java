

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
public class RunAway extends MiniGame implements Runnable
{
    private Thread main = new Thread(this);
    private int x = 500, y = 400;
    private ArrayList<Integer> xs = new ArrayList<Integer>();
    private ArrayList<Integer> ys = new ArrayList<Integer>();
    private boolean hit = false;
    private int wait = 0;
    public RunAway()
    {
        main.start();
        xs.add(300);
        ys.add(200);
    }

    public void draw(Graphics2D g2, JPanel panel)
    {
        if(!hit)
        {
            g2.setColor(Color.green);
            g2.fillRect(x, y, 50, 50);
            g2.setColor(Color.orange);
            for(int p=0; p<xs.size(); p++)
                g2.fillRect(xs.get(p), ys.get(p), 50, 50);
        }
    }

    public boolean isComplete()
    {
        return !hit;
    }

    public void up()
    {
        if(y>=0)
            y-=25;
    }

    public void down()
    {
        if(y<=600)
            y+=25;
    }

    public void right()
    {
        if(x<=1200)
            x+=25;
    }

    public void left()
    {
        if(x>=0)
            x-=25;
    }

    public void space()
    {

    }

    public void click(int mousex, int mousey){}

    public void drag(int mousex, int mousey){}

    public void mouseUpdate(int mousex, int mousey){}

    public void run()
    {
        while(true)
        {
            Rectangle player = new Rectangle(x, y, 50, 50);
            for(int p=0; p<xs.size(); p++)
            {
                Rectangle enemies = new Rectangle(xs.get(p), ys.get(p), 50, 50);
                if(player.intersects(enemies))
                    hit = true;
                if(xs.get(p) - x <0)
                    xs.set(p, xs.get(p)+5);
                else
                    xs.set(p, xs.get(p)-5);
                if(ys.get(p) - y <0)
                    ys.set(p, ys.get(p)+5);
                else
                    ys.set(p, ys.get(p)-5);
            }
            wait++;
            if(wait > 50)
            {
                double chance = Math.random();
                if(chance < .25)
                {
                    xs.add(0);
                    ys.add(0);
                }
                else if(chance>=.25 && chance<.5)
                {
                    xs.add(1100);
                    ys.add(0);
                }
                else if(chance>=.5 && chance<.75)
                {
                    xs.add(1100);
                    ys.add(700);
                }
                else
                {
                    xs.add(0);
                    ys.add(700);
                }
                wait = (int)(Math.random()*40);
                if(x<0)
                    x = 0;
                if(y<0)
                    y = 0;
                if(y>600)
                    y = 600;
                if(x>1200)
                    x = 1200;
            }
            try{main.sleep(20);} catch(Exception e) {}
        }
    }

    public String getDescription()
    {
        return "RUN AWAY (Use Arrows)";
    }
}