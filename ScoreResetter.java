

public class ScoreResetter
{
    public static void main(String args [])
    {
        //         LeaderBoard b = new LeaderBoard();
        //         HighScore h = new HighScore("Marco", 1);
        //         b.resetAllScores();
        //         b.updateScore(h);
        //         System.out.println(b);
        Serial s = new Serial("F:\\FinalProject/HighScores.txt");
        s.editFile("1.P1@@@0***2.P1@@@0***3.P1@@@0***4.P1@@@0***5.P1@@@0***6.P1@@@0***7.P1@@@0***8.P1@@@0***9.P1@@@0***10.P1@@@0***");
        //         System.out.println(s.convertString());
    }
}

