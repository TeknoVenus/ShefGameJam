# RogueCell
## A game made in 30 hours
This game was made using [Libgdx](https://github.com/libgdx/libgdx) during GameJam 3.0 at the University of Sheffield. 
![screenshot](http://i.imgur.com/QPGzhi1.png)

## Backstory
You are a private detective on the verge on uncovering the mafia. They've acquired advanced technology and devolved you into a single cell. In order to uncover the mafia and regain your true form you must fight the mafia, and absorb their dna to evolve.

### Gameplay
Use WASD or arrow keys to move around and between rooms. The map is procedually generated so is different every time you load the game. Click to shoot the mafia. Your health is displayed in the bottom left hand corner. 

**Note:** This game is very buggy and missing a lot of features. We built it in 30 hours so give us a break!

## Build Instructions
Libgdx projects use Gradle to manage dependencies, the build process, and IDE integration. To open RogueCell in your IDE of choice, simply clone this repo and follow the detailed instructions over on the [Libgdx Wiki](https://github.com/libgdx/libgdx/wiki/Setting-up-your-Development-Environment-%28Eclipse%2C-Intellij-IDEA%2C-NetBeans%29). 

To build a distributable .jar file, simply run `gradlew desktop:dist`. This will produce the file in the `\desktop\build\libs` directory.
