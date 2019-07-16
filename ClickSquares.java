

import java.awt.*;
import javax.swing.*;
public class ClickSquares extends MiniGame implements Runnable //Minigame idea by Sebastian Churion
{
    private Thread main = new Thread(this);
    private int [] x = new int [5];
    private int [] y = new int [5];
    private int clickx=3000, clicky=3000;
    private int score = 0;
    private int reload = 0;
    public ClickSquares()
    {
        main.start();   
        for(int p=0; p<5; p++)
        {
            x[p] = (int)(Math.random()*1000)+200;
            y[p] = (int)(Math.random()*500)+100;
        }
    }

    public void draw(Graphics2D g2, JPanel panel)
    {
        g2.setColor(Color.red);
        for(int p=0; p<5; p++)
            g2.fillOval(x[p], y[p], 65, 65);
    }

    public boolean isComplete()
    {
        return score>4;
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
        clickx = mousex-4;
        clicky = mousey-4;
    }

    public void drag(int mousex, int mousey)
    {
        if(reload>15)
        {
            clickx = mousex-4;
            clicky = mousey-4;
        }
    }

    public void mouseUpdate(int mousex, int mousey){}

    public String getDescription()
    {
        return "CLICK (Use Mouse)";
    }

    public void run()
    {
        while(true)
        {
            Rectangle mouse = new Rectangle(clickx, clicky, 15, 15);
            for(int p=0; p<5; p++)
            {
                Rectangle dots = new Rectangle(x[p], y[p], 65, 65);
                if(mouse.intersects(dots))
                {
                    x[p]=4000;
                    score++;
                }
            }
            reload++;
            if(reload > 20)
                reload = 0;
            try{main.sleep(25);} catch(Exception e) {}
        }
    }
}