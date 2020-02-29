Creators
--------
Ricky Lu, Charlie Liu, Jason Li

Data Structures Used
--------------------
HashMap (Found in Display (Actor))
- The HashMap is used implement a method to calculate the score of a word for our game
- Different letters have different score values based on their rarity in the English words ("A" has score of 1, and "Z" has a score of 10)
- The key of the HashMap is the letter, and the value is the score which the letter gives

Queue (Found in MyWorld (World))
- The Queue contains a list of Obstacle, each containing a random word 
- This Queue will dequeue one Obstacle at a time until it becomes empty, which will be refilled within a method
- Queue is very efficient in this case as it is the fastest way to display objects in the order that they were queued in

ArrayList (Found in MyWorld (World))
- The ArrayList is used to store all of the words read the text file 
- Each time a Obstacle is created, a new, random word will be selected inside this ArrayList and used
- The 3 text files provided were combined into one for simplicity

Features Difficult to Implement
-------------------------------
- We thought of an idea for a restricted view on the maximum difficulty. We created a black image with a transparent circle in the middle section that
  follows the player's mouse. Since the Flashlight is on top of most Actors, only the area under the transparent section can be seen, therefore effectively
  limiting the view area.

- When creating the Bullet class, we wanted it to only remove the Obstacle with the matching word. However, since the user may not always type the word 
  closest to the Planet, using a simple removeTouching() method will not work since it will possibly pass through multiple Obstacles and removing all of them.
  The solution was to put a parameter inside the Bullet constructor, which is a reference to the target Obstacle. So whenever the Bullet touches an Obstacle,
  it can check if the references are the same to decide if it should remove it.

Features Not Working
--------------------
 - We found it difficult to reduce the amount of lag when typing letters into the game interface on the school computers. Since the java based game mainly 
   utilizes CPU, there are sometimes delays when entering letters in the game.

 - Moreover, the game in general is slighty laggier than expected when run as a .jar file in school. It is mainly caused by the amount of moving objects in the
   world simultaneously and the somewhat outdated CPUs on school computer. We have attempted to fix the lag, but were not successful in the end.
