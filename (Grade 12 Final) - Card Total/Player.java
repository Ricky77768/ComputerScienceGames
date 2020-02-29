import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.text.DecimalFormat;

/**
 * The Player class does not interact with other Actors, but runs in the background methods such as
 * choosing a target number and evaluating a round.
 * 
 * @author (Ricky, Charlie) 
 * @version (June 9, 2019)
 */
public class Player extends Actor
{
    private static int score; // The current score of the player
    private static int target; // The current target number for this round
    private static GreenfootSound perfect = new GreenfootSound("Perfect.mp3"); // Perfected Sound Effect
    
    // ArrayList of Card that contains all Card selected by player to be evaluated
    public static ArrayList<Card> selected = new ArrayList<Card>();
    
    // ArrayList of int that contains possible sums of all combinations of ints from an array
    public static ArrayList<Integer> sums = new ArrayList<Integer>(); 
    
    // Default Constructor - Resets Score to 0
    public Player() {
        score = 0;
    }
    
    // Returns the ArrayList with all Card selected by player
    public static ArrayList<Card> getSelected() {
        return selected;
    }
    
    // Returns the current score of the player
    public static int getScore() {
        return score;
    }
    
    // Method to choose a target number for each round, and displaying it onto the world
    public static void chooseTarget(MyWorld world) {
        sums.clear();
        selected.clear();
        ArrayList<Card> cardList = world.getCardList();
        int[] values = new int[cardList.size()];
        int length = cardList.size();
        
        // Copies all values of the Card in world into an temporary int array
        for (int i = 0; i < length; i++) {
            values[i] = cardList.get(i).getValue();
        }
        
        // Method to find sums of all possible combinations of ints
        findSums(values, 0, values.length - 1, 0, sums);

        // Removal of one card combinations
        sums.remove(sums.indexOf(0));
        for (int i = 0; i < values.length; i++) {
            if (sums.indexOf(values[i]) != -1) {
                sums.remove(sums.indexOf(values[i]));
            }
        }
        
        // Sorts the ArrayList into ascending order
        Collections.sort(sums);
        
        // Ensures that the target number chosen will be around the middle of the Integer ArrayList (Consistent Difficulty)
        int index;
        while (true) {
            index = Greenfoot.getRandomNumber(sums.size());
            if (index >= sums.size() / 3 && index <= sums.size() / 3 * 2) {
                break;
            }
        }
        
        // Display the target number
        target = sums.get(index);
        Label label = new Label(">>> Make " + target + " <<<", 60);
        world.addObject(label, 500, 50);
    }   
    
    // Method to evaluate player's current round score
    public static void evaluate(MyWorld world, int timeTaken) {
        DecimalFormat df = new DecimalFormat("#.####");
        int roundScore; // Total score gained for this round
        int accuracy; // Score given for accuracy
        int timeOverLimit; // Amount of time the player is over for gaining bonus multiplier
        double factor = 2; // Speed bonus factor
        world.flipAllCards();
        
        // Calculates how off the player is from the target number
        for (int i = 0; i < selected.size(); i++) {
            target -= selected.get(i).getValue();
        }
        
        // Plays a sound effect if the player is right on the target number 
        if (target == 0) {
            perfect.play();
            Label label = new Label("Perfect!", 50);
            world.addObject(label, 800, 300);
        }
        else {             
            if (target < 0) {
               Label label = new Label("Over By " + Math.abs(target), 50);
               world.addObject(label, 800, 300);
            } else {
               Label label = new Label("Under By " + target, 50);
               world.addObject(label, 800, 300);
            }
        }
        
        // Calculate score for accuracy
        accuracy = (int) (500 / Math.pow(1.1, Math.abs(target)));
        
        // Calculate the time the player is over for gaining bonus multiplier
        timeOverLimit = timeTaken - world.getTimeBonusLimit();
        
        // Calculates the amount of multiplier lost for over bonus time limit
        if (timeOverLimit > 0) {
            factor = 2 - 0.0002 * timeOverLimit;
        }
        if (factor < 1) {
            factor = 1;
        }
        
        // Total score gained this round
        roundScore = (int) (accuracy * factor);
        
        // Displays score information
        Label label2 = new Label("Accuracy: " + accuracy + "/500", 30);
        world.addObject(label2, 800, 350);
        Label label3 = new Label("Speed: " + df.format(factor) + "x / 2x", 30);
        world.addObject(label3, 800, 380);
        Label label4 = new Label("(" + timeTaken + "ms / " + world.getTimeBonusLimit() + "ms)", 30);
        world.addObject(label4, 800, 410);
        Label label5 = new Label("Total: " + roundScore + "/1000", 50);
        world.addObject(label5, 800, 470);
        
        // Adds this round's score to the total score
        score += roundScore;
    }
    
    /*
     * Code taken and modified from https://www.geeksforgeeks.org/print-sums-subsets-given-set/
     * Method to find possible sums of all combinations of ints from an array
     */
    public static void findSums(int[] array, int left, int right, int sum, ArrayList<Integer> currentSums) { 
        if (left > right) { 
            for (int i = 0; i < currentSums.size(); i++) {
                if (currentSums.get(i) == sum) {
                    return;
                }
            }
            currentSums.add(sum);
            return; 
        } 
      
        findSums(array, left + 1, right, sum + array[left], currentSums); 
        findSums(array, left + 1, right, sum, currentSums); 
    } 
}
