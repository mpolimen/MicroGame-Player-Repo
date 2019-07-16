
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
public class BallBounce extends MiniGame implements Runnable
{
    private Thread main = new Thread(this);
    private int[] xs = new int[6];
    private int[] ys = new int[6];
    private int[] yVar = new int[6];
    private int x = 25;
    private boolean hit = false;
    public BallBounce()
    {
        main.start();
        for(int p=0; p<xs.length; p++)
        {
            if(p == 0)
                xs[p] = (int)(Math.random()*100)+150;
            else
                xs[p] = xs[p-1] + (int)(Math.random()*90)+125;   
            ys[p] = (int)(Math.random()*300)+300;
            yVar[p] = 15;
        }
    }

    public void draw(Graphics2D g2, JPanel panel)
    {
        g2.setColor(Color.pink);
        for(int p=0; p<xs.length; p++)
            g2.fillOval(xs[p], ys[p], 50, 50);
        g2.setColor(Color.green);
        if(!hit)
            g2.fillRect(x, 600, 40, 40);
        g2.setColor(Color.cyan);
        g2.fillRect(1200, 500, 5, 150);
    }

    public boolean isComplete()
    {
        return !hit && x>=1200;
    }

    public void up()
    {

    }

    public void down()
    {

    }

    public void right()
    {
        x+=20;
    }

    public void left()
    {

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
            Rectangle player = new Rectangle(x, 600, 40, 40);
            for(int p=0; p<xs.length; p++)
            {
                Rectangle balls = new Rectangle(xs[p], ys[p], 50, 50);
                if(player.intersects(balls))
                    hit = true;
                if(ys[p]>=600)
                    yVar[p] = -15;
                else if(ys[p]<=300)
                    yVar[p] = 15;
                ys[p]+=yVar[p];
            }
            try{main.sleep(20);} catch(Exception e) {}
        }
    }

    public String getDescription()
    {
        return "RUN THROUGH (Use Rigth to Move)";
    }
}