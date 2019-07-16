

import java.awt.*;
import javax.swing.*;
public class CatRain extends MiniGame implements Runnable
{
    private Image thing; //Do images later.
    private int x=140	, x2=150;
    private Thread main = new Thread(this);
    private int [] dropX = new int [200];
    private int [] dropY = new int [200];
    private boolean dry=true;
    private int turn=0;
    private int x2V=5;
    public CatRain()
    {
        main.start();
        ImageIcon i = new ImageIcon(this.getClass().getResource("CatWet.gif"));  thing = i.getImage();
        for(int p=0; p<200; p++)
        {
            dropX[p]=70*p;
            dropY[p]=(int)(Math.random()*100)*-1;
        }
    }

    public void draw(Graphics2D g2, JPanel panel)
    {
        //g2.drawImage(thing, x, y, 50, 50, panel);
        g2.setColor(Color.green);
        g2.fillRect(x, 500, 250 ,30);
        if(dry)
            g2.setColor(Color.yellow);
        else
            g2.setColor(Color.red);
        g2.fillRect(x2, 600, 50 ,50);
        g2.setColor(Color.cyan);
        for(int p=0; p<200; p++)
            g2.fillRect(dropX[p], dropY[p], 10 ,50);
    }

    public boolean isComplete()
    {
        return dry;
    }

    public void up(){}

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

    public void run()
    {
        while(true)
        {
            Rectangle cat = new Rectangle(x2, 600, 50 ,50);
            Rectangle umbrella = new Rectangle(x, 500, 250 ,30);
            for(int p=0; p<200; p++)
            {
                dropY[p]+=(int)(Math.random()*20)+50;
                if(dropY[p]>700)
                    dropY[p]=0;
                Rectangle raindrop=new Rectangle(dropX[p], dropY[p], 10 ,50);
                if(umbrella.intersects(raindrop))
                    dropY[p]=0;
                if(cat.intersects(raindrop))
                    dry=false;
            }
            if(x2>=1200)
            {
                turn=(int)(Math.random()*15);
                x2V = -x2V;
            }
            if(dry)
                x2+=x2V;
            turn++;
            if(turn>50)
            {
                turn=(int)(Math.random()*15);
                x2V = -1*x2V+((int)(Math.random()*15));
            }
            try{main.sleep(25);} catch(Exception e) {}
        }
    }

    public String getDescription()
    {
        return "COVER (Use Arrows)";
    }
}