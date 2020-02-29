import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The screen that shows up when the player chooses to set its own difficulty.
 * 
 * @author (Charlie) 
 * @version (June 9, 2019)
 */
public class FreeModeSelect extends World
{
    // Default Constructor
    public FreeModeSelect() {    
        super(1000, 500, 1); 
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare() {
        Slider slider1 = new Slider(600, 1);
        addObject(slider1, 600, 100);
        Slider slider2 = new Slider(600, 2);
        addObject(slider2, 600, 250);
        Slider slider3 = new Slider(600, 3);
        addObject(slider3, 600, 400);
        
        Button button1 = new Button("Start Special");
        addObject(button1, 300, 250);
        
        Label label1 = new Label("Number of Cards: ", 30);
        addObject(label1, 700, 50);
        Label label2 = new Label("Minimum Number: ", 30);
        addObject(label2, 700, 200);
        Label label3 = new Label("Maximum Number: ", 30);
        addObject(label3, 700, 350);
        
        // Drawing 3 white lines as slider boundaries
        getBackground().setColor(Color.WHITE);
        getBackground().fillRect(600, 95, 300, 15);
        getBackground().fillRect(600, 245, 300, 15);
        getBackground().fillRect(600, 395, 300, 15);
    }
    
    // Method to overwrite a label that is currently in the world at a specific location
    public void updateLabel(Label text, int x, int y) {
        removeObjects(getObjectsAt(x, y, Label.class));
        addObject(text, x, y);
    }   
}
