import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the class for Card behaviors. It has various functions that changes the state of the card
 * 
 * @author (Ricky, Jason) 
 * @version (June 9, 2019)
 */
public class Card extends Actor
{
    private static boolean canInteract = false; // Determine if the card states can be changed or not
    private int value; // The value which the card holds
    private boolean flipped = false; // If it should display the value
    private boolean clicked = false; // If it should have a gradient effect (To show it's selected)
    private boolean selected = true; // If it should have a blue border (To show it's selected during evaluation)
    private GreenfootImage[] cardState = new GreenfootImage[4]; // Contains all images
    private GreenfootSound click = new GreenfootSound("Click.mp3"); // Click Sound Effect
    
    // Default Constructor
    public Card(int theValue) {
        value = theValue;
        
        cardState[0] = new GreenfootImage("Card Back.png");
        cardState[1] = new GreenfootImage("Card Selected.png");
        cardState[2] = new GreenfootImage("Card Front - Chosen.png");
        cardState[3] = new GreenfootImage("Card Front - Not Chosen.png");
    }
    
    // Returns the value of the Card
    public int getValue() {
        return value;
    }
    
    // Changes the state of the Card (Selected)
    public void changeSelected() {
        selected = !selected;
    }
    
    // Changes whether the Card can be interacted with
    public static void changeInteract(boolean bool) {
        canInteract = bool;
    }

    // Changes the "flipped" state, also deletes the Label on top showing the value if neccesary
    public void changeFlipped() {
        flipped = !flipped;
        
        if (flipped) {
            Label text = new Label(value, 50);
            getWorld().addObject(text, getX(), getY());
        }
        else {
            removeLabel();
        }
    }  
    
    // Method to remove any Label touching the Card
    public void removeLabel() {
        removeTouching(Label.class);
    }
    
    /**
     * Act - do whatever the Card wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        MouseInfo mouse = Greenfoot.getMouseInfo(); 
        
        // Changes the image based on its state
        if (selected) {
            setImage(cardState[2]);
            getImage().scale(70, 105);
        } else if (flipped) {
            setImage(cardState[3]);
            getImage().scale(70, 105);
        } else if (clicked) {
            setImage(cardState[1]);
            getImage().scale(70, 105);
        } else {
            setImage(cardState[0]);
            getImage().scale(70, 105);
        }
        
        // If the Card can be interacted with, select/deselect the Card clicked
        if (canInteract) {
            if (Greenfoot.mouseClicked(this) && !clicked) {
                click.play();
                clicked = !clicked;
                Player.selected.add(this);
            }
            else if (Greenfoot.mouseClicked(this)) {
                click.play();
                clicked = !clicked;
                Player.selected.remove(Player.selected.indexOf(this));
            }
        }
    }    
}
