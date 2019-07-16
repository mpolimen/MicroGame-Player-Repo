

import java.awt.*;
import javax.swing.*;
public class Fly extends MiniGame implements Runnable
{
    private Thread main = new Thread(this);
    private int y = 50;
    private int var = 10;
    private boolean hit = false;
    private int[] xs = new int[10];
    private int[] ys = new int[10];
    public Fly()
    {
        main.start();   
        for(int p=0; p<xs.length; p++)
        {
            if(p%2 == 0)
                xs[p] = -200;
            else
                xs[p] = 1400;
            if(p>0)
                ys[p] = ys[p-1] + (int)(Math.random()*80)+70;
            else
                ys[p] = (int)(Math.random()*100)+20;
        }
    }

    public void draw(Graphics2D g2, JPanel panel)
    {
        g2.setColor(Color.green);
        if(!hit)
            g2.fillRect(550, y, 25, 25);
        g2.setColor(Color.gray);
        for(int p=0; p<xs.length; p++)
            g2.fillOval(xs[p], ys[p], 10, 10);
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
        var = 10;
    }

    public void click(int mousex, int mousey){}

    public void drag(int mousex, int mousey){}

    public void mouseUpdate(int mousex, int mousey){}

    public String getDescription()
    {
        return "FLY AND DODGE (Use Space)";
    }

    public void run()
    {
        while(true)
        {
            Rectangle player = new Rectangle(550, y, 25, 25);
            var-=2;
            y-=var;
            for(int p=0; p<xs.length; p++)
            {
                Rectangle obstacles = new Rectangle(xs[p], ys[p], 10, 10);
                if(player.intersects(obstacles))
                    hit = true;
                if(p%2 == 0)
                    xs[p]+=20;
                else
                    xs[p]-=20;
                if(p%2 == 0 && xs[p] > 1200)
                    xs[p] = 0;
                if(p%2 != 0 && xs[p] < 0)
                    xs[p] = 1200;
            }
            if(y>800 || y<0)
                hit=true;
            try{main.sleep(25);} catch(Exception e) {}
        }
    }
}