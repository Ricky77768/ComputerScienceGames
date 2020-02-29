import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The screen user sees when the game starts, along with playing the background music.
 * It has a button that goes to the Instructions screen
 * 
 * @author (Charlie) 
 * @version (June 9)
 */
public class StartScreen extends World
{
    // Static variable so that music doesn't overlap
    private static GreenfootSound music = new GreenfootSound("Jazzy Casino Music.mp3");
    
    // Default Constructor
    public StartScreen() {    
        super(1000, 500, 1); 
        music.setVolume(30);
        music.stop();
        music.play();
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare() {
        Button button = new Button("Start");
        addObject(button, 500, 350);
        Label label = new Label("This is NOT a fullscreen game!", 40);
        addObject(label, 500, 470);
    }
}
