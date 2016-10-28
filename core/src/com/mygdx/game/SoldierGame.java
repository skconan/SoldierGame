package com.mygdx.game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;
public class SoldierGame extends Game {
	SpriteBatch batch;
	
	public void create () {
		batch = new SpriteBatch();
		setScreen(new GameScreen(this));
	}

	public void render () {
		super.render();
	}

	public void dispose () {

	}
}
