import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The screen that is presented to the user when started. Gives instructions on how to start the game
 * 
 * @author (Jason) 
 * @date (April 11, 2019)
 */
public class StartScreen extends World
{
    // Constructor
    public StartScreen()
    {    
        super(800, 500, 1); 
    }

    public void act(){
        if (Greenfoot.isKeyDown("Enter")) {
            Greenfoot.setWorld(new MyWorld());
        }

        if (Greenfoot.isKeyDown("I")) {
            Greenfoot.setWorld(new Instructions());          
        }
    }
}
