import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

public class HighScore {
    
    public File scoreFile = new File("HighScore.txt");
    public File backUpFile = new File("BackUpScores.txt");

    public HighScore(){
        try{
            scoreFile.createNewFile();
            backUpFile.createNewFile();
        }catch(Exception e){
            e.printStackTrace();
        }   
    }


    public void addScore(int score){
        try{
        FileWriter fw1 = new FileWriter(scoreFile, true);
        FileWriter fw2 = new FileWriter(backUpFile, true);
        fw1.write(score+"\n");
        fw2.write(score+"\n");
        fw1.close();
        fw2.close();
        }catch(Exception e){
            System.out.println("Failed");
            e.printStackTrace();
        }
        
    }

    public void resetScore(){
        try{
        scoreFile.delete();
        scoreFile.createNewFile();
        FileWriter fw = new FileWriter(scoreFile);
        //fw.write("0"+"\n");
        fw.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }


    public int getHighestScore(){
        int highest = 0;
        try {
            Scanner scan = new Scanner(scoreFile);
            while(scan.hasNextLine()){
                int num = Integer.valueOf(scan.nextLine());
                if(num>highest){
                    highest = num;
                }
            }
            return highest;
        } catch (Exception e) {
            return highest;
        }
    }

}      

    
