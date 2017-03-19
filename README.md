# FloodIt-Game

My own implementation of the popular Game [FloodIt](https://play.google.com/store/apps/details?id=com.labpixies.flood&hl=en). 

In this game, a square board is filled with dots, each having one of six possible colors. The players initially “captures” the top left dot. The player keeps choosing one of the six colors. Each time a color is chosen, the dots that have already been captured turn into that color. Each dot that is a neighbor of a captured dot, and whose color matches the newly selected color becomes itself captured. The game ends once the entire board is captured (the board is “flooded” because all the dots have the same color). As the game can only progress (a captured dot cannot be lost), the issue of the game is certain. The goal is thus to flood the board in as few steps as possible.
