package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.RogueLite;
import com.mygdx.game.RoomRepresentation;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = RoomRepresentation.getWindowSize();
		config.height = RoomRepresentation.getWindowSize();
		new LwjglApplication(new RogueLite(), config);
	}
}
