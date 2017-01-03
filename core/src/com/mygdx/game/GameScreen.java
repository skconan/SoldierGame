package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input.Keys;

public class GameScreen extends ScreenAdapter {
	private World world;
	private WorldRenderer worldRenderer;
	
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
		if(world.status == 's') {
			start();
		} else if (world.status == 'p') {
			play();
		} else if (world.status == 'w') {
			gameWin();
		} else if (world.status == 'o') {
			gameOver();
		}
		
	}
	
	public void start() {
    	if(Gdx.input.isKeyJustPressed(Keys.ANY_KEY)) {
    		world.status = 'p';
    		world.init();
    		world.monsters.initMonsters();
    	}
	}
	
	public void play() {
		if (Gdx.input.justTouched()){
			world.point.shoot();
		}
		if(Gdx.input.isKeyJustPressed(Keys.RIGHT) || Gdx.input.isKeyJustPressed(Keys.D)) {
			world.arrow.checkPress(0);
        } else if(Gdx.input.isKeyJustPressed(Keys.UP) || Gdx.input.isKeyJustPressed(Keys.W)) {
        	world.arrow.checkPress(90);	
        } else if(Gdx.input.isKeyJustPressed(Keys.LEFT) || Gdx.input.isKeyJustPressed(Keys.A)) {
        	world.arrow.checkPress(180);	
        } else if(Gdx.input.isKeyJustPressed(Keys.DOWN) || Gdx.input.isKeyJustPressed(Keys.S)) {
        	world.arrow.checkPress(270);	
        }
		if(Gdx.input.isKeyJustPressed(Keys.R)) {
			world.bullet = 1000;
        }
	}
	
	public void gameOver() { 
    	if(Gdx.input.isKeyJustPressed(Keys.ANY_KEY)) {
    		world.status = 'p';
    		world.init();
    		world.monsters.initMonsters();
    	}
	}
	
	public void gameWin() {  
    	if(Gdx.input.isKeyJustPressed(Keys.ANY_KEY)) {
    		world.status = 's';
    	}
	}
}
