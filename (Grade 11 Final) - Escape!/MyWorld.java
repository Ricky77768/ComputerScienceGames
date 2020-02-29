import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The world where all the levels are constructed, each within a method
 * (Sorry about the naming of instances in this world, I used "Save the World" function multiple times and the names
 * were messed up. There is simply too many to change)
 * 
 * @author (Ricky Lu) 
 * @date (Jan.18th)
 */
public class MyWorld extends greenfoot.World
{       
    GreenfootSound BG = new GreenfootSound("BG - Water Dripping.mp3");
    
    public MyWorld()
    {    
        super(800, 500, 1); 
        prepare();
    }
    
    // A function called when I want to initialize a label within an Actor
    public void addLabel(Label timer, int x, int y) {
        addObject(timer, x, y);
    }
    
    // Add main character, start background sound, and make sure the main character and time label is always in front
    private void prepare()
    {
        setPaintOrder(MainCharacter.class, Label.class);
        MainCharacter player = new MainCharacter();
        addObject(player, 50, 400);
        BG.playLoop();
        level1();      
    }
    
    // Level 1
    public void level1() {
        setBackground(new GreenfootImage("BG1.png"));
        CollisionL collisionL = new CollisionL();
        addObject(collisionL,244,481);
        CollisionT collisionT = new CollisionT();
        addObject(collisionT,268,457);
        CollisionT collisionT2 = new CollisionT();
        addObject(collisionT2,308,457);
        CollisionT collisionT3 = new CollisionT();
        addObject(collisionT3,348,457);
        CollisionL collisionL2 = new CollisionL();
        addObject(collisionL2,371,433);
        CollisionT collisionT4 = new CollisionT();
        addObject(collisionT4,395,409);
        CollisionT collisionT5 = new CollisionT();
        addObject(collisionT5,435,409);
        CollisionT collisionT6 = new CollisionT();
        addObject(collisionT6,474,409);
        CollisionR collisionR = new CollisionR();
        addObject(collisionR,497,433);
        CollisionT collisionT7 = new CollisionT();
        addObject(collisionT7,521,458);
        CollisionT collisionT8 = new CollisionT();
        addObject(collisionT8,561,458);
        CollisionT collisionT9 = new CollisionT();
        addObject(collisionT9,601,458);
        CollisionR collisionR2 = new CollisionR();
        addObject(collisionR2,625,482);
        Label label = new Label("A & D to Move Left & Right", 30);
        Label label2 = new Label("Space to Jump", 30);
        Label label3 = new Label("(Q to Skip Tutorial)", 30);
        addObject(label3, 680, 20);
        addObject(label,200,200);
        addObject(label2,200,250);
    }
    
    // Level 2
    public void level2() {
        CollisionL collisionL = new CollisionL();
        addObject(collisionL,244,481);
        CollisionT collisionT = new CollisionT();
        addObject(collisionT,268,457);
        CollisionT collisionT2 = new CollisionT();
        addObject(collisionT2,308,457);
        CollisionT collisionT3 = new CollisionT();
        addObject(collisionT3,348,457); 
        CollisionT collisionT7 = new CollisionT();
        addObject(collisionT7,521,458);
        CollisionT collisionT8 = new CollisionT();
        addObject(collisionT8,561,458);
        CollisionT collisionT9 = new CollisionT();
        addObject(collisionT9,601,458);
        CollisionR collisionR2 = new CollisionR();
        addObject(collisionR2,624,481);
        CollisionL collisionL1 = new CollisionL();
        addObject(collisionL1,497,481);
        CollisionR collisionR = new CollisionR();
        addObject(collisionR,371,481);
        Spikes spikes = new Spikes();
        addObject(spikes,465,485);
        Spikes spikes2 = new Spikes();
        addObject(spikes2,435,485);
        Spikes spikes3 = new Spikes();
        addObject(spikes3,405,485);
        Label label = new Label("Spikes Well... Kill You", 30);
        addObject(label,200,200);
        Label label3 = new Label("(Q to Skip Tutorial)", 30);
        addObject(label3, 680, 20);
    }   
    
