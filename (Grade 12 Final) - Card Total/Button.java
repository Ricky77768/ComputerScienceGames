import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class contains all the Button functions. It was originally seperate classes. However, it was
 * cluttering so we decided to compile all the buttons into one class.
 * 
 * @author (Ricky, Jason, Charlie) 
 * @version (June 9, 2019)
 */
public class Button extends Actor
{
    private String function; // Defines what this button will do
    private GreenfootImage[] images = new GreenfootImage[10]; // Contains all images for all buttons
    private MouseInfo mouse = Greenfoot.getMouseInfo(); // Required to determine if mouse has clicked something
    private GreenfootSound click = new GreenfootSound("Click.mp3"); // Clicking sound effect
    
    /*
     * This is a boolean created to prevent NullPointerException to be thrown. Since some buttons
     * requires a casted reference to MyWorld while others don't, if the world is currently is not
     * in MyWorld, the reference created to it will be Null, which will throw an exception when called
     */
    private boolean activated = false; // Boolean created to prevent NullPointException
    
    // For buttons with function "Ready" and "Submit"
    private SimpleTimer timer = new SimpleTimer(); // Timer to keep track how long it took for player to complete a round
    
    // For buttons with function "Ready Special" (For Free Play Mode)
    private int min; // Current user-defined mimimum value on a card for each round
    private int max; // Current user-defined maximum value on a card for each round
    private int numOfCards; // Current user-defined number of cards for each round
    
    // Default Constructor
    public Button(String theFunction) {
        function = theFunction;
        loadImages();
    }
    
    /*
     * Special Constructor (For "Submit" and "Ready") that requires a SimpleTimer reference to be passed 
     * on to keep track of time taken for the player to complete a round.
     */
    public Button(String theFunction, SimpleTimer theTimer) {
        function = theFunction;
        timer = theTimer;
        loadImages();
    }
    
    // Loads all images into a GreenFootImage array
    public void loadImages() {
        images[0] = new GreenfootImage("Start.png");
        images[1] = new GreenfootImage("Left Arrow.png");
        images[2] = new GreenfootImage("Right Arrow.png");
        images[3] = new GreenfootImage("Free Mode.png");
        images[4] = new GreenfootImage("Challenge Mode.png");
        images[5] = new GreenfootImage("Back.png");
        images[6] = new GreenfootImage("Ready.png");
        images[7] = new GreenfootImage("Submit.png");
        images[8] = new GreenfootImage("Continue.png");
        images[9] = new GreenfootImage("Play Again.png");
    }

