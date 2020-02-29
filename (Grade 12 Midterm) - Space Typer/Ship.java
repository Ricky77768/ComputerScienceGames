import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Ship "shoots" a Bullet to destroy a Obstacle. It will move to the Y-Pos of target Obstacle when shooting
 * 
 * @author (Charlie) 
 * @date (April 11, 2019)
 */
public class Ship extends Actor
{
    private int currentImage = 0; // Current image of the Ship animation cycle
    private GreenfootImage[] shipCycle = new GreenfootImage[3]; // Images of the Ship
    private SimpleTimer delay = new SimpleTimer(); // A delay for switching between the images
    
    // Constructor
    public Ship() { 
        // Loads images
        for (int i = 0; i < shipCycle.length; i++) {   
            shipCycle[i] = new GreenfootImage("ship" + (i + 1) + ".png");
        }
    }
    
    public void act() 
    {   
        // Switches Images
        if (delay.millisElapsed() > 200) {
            this.setImage(shipCycle[currentImage]);
            if (currentImage == 2) {
                currentImage = 0;
            }
            else {
                currentImage++;
            }
            delay.mark();
        }
    }
}
