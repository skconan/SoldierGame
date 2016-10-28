package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;

public class GameScreen extends ScreenAdapter {
	private SoldierGame soldierGame;
	private Texture bgImg;
	private Texture pointImg;
	
	public GameScreen(SoldierGame soldierGame){
		this.soldierGame = soldierGame;
		bgImg = new Texture("bg.png");
		pointImg = new Texture("point.fw.png");
	}
	
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Vector2 pt = getPositionMouse();
        SpriteBatch batch = soldierGame.batch;
        batch.begin();
        batch.draw(bgImg, 0, 0);
        batch.draw(pointImg, pt.x-60,SoldierGame.HEIGHT-pt.y-60);
        batch.end();
    }
	
	public Vector2 getPositionMouse() {
		Vector2 position = new Vector2(0,0);
		position.x = Gdx.input.getX();
		position.y = Gdx.input.getY();
		return position;		
	}
}
