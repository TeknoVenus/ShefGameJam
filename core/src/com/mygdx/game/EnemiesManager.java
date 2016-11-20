package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

public class EnemiesManager {
    private static List<Enemy> enemies = new ArrayList<Enemy>(5);
    private static Player player;
    private static RogueLite  game;
    
    public static void addEnemy(int x, int y) {
    	enemies.add(new Enemy(x, y, player, game.batch));
    }
    
    public static List<Enemy> getEnemies() {
    	return enemies;
    }
    
    public static void setPlayer(Player _player) {
    	player = _player;
    }
    
    public static void setGame(RogueLite _game) {
    	game = _game;
    }
}