    public void act() 
    {
        // This swtich function contains all Button that does not require a MyWorld cast
        switch (function) {
            // Button that advances to the Instructions screen
            case "Start":
                setImage(images[0]);
                if (Greenfoot.mouseClicked(this)) {
                    click.play();
                    Instructions instructions = new Instructions();
                    Greenfoot.setWorld(instructions);
                }
                activated = true;
                break;
                
            // Button that starts the game with user-defined parameters
            case "Start Special":
                setImage(images[0]);
                
                // Reads what the current user-defined parameters are
                for (Slider x : getWorld().getObjects(Slider.class)) {
                    if (x.getType() == 1) {
                        numOfCards = (int) x.getValue();
                    } else if (x.getType() == 2) {
                        min = (int) x.getValue();
                    } else {
                        max = (int) x.getValue();
                    }
                }
                
                // The button will appear faded if conditions are not meet (max cannot be bigger then min)
                if (min > max) {
                    getImage().setTransparency(100);
                } else {
                    getImage().setTransparency(255);
                }
                
                // The button can only be clicked if max is bigger or equal to min
                if (Greenfoot.mouseClicked(this) && min <= max) {
                    click.play();
                    MyWorld myWorld = new MyWorld(numOfCards, min, max);
                    Greenfoot.setWorld(myWorld);
                }
                activated = true;
                break;
               
            // Button used to navigate arounds the instructions, goes to previous image
            case "Left Arrow": 
                setImage(images[1]);
                for (Tutorial x : getWorld().getObjects(Tutorial.class)) {
                    // The button will appear faded if it should not be clicked
                    if (x.getStep() == 1) {
                        getImage().setTransparency(100);
                    } else {
                        getImage().setTransparency(255);
                    }
                    
                    // The button cannot be clicked if it is on first image
                    if (Greenfoot.mouseClicked(this) && x.getStep() > 1) {
                        click.play();
                        x.changeStep(false);
                    }
                }
                activated = true;
                break;
            
            // Button used to navigate arounds the instructions, goes to next image
            case "Right Arrow":
                setImage(images[2]);
                for (Tutorial x : getWorld().getObjects(Tutorial.class)) {
                    // The button will appear faded if should not be clicked
                    if (x.getStep() == 4) {
                        getImage().setTransparency(100);
                    } else {
                        getImage().setTransparency(255);
                    }
                    
                    // The button cannot be clicked if it is on last image
                    if (Greenfoot.mouseClicked(this) && x.getStep() < 4) {
                        click.play();
                        x.changeStep(true);
                    }
                }
                activated = true;
                break;
            
            // Button that tells the game to use user-defined game parameters to start a game
            case "Free Mode": 
                setImage(images[3]);
                if (Greenfoot.mouseClicked(this)) {
                    click.play();
                    FreeModeSelect freeModeSelect = new FreeModeSelect();
                    Greenfoot.setWorld(freeModeSelect);
                }
                activated = true;
                break;

            // Button that tells the game to use pre-defined game parameters with increasing difficulty to start a game
            case "Challenge Mode":  
                setImage(images[4]);
                if (Greenfoot.mouseClicked(this)) {
                    click.play();
                    MyWorld myWorld = new MyWorld();
                    Greenfoot.setWorld(myWorld);
                }
                activated = true;
                break;

            // Button that sends the user back to StartScreen
            case "Again":
                setImage(images[9]);
                if (Greenfoot.mouseClicked(this)) {
                    click.play();
                    StartScreen startScreen = new StartScreen();
                    Greenfoot.setWorld(startScreen);
                }
                activated = true;
                break;
        }

        // For Buttons that require a reference of MyWorld 
        if (!activated) {
            MyWorld world = (MyWorld) getWorld(); // Getting world reference

            switch (function) {
                // Button that sends the player back to StartScreen mid-game
                case "Back":
                    setImage(images[5]);
                    if (Greenfoot.mouseClicked(this)) {
                        click.play();
                        StartScreen startScreen = new StartScreen();
                        Greenfoot.setWorld(startScreen);
                    }
                    break;
                   
                /*
                 * Button that the player should click once they memorized the cards. It will cause
                 * the cards to be shuffled into ascending order, faced down.
                 */
                case "Ready": 
                    setImage(images[6]);
                    if (Greenfoot.mouseClicked(this)) {
                        click.play();
                        world.reorganizeCards();
                        Player.chooseTarget(world);
                        Card.changeInteract(true);
                        
                        Button submit = new Button("Submit", timer);
                        world.addObject(submit, getX(), getY());
                        world.removeObject(this);
                    }
                    break;
                    
                /*
                 * Button that the player should click once they made their selections (Min. 2 cards). 
                 * Their selection will be evaluated based on accuracy and speed.
                 */
                case "Submit":  
                    setImage(images[7]);
                    
                    // The button appears faded if less than 2 cards are selected
                    if (Player.getSelected().size() >= 2) {
                        getImage().setTransparency(255);
                    }
                    else {
                        getImage().setTransparency(100);
                    }
                    
                    // The button can only be clicked if 2 or more cards are selected
                    if (Greenfoot.mouseClicked(this) && Player.getSelected().size() >= 2) {
                        click.play();
                        
                        // Changes the state of all selected cards, so that the user will know what they submitted
                        for (int i = 0; i < Player.selected.size(); i++) {
                            Player.selected.get(i).changeSelected();
                        }
    
                        Player.evaluate(world, timer.millisElapsed());
                        Card.changeInteract(false);
                        
                        Button toContinue = new Button("Continue");
                        world.addObject(toContinue, getX(), getY());
                        world.removeObject(this);
                    }
                    break;

                // Button that advances the user into the next round
                case "Continue":   
                    setImage(images[8]);
                    if (Greenfoot.mouseClicked(this)) {
                        click.play();
                        world.removeLabels();
                        world.nextRound();
                        world.removeObject(this);
                    }
                break;
            }
        }
    }    
}
