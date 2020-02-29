import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Moving platforms that move from side to side at a fixed rate, it will go in the opposite direction once they hit their limit
 * 
 * @author (Ricky Lu) 
 * @date (Jan. 18th)
 */
public class movingPlatformV extends Actor
{
    private int maxY; // Highest point the platform will move to
    private int minY; // Lowest point the platform will move to
    private int speed; // Speed the platform moves at
    private boolean goingUp; // If it is currently going up
    
    public movingPlatformV(int highest, int lowest, int theSpeed, boolean isUp) {
        maxY = highest;
        minY = lowest;
        speed = theSpeed;
        goingUp = isUp;
    }
    
    public int getMaxY() {
        return maxY;
    }
    
    public int getMinY() {
        return minY;
    }
    
    public int getSpeed() {
        return speed;
    }
    
    public boolean getGoingUp() {
        return goingUp;
    }
    
    public void act() 
    {
        // Check if the platform has reached highest or lowest point
        if (getY() < maxY) {
            goingUp = false;
        }
        else if (getY() > minY) {
            goingUp = true;
        }

        // Determine if platform should move up and down
        if (goingUp == true) {
            setLocation(getX(), getY() - speed);
        }
        else {
            setLocation(getX(), getY() + speed);
        }
    }   
}
