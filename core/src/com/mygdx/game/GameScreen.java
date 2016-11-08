package com.mygdx.game;

import com.badlogic.gdx.ScreenAdapter;

public class GameScreen extends ScreenAdapter {
	World world;
	WorldRenderer worldRenderer;
	
	public GameScreen(SoldierGame soldierGame){
		world = new World(soldierGame);
		worldRenderer = new WorldRenderer(soldierGame, world);
	}
	
	public void render(float delta) {
		worldRenderer.render(delta);
		update(delta);
    }
	
	public void update(float delta){
		world.update();
	}
	
	
}
