import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Gamemode extends Actor
{
    private GreenfootImage[] state = new GreenfootImage[2];
    private boolean isPractice = false;
    
    // Initialize images
    public Gamemode() {
        state[0] = new GreenfootImage("Gamemode1.png");
        state[1] = new GreenfootImage("Gamemode2.png");
    }
    
    public boolean getIsPractice() {
        return isPractice;
    }
    
    // A method to switch gamemode 
    public void switchMode() {
        if (isPractice == false) {
            isPractice = true;
        }
        else {
            isPractice = false;
        }
    }
    
    // Match its current image whenever a switch happens
    public void act() 
    {
        if (isPractice == false) {
            setImage(state[0]);
        }
        else {
            setImage(state[1]);
        }     
    }    
}