    // Level 3
    public void level3() {      
        for (int i = 0; i < 12; i++) {
            Spikes spikes = new Spikes();
            addObject(spikes,230 + i * 30,484);
        }
        
        for (int i = 0; i < 8; i++) {
            CollisionL collisionL = new CollisionL();
            addObject(collisionL,600,500 - i * 40);
        }
        
        for (int i = 0; i < 8; i++) {
            CollisionR collisionR = new CollisionR();
            addObject(collisionR,650,500 - i * 40);
        }
        
        Bouncer bouncer = new Bouncer();
        addObject(bouncer,170,482);
        movingPlatformH movingPlatformH = new movingPlatformH(300, 500, 1, true);
        addObject(movingPlatformH,300,280);
        CollisionT collisionT = new CollisionT();
        addObject(collisionT,625,196);
        Label label = new Label("Some Blocks Have Special Functions", 30);
        addObject(label,250,200);
        Label label3 = new Label("(Q to Skip Tutorial)", 30);
        addObject(label3, 680, 20);
    }   
    
    // Level 4
    public void level4() {
        setBackground(new GreenfootImage("BG2.png"));
        for (int i = 0; i < 6; i++) {
            CollisionL collisionL = new CollisionL();
            addObject(collisionL,200,500 - i * 40);
        }
        
        for (int i = 0; i < 6; i++) {
            CollisionR collisionR = new CollisionR();
            addObject(collisionR,250,500 - i * 40);
        }
        
        for (int i = 0; i < 6; i++) {
            JumpSwapL jumpSwapL = new JumpSwapL(true);
            addObject(jumpSwapL,550,500 - i * 40);
        }
        
        for (int i = 0; i < 6; i++) {
            JumpSwapR jumpSwapR = new JumpSwapR(true);
            addObject(jumpSwapR,600,500 - i * 40);
        }
        
        CollisionT collisionT = new CollisionT();
        addObject(collisionT,225,276);
        JumpSwapT jumpSwapT = new JumpSwapT(true);
        addObject(jumpSwapT,575,276);
        JumpRing jumpRing = new JumpRing();
        addObject(jumpRing,131,336);
        Label label3 = new Label("(Q to Skip Tutorial)", 30);
        addObject(label3, 680, 20);
    }   
    
    // Level 5
    public void level5() {
        Label label3 = new Label("Jump to Change Gamemode", 25);
        addObject(label3,225,100);
        Gamemode gamemode = new Gamemode();
        addObject(gamemode, 415, 225);
    }   
    
    // Level 6
    public void level6() {
        for (int i = 0; i < 8; i++) {
            CollisionT collisionT6 = new CollisionT();
            addObject(collisionT6,528 + 40 * i,306);
        }
        
        for (int i = 0; i < 8; i++) {
            CollisionB collisionB6 = new CollisionB();
            addObject(collisionB6,528 + 40 * i,353);
        }

        for (int i = 0; i < 14; i++) {
            Spikes spikes = new Spikes();
            addObject(spikes,350 + i * 30,484);
        }
        
        CollisionL collisionL = new CollisionL();
        addObject(collisionL,166,480);
        CollisionT collisionT = new CollisionT();
        addObject(collisionT,190,456);
        CollisionT collisionT2 = new CollisionT();
        addObject(collisionT2,230,456);
        CollisionT collisionT3 = new CollisionT();
        addObject(collisionT3,270,456);
        CollisionR collisionR = new CollisionR();
        addObject(collisionR,295,481);
        CollisionT collisionT4 = new CollisionT();
        addObject(collisionT4,367,360);
        CollisionT collisionT5 = new CollisionT();
        addObject(collisionT5,407,360);
        CollisionL collisionL2 = new CollisionL();
        addObject(collisionL2,343,383);
        CollisionR collisionR2 = new CollisionR();
        addObject(collisionR2,430,384);
        CollisionB collisionB = new CollisionB();
        addObject(collisionB,367,407);
        CollisionB collisionB2 = new CollisionB();
        addObject(collisionB2,407,407);
        CollisionL collisionL3 = new CollisionL();
        addObject(collisionL3,504,329);
        CollisionB collisionB3 = new CollisionB();
        addObject(collisionB3,528,353);
        CollisionB collisionB4 = new CollisionB();
        addObject(collisionB4,568,353);
    }   
    
