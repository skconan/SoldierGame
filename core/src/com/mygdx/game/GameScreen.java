package com.mygdx.game;

import java.security.Key;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

public class GameScreen extends ScreenAdapter {
	private World world;
	private WorldRenderer worldRenderer;
	private Vector2 mousePt = new Vector2();
	private Vector2 objPt = new Vector2();
	
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
		mousePt = world.point.getPositionMouse();
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
		objPt.x = SoldierGame.WIDTH/2;
		objPt.y = SoldierGame.HEIGHT/2;
    	if(world.mouseLeftClickButton(mousePt, objPt)) {
    		world.status = 'p';
    		world.init();
    		world.monsters.initMonsters();
    	}
    	System.out.println(world.status);
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
	}
	
	public void gameOver() { 
		objPt.x = SoldierGame.WIDTH/2;
		objPt.y = SoldierGame.HEIGHT/2;
    	if(world.mouseLeftClickButton(mousePt, objPt)) {
    		world.status = 's';
    	}
	}
	
	public void gameWin() {  
		objPt.x = SoldierGame.WIDTH/2;
		objPt.y = SoldierGame.HEIGHT/2;
    	if(world.mouseLeftClickButton(mousePt, objPt)) {
    		world.status = 's';
    	}
	}
}
