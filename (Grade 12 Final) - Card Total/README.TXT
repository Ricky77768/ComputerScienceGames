Creators
--------
Ricky Lu, Charlie Liu, Jason Li

Data Structures Used
--------------------
ArrayList (Found in MyWorld (World), Player (Actor))
- (MyWorld) The ArrayList is used to store all the Card (class) that should be on screen during each round
- (Player) The ArrayList stores all the Card (class) selected by user currently. It changes as the user selects/deselects cards. 
  The player also has an Integer ArrayList for storing all possible sums of all combinations of ints from an array.

Recursion Used
--------------
Recursive Sorting Algorithm - Merge Sort (Found in MyWorld (World))
- We have used to recurisve algorithm to sort the cards on screen into an ascending order.

Recurisve Method - FindSum (Found in Player (Actor))
- This method is used to find possible sums of all combinations of ints from an array.
* Code taken and modified from https://www.geeksforgeeks.org/print-sums-subsets-given-set/

Features Difficult to Implement
-------------------------------
- We thought of implementing a free mode where the player can choose how difficulty their game is by changing game parameters themselves. 
  However, the slider aspect and their value calculations were fairly difficult to implement.

- We tested around with many scoring methods to ensure that the score given is as fair as possible, as the base of the game is down to luck. 
  Some of the actions include limiting the range of numbers that can be chosen as target number, giving a score based on accuracy and speed, 
  and tweaking the difficulty many times.

- We realized mid-way that we have upwards of 10 types of buttons to implement, which will create too many seperate classes. 
  In the end, we decided to combine all of them into one class. Due to conflicting variables and functions, many buttons have to be re-coded.

Features Not Working
--------------------
 - Sometimes, when a Card/Button is clicked very fast, the clicking sound effect will not play.
 - The Background music was not looping properly, we made it so that it plays once, and if the user returns to the start screen, the music restarts

Credits
-------
Background Song: https://www.youtube.com/watch?v=95ZEgu1LJXc