    // Level 7
    public void level7() {
        setBackground(new GreenfootImage("BG3.png"));
        for (int i = 0; i < 4; i++) {
            CollisionT collisionT = new CollisionT();
            addObject(collisionT,20 + i * 40,320);
        }
        
        for (int i = 0; i < 5; i++) {
            CollisionB collisionB = new CollisionB();
            addObject(collisionB,20 + i * 40,370);
        }
        
        for (int i = 0; i < 3; i++) {
            CollisionR collisionR = new CollisionR();
            addObject(collisionR,204,346 - i * 40);
        }
        
        for (int i = 0; i < 3; i++) {
            CollisionL collisionL = new CollisionL();
            addObject(collisionL,164,296 - i * 34);
        }    
        
        for (int i = 0; i < 4; i++) {
            CollisionT collisionT5 = new CollisionT();
            addObject(collisionT5,187 + i * 40,203);
        }
        
        for (int i = 0; i < 2; i++) {
            CollisionB collisionB2 = new CollisionB();
            addObject(collisionB2,228 + i * 40,243);
        }
        
        for (int i = 0; i < 4; i++) {
              CollisionR collisionR4 = new CollisionR();
              addObject(collisionR4,331,227 + i * 36);
        }
        
        for (int i = 0; i < 4; i++) {
            CollisionL collisionL4 = new CollisionL();
            addObject(collisionL4,292,266 + i * 40);
        }
        
        for (int i = 0; i < 4; i++) {
            CollisionT collisionT = new CollisionT();
            addObject(collisionT,355 + i * 40,360);
        }
        
        for (int i = 0; i < 6; i++) {
            CollisionB collisionB = new CollisionB();
            addObject(collisionB,315 + i * 40,410);
        }
        
        for (int i = 0; i < 3; i++) {
            CollisionR collisionR = new CollisionR();
            addObject(collisionR,539,386 - i * 40);
        }
        
        for (int i = 0; i < 3; i++) {
            CollisionL collisionL = new CollisionL();
            addObject(collisionL,499,335 - i * 34);
        }    
        
        for (int i = 0; i < 9; i++) {
            CollisionT collisionT5 = new CollisionT();
            addObject(collisionT5,522 + i * 40,243);
        }
        
        for (int i = 0; i < 7; i++) {
            CollisionB collisionB2 = new CollisionB();
            addObject(collisionB2,563 + i * 40,283);
        }
        
        Spikes spikes = new Spikes();
        addObject(spikes,265,183);
        Spikes spikes2 = new Spikes();
        addObject(spikes2,295,183);
        Spikes spikes3 = new Spikes();
        addObject(spikes3,443,340);
        Spikes spikes4 = new Spikes();
        addObject(spikes4,473,340);
    }   
    
    // Level 8
    public void level8() {
        for (int i = 0; i < 3; i++) {
            CollisionT collisionT = new CollisionT();
            addObject(collisionT,20 + i * 40,320);
        }
        
        for (int i = 0; i < 3; i++) {
            CollisionB collisionB = new CollisionB();
            addObject(collisionB,20 + i * 40,370);
        }
        
        for (int i = 0; i < 5; i++) {
            Spikes spikes = new Spikes();
            addObject(spikes,10 + i * 30,485);
        }
        
        for (int i = 0; i < 13; i++) {
            Spikes spikes2 = new Spikes();
            addObject(spikes2,280 + i * 30,485);
        }
        
        for (int i = 0; i < 10; i++) {
            CollisionL collisionL = new CollisionL();
            addObject(collisionL,760,0 + i * 40);
        }
        
        CollisionR collisionR = new CollisionR();
        addObject(collisionR,124,345);
        movingPlatformH movingPlatformH = new movingPlatformH(450, 600, 1, true);
        addObject(movingPlatformH,500,250);
        Bouncer bouncer = new Bouncer();
        addObject(bouncer,205,481);
        CollisionB collisionB6 = new CollisionB();
        addObject(collisionB6,782,384);
    }   
    
