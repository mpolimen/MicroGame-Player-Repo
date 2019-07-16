

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
public class Pong extends MiniGame implements Runnable
{
    private Thread main = new Thread(this);
    private int y = 300;
    private int ballX = 500, ballY;
    private int varX = -12, varY = 10;
    private int wait = 0;
    public Pong()
    {
        main.start();
        ballY = (int)(Math.random()*500)+100;
    }

    public void draw(Graphics2D g2, JPanel panel)
    {
        g2.setColor(Color.green);
        g2.fillRect(50, y, 20, 130);
        g2.setColor(Color.gray);
        g2.fillRect(950, 0, 20, 700);
        g2.setColor(Color.black);
        g2.fillOval(ballX, ballY, 40, 40);
    }

    public boolean isComplete()
    {
        return ballX>0;
    }

    public void up()
    {
        y-=25;
    }

    public void down()
    {
        y+=25;
    }

    public void right()
    {

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
            Rectangle player = new Rectangle(65, y, 5, 130);
            Rectangle enemy = new Rectangle(950, 0, 20, 700);
            Rectangle ball = new Rectangle(ballX, ballY, 40, 40);
            if(player.intersects(ball))
            {
                ballX+=5;
                varX = -varX;
            }
            if(enemy.intersects(ball))
            {
                ballX-=5;
                varX = -varX;
            }
            ballX+=varX;
            ballY+=varY;
            if(ballY>=650)
            {
                ballY-=5;
                varY = -varY;
            }
            if(ballY<=0)
            {
                ballY+=5;
                varY = -varY;
            }
            wait++;
            if(wait>25)
            {
                if(varX > 0)
                    varX+=(int)(Math.random()*4)+1;
                else
                    varX-=(int)(Math.random()*4)+1;
                if(varY > 0)
                    varY+=(int)(Math.random()*5);
                else
                    varY-=(int)(Math.random()*5);
                wait = 0;
            }
            try{main.sleep(20);} catch(Exception e) {}
        }
    }

    public String getDescription()
    {
        return "PONG (Use Up and Down)";
    }
}