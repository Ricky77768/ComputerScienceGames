    import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
    import java.util.*;

/**
 * The main character that the player controls
 * 
 * @author (Ricky Lu) 
 * @date (Jan. 18th)
 */
public class MainCharacter extends Actor
{
    private int maxHSpeed = 8; // Maximum speed that the character can go horizontally
    private int timeLeft = 180; // Time allowed for each attempt or timer for practice mode
    private int moveVertical = 0; // Current horizontal character speed (+ down, - up)
    private int moveHorizontal = 0; // Current vertical character speed (- left, + right)
    private int progress = 1; // Which level the character is currently on
    private int respawnY = 400; // height which player respawns at
    private int deathCount = 0; // Number of times the player has died
    private boolean inAir = true; // Check if the character is in the air (No Jumping) 
    private boolean onObject = false; // Check if character is on object/ground (Determine if it can jump)
    private boolean practiceMode = false; // Check if practice mode is on (No Timer)
    SimpleTimer moveHDelay = new SimpleTimer(); // Delay set up to reduce character speed gradually
    SimpleTimer jumpRingDelay = new SimpleTimer(); // Delay set up to prevent jump ring spam
    SimpleTimer countdown = new SimpleTimer(); // Timer set up to change timeLeft
    SimpleTimer changeDelay = new SimpleTimer(); // Delay set up to prevent spam change of gamemode
    Label timer = new Label(timeLeft, 50); // Label used to display time left for this attempt
    Label levelProgress = new Label("Level " + progress + "/10", 25); // Label used to display which level the user is on
    GreenfootSound countdown60 = new GreenfootSound("1 min Countdown.mp3");
    GreenfootSound timesUp = new GreenfootSound("Out of Time.mp3");
    
