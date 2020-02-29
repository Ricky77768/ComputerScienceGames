import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The bullet spawns each time a word is successfully typed. It will then travel to that specific 
 * Obstacle and remove it, along with itself, from the World.
 * 
 * @author (Ricky) 
 * @date (April 11, 2019)
 */
public class Bullet extends Actor
{
    private int currentImage = 0; // Integer to keep track which image is the Bullet
    private GreenfootImage[] bulletCycle = new GreenfootImage[4]; // Array that stores the Bullet's animations
    private Obstacle target; // The Obstacle that this Bullet can remove (Determined at Constructor)
    private Obstacle touched; // The Obstacle that this currently touching, if any
    private GreenfootSound bulletfire = new GreenfootSound("bullet_fire.mp3");
    private GreenfootSound explode = new GreenfootSound("explode.mp3");
    private SimpleTimer delay = new SimpleTimer(); // Delay between the animation of Bullet
    
    // Constuctor (Gives each Bullet an Obstacle that it can clear) 
    public Bullet(Obstacle goal) {
        explode.setVolume(20);
        bulletfire.setVolume(25);
        bulletfire.play();
        target = goal;
        
        // Load images
        for (int i = 0; i < bulletCycle.length; i++) {   
            bulletCycle[i] = new GreenfootImage("bullet" + (i + 1) + ".png");
        }
    }
    
    public void act() 
    {
        MyWorld world = (MyWorld) getWorld();
        move(8);
        
        // Switching between the different images
        if (delay.millisElapsed() > 50) {
            this.setImage(bulletCycle[currentImage]);
            if (currentImage == 3) {
                currentImage = 0;
            }
            else {
                currentImage++;
            }
            delay.mark();
        }  
        
        // Bug Prevention - If not removed at the edge of the world, remove
        if (isAtEdge()) {
            world.removeObject(this);
        }
        
        /* Gets a reference of that Obstacle when it touches one. If the reference matches with the
         * target reference, it will remove itself along with the Obstacle
         */
        if (isTouching(Obstacle.class)) {
            touched = getIntersectingObjects(Obstacle.class).get(0);
            
            if (touched == target) {
                explode.play();
                target.removeLabel();
                target.explode();
                world.removeObject(this);
            }
        }
    }    
}
