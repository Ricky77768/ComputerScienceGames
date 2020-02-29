import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Planet is the objective of the game - protecting it. The player loses if an Obstacle touches the Planet 
 * 
 * @author (Jason) 
 * @date (April 11, 2019)
 */

public class Planet extends Actor
{
    private GreenfootSound explode = new GreenfootSound("explode.mp3");
    public void act() 
    {
        // Gameover if a Obstacle touches it, and passes the player's score to the EndScreen
        if (isTouching(Obstacle.class)) {
            explode.play();
            EndScreen gameover = new EndScreen(Display.getScore());
            Greenfoot.setWorld(gameover);
        }
    }    
}
