import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * The world where the game is started in. It has many field variables that dictate how difficult the 
 * game is. It has a Queue of Obstacle which is refilled as difficulty goes up. It will dequeue Obstacle
 * that the player has to shoot. It contains an ArrayList of String which will be randomly selected to
 * type. It also has a few helper methods for Actors to add/update object straight from their respective 
 * classes.
 * 
 * @author (Ricky, Charlie, Jason) 
 * @date (April 11, 2019)
 */
public class MyWorld extends World
{
    // Functionality Variables
    private static ArrayList<String> words = new ArrayList<String>(); // Contains all possible words
    private static boolean musicStarted = false; // Staic due to only wanting the music to start once
    private SimpleTimer delay = new SimpleTimer(); // Timer to delay between each Obstacle spawn
    private Scanner reader = new Reader().getScanner("words.txt"); // Scanner to read file
    private GreenfootSound backgroundMusic = new GreenfootSound("background_music.mp3");
    private GreenfootSound difficultyUp = new GreenfootSound("difficulty_up.mp3");
    
    // Gameplay variables
    private Queue<Obstacle> obstacles = new Queue<Obstacle>(); // Queue to store/dequeue Obstacle
    private int difficulty = 1; // Difficulty of the game, affects the following fields
    private int numOfObstacles = 7; // The number of Obstacle queued for the next difficulty
    private int spawnTime = 3000; // Delay in Milliseconds between each Obstacle Spawn
    private int minLetters = 3; // The shortest length a word can be
    private int maxLetters = 7; // The longest length a word can be
    private int pattern = 1; // (Only for max difficulty) A Pattern it will spawn for Obstacle
    private boolean flashlight = false; // Is flashlight is enabled (highest difficulty)
    
    // Constructor
    public MyWorld()
    {    
        super(800, 500, 1);
        
        // Order of Actors due to the need for certain Actors to be on top of others
        setPaintOrder(LabelT.class, Bullet.class, Ship.class, Shield.class, Planet.class, Flashlight.class, Label.class, Obstacle.class);
        
        // Starting the music only once
        if (!musicStarted) {
            backgroundMusic.setVolume(30);
            backgroundMusic.playLoop();
            musicStarted = true;
        }
        
        // Put all the words into an ArrayList
        while (reader.hasNext()) {
            words.add(reader.nextLine());
        }
        
        Display display = new Display();
        addObject(display, 0, 0);
        Planet planet = new Planet();
        addObject(planet, 10, 260);
        Shield shield = new Shield();
        addObject(shield, 85, 160);
        Ship ship = new Ship();
        addObject(ship, 85, 160);
        LabelT currentDifficulty = new LabelT("Difficulty: " + difficulty + "/5", 30); 
        addObject(currentDifficulty, 700, 470);
        
        // Fill the Queue with a specified amount of Obstacle
        obstacles = refreshQueue(obstacles, numOfObstacles);
    }
    
    public void act() {
        // Spawning Obstacle based on a delay
        if (delay.millisElapsed() > spawnTime && !obstacles.isEmpty()) {
            if (difficulty < 5) {
                // Randomly spawns Obstacle along a range on the Y-Axis
                addObject(obstacles.dequeue(), 800, Greenfoot.getRandomNumber(290) + 20);
            }
            else {                
                switch (pattern) {
                    // Spawns in a fixed pattern to prevent Obstacle stacking from high spawn rates
                    case 1:
                        addObject(obstacles.dequeue(), 800, 40);
                        pattern = 2;
                        break;
                    
                    case 2:
                        addObject(obstacles.dequeue(), 800, 100);
                        pattern = 3;
                        break;
                        
                    case 3:
                        addObject(obstacles.dequeue(), 800, 160);
                        pattern = 4;
                        break;
                        
                    case 4:
                        addObject(obstacles.dequeue(), 800, 220);
                        pattern = 5;
                        break;
                        
                    case 5:
                        addObject(obstacles.dequeue(), 800, 280);
                        pattern = 1;
                        break;
                }
            }
            delay.mark();
        }
        
        /* Increases the difficulty and generate a new Queue of Obstacle when all of the Obstacles from
           the Queue is cleared */
        if (obstacles.isEmpty() && difficulty < 5 && Display.getCleared() == numOfObstacles) {
            difficultyUp.play();
            difficulty++;
            Display.setCleared(0);
            
            LabelT currentDifficulty = new LabelT("Difficulty: " + difficulty + "/5", 30); 
            updateLabel(currentDifficulty, 700, 470);
            if (difficulty > 4) {
                LabelT maxDifficulty = new LabelT("Difficulty: MAX", 30); 
                updateLabel(maxDifficulty, 700, 470);
            }
            
            // Changes varies game field variables that affect gameplay
            switch (difficulty) {
                case 2:
                    numOfObstacles = 8;
                    spawnTime = 2700;
                    minLetters = 4;
                    maxLetters = 8;
                    this.setBackground(new GreenfootImage("background2.png"));
                    break;
                    
                case 3:
                    numOfObstacles = 9;
                    spawnTime = 2400;
                    minLetters = 5;
                    maxLetters = 9;
                    this.setBackground(new GreenfootImage("background3.png"));
                    break;
                    
                case 4:
                    numOfObstacles = 10;
                    spawnTime = 2200;
                    minLetters = 7;
                    maxLetters = 11;
                    this.setBackground(new GreenfootImage("background4.png"));
                    break;
                    
                case 5:
                    numOfObstacles = 500; // Simulates Endless Mode, no breaks
                    spawnTime = 2000;
                    minLetters = 8;
                    maxLetters = 20;
                    enableFlashlight();
                    this.setBackground(new GreenfootImage("background5.png"));
                    break;
            }
            obstacles = refreshQueue(obstacles, numOfObstacles);
        }
    }
    
    // Enables the restricted view mode for max difficulty
    public void enableFlashlight() {
        Flashlight flashlight = new Flashlight();
        addObject(flashlight, 400, 250);
    }
    
    // Method to refill Queue with a specific amount of Obstacle
    public Queue<Obstacle> refreshQueue(Queue<Obstacle> q, int amount) {
        while (!q.isEmpty()) {
            q.dequeue();
        }
        
        while (q.size() < amount) {
            q.enqueue(new Obstacle(minLetters, maxLetters)); 
        }
        return q;
    }
    
    // Returns the list of words
    public static ArrayList<String> getWords() {
        return words;
    }
    
    // A function called to display a label within an Actor
    public void addLabel(Label text, int x, int y) {
        addObject(text, x, y);
    }
    
    // A function called to update the label within an Actor
    public void updateLabel(Label text, int x, int y) {
        removeObjects(getObjectsAt(x, y, Label.class));
        addObject(text, x, y);
    }
    
    // A function called to display a labelT within an Actor
    public void addLabel(LabelT text, int x, int y) {
        addObject(text, x, y);
    }
    
    // A function called to update the labelT within an Actor
    public void updateLabel(LabelT text, int x, int y) {
        removeObjects(getObjectsAt(x, y, LabelT.class));
        addObject(text, x, y);
    }
}
