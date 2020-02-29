
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * This is where the word will be typed, displayed, and evaluated. It contains a HashMap with letters
 * and their respective values. The scoring of a word depends on the total letter values of each
 * individual letter in a word. When the word typed matches a Obstacle, a Bullet will be spawned to 
 * destroy it, the Ship will move to the Obstacle, and score will be calculated and added.
 * 
 * @author (Ricky, Jason, Charlie) 
 * @date (April 11, 2019)
 */
public class Display extends Actor
{
    private String word = ""; // The word that is displayed on screen
    private String currentChar = ""; // The character that will be evaluated, and added to the end of "word" if fits
    private String forbiddenWord = ""; // Bug Prevention - To prevent a Obstacle being typed twice
    private boolean isLetter = false; // A boolean that indicates whether the character is valid (Letters)
    private boolean labelCreated = false; // One time label creation of score
    private static int cleared = 0; // Amount of obstacles already cleared in this difficulty
    private static int score = 0; // The score of the player
    private int lastScore = 0; // Amount of score gained in the last Obstacle cleared
    private SimpleTimer delay = new SimpleTimer();
    private HashMap<String, Integer> letterValues = new HashMap<String, Integer>();
    
    // Constructor
    public Display() {
        // Reset related variables
        cleared = 0;
        score = 0;
        
        // Putting all the letters and their respective values into a HashMap
        letterValues.put("a", 1);
        letterValues.put("e", 1);
        letterValues.put("i", 1);
        letterValues.put("o", 1);
        letterValues.put("u", 1);
        letterValues.put("n", 1);
        letterValues.put("r", 1);
        letterValues.put("t", 1);
        letterValues.put("l", 1);
        letterValues.put("s", 1);
        letterValues.put(" ", 1);
        letterValues.put("d", 2);
        letterValues.put("g", 2);
        letterValues.put("b", 3);
        letterValues.put("c", 3);
        letterValues.put("m", 3);
        letterValues.put("p", 3);
        letterValues.put("f", 4);
        letterValues.put("h", 4);
        letterValues.put("v", 4);
        letterValues.put("w", 4);
        letterValues.put("y", 4);
        letterValues.put("k", 5);
        letterValues.put("-", 5);
        letterValues.put("j", 8);
        letterValues.put("x", 8);
        letterValues.put("q", 10);
        letterValues.put("z", 10);
    }
    
    public void act() 
    {
        MyWorld world = (MyWorld) getWorld();
        
        // One-time label creation
        if (!labelCreated) {
            LabelT currentScore = new LabelT("Score: " + score + " (+" + lastScore + ")", 30);
            world.addLabel(currentScore, 400, 470);
            labelCreated = true;
        }
        
        // Code to constantly update/display the newly typed word
        LabelT display = new LabelT(word, 50);
        world.updateLabel(display, 400, 410);
        
        // Get current user's keystroke
        currentChar = Greenfoot.getKey();
        
        // Bug Prevention - If the character is null, it should not be added to the word
        if (currentChar == null) {
            return;
        }
        
        // Add to end of the word if it is a valid character
        if (isLetter(currentChar) && word.length() < 21) {
            word += currentChar;
            return;
        }
        
        // Code to make sure it adds " " to the word instead of "space"
        if (currentChar.equals("space")) {
            word += " ";
            return;
        }
        
        // Removes the last letter of word
        if (currentChar.equals("backspace") && word.length() > 0) {
            word = word.substring(0, word.length() - 1);
            return;
        }
        
        // Evaluate the word, and clear the word
        if (currentChar.equals("enter")) {
            checkWord(word, world.getObjects(Ship.class).get(0));
            word = "";
            return;
        }
    } 
    
    // Method used to check if the character typed is a valid letter
    public boolean isLetter(String input) {        
        switch (input) {
            case "a": return true;
            case "b": return true;
            case "c": return true;
            case "d": return true;
            case "e": return true;
            case "f": return true;
            case "g": return true;
            case "h": return true;
            case "i": return true;
            case "j": return true;
            case "k": return true;
            case "l": return true;
            case "m": return true;
            case "n": return true;
            case "o": return true;
            case "p": return true;
            case "q": return true;
            case "r": return true;
            case "s": return true;
            case "t": return true;
            case "u": return true;
            case "v": return true;
            case "w": return true;
            case "x": return true;
            case "y": return true;
            case "z": return true;
            case "-": return true;
            default: return false;
        }
    }
    
    // Method to check if any of the Obstacle matches the word typed
    public void checkWord(String input, Ship ship) {
        List<Obstacle> obstacles = getWorld().getObjects(Obstacle.class); // A List of Obstacle in world currently
        MyWorld world = (MyWorld) getWorld();  // Get world reference
        
        for (Obstacle o : obstacles) {
            
            // Bug Prevention - To make sure a word that has a Bullet aimed at it can not be typed again
            if (o.getValue().equals(input) && !forbiddenWord.equals(input)) {
                cleared++;
                forbiddenWord = input;
                lastScore = calculateScore(input, letterValues);
                score += lastScore;
                
                // Spawn a Bullet and move the Ship to the Y-Pos of the correct Obstacle 
                Bullet bullet = new Bullet(o);
                world.addObject(bullet, 100, o.getY());
                ship.setLocation(ship.getX(), o.getY());
                
                // Update score
                LabelT currentScore = new LabelT("Score: " + score + " (+" + lastScore + ")", 30);
                world.updateLabel(currentScore, 400, 470);
            }
        }
    }
    
    // Method used to calculate how much a word is worth
    public int calculateScore(String word, HashMap<String, Integer> values) {
        int total = 0; // Total value of the word
        
        for (int i = 0; i < word.length(); i++) {
            total += values.get(word.substring(i, i + 1));
        }
        
        return total;
    }
    
    // Get the value of cleared
    public static int getCleared() {
        return cleared;
    }
    
    // Get the value of score
    public static int getScore() {
        return score;
    }
    
    // Set the value of cleared
    public static void setCleared(int input) {
        cleared = input;
    }
}
