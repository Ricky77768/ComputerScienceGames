import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class contains the slidable buttons that lets users to choose different gameplay parameters
 * on "Free Mode".
 * 
 * @author (Ricky) 
 * @version (June 9, 2019)
 */
public class Slider extends Actor
{
    private static final int SLIDING_DISTANCE = 300; // How far the user can drag each button
    private int startX; // The starting X position of the button
    private int type; // Dictates what kind of value the button should hold
    private double value; // The value (gameplay parameter) it currently holds, depend on its "type"
    
    // Default Constructor
    public Slider(int startingX, int theType) {
        startX = startingX;
        type = theType;
    }
    
    // Returns the type of this button
    public int getType() {
        return type;
    }
    
    // Returns the value of this button
    public double getValue() {
        return value;
    }
    
    /**
     * Act - do whatever the Slider wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // If the mouse is holding on a slider and within range, moves the button horizontally to mouse location
        if (Greenfoot.mouseDragged(this) && getX() >= startX && getX() <= startX + SLIDING_DISTANCE)
        {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            setLocation(mouse.getX(), getY());
        }
        
        // Sets the button in range if it goes out of bounds
        if (getX() < startX) {
            setLocation(startX, getY());
        } else if (getX() >= startX + SLIDING_DISTANCE) {
            setLocation(startX + SLIDING_DISTANCE, getY());
        }
        
        // Calculates its value based on how far the slider is from current location
        if (type == 1) {
            value = (getX() - startX) / (double) SLIDING_DISTANCE * 7 + 3;
        } else {
            value = (getX() - startX) / (double) SLIDING_DISTANCE * 99 + 1;
        }
        
        // Displays the value into the world
        Label label = new Label((int) value, 45);
        getWorld().removeObjects(getWorld().getObjectsAt(startX + SLIDING_DISTANCE - 50, getY() - 50, Label.class));
        getWorld().addObject(label, startX + SLIDING_DISTANCE - 50, getY() - 50);
    }  
}
