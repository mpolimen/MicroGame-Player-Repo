

import java.awt.*;
import javax.swing.*;
public class Snake extends MiniGame implements Runnable //Minigame idea by Dobie Memes
{
    private Thread main = new Thread(this);
    private int bodyPartSize=10;
    private int bodyParts=20;
    private int x [] = new int [200];
    private int y [] = new int [200];
    private int foodx=0, foody=0;
    private boolean left=false, right=true, up=false, down=false;
    private boolean fed=false;
    public Snake()
    {
        main.start();   
        for(int p=0; p<bodyParts; p++)
        {
            x[p]=50-p*10;
            y[p]=50;
        }
        foodx=(int)(Math.random()*1000)+200;
        foody=(int)(Math.random()*200)+400;
    }

    private void move()
    {
        for(int p=bodyParts-1; p>0; p--)
        {
            x[p]=x[p-1];
            y[p]=y[p-1];
        }
        if(left)
            x[0]-=bodyPartSize;
        if(right)
            x[0]+=bodyPartSize;
        if(up)
            y[0]-=bodyPartSize;
        if(down)
            y[0]+=bodyPartSize;
    }

    public void draw(Graphics2D g2, JPanel panel)
    {
        for(int p=0; p<bodyParts; p++)
        {
            g2.setColor(Color.green);
            g2.fillOval(x[p],y[p],bodyPartSize,bodyPartSize);
        }
        g2.setColor(Color.magenta);
        if(!fed)
            g2.fillRect(foodx,foody,10,10);
    }

    public boolean isComplete()
    {
        return fed;
    }

    public void up()
    {
        up=true;
        right=false;
        left=false;
    }

    public void down()
    {
        down=true;
        right=false;
        left=false;
    }

    public void right()
    {
        right=true;
        up=false;
        down=false;
    }

    public void left()
    {
        left=true;
        up=false;
        down=false;
    }

    public void space(){}

    public void click(int mousex, int mousey){}

    public void drag(int mousex, int mousey){}

    public void mouseUpdate(int mousex, int mousey){}

    public String getDescription()
    {
        return "COLLECT (Use Arrows)";
    }

    public void run()
    {
        while(true)
        {
            Rectangle head = new Rectangle(x[0],y[0],bodyPartSize,bodyPartSize);
            Rectangle food = new Rectangle (foodx, foody, 10,10);
            if(head.intersects(food))
                fed=true;
            move();
            try{main.sleep(25);} catch(Exception e) {}
        }
    }
}