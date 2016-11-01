package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GameScreen extends ScreenAdapter {
	private SoldierGame soldierGame;
	private Texture bgImg;
	private PointRenderer pointRenderer;
	private MonstersRenderer monstersRenderer;
	private SoldierRenderer soldierRenderer;
	private Monsters monsters;
	public GameScreen(SoldierGame soldierGame){
		monsters = new Monsters();
		soldierRenderer = new SoldierRenderer(soldierGame.batch);
		pointRenderer = new PointRenderer(soldierGame.batch);
		monstersRenderer = new MonstersRenderer(soldierGame.batch,monsters);
		this.soldierGame = soldierGame;
	}
	
	public void render(float delta) {
		bgImg = new Texture("bg.png");
		
        SpriteBatch batch = soldierGame.batch;
        
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.begin();
        batch.draw(bgImg, 0, 0);
        batch.end();
        soldierRenderer.render();
        monstersRenderer.render();
        pointRenderer.render();
    }
	
	public void update(float delta){
		
	}
	
	
}
