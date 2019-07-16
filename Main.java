

import javax.swing.*;
import java.awt.*;
public class Main
{
    public Main()
    {
        JFrame frame = new JFrame();
        frame.setTitle("WarioWare 2.0");
        frame.setSize(1335,700);
        frame.setResizable(false);
        frame.setLocation(15,15);
        frame.add(new Game());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        Image icon;
        ImageIcon i = new ImageIcon(this.getClass().getResource("GameIcon.png"));
        icon = i.getImage();
        frame.setIconImage(icon);
    }


    /*
     * Press OK To Start
     */
    public static void main(String args [])
    {
        new Main();
    }
}

