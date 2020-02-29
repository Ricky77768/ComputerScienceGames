import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Similiar to CollisionB, except it switches states when the player jumps
 * One state it has collisions, the other state does not
 * 
 * @author (Ricky Lu) 
 * @date (Jan. 18th)
 */
public class JumpSwapB extends Actor
{
    private boolean visibility; // Determines if the block is on or off
    
    public JumpSwapB(boolean isOn) {
        visibility = isOn;
    }
   
    public boolean getVisibility() {
        return visibility;
    }
    
    // A function to switch state
    public void switchState() {
        if (visibility == false) {
            visibility = true;
        }
        else {
            visibility = false;
        }
    }
    
    // Visual help for distinguishing states (more transparency = collision is OFF)
    public void act() 
    {
        if (visibility == true) {
            getImage().setTransparency(255);
        }
        else {
            getImage().setTransparency(100);
        }   
    }    
}
