

import java.awt.*;
import javax.swing.*;
public class Dodge extends MiniGame implements Runnable
{
    private Thread main = new Thread(this);
    private int y=500;
    private int x;
    private int jumps=0;
    private boolean hit=false;
    private boolean jump=false;
    private int var=35;
    private boolean stop;
    private int pause = 0;
    public Dodge()
    {
        main.start();   
        stop = Math.random()>.5;
        x=2000+(int)(Math.random()*400);
    }

    public void draw(Graphics2D g2, JPanel panel)
    {
        g2.setColor(Color.green);
        if(!hit)
            g2.fillRect(100, y, 50, 50);
        g2.setColor(Color.red);
        g2.fillRect(x, 400, 130, 130);
    }

    public boolean isComplete()
    {
        return !hit;
    }

    public void up()
    {

    }

    public void down(){}

    public void right(){}

    public void left(){}

    public void space()
    {
        if(jumps<1)
        {
            jump=true;
            jumps++;
        }
    }

    public void click(int mousex, int mousey){}

    public void drag(int mousex, int mousey){}

    public void mouseUpdate(int mousex, int mousey){}

    public String getDescription()
    {
        return "JUMP (Use Space)";
    }

    public void run()
    {
        while(true)
        {
            Rectangle player = new Rectangle(100, y, 50, 50);
            Rectangle enemy = new Rectangle(x, 400, 130, 130);
            if(player.intersects(enemy))
                hit=true;
            if(jump)
            {
                y-=var;
                var-=2;
                if(y>=500)
                    jump=false;
            }
            if(!stop || x>500)
                x-=60;
            if(stop && x<=500)
            {
                pause++;
                if(pause >= 30)
                    stop=false;
            }
            try{main.sleep(25);} catch(Exception e) {}
        }
    }
}