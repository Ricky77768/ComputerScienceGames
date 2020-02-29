import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * The world where the game takes places in. It has a ArrayList of Cards which are used in game, as 
 * well as methods and parameters defines how the game plays.
 * 
 * @author (Ricky, Jason, Chalie) 
 * @version (June 9, 2019)
 */
public class MyWorld extends World
{
    // Game Parameters
    private int round = 0; // The round the game is currently in, max is 10
    private int numOfCards; // The number of cards displayed on screen this round
    private int min; // The smallest number a card can have this round
    private int max; // The biggest number a card can have this round
    private boolean freePlay = false; // Whether free play (custom difficulty) is on or not
    private ArrayList<Card> cardList = new ArrayList<Card>(); // ArrayList of Card that curently on screen
    
    // Default Constructor (Challenge Mode)
    public MyWorld()
    {    
        super(1000, 500, 1);
        Player player = new Player();
        addObject(player,0,0);
        Button back = new Button("Back");
        addObject(back, 120, 50);
        
        nextRound();
    }
    
    // Constructor for Free Play (Parameters defined by user)
    public MyWorld(int numberOfCards, int minimum, int maximum) {
        super(1000, 500, 1);
        Player player = new Player();
        addObject(player,0,0);
        Button back = new Button("Back");
        addObject(back, 120, 50);
        
        numOfCards = numberOfCards;
        min = minimum;
        max = maximum;
        freePlay = true;
        
        nextRound();
    }
    
    // Advances the game into next round and make neccesary parameter changes
    public void nextRound() {
        // Reaching round 10 means the game has ended, thus moving to the results screen
        if (round == 10) {
            EndScreen endScreen = new EndScreen(Player.getScore());
            Greenfoot.setWorld(endScreen);
        }
        
        round++;
        
        // If it is Challenge Mode, these are the predefined parameters for each round
        if (!freePlay) {
            switch (round) {
                case 1:      
                    max = 4;
                    min = 1;
                    numOfCards = 3;
                    break;
                    
                case 2: 
                    max = 5;
                    min = 1;
                    numOfCards = 3;
                    break;
                    
                case 3: 
                    max = 6;
                    min = 1;
                    numOfCards = 4;
                    break;
                    
                case 4: 
                    max = 7;
                    min = 2;
                    numOfCards = 4;
                    break;
                    
                case 5: 
                    max = 9;
                    min = 2;
                    numOfCards = 4;
                    break;
                    
                case 6: 
                    max = 11;
                    min = 3;
                    numOfCards = 5;
                    break;
                    
                case 7: 
                    max = 13;
                    min = 4;
                    numOfCards = 5;
                    break;
                    
                case 8: 
                    max = 15;
                    min = 5;
                    numOfCards = 6;
                    break;
                    
                case 9: 
                    max = 17;
                    min = 6;
                    numOfCards = 7;
                    break;
                    
                case 10: 
                    max = 20;
                    min = 8;
                    numOfCards = 8;
                    break;
            }       
        }
        
        // Clears the cards on screen and generates new Cards
        removeCards();
        generateCards();
        
        Button ready = new Button("Ready");
        addObject(ready, 500, 350);
  
        // Displays current status (Score and Round)
        Label label = new Label("Score: " + Player.getScore(), 40);
        addObject(label, 150, 450);
        Label label2 = new Label("Round: " + round + "/10", 30);
        addObject(label2, 150, 420);
    }
    
    // Method to return the ArrayList with Card
    public ArrayList<Card> getCardList() {
        return cardList;
    }
    
    // Calculates the amount of time user has for this round before the speed bonus wears off
    public int getTimeBonusLimit() {
        return 1500 * numOfCards;
    }
    
    // Visually "flip" all cards so that their values are shown
    public void flipAllCards() {
        for (int i = 0; i < cardList.size(); i++) {
            cardList.get(i).changeFlipped();
        }      
    }   
    
    // Clears the ArrayList with Card and generates a new set of Card with required parameters
    public void generateCards() {
        cardList.clear();
        for (int i = 0; i < numOfCards; i++) {
            cardList.add(new Card(Greenfoot.getRandomNumber(max - min + 1) + min));
        }

        displayCards();
    }
    
    /* 
     * Calculates where the card should be placed at based on number of cards (As centred as possible).
     * It also "flips" the card so that the values are shown.
     */
    public void displayCards() {
        int initialStartPos = 500 - (cardList.size() - 1) * 40;

        for (int i = 0; i < cardList.size(); i++) {
            addObject(cardList.get(i), initialStartPos + i * 80, 150);            
            cardList.get(i).changeFlipped();
        }
    }
    
    /*
     * This takes all the values of Card currently on screen and sorts them so that they display in
     * ascending order, faced down.
     */
    public void reorganizeCards() {
        // Takes all values and put them into a temporary int array for sorting
        int length = cardList.size();
        int[] values = new int[length];
        for (int i = 0; i < length; i++) {
            values[i] = cardList.get(i).getValue();
        }
        
        // Uses Merge Sort to recursively sort the values
        mergeSort(values, new int[values.length], 0, values.length - 1);
        
        // Reorganizes the Card order in ArrayList, and displays them faced down.
        cardList.clear();
        removeCards();
        for (int i = 0; i < length; i++) {
            cardList.add(new Card(values[i]));
        }
        displayCards();
        
        // Changes their states (Flipped, Selected)
        for (int i = 0; i < length; i++) {
            cardList.get(i).changeFlipped();
            cardList.get(i).changeSelected();
        }
    }
    
    // Method to remove all Card on screen, as well as the Label that displays their value
    public void removeCards() {
        for (Card x : getObjects(Card.class)) {
            x.removeLabel();
            removeObject(x);
        }
    }
    
    // Method to remove all remaining Label on screen
    public void removeLabels() {
       for (Label x : getObjects(Label.class)) {
            removeObject(x);
       }
    }
    
    // Part of Merge Sort - Seperation
    private static void mergeSort(int[] array, int[] temp, int low, int high) {
        // Sorted if low is greater than high
        if (low >= high) {  
            return;
        }

        int mid = (low + high) / 2;  // Index of middle element
        mergeSort(array, temp, low, mid);  // Sort left side of array
        mergeSort(array, temp, mid + 1, high); // Sort right side of array
        merge(array, temp, low, mid, high);  //Combine both halves
    } 

    // Part of Merge Sort - Combine
    private static void merge(int[] array, int[] aux, int low, int mid, int high){
        // Copy
        for(int k = low; k <= high; k++) { 
            aux[k] = array[k];
        }

        // Create Indices 
        int i = low;  
        int j = mid + 1;

        // Merge
        for (int k = low; k <= high; k++) {
            if (i > mid) {
                array[k] = aux[j];
                j++;
            } else if (j > high) {
                array[k] = aux[i];
                i++;
            } else if (aux[j] < aux[i]) { 
                array[k] = aux[j];
                j++;
            } else {   
                array[k] = aux[i];
                i++;
            }
        }
    }
}
