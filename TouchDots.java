

import java.awt.*;
import javax.swing.*;
public class TouchDots extends MiniGame implements Runnable //Minigame idea by Michael Olsen
{
    private Thread main = new Thread(this);
    private int score = 0;
    private int xr, yr;
    private int x, y;
    public TouchDots()
    {
        main.start();   
        xr = (int)(Math.random()*900)+100;
        yr = (int)(Math.random()*550)+100;
    }

    public void draw(Graphics2D g2, JPanel panel)
    {
        g2.setColor(Color.magenta);
        if(score<9)
        {
            g2.fillOval(xr, yr, 65, 65);
            g2.drawString(score+"", 1150, 50);
        }
    }

    public boolean isComplete()
    {
        return score>8;
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
        return "TOUCH NINE DOTS (Use Mouse)";
    }

    public void run()
    {
        while(true)
        {
            Rectangle mouse = new Rectangle(x, y, 5, 5);
            Rectangle dots = new Rectangle(xr, yr, 65, 65);
            if(mouse.intersects(dots))
            {
                xr = (int)(Math.random()*900)+100;
                yr = (int)(Math.random()*550)+100;
                score++;
            }

            try{main.sleep(25);} catch(Exception e) {}
        }
    }
}