    // Level 9
    public void level9() {
        for (int i = 0; i < 15; i++) {
            Spikes spikes = new Spikes();
            addObject(spikes,210 + i * 30,485);
        }
        
        for (int i = 0; i < 10; i++) {
            SpikesL spikesL = new SpikesL();
            addObject(spikesL,785,15 + i * 30);
        }
        
        for (int i = 0; i < 3; i++) {
            CollisionL collisionL = new CollisionL();
            addObject(collisionL,650,480 - i * 40);
        }
        
        for (int i = 0; i < 3; i++) {
            CollisionR collisionR = new CollisionR();
            addObject(collisionR,700,480 - i * 40);
        }
        
        CollisionT collisionT = new CollisionT();
        addObject(collisionT,675,378);
        CollisionL collisionL4 = new CollisionL();
        addObject(collisionL4,207,291);
        CollisionT collisionT2 = new CollisionT();
        addObject(collisionT2,231,268);
        CollisionB collisionB = new CollisionB();
        addObject(collisionB,231,315);
        CollisionB collisionB2 = new CollisionB();
        addObject(collisionB2,271,315);
        CollisionR collisionR4 = new CollisionR();
        addObject(collisionR4,295,292);
        CollisionT collisionT3 = new CollisionT();
        addObject(collisionT3,271,268);
        movingPlatformH movingPlatformH = new movingPlatformH(400, 600, 2, true);
        addObject(movingPlatformH,400,275);
        CollisionT collisionT4 = new CollisionT();
        addObject(collisionT4,471,176);
        CollisionR collisionR5 = new CollisionR();
        addObject(collisionR5,495,199);
        CollisionL collisionL5 = new CollisionL();
        addObject(collisionL5,447,199);
        CollisionB collisionB3 = new CollisionB();
        addObject(collisionB3,470,223);
        SpikesB spikes26 = new SpikesB();
        addObject(spikes26,470,242);
        Spikes spikes27 = new Spikes();
        addObject(spikes27,470,157);
        Bouncer bouncer = new Bouncer();
        addObject(bouncer,150,480);
    }   
    
    // Level 10
    public void level10() { 
        setBackground(new GreenfootImage("BG4.png"));
        for (int i = 0; i < 13; i++) {
            Spikes spikes = new Spikes();
            addObject(spikes,230 + i * 30,485);
        }        
        
        for (int i = 0; i < 12; i++) {
            SpikesB spikesB = new SpikesB();
            addObject(spikesB,241 + i * 30,118);
        }
        
        for (int i = 0; i < 9; i++) {
            CollisionT collisionT3 = new CollisionT();
            addObject(collisionT3,246 + i * 40,329);
        }
        
        for (int i = 0; i < 9; i++) {
            CollisionB collisionB = new CollisionB();
            addObject(collisionB,246 + i * 40,376);
        }
        
        for (int i = 0; i < 9; i++) {
            CollisionB collisionB = new CollisionB();
            addObject(collisionB,246 + i * 40,100);
        }
        
        for (int i = 0; i < 9; i++) {
            CollisionT collisionT3 = new CollisionT();
            addObject(collisionT3,246 + i * 40,55);
        }
        
        for (int i = 0; i < 3; i++) {
            JumpSwapL jumpSwapL = new JumpSwapL(false);
            addObject(jumpSwapL,400,180 + i * 40);
        }
        
        for (int i = 0; i < 3; i++) {
            JumpSwapR jumpSwapR = new JumpSwapR(false);
            addObject(jumpSwapR,445,180 + i * 40);
        }
        
        CollisionL collisionL = new CollisionL();
        addObject(collisionL,145,480);
        CollisionL collisionL2 = new CollisionL();
        addObject(collisionL2,145,440);
        CollisionT collisionT = new CollisionT();
        addObject(collisionT,169,416);
        CollisionR collisionR = new CollisionR();
        addObject(collisionR,193,440);
        CollisionR collisionR2 = new CollisionR();
        addObject(collisionR2,193,480);
        CollisionL collisionL3 = new CollisionL();
        addObject(collisionL3,630,480);
        CollisionL collisionL4 = new CollisionL();
        addObject(collisionL4,630,440);
        CollisionT collisionT2 = new CollisionT();
        addObject(collisionT2,654,416);
        CollisionR collisionR3 = new CollisionR();
        addObject(collisionR3,678,440);
        CollisionR collisionR4 = new CollisionR();
        addObject(collisionR4,678,480);
        CollisionL collisionL5 = new CollisionL();
        addObject(collisionL5,222,352);
        CollisionR collisionR5 = new CollisionR();
        addObject(collisionR5,590,352);
        CollisionR collisionR6 = new CollisionR();
        addObject(collisionR6,590,78);
        CollisionL collisionL6 = new CollisionL();
        addObject(collisionL6,222,77);
        Bouncer bouncer = new Bouncer();
        addObject(bouncer,347,305);
        Bouncer bouncer2 = new Bouncer();
        addObject(bouncer2,540,305);
        JumpSwapT jumpSwapT = new JumpSwapT(false);
        addObject(jumpSwapT,423,156);
        JumpSwapB jumpSwapB = new JumpSwapB(false);
        addObject(jumpSwapB,423,283);
    }   
    
