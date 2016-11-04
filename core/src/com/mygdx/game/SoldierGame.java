package com.mygdx.game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;
public class SoldierGame extends Game {
	public static final int HEIGHT = 450;
	public static final int WIDTH = 800;
	SpriteBatch batch;
	
	public void create () {
		batch = new SpriteBatch();
		setScreen(new GameScreen(this));
	}

	public void render () {
		super.render();
	}

	public void dispose () {
        batch.dispose();
    }
}
