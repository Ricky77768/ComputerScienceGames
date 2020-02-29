import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The screen that shows up after gameover, and displays the user's score and a button to play the game again
 *
 * @author (Charlie) 
 * @date (April 11, 2019)
 */
public class EndScreen extends World
{
    // Constructor
    public EndScreen(int score)
    {    
        super(800, 500, 1);  
        setPaintOrder(Again.class,Flashlight.class);
        
        Flashlight flashlight = new Flashlight();
        addObject(flashlight, 400, 250);
        Again button = new Again();
        addObject(button, 400, 400);
        Label label = new Label("Final Score", 40);
        addObject(label, 400, 250);
        Label label2 = new Label(score, 50);
        addObject(label2, 400, 300);
    }
}
