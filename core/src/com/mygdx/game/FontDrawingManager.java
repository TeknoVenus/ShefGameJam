package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class FontDrawingManager {
	
	private BitmapFont text = new BitmapFont();
	private Batch batch;
	
	public FontDrawingManager(Batch batch) {
		this.batch = batch;
	}
	
	public void drawText(String text, float x, float y) {
		this.text.draw(batch, text, x, y);
	}
}
