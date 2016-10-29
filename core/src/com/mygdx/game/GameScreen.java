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
	private Texture pointImg;
	private Texture soldierImg;
	private Point point = new Point();
	public GameScreen(SoldierGame soldierGame){
		this.soldierGame = soldierGame;
		bgImg = new Texture("bg.png");
		pointImg = new Texture("point.fw.png");
		soldierImg = new Texture("soldierGun.fw.png");
	}
	
	public void render(float delta) {
		float dimension = point.getDimension();

		Vector2 pt = point.getPositionMouse();
        SpriteBatch batch = soldierGame.batch;
        
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
        batch.begin();
        batch.draw(bgImg, 0, 0);
        batch.draw(pointImg, pt.x-dimension/2,SoldierGame.HEIGHT-pt.y-dimension/2,dimension,dimension);
        batch.draw(soldierImg, pt.x-60, 0);
        batch.end();
    }
	
	public void update(float delta){
		
	}
	
	
}
