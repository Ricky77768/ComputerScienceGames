import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The world that shows instuctions on how to play the game
 * 
 * @author (Jason, Picture by Ricky) 
 * @date (April 11, 2019)
 */
public class Instructions extends World
{
    // Constructor
    public Instructions()
    {    
        super(800, 500, 1); 
    }
    
    public void act(){
        if (Greenfoot.isKeyDown("Enter")) {
            Greenfoot.setWorld(new MyWorld());
        }
        
        if (Greenfoot.isKeyDown("backspace")) {
            Greenfoot.setWorld(new StartScreen());          
        }
    }
}
