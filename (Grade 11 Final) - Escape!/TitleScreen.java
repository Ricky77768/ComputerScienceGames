import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This world is the first world that players will see, acts like an introdcution
 * 
 * @author (Ricky Lu) 
 * @date (Jan.18th)
 */
public class TitleScreen extends World
{
    public TitleScreen()
    {    
        super(800, 500, 1);
    }
    
    public void act()
    {
        if(Greenfoot.isKeyDown("enter")) {
            Greenfoot.setWorld(new MyWorld());
        }
    }
}
