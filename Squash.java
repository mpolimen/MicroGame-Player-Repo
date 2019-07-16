

import java.awt.*;
import javax.swing.*;
public class Squash extends MiniGame implements Runnable //Minigame idea by Ronan Ryan
{
    private Thread main = new Thread(this);
    private boolean dead=false;
    private int x=100, y=500;
    private int var=40;
    private int x2V=-15, x2=1200 , turn=0;
    public Squash()
    {
        main.start();   
    }

    public void draw(Graphics2D g2, JPanel panel)
    {
        g2.setColor(Color.green);
        g2.fillRect(x, y, 50, 50);
        g2.setColor(Color.red);
        if(!dead)
            g2.fillRect(x2, 500, 50, 50);
    }

    public boolean isComplete()
    {
        return dead;
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

    public void space(){}

    public void click(int mousex, int mousey){}

    public void drag(int mousex, int mousey){}

    public void mouseUpdate(int mousex, int mousey){}

    public String getDescription()
    {
        return "SQUASH (Use Arrows)";
    }

    public void run()
    {
        while(true)
        {
            Rectangle player = new Rectangle(x, y, 50, 50);
            Rectangle enemy = new Rectangle(x2, 500, 50, 50);
            y-=var;
            var-=2;
            if(y>=500)
                var=35;
            if(player.intersects(enemy))
                dead=true;

            if(!dead)
                x2+=x2V;
            turn++;
            if(turn>50)
            {
                turn=(int)(Math.random()*15);
                if(x2V > 0)
                    x2V = -1*(x2V+((int)(Math.random()*15)));
                else
                    x2V = -1*(x2V+((int)(Math.random()*15)));
            }
            if(x2<=0)
            {
                x2V = -x2V;
                x2+=5;
            }
            if(x2>=1200)
            {
                x2V = -x2V;
                x2-=5;
            }

            if(x2V < 0 && x2V> -15)
                x2V = -15;
            if(x2V > 0 && x2V < 15)
                x2V = 15;
            try{main.sleep(25);} catch(Exception e) {}
        }
    }
}