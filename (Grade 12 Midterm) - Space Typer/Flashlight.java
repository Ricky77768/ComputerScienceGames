import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A image with a tranparent middle section to restrict view area when the max difficulty is reached
 * 
 * @author (Ricky, Jason) 
 * @date (April 11, 2019)
 */
public class Flashlight extends Actor
{
    public void act() 
    { 
        // Make the image (transparent section in the middle) to follow the player's mouse
        MouseInfo follow = Greenfoot.getMouseInfo();
        if (follow != null) {
            setLocation(follow.getX(), follow.getY());
        }
    }    
}