    public void act() 
    {
        // Get world reference
        MyWorld world = (MyWorld) getWorld();
        
        // Switches gamemode on Level 5
        if (isTouching(Gamemode.class) && changeDelay.millisElapsed() > 1000) {
            Gamemode gamemode = (Gamemode) getOneIntersectingObject(Gamemode.class);
            gamemode.switchMode();
            changeDelay.mark();
        }
            
        // Start the timer (practice & challenge)
        if (progress == 6) {
            world.addLabel(timer, 400, 20);
            world.addLabel(levelProgress, 400, 50);
        }
        
        // Add a clock ticking sound when there is only 60 seconds left (challenge)
        if (practiceMode == false && timeLeft < 60) {
            countdown60.play();
        }

        // timeLeft countdown (challenge)
        if (countdown.millisElapsed() > 1000 && progress > 5 && practiceMode == false) {
            timeLeft--;
            timer.setValue(timeLeft);
            countdown.mark();
        }
        
        // timeLeft countdown (practice)
        if (countdown.millisElapsed() > 1000 && progress > 5 && practiceMode == true) {
            timeLeft++;
            timer.setValue(timeLeft);
            countdown.mark();
        }
        
        // Game Over (Out of Time)
        if (timeLeft < 0) {
            Greenfoot.setWorld(new Gameover());
            timesUp.play();
        }
        
        if (progress > 15 && practiceMode == false) {
            Greenfoot.setWorld(new EndScreen(timeLeft, deathCount));
        }
        
        if (progress > 15 && practiceMode == true) {
            Greenfoot.setWorld(new PracticeEndScreen(timeLeft, deathCount));
        }
        
        // Skip tutorial
        if (Greenfoot.isKeyDown("Q") && progress < 5) {
            setLocation(15, getY());
            progress = 5;
            clearWorld();
            world.level5();
        }    
        
        // Move Left
        if (Greenfoot.isKeyDown("A")) {
            if (moveHorizontal > -1 * maxHSpeed) {
                moveHorizontal -= 1;
            }
        }

        if (moveHorizontal < 0) {  
            if (moveHDelay.millisElapsed() > 20) {
                moveHorizontal += 1;
                moveHDelay.mark();
            }
        }

        // Move Right
        if (Greenfoot.isKeyDown("D")) {
            if (moveHorizontal < maxHSpeed) {
                moveHorizontal += 1;
            }
        }   

        if (moveHorizontal > 0) {  
            if (moveHDelay.millisElapsed() > 20) {
                moveHorizontal -= 1;
                moveHDelay.mark();
            }
        }
        setLocation(getX() + moveHorizontal, getY());  

        // Jump or Using Jump Ring
        if (Greenfoot.isKeyDown("space")) {
            if (inAir == false) { 
                setLocation(getX(), getY() - 10);
                moveVertical = -18;
                inAir = true;
                jumpSwapSwitch();
            }
            else if (isTouching(JumpRing.class) && jumpRingDelay.millisElapsed() > 300) {
                inAir = false;
                jumpRingDelay.mark();
            }
        }    
        
        // Bottom Border Prevention
        if (getY() >= getWorld().getHeight() - 15) {
            moveVertical = 0;
            setLocation(getX(), getWorld().getHeight() - 15);
            inAir = false;
            onObject = true;
        }
        
        // Top Border Prevention
        if (getY() <= 15) {
            moveVertical = 0;
            setLocation(getX(), 15);
        }

        // Left Border Prevention
        if (getX() <= 15) {
            setLocation(15, getY());
            moveHorizontal = 0;
        }

        // Right Border Detection (Go to next level)
        if (getX() >= getWorld().getWidth() - 15) {
            
            // Check if practice mode is on
            if (progress == 5) {
                for (Gamemode x : (java.util.List<Gamemode>) getWorld().getObjects(Gamemode.class)) {
                    if (x.getIsPractice() == true) {
                        practiceMode = true;
                        timeLeft = 0;
                    }
                }
            }
            
            setLocation(15, getY());
            progress++;
            levelProgress.setValue("Level " + (int) (progress - 5) + "/10");
            clearWorld();
            
            // Check which level to create
            switch (progress) {
            case 2:  world.level2();
                     break;
            case 3:  world.level3();
                     break;
            case 4:  world.level4();
                     break;
            case 5:  world.level5();
                     break;
            case 6:  world.level6();
                     break;
            case 7:  world.level7();
                     break;
            case 8:  world.level8();
                     break;
            case 9:  world.level9();
                     break;
            case 10: world.level10();
                     break;
            case 11: world.level11();
                     break;
            case 12: world.level12();
                     break;
            case 13: world.level13();
                     break;
            case 14: world.level14();
                     break;
            case 15: world.level15();
                     break;  
            }
        }

        // Freefall
        if (onObject == false) {
            moveVertical += 1;
            setLocation(getX(), getY() + moveVertical);
        }
        
        // Top Collision
        if (isTouching(CollisionT.class)) {
            onObject = true;
            inAir = false;
            moveVertical = 0;
            setLocation(getX(), getOneIntersectingObject(CollisionT.class).getY() - getOneIntersectingObject(CollisionT.class).getImage().getHeight() * 2);
        }
        else {
            onObject = false;
        }   

        // Bottom Collision
        if (isTouching(CollisionB.class)) {
            setLocation(getX(), getOneIntersectingObject(CollisionB.class).getY() + getOneIntersectingObject(CollisionB.class).getImage().getHeight() * 3);
            moveVertical = 0;
        }

        // Left Collision
        if (isTouching(CollisionL.class)) {
            setLocation(getOneIntersectingObject(CollisionL.class).getX() - 18, getY());
            moveHorizontal = 0;
        }
        
        // Right Collision
        if (isTouching(CollisionR.class)) {
            setLocation(getOneIntersectingObject(CollisionR.class).getX() + 18, getY());
            moveHorizontal = 0;
        }
        
        // Touching Bouncer
        if (isTouching(Bouncer.class)) {
            moveVertical = -25;
        }
        
        // Touching Spikes (Death)
        if (isTouching(Spikes.class) || isTouching(SpikesL.class) || isTouching(SpikesB.class)) {
            if (progress > 5) {
                deathCount++;
            }
            
            // There are few cases where the player needs to respawn at a different height
            switch (progress) {
            case 7:  respawnY = 300;
                     break;
            case 8:  respawnY = 300;
                     break;
            case 12: respawnY = 250;
                     break;
            case 14: respawnY = 80;
                     break;
            case 15: respawnY = 250;
                     break;
            default: respawnY = 400;
                     break;  
            }

            setLocation(50, respawnY);
            moveVertical = 0;
            moveHorizontal = 0;
            onObject = false;
            inAir = true;
        }
        
        // Touching Vertical Moving Platforms
        if (isTouching(movingPlatformV.class)) {
            onObject = true;
            inAir = false;
            moveVertical = 0;
            setLocation(getX(), getOneIntersectingObject(movingPlatformV.class).getY() - getOneIntersectingObject(movingPlatformV.class).getImage().getHeight());   
        }
        else {
            onObject = false;
        }   
        
        // Touching Horizontal Moving Platforms
        if (isTouching(movingPlatformH.class)) {
            onObject = true;
            inAir = false;
            moveVertical = 0;
            setLocation(getX(), getOneIntersectingObject(movingPlatformH.class).getY() - getOneIntersectingObject(movingPlatformH.class).getImage().getHeight());
            
            // Code to ensure that the character moves with the platform
            movingPlatformH platform = (movingPlatformH) getOneIntersectingObject(movingPlatformH.class);
            if (platform.getGoingRight()) {
                setLocation(getX() + platform.getSpeed(), getY());
            } else {
                setLocation(getX() - platform.getSpeed(), getY());
            }
        }
        else {
            onObject = false;
        }   

        // Jump Swap Block - Top Collision
        if (isTouching(JumpSwapT.class)) {
            JumpSwapT jumpSwapT = (JumpSwapT) getOneIntersectingObject(JumpSwapT.class);
            
            // Collision should only happen if the block is ON
            if (jumpSwapT.getVisibility()) {
                onObject = true;
                inAir = false;
                moveVertical = 0;
                setLocation(getX(), getOneIntersectingObject(JumpSwapT.class).getY() - getOneIntersectingObject(JumpSwapT.class).getImage().getHeight() * 2);
            }
        }
        else {
            onObject = false;
        }   
        
        // Jump Swap Block - Bottom Collision
        if (isTouching(JumpSwapB.class)) {
            JumpSwapB jumpSwapB = (JumpSwapB) getOneIntersectingObject(JumpSwapB.class);
            
            // Collision should only happen if the block is ON
            if (jumpSwapB.getVisibility()) {
                setLocation(getX(), getOneIntersectingObject(JumpSwapB.class).getY() + getOneIntersectingObject(JumpSwapB.class).getImage().getHeight() * 3);
                moveVertical = 0;
            }
        }
        else {
            onObject = false;
        } 
        
        // Jump Swap Block - Left Collision
        if (isTouching(JumpSwapL.class)) {
            JumpSwapL jumpSwapL = (JumpSwapL) getOneIntersectingObject(JumpSwapL.class);
            
            // Collision should only happen if the block is ON
            if (jumpSwapL.getVisibility()) {
                setLocation(getOneIntersectingObject(JumpSwapL.class).getX() - 18, getY());
                moveHorizontal = 0;
            }
        }
        else {
            onObject = false;
        }  
        
        // Jump Swap Block - Right Collision
        if (isTouching(JumpSwapR.class)) {
            JumpSwapR jumpSwapR = (JumpSwapR) getOneIntersectingObject(JumpSwapR.class);
            
            // Collision should only happen if the block is ON
            if (jumpSwapR.getVisibility()) {
                setLocation(getOneIntersectingObject(JumpSwapR.class).getX() + 18, getY());
                moveHorizontal = 0;
            }
        }
        else {
            onObject = false;
        }  
    }     
    
