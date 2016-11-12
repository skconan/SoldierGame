package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input.Keys;

public class GameScreen extends ScreenAdapter {
	World world;
	WorldRenderer worldRenderer;
	
	public GameScreen(SoldierGame soldierGame) {
		world = new World(soldierGame);
		worldRenderer = new WorldRenderer(soldierGame, world);
	}
	
	public void render(float delta) {
		worldRenderer.render(delta);
		update(delta);
    }
	
	public void update(float delta) {
		world.update();
		if (Gdx.input.justTouched()){
			world.point.shoot();
		}
		if(Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
			world.arrow.checkPress(0);
        } else if(Gdx.input.isKeyJustPressed(Keys.UP)) {
        	world.arrow.checkPress(90);	
        } else if(Gdx.input.isKeyJustPressed(Keys.LEFT)) {
        	world.arrow.checkPress(180);	
        } else if(Gdx.input.isKeyJustPressed(Keys.DOWN)) {
        	world.arrow.checkPress(270);	
        }
	}
	
	
}
