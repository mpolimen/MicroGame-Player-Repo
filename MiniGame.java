

import java.awt.*;
import javax.swing.*;
public abstract class MiniGame
{
    public abstract void draw(Graphics2D g2, JPanel panel);

    public abstract void up();

    public abstract void down();

    public abstract void right();

    public abstract void left();

    public abstract void space();

    public abstract void click(int mousex, int mousey);

    public abstract void drag(int mousex, int mousey);

    public abstract void mouseUpdate(int mousex, int mousey);

    public abstract boolean isComplete();

    public abstract String getDescription();
}