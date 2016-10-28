package com.mygdx.game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends ScreenAdapter {
	private SoldierGame soldierGame;
	private Texture bgImg;
	
	public GameScreen(SoldierGame soldierGame){
		this.soldierGame = soldierGame;
		bgImg = new Texture("bg.png");
	}
	
	public void render(float delta) {
        SpriteBatch batch = soldierGame.batch;
        batch.begin();
        batch.draw(bgImg, 0, 0);
        batch.end();
    }
	
}