    // Level 11
    public void level11() {
        for (int i = 0; i < 23; i++) {
            Spikes spikes = new Spikes();
            addObject(spikes,210 + i * 30,485);
        }
        
        for (int i = 0; i < 8; i++) {
            SpikesL spikesL = new SpikesL();
            addObject(spikesL,785,15 + i * 30);
        }
        
        CollisionT collisionT = new CollisionT();
        addObject(collisionT,779,324);
        CollisionT collisionT2 = new CollisionT();
        addObject(collisionT2,739,324);
        CollisionL collisionL = new CollisionL();
        addObject(collisionL,715,347);
        CollisionB collisionB = new CollisionB();
        addObject(collisionB,739,372);
        CollisionB collisionB2 = new CollisionB();
        addObject(collisionB2,779,372);
        Bouncer bouncer = new Bouncer();
        addObject(bouncer,146,484);
        JumpSwapT jumpSwapT = new JumpSwapT(true);
        addObject(jumpSwapT,240,236);
        JumpSwapL jumpSwapL = new JumpSwapL(true);
        addObject(jumpSwapL,216,259);
        JumpSwapR jumpSwapR = new JumpSwapR(true);
        addObject(jumpSwapR,264,259);
        JumpSwapB jumpSwapB = new JumpSwapB(true);
        addObject(jumpSwapB,240,284);
        JumpSwapT jumpSwapT2 = new JumpSwapT(true);
        addObject(jumpSwapT2,387,363);
        JumpSwapL jumpSwapL2 = new JumpSwapL(true);
        addObject(jumpSwapL2,364,387);
        JumpSwapR jumpSwapR2 = new JumpSwapR(true);
        addObject(jumpSwapR2,412,387);
        JumpSwapB jumpSwapB2 = new JumpSwapB(true);
        addObject(jumpSwapB2,388,411);
        JumpSwapT jumpSwapT3 = new JumpSwapT(false);
        addObject(jumpSwapT3,368,125);
        JumpSwapL jumpSwapL3 = new JumpSwapL(false);
        addObject(jumpSwapL3,344,149);
        JumpSwapR jumpSwapR3 = new JumpSwapR(false);
        addObject(jumpSwapR3,392,149);
        JumpSwapB jumpSwapB3 = new JumpSwapB(false);
        addObject(jumpSwapB3,368,173);
        JumpSwapT jumpSwapT4 = new JumpSwapT(false);
        addObject(jumpSwapT4,583,288);
        JumpSwapL jumpSwapL4 = new JumpSwapL(false);
        addObject(jumpSwapL4,559,311);
        JumpSwapR jumpSwapR4 = new JumpSwapR(false);
        addObject(jumpSwapR4,607,311);
        JumpSwapB jumpSwapB4 = new JumpSwapB(false);
        addObject(jumpSwapB4,583,335);
    }   
    
