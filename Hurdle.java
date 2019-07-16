

import java.awt.*;
import javax.swing.*;
public class Hurdle extends MiniGame implements Runnable
{
    private Thread main = new Thread(this);
    private int y = 400;
    private int x = -50;
    private int var = 0;
    private int xVar=15;
    private int [] xs = new int [2];
    private boolean hit = false;
    public Hurdle()
    {
        main.start();
        xs[0] = (int)(Math.random()*300)+300;
        for(int p=1; p<2; p++)
            xs[p]=xs[p-1]+150 +(int)(Math.random()*300)+100;
    }

    public void draw(Graphics2D g2, JPanel panel)
    {
        g2.setColor(Color.green);
        if(!hit)
            g2.fillRect(x, y, 50, 50);
        g2.setColor(Color.red);
        for(int p=0; p<2; p++)
            g2.fillRect(xs[p], 400, 50, 50);
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
        if(y>400)
            var=26;
    }

    public void click(int mousex, int mousey){}

    public void drag(int mousex, int mousey){}

    public void mouseUpdate(int mousex, int mousey){}

    public String getDescription()
    {
        return "HURDLE (Use Space)";
    }

    public void run()
    {
        while(true)
        {
            Rectangle player = new Rectangle(x, y, 50, 50);
            for(int p=0; p<2; p++)
            {
                Rectangle blox = new Rectangle(xs[p], 400, 50, 50);
                if(blox.intersects(player))
                    hit=true;
            }
            var-=3;
            if(y>400 && var<=0)
                var=0;
            y-=var;
            x+=xVar;	
            if(x>=1200)
                xVar=-15;
            try{main.sleep(25);} catch(Exception e) {}
        }
    }
}