

import java.awt.*;
import javax.swing.*;
public class Tap extends MiniGame implements Runnable
{
    private Thread main = new Thread(this);
    private int x = 0;
    public Tap()
    {
        main.start();   
    }

    public void draw(Graphics2D g2, JPanel panel)
    {
        g2.setColor(Color.green);
        g2.fillRect(x, 450, 20, 100);
        g2.setColor(Color.cyan);
        g2.fillRect(1200, 450, 20, 100);
    }

    public boolean isComplete()
    {
        return x>=1200;
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

    }

    public void click(int mousex, int mousey)
    {
        x+=29;
    }

    public void drag(int mousex, int mousey){}

    public void mouseUpdate(int mousex, int mousey){}

    public String getDescription()
    {
        return "TAP FAST (Use Left and Right Click)";
    }

    public void run()
    {
        while(true)
        {

            try{main.sleep(25);} catch(Exception e) {}
        }
    }
}