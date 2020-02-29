import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Moving platforms that move from side to side at a fixed rate, it will go in the opposite direction once they hit their limit
 * 
 * @author (Ricky Lu) 
 * @date (Jan. 18th)
 */
public class movingPlatformH extends Actor
{
    private int minX; // Leftmost point the platform will move to
    private int maxX; // Rightmost point the platform will move to
    private int speed; // Speed the platform moves at
    private boolean goingRight; // If it is currently going right
    
    public movingPlatformH(int leftMost, int rightMost, int theSpeed, boolean isRight) {
        minX = leftMost;
        maxX = rightMost;
        speed = theSpeed;
        goingRight = isRight;
    }
    
    public int getMaxX() {
        return maxX;
    }
    
    public int getMinX() {
        return minX;
    }
    
    public int getSpeed() {
        return speed;
    }
    
    public boolean getGoingRight() {
        return goingRight;
    }
    
    public void act() 
    {
        // Check if the platform has reached leftmost or rightmost point
        if (getX() > maxX) {
            goingRight = false;
        }
        else if (getX() < minX) {
            goingRight = true;
        }        
        
        // Check whether the platform should move left or right
        if (goingRight == true) {
            setLocation(getX() + speed, getY());
        }
        else {
            setLocation(getX() - speed, getY());
        }
    }   
}