    // Level 12
    public void level12() {
        for (int i = 0; i < 22; i++) {
            Spikes spikes = new Spikes();
            addObject(spikes,0 + i * 30,485);
        }
        
        for (int i = 0; i < 2; i++) {
            CollisionT collisionT = new CollisionT();
            addObject(collisionT,20 + i * 40,320);
        }
        
        for (int i = 0; i < 3; i++) {
            CollisionB collisionB = new CollisionB();
            addObject(collisionB,20 + i * 40,370);
        }
        
        for (int i = 0; i < 2; i++) {
            CollisionR collisionR = new CollisionR();
            addObject(collisionR,124,346 - i * 40);
        }
        
        for (int i = 0; i < 4; i++) {
            JumpSwapR jumpSwapR = new JumpSwapR(false);
            addObject(jumpSwapR,506,235 + i * 40);
        }

        for (int i = 0; i < 4; i++) {
            JumpSwapL jumpSwapL = new JumpSwapL(false);
            addObject(jumpSwapL,458,236 + i * 40);
        }
        
        for (int i = 0; i < 6; i++) {
            CollisionL collisionL3 = new CollisionL();
            addObject(collisionL3,649,480 - i * 40);
        }
        
        for (int i = 0; i < 6; i++) {
            CollisionR collisionR4 = new CollisionR();
            addObject(collisionR4,697,480 - i * 40);
        }
        
        for (int i = 0; i < 2; i++) {
            CollisionL collisionL4 = new CollisionL();
            addObject(collisionL4,649,0 + i * 40);
        }
        
        for (int i = 0; i < 2; i++) {
            CollisionR collisionR5 = new CollisionR();
            addObject(collisionR5,697,0 + i * 40);
        }
        
        Spikes spikes = new Spikes();
        addObject(spikes,190,207);
        Spikes spikes2 = new Spikes();
        addObject(spikes2,220,207);
        CollisionL collisionL = new CollisionL();
        addObject(collisionL,84,306);  
        CollisionT collisionT3 = new CollisionT();
        addObject(collisionT3,103,283);
        CollisionL collisionL2 = new CollisionL();
        addObject(collisionL2,160,250);
        CollisionB collisionB4 = new CollisionB();
        addObject(collisionB4,184,274);
        CollisionB collisionB5 = new CollisionB();
        addObject(collisionB5,224,274);
        CollisionR collisionR3 = new CollisionR();
        addObject(collisionR3,248,250);
        CollisionT collisionT4 = new CollisionT();
        addObject(collisionT4,225,226);
        CollisionT collisionT5 = new CollisionT();
        addObject(collisionT5,185,226);
        movingPlatformH movingPlatformH = new movingPlatformH(150, 450, 2, true);
        addObject(movingPlatformH,150,420);
        movingPlatformV movingPlatformV = new movingPlatformV(200, 400, 1, false);
        addObject(movingPlatformV,590,400);
        JumpSwapT jumpSwapT = new JumpSwapT(false);
        addObject(jumpSwapT,482,211);
        JumpSwapB jumpSwapB = new JumpSwapB(false);
        addObject(jumpSwapB,482,379); 
        CollisionT collisionT6 = new CollisionT();
        addObject(collisionT6,673,257);
        Spikes spikes25 = new Spikes();
        addObject(spikes25,673,238);
        CollisionB collisionB6 = new CollisionB();
        addObject(collisionB6,673,64);
        SpikesB spikesB = new SpikesB();
        addObject(spikesB,672,83);
    }   
    
    // Level 13
    public void level13() {
        for (int i = 0; i < 18; i++) {
            Spikes spikes = new Spikes();
            addObject(spikes,210 + i * 30,485);
        }
        
        for (int i = 0; i < 4; i++) {
            CollisionB collisionB = new CollisionB();
            addObject(collisionB,326 + i * 40,110);
        }

        for (int i = 0; i < 4; i++) {
            CollisionT collisionT2 = new CollisionT();
            addObject(collisionT2,326 + i * 40,63);
        }
        
        for (int i = 0; i < 4; i++) {
            SpikesB spikesB = new SpikesB();
            addObject(spikesB,342 + i * 30,129);
        }
        
        for (int i = 0; i < 10; i++) {
            CollisionL collisionL3 = new CollisionL();
            addObject(collisionL3,740,480 - i * 40);
        }
        
        for (int i = 0; i < 10; i++) {
            CollisionR collisionR4 = new CollisionR();
            addObject(collisionR4,790,480 - i * 40);
        }
        
        CollisionL collisionL = new CollisionL();
        addObject(collisionL,139,480);
        CollisionT collisionT = new CollisionT();
        addObject(collisionT,163,456);
        CollisionR collisionR = new CollisionR();
        addObject(collisionR,187,480);
        JumpRing jumpRing = new JumpRing();
        addObject(jumpRing,255,350);
        JumpRing jumpRing2 = new JumpRing();
        addObject(jumpRing2,371,260);
        movingPlatformH movingPlatformH = new movingPlatformH(500, 670, 1, true);
        addObject(movingPlatformH,500,340);
        CollisionL collisionL2 = new CollisionL();
        addObject(collisionL2,302,87);
        CollisionR collisionR2 = new CollisionR();
        addObject(collisionR2,470,86);
        CollisionT collisionT6 = new CollisionT();
        addObject(collisionT6,765,97);
        CollisionT collisionT7 = new CollisionT();
        addObject(collisionT7,716,272);
        CollisionT collisionT8 = new CollisionT();
        addObject(collisionT8,676,272);
        CollisionL collisionL13 = new CollisionL();
        addObject(collisionL13,653,295);
        CollisionB collisionB5 = new CollisionB();
        addObject(collisionB5,677,318);
        CollisionB collisionB6 = new CollisionB();
        addObject(collisionB6,717,318);
        Bouncer bouncer = new Bouncer();
        addObject(bouncer,697,249);
    }   
    
