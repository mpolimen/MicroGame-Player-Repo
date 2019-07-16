

import java.awt.*;
import javax.swing.*;
public class Platformer extends MiniGame implements Runnable
{
    private Thread main = new Thread(this);
    private int x=100, y=500;
    private int var=55;
    private int platX = 200, platY = 500;
    public Platformer()
    {
        main.start();   
    }

    public void draw(Graphics2D g2, JPanel panel)
    {
        g2.setColor(Color.green);
        g2.fillRect(x, y, 50, 50);
        g2.setColor(Color.darkGray);
        g2.fillRect(platX, platY, 50, 30);
    }

    public boolean isComplete()
    {
        return y<700;
    }

    public void up()
    {
    }

    public void down(){}

    public void right()
    {
        x+=20;
    }

    public void left()
    {
        x-=20;
    }

    public void space(){}

    public void click(int mousex, int mousey){}

    public void drag(int mousex, int mousey){}

    public void mouseUpdate(int mousex, int mousey){}

    public String getDescription()
    {
        return "SURVIVE (Use Arrows)";
    }

    public void run()
    {
        while(true)
        {
            Rectangle player = new Rectangle(x, y, 50, 50);
            Rectangle platform = new Rectangle(platX, platY, 40, 30);
            y-=var;
            var-=3;
            if(player.intersects(platform))
            {
                var=60;
                platX = (int)(Math.random()*800)+300;
                platY = (int)(Math.random()*300)+300;
            }
            try{main.sleep(25);} catch(Exception e) {}
        }
    }
}