    // Function used to switch all the Jump Swap blocks' states. Called everytime the player jumps or uses a jump ring
    public void jumpSwapSwitch() {    
        for (JumpSwapT swapT : (java.util.List<JumpSwapT>) getWorld().getObjects(JumpSwapT.class)) {
            swapT.switchState();
        }
        
        for (JumpSwapB swapB : (java.util.List<JumpSwapB>) getWorld().getObjects(JumpSwapB.class)) {
            swapB.switchState();
        }
        
        for (JumpSwapL swapL : (java.util.List<JumpSwapL>) getWorld().getObjects(JumpSwapL.class)) {
            swapL.switchState();
        }
        
        for (JumpSwapR swapR : (java.util.List<JumpSwapR>) getWorld().getObjects(JumpSwapR.class)) {
            swapR.switchState();
        }
    }
    
    // Function to clear the entire world except the MainCharacter some of the Labels
    public void clearWorld() {
        for (Bouncer x : (java.util.List<Bouncer>) getWorld().getObjects(Bouncer.class)) {
            getWorld().removeObject(x);
        }
        
        for (CollisionB x : (java.util.List<CollisionB>) getWorld().getObjects(CollisionB.class)) {
            getWorld().removeObject(x);
        }
        
        for (CollisionL x : (java.util.List<CollisionL>) getWorld().getObjects(CollisionL.class)) {
            getWorld().removeObject(x);
        }
        
        for (CollisionR x : (java.util.List<CollisionR>) getWorld().getObjects(CollisionR.class)) {
            getWorld().removeObject(x);
        }
        
        for (CollisionT x : (java.util.List<CollisionT>) getWorld().getObjects(CollisionT.class)) {
            getWorld().removeObject(x);
        }
        
        for (JumpRing x : (java.util.List<JumpRing>) getWorld().getObjects(JumpRing.class)) {
            getWorld().removeObject(x);
        }
        
        for (JumpSwapB x : (java.util.List<JumpSwapB>) getWorld().getObjects(JumpSwapB.class)) {
            getWorld().removeObject(x);
        }
        
        for (JumpSwapL x : (java.util.List<JumpSwapL>) getWorld().getObjects(JumpSwapL.class)) {
            getWorld().removeObject(x);
        }
        
        for (JumpSwapR x : (java.util.List<JumpSwapR>) getWorld().getObjects(JumpSwapR.class)) {
            getWorld().removeObject(x);
        }
        
        for (JumpSwapT x : (java.util.List<JumpSwapT>) getWorld().getObjects(JumpSwapT.class)) {
            getWorld().removeObject(x);
        }
        
        for (Spikes x : (java.util.List<Spikes>) getWorld().getObjects(Spikes.class)) {
            getWorld().removeObject(x);
        }
        
        for (SpikesL x : (java.util.List<SpikesL>) getWorld().getObjects(SpikesL.class)) {
            getWorld().removeObject(x);
        }
        
        for (SpikesB x : (java.util.List<SpikesB>) getWorld().getObjects(SpikesB.class)) {
            getWorld().removeObject(x);
        }
        
        for (movingPlatformH x : (java.util.List<movingPlatformH>) getWorld().getObjects(movingPlatformH.class)) {
            getWorld().removeObject(x);
        }
        
        for (movingPlatformV x : (java.util.List<movingPlatformV>) getWorld().getObjects(movingPlatformV.class)) {
            getWorld().removeObject(x);
        }
        
        for (Gamemode x : (java.util.List<Gamemode>) getWorld().getObjects(Gamemode.class)) {
            getWorld().removeObject(x);
        }
        
        // Removing only labels from the tutorial levels
        if (progress < 7) {
            for (Label x : (java.util.List<Label>) getWorld().getObjects(Label.class)) {
                getWorld().removeObject(x);
            }
        }
    }
}
