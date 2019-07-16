

import java.awt.*;
import javax.swing.*;
public class Search extends MiniGame implements Runnable
{
    private Thread main = new Thread(this);
    private int xr, yr;
    private int x = 2000, y;
    private boolean found = false;
    public Search()
    {
        main.start();   
        xr = (int)(Math.random()*900)+100;
        yr = (int)(Math.random()*500)+100;
    }

    public void draw(Graphics2D g2, JPanel panel)
    {
        if(!found)
        {
            g2.setColor(Color.black);
            g2.fillRect(0,0, 1400, 750);
            g2.setColor(Color.yellow);
            g2.fillOval(x, y, 65, 65);
            //             g2.drawString(xr+" "+yr, 1000, 50);
        }
        else
        {
            g2.setColor(Color.orange);
            g2.fillRect(xr,yr, 50, 50);   
        }
    }

    public boolean isComplete()
    {
        return found;
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

    public void space(){}

    public void click(int mousex, int mousey)
    {

    }

    public void drag(int mousex, int mousey){}

    public void mouseUpdate(int mousex, int mousey)
    {
        x = mousex;
        y = mousey;
    }

    public String getDescription()
    {
        return "SEARCH & FIND (Use Mouse)";
    }

    public void run()
    {
        while(true)
        {
            Rectangle mouse = new Rectangle(x, y, 65, 65);
            Rectangle dot = new Rectangle(xr, yr, 50, 50);
            if(mouse.intersects(dot))
            {
                found = true;
            }

            try{main.sleep(25);} catch(Exception e) {}
        }
    }
}