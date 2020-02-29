import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A Screen that shows up if at the end of a practice run, displaying their stats
 * 
 * @author (Ricky Lu) 
 * @date (Jan.18th)
 */
public class PracticeEndScreen extends World
{
    public PracticeEndScreen(int timeTaken, int deathCount)
    {    
        super(800, 500, 1);
        Label label = new Label("Practice Complete", 60);
        addObject(label, 400, 180);
        Label label2 = new Label("Time Used: " + timeTaken, 40);
        addObject(label2, 400, 280);
        Label label3 = new Label("Deaths: " + deathCount, 40);
        addObject(label3, 400, 330);
    }
}