    // Level 14
    public void level14() {
        for (int i = 0; i < 2; i++) {
            CollisionT collisionT = new CollisionT();
            addObject(collisionT,20 + i * 40,120);
        }
        
        for (int i = 0; i < 3; i++) {
            CollisionB collisionB = new CollisionB();
            addObject(collisionB,20 + i * 40,170);
        }
        
        for (int i = 0; i < 2; i++) {
            CollisionR collisionR = new CollisionR();
            addObject(collisionR,124,146 - i * 40);
        }
        
        for (int i = 0; i < 8; i++) {
            CollisionR collisionR3 = new CollisionR();
            addObject(collisionR3,336,19 + i * 40);
        }

        for (int i = 0; i < 8; i++) {
            CollisionL collisionL2 = new CollisionL();
            addObject(collisionL2,286,19 + i * 40);
        }
        
        for (int i = 0; i < 6; i++) {
            CollisionB collisionB4 = new CollisionB();
            addObject(collisionB4,399 - i * 40,371);
        }
        
        for (int i = 0; i < 3; i++) {
            Spikes spikes = new Spikes();
            addObject(spikes,202 + i * 30,305); 
        }

        for (int i = 0; i < 2; i++) {
            Spikes spikes = new Spikes();
            addObject(spikes,285 + i * 30,485); 
        }

        for (int i = 0; i < 4; i++) {
            Spikes spikes = new Spikes();
            addObject(spikes,615 + i * 30,485); 
        }
        
        for (int i = 0; i < 7; i++) {
            CollisionL collisionL3 = new CollisionL();
            addObject(collisionL3,740,480 - i * 40);
        }

        for (int i = 0; i < 7; i++) {
            CollisionR collisionR4 = new CollisionR();
            addObject(collisionR4,790,480 - i * 40);
        }
        
        CollisionL collisionL = new CollisionL();
        addObject(collisionL,82,106);
        CollisionT collisionT3 = new CollisionT();
        addObject(collisionT3,102,82);
        CollisionT collisionT4 = new CollisionT();
        addObject(collisionT4,360,323);
        CollisionT collisionT5 = new CollisionT();
        addObject(collisionT5,400,323);
        CollisionR collisionR11 = new CollisionR();
        addObject(collisionR11,423,347);
        CollisionL collisionL10 = new CollisionL();
        addObject(collisionL10,174,347);
        CollisionT collisionT6 = new CollisionT();
        addObject(collisionT6,198,324);
        CollisionT collisionT7 = new CollisionT();
        addObject(collisionT7,237,324);
        CollisionT collisionT8 = new CollisionT();
        addObject(collisionT8,262,324);
        JumpSwapB jumpSwapB = new JumpSwapB(true);
        addObject(jumpSwapB,296,442);
        JumpSwapL jumpSwapL = new JumpSwapL(true);
        addObject(jumpSwapL,273,419);
        JumpSwapR jumpSwapR = new JumpSwapR(true);
        addObject(jumpSwapR,319,420);
        JumpSwapT jumpSwapT = new JumpSwapT(true);
        addObject(jumpSwapT,296,396);
        JumpRing jumpRing = new JumpRing();
        addObject(jumpRing,496,349);
        CollisionT collisionT9 = new CollisionT();
        addObject(collisionT9,765,217);
        JumpSwapB jumpSwapB2 = new JumpSwapB(true);
        addObject(jumpSwapB2,588,345);
        JumpSwapL jumpSwapL2 = new JumpSwapL(true);
        addObject(jumpSwapL2,565,321);
        JumpSwapR jumpSwapR2 = new JumpSwapR(true);
        addObject(jumpSwapR2,611,321);
        JumpSwapT jumpSwapT2 = new JumpSwapT(true);
        addObject(jumpSwapT2,588,298);  
        JumpSwapB jumpSwapB3 = new JumpSwapB(false);
        addObject(jumpSwapB3,662,345);
        JumpSwapL jumpSwapL3 = new JumpSwapL(false);
        addObject(jumpSwapL3,638,321);
        JumpSwapR jumpSwapR3 = new JumpSwapR(false);
        addObject(jumpSwapR3,686,321);
        JumpSwapT jumpSwapT3 = new JumpSwapT(false);
        addObject(jumpSwapT3,662,298);
    }   
    
