import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A Screen that shows up if the player reaches the end, and will recieve a score and a rank
 * 
 * @author (Ricky Lu) 
 * @date (Jan.18th)
 */
public class EndScreen extends World
{
    private int score = 0; // Final score of the player
    private int timeScore = 0; // Amount of points earned based on time used
    private int deathScore = 0; // Amount of points earned based on death count
    private String rank; // Final rank of the player
    
    public EndScreen(int timeRemaining, int deathCount)
    {    
        super(800, 500, 1);
        
        // Score Calculation
        timeScore = timeRemaining;
        deathScore = (25 - deathCount) * 5;
        if (deathScore < 0) {
            deathScore = 0;
        }
        score = timeScore + deathScore;
        
        // Rank Calculation
        if (score < 20) {
            rank = "C-";
        }
        
        if (score > 19 && score < 40) {
            rank = "C";
        }
        
        if (score > 39 && score < 60) {
            rank = "C+";
        }
        
        if (score > 59 && score < 90) {
            rank = "B-";
        }
        
        if (score > 89 && score < 120) {
            rank = "B";
        }
        
        if (score > 119 && score < 140) {
            rank = "B+";
        }
        
        if (score > 139 && score < 150) {
            rank = "A-";
        }
        
        if (score > 149 && score < 160) {
            rank = "A";
        }
        
        if (score > 159 && score < 170) {
            rank = "A+";
        }
        
        if (score > 169 && score < 175) {
            rank = "S-";
        }
        
        if (score > 174 && score < 180) {
            rank = "S";
        }
        
        if (score > 179) {
            rank = "S+";
        }
        
        // Displaying the Scores
        Label timeLeft = new Label(timeRemaining, 50);
        addObject(timeLeft, 67, 94);
        Label death = new Label(deathCount, 50);
        addObject(death, 186, 94);
        Label scoreInTime = new Label(timeScore, 70);
        addObject(scoreInTime, 580, 360);
        Label scoreInDeath = new Label(deathScore, 70);
        addObject(scoreInDeath, 580, 420);
        Label finalScore = new Label(score, 80);
        addObject(finalScore, 122, 393);
        Label finalRank = new Label(rank, 70);
        addObject(finalRank, 312, 205);
    }
}
