

import java.awt.*;
import javax.swing.*;
public class Bounce extends MiniGame implements Runnable
{
    private Thread main = new Thread(this);
    private int x = 600;
    private int ballX, ballY = 100;
    private int varX = 15, varY = 15;
    private boolean lost = false;
    private int xPause = 0;
    public Bounce()
    {
        main.start();
        ballX = (int)(Math.random()*1100);
    }

    public void draw(Graphics2D g2, JPanel panel)
    {
        g2.setColor(Color.green);
        g2.fillRect(x, 550, 225, 50);
        g2.setColor(Color.red);
        g2.fillOval(ballX, ballY, 60, 60);
    }

    public boolean isComplete()
    {
        return !lost;
    }

    public void up(){}

    public void down(){}

    public void right()
    {
        x += 40;
    }

    public void left()
    {
        x -= 40;
    }

    public void space(){}

    public void click(int mousex, int mousey){}

    public void drag(int mousex, int mousey){}

    public void mouseUpdate(int mousex, int mousey){}

    public void run()
    {
        while(true)
        {
            Rectangle player = new Rectangle(x, 550, 225, 10);
            Rectangle ball = new Rectangle(ballX, ballY, 60, 60);           
            ballX += varX;
            ballY += varY;
            if(ballX <= 0)
                varX = 15;
            if(ballX >= 1200)
                varX = -15;
            if(player.intersects(ball))
            {
                varY = -15;
                ballY-=20;
                xPause-=2;
            }
            if(ballY <= 0)
                varY = 15;
            if(ballY > 750)
                lost = true;
            xPause++;
            if(xPause >= 120)
            {
                varX *= -1;
                xPause = (int)(Math.random()*70)+30;
            }
            try{main.sleep(20);} catch(Exception e) {}
        }
    }

    public String getDescription()
    {
        return "DON'T LET IT FALL (Use Arrows)";
    }
}