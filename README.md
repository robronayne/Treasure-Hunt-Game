# Treasure Hunt Game

This was the final project I completed while taking the Java programming course with Professor Schougaard at San Diego Mesa College. This courses syllabus may be accessed [here](/Syllabus.pdf). For a complete demonstration of the game, please view the comprehensive tutorial [here](https://screencast-o-matic.com/watch/cql6q4URdD).

## About

This game was constructed with the intent of reinforcing object-oriented design principles in Java. Since the board's layout is randomly generated, every play through will be different. A player can simply click the covered tiles to reveal whether or not they contain treasure, a troll, or nothing. Tiles that contain treasure will reward a player with 1 to 3 points, and tiles that contain trolls will steal the points that the player has accumulated. Finally, tiles that contain nothing won't affect the player's score total. 
 
Each of the source code files is *excessively* well documented, and any logic or design questions a user might have about the program will most likely be answered there.

## Usage

In order to get started, the user must navigate to the provided Treasure Game directory in their terminal. Once inside the directory run the command `javac *.java` to compile the Java executable. After completion, the game can be played via `java TreasureGame`.

To clean up the directory after playing the game, run `rm *.class` to remove all of the class files that were created during compilation.