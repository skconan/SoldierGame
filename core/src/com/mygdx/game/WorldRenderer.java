package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class WorldRenderer {
	private World world;
	private Texture bgImg, bgImgView, bgImgTop, moonImg, playImg, gameOverImg, gameWinImg;
	private BitmapFont font;
	private SpriteBatch batch;
	
	WorldRenderer(SoldierGame soldierGame, World world) {
		this.world = world;
		this.batch = soldierGame.batch;
		bgImg = new Texture("bg.png");
		bgImgTop = new Texture("bg_top.gif");
		bgImgView = new Texture("bg_view.png");
		moonImg = new Texture("moon.png");
		playImg = new Texture("start.fw.png");
		gameOverImg = new Texture("gameOver.fw.png");
		gameWinImg = new Texture("win.fw.png");
		font = new BitmapFont();
		Gdx.graphics.setResizable(false);
	}
	
	public void render(float delta) {      
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        if(world.getStatusGame() == 's') {
        	batch.draw(playImg, 0, 0);
        } else if(world.getStatusGame() == 'p') {
        	background();
        	font.draw(batch, "SCORE : " + world.score +"/" +world.scoreWin, 10, SoldierGame.HEIGHT-10);
        } else if(world.getStatusGame() == 'w') {
        	batch.draw(gameWinImg, 0, 0);
        } else if(world.getStatusGame() == 'o') {
        	batch.draw(gameOverImg, 0, 0);
	    }
        batch.end();
        if(world.getStatusGame() == 'p') {
	        world.monstersRenderer.render();
	        world.arrow.render();
	        world.soldier.render();
	        world.point.render();
        }
    }
	
	public void background() {
		Vector2 ptMouse = world.point.getPositionMouse();
		batch.draw(bgImgTop, 0, 0, SoldierGame.WIDTH, SoldierGame.HEIGHT);
        batch.draw(bgImgView,(int) (0.07*ptMouse.x-SoldierGame.WIDTH/2), 0, SoldierGame.WIDTH*2, SoldierGame.HEIGHT);
        batch.draw(moonImg,(int) (0.01*ptMouse.x-SoldierGame.WIDTH/2-200), 50, SoldierGame.WIDTH*2, SoldierGame.HEIGHT);
        batch.draw(bgImg, 0, 0, SoldierGame.WIDTH, SoldierGame.HEIGHT);
	}
}
