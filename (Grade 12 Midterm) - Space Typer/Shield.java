import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * A protection layer for the Planet. It removes all Obstacle on screen if one touches it. It has 2 charges.
 * Once all 2 charges are used, it dissapears.
 * 
 * @author (Ricky, Charlie) 
 * @date (April 11, 2019)
 */
public class Shield extends Actor
{
    private int shields = 2; // Amount of uses left
    private boolean labelCreated = false; // One time Label creation
    private GreenfootSound shieldBreak = new GreenfootSound("shield_break.mp3");
    
    public void act() 
    {
        MyWorld world = (MyWorld) getWorld();
        
        // One time Label creation
        if (!labelCreated) {
            LabelT currentShields = new LabelT("Shields: " + shields + "/2", 30);
            world.addLabel(currentShields, 100, 470);
            labelCreated = true;
        }
    }    
    
    // Method to clear all Obstacle/Bullet on screen
    public void clearAllObstacles(MyWorld world) {
        shieldBreak.play();
        shields--;
        
        for (Obstacle o : world.getObjects(Obstacle.class)) {
            o.removeLabel();
            world.removeObject(o);
            
            // Bug Prevention - To make sure difficulty still progresses properly
            Display.setCleared(Display.getCleared() + 1);
        }
        
        for (Bullet b : world.getObjects(Bullet.class)) {
            world.removeObject(b);
        }
        
        // Removes the Shield itself if no charges left
        if (shields == 0) {
            world.removeObject(world.getObjects(Shield.class).get(0));
        }
        
        // Updates th display for shield, changes color based on the charges left
        LabelT currentShields = new LabelT("Shields: " + shields + "/2", 30);
        if (shields == 1) {
            currentShields.setFillColor(Color.YELLOW);
            this.setImage(new GreenfootImage("shield2.png"));
        }
        else if (shields == 0) {
            currentShields.setFillColor(Color.RED);
        }
        
        world.updateLabel(currentShields, 100, 470);
    }
}
