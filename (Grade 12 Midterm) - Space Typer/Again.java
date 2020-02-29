import greenfoot.*;
import java.util.List;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
 
/**
 * A button that appears on the EndScreen that allows the player to restart the game
 * 
 * @author (Charlie) 
 * @date (April 11, 2019)
 */ 
public class Again extends Actor
{
    boolean mouseOver = false; // To determine if mouse is hovering over the button
 
    public void act()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();  
         
        if (!mouseOver && Greenfoot.mouseMoved(this))  
        {  
            setImage("playagain.png");  
            mouseOver = true;  
        }  
        
        if (mouseOver && Greenfoot.mouseMoved(null) && ! Greenfoot.mouseMoved(this))  
        {  
            setImage("playagain2.png");  
            mouseOver = false;  
        } 
 
        if (Greenfoot.mouseClicked(this))  
        {  
            Greenfoot.setWorld(new MyWorld());
        }   
    }   
} 