    // Level 15
    public void level15() {
        for (int i = 0; i < 2; i++) {
            CollisionT collisionT = new CollisionT();
            addObject(collisionT,20 + i * 40,320);
        }

        for (int i = 0; i < 3; i++) {
            CollisionB collisionB = new CollisionB();
            addObject(collisionB,20 + i * 40,370);
        }

        for (int i = 0; i < 2; i++) {
            CollisionR collisionR = new CollisionR();
            addObject(collisionR,124,346 - i * 40);
        }
        
        for (int i = 0; i < 10; i++) {
            CollisionL collisionL3 = new CollisionL();
            addObject(collisionL3,270,480 - i * 40);
        }

        for (int i = 0; i < 10; i++) {
            CollisionR collisionR4 = new CollisionR();
            addObject(collisionR4,320,480 - i * 40);
        }

        for (int i = 0; i < 10; i++) {
            CollisionL collisionL3 = new CollisionL();
            addObject(collisionL3,470,20 + i * 40);
        }

        for (int i = 0; i < 10; i++) {
            CollisionR collisionR4 = new CollisionR();
            addObject(collisionR4,520,20 + i * 40);
        }
        
        for (int i = 0; i < 7; i++) {
            Spikes spikes12 = new Spikes();
            addObject(spikes12,40 + i * 30,485); 
        }

        for (int i = 0; i < 15; i++) {
            Spikes spikes12 = new Spikes();
            addObject(spikes12,350 + i * 30,485); 
        }
        
        for (int i = 0; i < 12; i++) {
            SpikesL spikesL = new SpikesL();
            addObject(spikesL,785, 130 + i * 30);
        }
        
        CollisionL collisionL = new CollisionL();
        addObject(collisionL,79,307);
        CollisionT collisionT3 = new CollisionT();
        addObject(collisionT3,101,284);
        movingPlatformV movingPlatformV = new movingPlatformV(200, 400, 1, true);
        addObject(movingPlatformV,200,400);
        CollisionT collisionT4 = new CollisionT();
        addObject(collisionT4,294,95);
        CollisionB collisionB4 = new CollisionB();
        addObject(collisionB4,495,403);
        movingPlatformH movingPlatformH = new movingPlatformH(400, 750, 2, true);
        addObject(movingPlatformH,400,455);
        CollisionT collisionT5 = new CollisionT();
        addObject(collisionT5,721,349);
        CollisionL collisionL22 = new CollisionL();
        addObject(collisionL22,698,373);
        CollisionL collisionL23 = new CollisionL();
        addObject(collisionL23,698,413);
        CollisionB collisionB5 = new CollisionB();
        addObject(collisionB5,722,437);
        CollisionR collisionR23 = new CollisionR();
        addObject(collisionR23,745,373);
        CollisionR collisionR24 = new CollisionR();
        addObject(collisionR24,745,413);
        JumpRing jumpRing = new JumpRing();
        addObject(jumpRing,602,328);
        JumpRing jumpRing2 = new JumpRing();
        addObject(jumpRing2,652,206);
        JumpRing jumpRing3 = new JumpRing();
        addObject(jumpRing3,711,101);
        CollisionT collisionT6 = new CollisionT();
        addObject(collisionT6,781,49);
        CollisionL collisionL24 = new CollisionL();
        addObject(collisionL24,757,73);
        CollisionB collisionB6 = new CollisionB();
        addObject(collisionB6,780,97);
    }   
}
