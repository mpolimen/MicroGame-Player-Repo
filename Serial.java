

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;

public class Serial
{
    private String url;
    public Serial(String inUrl)
    {
        url=inUrl;
    }

    public String getString()
    {
        return url;
    }

    public String readFile()
    {
        String out="";
        try
        {        
            File f = new File(url);
            Scanner scan = new Scanner(f);
            while(scan.hasNext())
                out+=scan.next()+" ";
        }
        catch(FileNotFoundException e) {}
        return out;
    }

    public void editFile(String in)
    {
        try
        {
            Formatter f = new Formatter(url);
            f.format("%s",in);
            f.close();
        }
        catch(Exception e) {}
    }   

    public String convertString()
    {
        String line = null;
        StringBuilder  stringBuilder = new StringBuilder();

        try
        {
            File f = new File(url);
            Scanner scan = new Scanner(f);
            while(scan.hasNext())
            {
                line=scan.next();
                stringBuilder.append(line+" ");
            }

        }
        catch(FileNotFoundException e) {}
        return stringBuilder.toString();
    }
}

