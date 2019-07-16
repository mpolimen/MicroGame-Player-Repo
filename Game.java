

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Game extends JPanel implements KeyListener, Runnable, MouseListener, MouseMotionListener
{
    private Thread main = new Thread(this);
    private Font big = new Font("Bold",1,71);
    private Font med = new Font("Bold",1,50);
    private Font small = new Font("Bold",1,20);
    private MiniGame [] games = new MiniGame[20];
    private boolean right=false;
    private boolean left=false;
    private boolean up=false;
    private boolean down=false;
    private boolean space=false;
    private int gameIndex = 0;
    private int score=0;
    private int pageCt=0;
    private int wait=0;
    private int hp=4;
    private boolean scored = false;
    private boolean isComplete = true;
    private String name="";
    private LeaderBoard scores = new LeaderBoard();
    public Game()
    {
        super.setDoubleBuffered(true);
        main.start();
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        initGames();
    }

    public void initGames()
    {
        games[0] = new CatRain();
        games[1] = new Dodge();
        games[2] = new Snake();
        games[3] = new Squash();
        games[4] = new ClickSquares();
        games[5] = new Shooter();
        games[6] = new Flappy();
        games[7] = new Tap();
        games[8] = new Hurdle();
        games[9] = new Bounce();
        games[10] = new Intersect();
        games[11] = new BlockGrab();
        games[12] = new TouchDots();
        games[13] = new RunAway();
        games[14] = new BallBounce();
        games[15] = new Pong();
        games[16] = new Platformer();
        games[17] = new Action();
        games[18] = new Search();
        games[19] = new Fly();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if(pageCt==0)
        {
            g2.drawString("Click to begin...", 700, 350);
            g2.setFont(small);
            g2.drawString("Play through minigames and complete them to score. If you lose, you lose a life.", 50, 50);
            g2.drawString("You control a green player in all games with a green object. If there is no green object the rules will make sense otherwise.", 50, 100);
            g2.drawString("For games with the mouse. If you are required to click, keep the mouse generally steady when clicking for better detection.", 50, 150);
            g2.drawString("For games with keys. Pressing multiple keys at once may register as no key being pressed. Also, keys can be held in every game.", 50, 200);
            g2.drawString("DISCLAIMER: The games were made and tested to be difficult, but all are fair. Most games have a completely random element.", 50, 250);
            g2.drawString("The first couple times through you'll probably lose, so practice up! Enjoy!", 50, 300);
        }
        if(pageCt==1)
        {
            g2.drawString("Waiting...", 600, 300);
            g2.setFont(big);
            g2.drawString("HP:"+hp, 500, 400);
            g2.drawString("GAMES COMPLETED "+score, 500, 500);
            if(score>0 || hp<4)
            {
                if(isComplete)
                {
                    g2.setColor(Color.green);
                    g2.drawString("SUCCESS", 500, 200);
                }
                else
                {
                    g2.setColor(Color.red);
                    g2.drawString("FAILURE", 500, 200);
                }
            }
        }
        if(pageCt==2)
        {
            g2.setColor(Color.red);
            g2.setFont(big);
            games[gameIndex].draw(g2, this);
            g2.setColor(Color.darkGray);
            g2.drawString(wait+"", 50, 90);
            g2.setColor(Color.red);
            g2.drawString(games[gameIndex].getDescription(), 50, 300);
        }
        if(pageCt==3)
        {
            g2.setFont(big);
            g2.drawString("You Lose!", 100, 300);
            g2.drawString("Scored: "+score, 100, 375);
            g2.setFont(med);
            for(int p=0; p<10; p++)
            {
                g2.drawString((p+1)+". "+scores.getList().get(p).getName()+"  "+scores.getList().get(p).getScore(), 500, 65*p+50);
            }
            g2.setFont(small);
            if(scored)
                g2.drawString("Name: "+name,10 ,500);
            else
                g2.drawString("Press R to Restart",10 ,500);
        }
    }

    public void run()
    {
        while(true)
        {
            repaint();
            try{main.sleep(25);} catch(Exception e) {}
            if(pageCt==1)
            {
                wait++;
                if(wait>100)
                {
                    gameIndex = (int)(Math.random()*games.length);
                    //                     gameIndex=19;
                    pageCt=2;
                    wait=200;
                    initGames();
                }
            }
            if(pageCt==2)
            {
                wait--;
                if(wait<=0)
                {
                    if(!games[gameIndex].isComplete())
                    {
                        hp--;
                        isComplete = false;
                    }
                    else
                    {
                        score++;
                        isComplete = true;
                    }
                    if(hp!=0)
                        pageCt=1;
                    else
                    {
                        pageCt=3;
                        if(scores.isValid(score))
                            scored = true;
                    }
                    wait=0;
                }
                if(up)
                {
                    games[gameIndex].up();
                }
                if(right)
                {
                    games[gameIndex].right();
                }
                if(left)
                {
                    games[gameIndex].left();
                }
                if(down)
                {
                    games[gameIndex].down();
                }
                if(space)
                {
                    games[gameIndex].space();
                }
            }
        }
    }

    public void keyPressed(KeyEvent e)
    {
        int code=e.getKeyCode();
        if(code==KeyEvent.VK_UP)
        {
            up=true;
        }
        if(code==KeyEvent.VK_RIGHT)
        {
            right=true;
        }
        if(code==KeyEvent.VK_LEFT)
        {
            left=true;
        }
        if(code==KeyEvent.VK_DOWN)
        {
            down=true;
        }
        if(code==KeyEvent.VK_SPACE)
        {
            space=true;
        }
        repaint();
    }

    public void keyReleased(KeyEvent e)
    {
        int code=e.getKeyCode();
        right=false; left=false; up=false; down=false; space=false;
        if(scored && code==KeyEvent.VK_ENTER)
        {
            scored = false;
            if(name.length()>29)
                name = name.substring(0,29);
            if(name.indexOf("@@@")>-1 || name.indexOf("***")>-1)
                name = "Player";
            scores.updateScore(new HighScore(name.substring(0, name.length()), score));
        }
        if(scored && code!=KeyEvent.VK_ENTER && code!=KeyEvent.VK_BACK_SPACE && code!=KeyEvent.VK_SHIFT)
            name += ""+e.getKeyChar();
        if(scored && code==KeyEvent.VK_BACK_SPACE)
        {
            name = name.substring(0, name.length()-1);
        }
        if(!scored && pageCt==3 && code==KeyEvent.VK_R)
        {
            score = 0;
            hp = 4;
            name = "";
            wait = 0;
            pageCt = 0;
        }
        if(gameIndex == 17)
            ((Action)(games[gameIndex])).keyRelease(true);
        repaint();
    }

    public void keyTyped(KeyEvent e)
    {
        int code=e.getKeyCode();

    }

    public void mouseClicked(MouseEvent e)
    {
        int mousex=e.getX();
        int mousey=e.getY();
        if(pageCt==0)
            pageCt=1;
        if(pageCt==2)
            games[gameIndex].click(mousex, mousey);
    }

    public void mouseExited(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseMoved(MouseEvent e)
    {
        int mousex=e.getX();
        int mousey=e.getY();
        if(pageCt==2)
            games[gameIndex].mouseUpdate(mousex, mousey);
    }

    public void mouseDragged(MouseEvent e)
    {
        int mousex=e.getX();
        int mousey=e.getY();
        if(pageCt==2)
            games[gameIndex].drag(mousex, mousey);
    }
}

