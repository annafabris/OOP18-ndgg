## Ndgg

The proposed software is a two-dimensional platform game inspired by the game [Nidhogg](https://store.steampowered.com/app/94400/Nidhogg/), where two players must challenge each other to defend their side of the room and to try to reach the door in the opponent's side to go to the next room. 

You can jump, throw the opponenent the sword or simply attack him. The first player who reaches the goal will win the match. Beware, your opponents will fight back!

## Authors
* Amaducci Giada  
* [Fabris Anna](https://github.com/annafabris)
* Lega Davide  
* Lucchi Riccardo

## Player's guide
As soon as you start the application, the main menu will open, from which you'll be able to choose between the following options
* **New game**: to start a new game
* **Options**: to adjust the game settings to your liking:
    * Volume: this screen allows you to change the volume in the game
    * Music: this screen allows you to remove music from the game
* **Exit**: to exit the application


![Alt text](res/images/README/menu.png?raw=true)

After the game screen is displayed, the right player can use the arrows to interact in the game, the left player can use the W, A, S, D keys.
The player can walk, run, jump, throw the sword, pick it up and disarm the opponent.

![Alt text](res/images/README/game.png)

##### Commands Player1:  
A -> Move Left  
D -> Move Right  
W -> Jump  
X -> Change Guard  
Z -> Attack  
Spacebar -> Throw Sword  

##### Commands Player2:
J -> Move Left  
L -> Move Right  
I-> Jump  
N -> Change Guard  
P -> Attack  
M -> Throw Sword    

## Usage
This game needs Java 8 and JavaFX 8 to run.

## Project structure
The project is structured in folders:

- `css`, which contains all the css files;
- `db`, everything necessary to make the database work;
- `images`, just some README images;
- `js`, which contains all the javascripts files;
- `template`, which contains some already given php files;
- `uploads`, which contains some useful media;
- `utils`, which contains all the function/images necessary for the site;
