 

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
public class LeaderBoard
{
    private ArrayList<HighScore> list;
    private Serial s;

    public LeaderBoard()
    //Original names in format of: 1.P1@@@0 2.P1@@@0
    {
        list = new ArrayList<HighScore>();
        s = new Serial("D:\\FinalProject/HighScores.txt");
        String file = s.convertString();
        /*
         * *
        ATTENTION!!! If you're having a runtime error when playing the game fix it like this...
        Go into the final project folder and find HighScores.txt
        Right click and select properties. Find the path and make the String above equal to the path. (Remember to double up on forward slashes in the String.)
        s = new Serial("F:\\FinalProject/HighScores.txt"); Is an example of the name of the path and where it should go in the code.
        The first letter will usually be the problem and if you can't find the path change only the first capital letter accordingly... 
        If the game is on a flashdrive the first letter will either be E or F. If on the school's hardrive it will be an H.
         * *
         */
        for(int p=0; p<10; p++)
        {
            String tempName = file.substring(file.indexOf((p+1)+".")+2, file.substring(file.indexOf((p+1)+".")).indexOf("@@@")+file.indexOf((p+1)+"."));
            int tempNum = Integer.parseInt(file.substring(file.substring(file.indexOf((p+1)+".")).indexOf("@@@")+3+file.indexOf((p+1)+"."), file.substring(file.indexOf((p+1)+".")).indexOf("***")+file.indexOf((p+1)+".")));
            if(p==9)
                tempName = file.substring(file.indexOf((p+1)+".")+3, file.substring(file.indexOf((p+1)+".")).indexOf("@@@")+file.indexOf((p+1)+"."));
            list.add(new HighScore(tempName, tempNum));
        }

    }

    public boolean isValid(int score)
    {
        return list.get(9).getScore()<score;
    }

    public int findRankIndex(int score)
    {
        if(!isValid(score))
            return -1;
        for(int p=8; p>=0; p--)
        {
            if(list.get(p).getScore()==score)
                return p+1;
            if(list.get(p).getScore()>score)
                return p+1;
        }
        return 0;
    }

    public void updateScore(HighScore input)
    {
        if(findRankIndex(input.getScore())!=-1)
        {
            list.add(findRankIndex(input.getScore()), input);
            list.remove(10);
            s.editFile("1."+list.get(0).getName()+"@@@"+list.get(0).getScore()+"***"+
                "2."+list.get(1).getName()+"@@@"+list.get(1).getScore()+"***"+
                "3."+list.get(2).getName()+"@@@"+list.get(2).getScore()+"***"+
                "4."+list.get(3).getName()+"@@@"+list.get(3).getScore()+"***"+
                "5."+list.get(4).getName()+"@@@"+list.get(4).getScore()+"***"+
                "6."+list.get(5).getName()+"@@@"+list.get(5).getScore()+"***"+
                "7."+list.get(6).getName()+"@@@"+list.get(6).getScore()+"***"+
                "8."+list.get(7).getName()+"@@@"+list.get(7).getScore()+"***"+
                "9."+list.get(8).getName()+"@@@"+list.get(8).getScore()+"***"+
                "10."+list.get(9).getName()+"@@@"+list.get(9).getScore()+"***");
        }
    }

    public String toString()
    {
        return s.convertString();
    }

    public ArrayList<HighScore> getList()
    {
        return list;
    }

    public void resetAllScores()
    {
        s.editFile("1.P1@@@0***2.P1@@@0***3.P1@@@0***4.P1@@@0***5.P1@@@0***6.P1@@@0***7.P1@@@0***8.P1@@@0***9.P1@@@0***10.P1@@@0***");
    }
}

