

import java.awt.*;
import javax.swing.*;
public class BlockGrab extends MiniGame implements Runnable //Minigame Idea by Sebastian Churion
{
    private Thread main = new Thread(this);
    private int x = 500, y = 400;
    private int xs[] = new int [8];
    private int ys[] = new int [8];
    private int score = 0;
    public BlockGrab()
    {
        main.start();
        for(int p=0; p<xs.length; p++)
        {
            if(p%4 == 0)
            {
                xs[p] = 0 - (int)(Math.random()*200);
                ys[p] = (int)(Math.random()*600);
            }
            else if(p%3 == 0)
            {
                ys[p] = 0 - (int)(Math.random()*200);
                xs[p] = (int)(Math.random()*1100);
            }
            else if(p%2 == 0)
            {
                xs[p] = 1300 +(int)(Math.random()*200);
                ys[p] = (int)(Math.random()*600);
            }
            else
            {
                ys[p] = 700 +(int)(Math.random()*200);
                xs[p] = (int)(Math.random()*1100);
            }
        }
    }

    public void draw(Graphics2D g2, JPanel panel)
    {
        g2.setColor(Color.green);
        g2.fillRect(x ,y , 70, 70);
        g2.setColor(Color.magenta);
        for(int p=0; p<xs.length; p++)
            g2.fillRect(xs[p], ys[p], 100, 100);
    }

    public boolean isComplete()
    {
        return score>7;
    }

    public void up()
    {
        y-=30;
    }

    public void down()
    {
        y+=30;
    }

    public void right()
    {
        x+=30;
    }

    public void left()
    {
        x-=30;
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
            Rectangle player = new Rectangle(x ,y , 70, 70);
            for(int p=0; p<xs.length; p++)
            {
                Rectangle blox = new Rectangle(xs[p], ys[p], 100, 100);
                if(p%4 == 0)
                {
                    xs[p]+=5;
                }
                else if(p%3 == 0)
                {
                    ys[p]+=5;
                }
                else if(p%2 == 0)
                {
                    xs[p]-=5;
                }
                else
                {
                    ys[p]-=5;
                }
                if(player.intersects(blox))
                {
                    xs[p] = 50000;
                    score++;
                }
            }

            try{main.sleep(20);} catch(Exception e) {}
        }
    }

    public String getDescription()
    {
        return "GRAB ALL SQUARES (Use Arrows)";
    }
}