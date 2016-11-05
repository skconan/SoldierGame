package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GameScreen extends ScreenAdapter {
	private SoldierGame soldierGame;
	private Texture bgImg, bgImgView, bgImgTop, moon;
	private PointRenderer pointRenderer;
	private MonstersRenderer monstersRenderer;
	private SoldierRenderer soldierRenderer;
	private Point point = new Point();

	public GameScreen(SoldierGame soldierGame){
		this.soldierGame = soldierGame;
		soldierRenderer = new SoldierRenderer(soldierGame.batch);
		pointRenderer = new PointRenderer(soldierGame.batch);
		monstersRenderer = new MonstersRenderer(soldierGame.batch);
		
		bgImg = new Texture("bg.png");
		bgImgTop = new Texture("bg_top.gif");
		bgImgView = new Texture("bg_view.png");
		moon = new Texture("moon.png");
	}
	
	public void render(float delta) {
		
		Vector2 pt = point.getPositionMouse();
        SpriteBatch batch = soldierGame.batch;
        
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       
        batch.begin();
        batch.draw(bgImgTop, 0, 0, SoldierGame.WIDTH, SoldierGame.HEIGHT);
        batch.draw(bgImgView,(int) (0.07*pt.x-SoldierGame.WIDTH/2), 0, SoldierGame.WIDTH*2, SoldierGame.HEIGHT);
        batch.draw(moon,(int) (0.01*pt.x-SoldierGame.WIDTH/2-200), 50, SoldierGame.WIDTH*2, SoldierGame.HEIGHT);
        batch.draw(bgImg, 0, 0, SoldierGame.WIDTH, SoldierGame.HEIGHT);
        batch.end();
        
        soldierRenderer.render();
        pointRenderer.render();
        monstersRenderer.render(delta);
    }
	
	public void update(float delta){
	
	}
	
	
}
