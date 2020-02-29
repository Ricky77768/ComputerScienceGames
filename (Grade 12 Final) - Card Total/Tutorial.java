import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the Actor that tells the player how to play the game, can be navigated using two buttons.
 * 
 * @author (Jason, Charlie) 
 * @version (a version number or a date)
 */
public class Tutorial extends Actor
{
    private int step = 1; // The current image it should display (1-4)
    private GreenfootImage[] steps = new GreenfootImage[4]; // Array containing all images
    
    // Default Constructor - Loads all the images
    public Tutorial() {
       steps[0] = new GreenfootImage("Step 1.png");
       steps[1] = new GreenfootImage("Step 2.png");
       steps[2] = new GreenfootImage("Step 3.png");
       steps[3] = new GreenfootImage("Step 4.png");
    }
    
    /**
     * Act - do whatever the Tutorial wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        setImage(steps[step - 1]);
    }    
    
    // Returns the current step
    public int getStep() {
        return step;
    }
    
    // Changes the current step, by 1, up or down
    public void changeStep(boolean add) {
        if (add) {
            step++;
        } else {
            step--;
        }
    }
}
