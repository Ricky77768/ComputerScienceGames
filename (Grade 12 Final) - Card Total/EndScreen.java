import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The screen that will show up and display the user's score, as well as prompting the user to play again
 * with a button that restarts the game.
 * 
 * @author (Jason) 
 * @date (June 9, 2019)
 */
public class EndScreen extends World
{
    // Default Constructor
    public EndScreen(int score) {    
        super(1000, 500, 1); 
        Button again = new Button("Again");
        addObject(again, 500, 400);
        Label label1 = new Label("The End!", 50);
        addObject(label1, 500, 100);
        Label label2 = new Label("Your Score", 40);
        addObject(label2, 500, 150);
        Label label3 = new Label(score + " / 10000", 80);
        addObject(label3, 500, 250);
    }
}
