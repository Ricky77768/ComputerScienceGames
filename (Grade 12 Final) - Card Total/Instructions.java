import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Screen that shows up when the player is prompted to choose their gamemode. It also has instructions
 * 
 * @author (Jason) 
 * @version (June 9)
 */
public class Instructions extends World
{
    // Default Constructor
    public Instructions() {    
        super(1000, 500, 1); 
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare() {
        Button button = new Button("Challenge Mode");
        addObject(button, 300, 420);
        Button button2 = new Button("Free Mode");
        addObject(button2, 700, 420);
        Button button3 = new Button("Left Arrow");
        addObject(button3, 450, 330);
        Button button4 = new Button("Right Arrow");
        addObject(button4, 550, 330);
        
        Label label1 = new Label("10 Rounds, Increasing Difficulty", 30);
        addObject(label1, 300, 480);
        Label label2 = new Label("10 Rounds, Custom Difficulty", 30);
        addObject(label2, 700, 480);
        
        Tutorial tutorial = new Tutorial();
        addObject(tutorial, 500, 150);
    }
}
