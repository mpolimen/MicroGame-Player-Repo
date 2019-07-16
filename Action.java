

import java.awt.*;
import javax.swing.*;
public class Action extends MiniGame implements Runnable
{
    private Thread main = new Thread(this);
    private int move;
    private int score = 0;
    private String output = "";
    private boolean correct = false;
    public Action()
    {
        main.start();   
        move = (int)(Math.random()*5);
    }

    public void draw(Graphics2D g2, JPanel panel)
    {
        if(score >= 0 && score < 8)
        {
            g2.setColor(Color.black);
            g2.drawString(output, 600, 500);
            g2.setColor(Color.magenta);
            g2.drawString(score+"", 1100, 50);
        }
    }

    public boolean isComplete()
    {
        return score > 7;
    }

    public void up()
    {
        if(move == 2)
        {
            correct = true;
        }
    }

    public void down()
    {
        if(move == 3)
        {
            correct = true;
        }
    }

    public void right()
    {
        if(move == 1)
        {
            correct = true;
        }
    }

    public void left()
    {
        if(move == 0)
        {
            correct = true;
        }
    }

    public void space()
    {
        if(move == 4)
        {
            correct = true;
        }
    }

    public void click(int mousex, int mousey)
    {        
    }

    public void drag(int mousex, int mousey){}

    public void mouseUpdate(int mousex, int mousey){}

    public String getDescription()
    {
        return "DO THE ACTION 8 TIMES (Use Keys)";
    }

    public void run()
    {
        while(true)
        {
            if(move == 0)
                output = "Press Left";
            else if(move == 1)
                output = "Press Right";
            else if(move == 2)
                output = "Press Up";
            else if(move == 3)
                output = "Press Down";
            else
                output = "Press Space";
            try{main.sleep(25);} catch(Exception e) {}
        }
    }

    public void keyRelease(boolean released)
    {
        if(score >= 0 && score < 8)
        {
            if(correct)
            {
                score++;
                move = (int)(Math.random()*5);
                correct = false;
            }
            else
                score = -1000;
        }
    }
}