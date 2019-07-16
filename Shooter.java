

import java.awt.*;
import javax.swing.*;
public class Shooter extends MiniGame implements Runnable //Minigame idea by Isaac Lake
{
    private Thread main = new Thread(this);
    private int x = 500;
    private int [] x2 = new int[7];
    private int shotx=x, shoty=500;
    private boolean fired = false;
    private int score = 0;
    public Shooter()
    {
        main.start();  
        for(int p=0; p<7; p++)
            x2[p] = (int)(Math.random()*1000)+200;
    }

    public void draw(Graphics2D g2, JPanel panel)
    {
        g2.setColor(Color.cyan);
        g2.fillRect(shotx, shoty, 15, 30);
        g2.setColor(Color.green);
        g2.fillRect(x, 500, 50, 50);
        g2.setColor(Color.red);
        for(int p=0; p<7; p++)
            g2.fillRect(x2[p], 100, 100, 50);
    }

    public boolean isComplete()
    {
        return score>6;
    }

    public void up()
    {
    }

    public void down(){}

    public void right()
    {
        x+=10;
    }

    public void left()
    {
        x-=10;
    }

    public void space()
    {
        fired = true;
        shotx = x+20;
    }

    public void click(int mousex, int mousey){}

    public void drag(int mousex, int mousey){}

    public void mouseUpdate(int mousex, int mousey){}

    public String getDescription()
    {
        return "SHOOT (Use Arrows and Space)";
    }

    public void run()
    {
        while(true)
        {
            Rectangle bullet = new Rectangle(shotx, shoty, 15, 30);
            for(int p=0; p<7; p++)
            {
                Rectangle enemies = new Rectangle(x2[p], 100, 100, 50);
                if(bullet.intersects(enemies))
                {
                    x2[p]=3000;
                    shoty = 500;
                    fired = false;
                    score++;
                }
            }
            if(fired)
            {
                shoty-=45;
                if(shoty<0)
                {
                    shoty = 500;
                    fired = false;
                }
            }
            else
                shotx = x+25;

            try{main.sleep(15);} catch(Exception e) {}
        }
    }
}