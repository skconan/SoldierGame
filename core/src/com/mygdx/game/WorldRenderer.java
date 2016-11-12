package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class WorldRenderer {
	private SoldierGame soldierGame;
	private World world;
	private Texture bgImg, bgImgView, bgImgTop, moonImg, playImg, gameOverImg;
	private BitmapFont font;
	private SpriteBatch batch;
	private Vector2 mousePt = new Vector2();
	private Vector2 objPt = new Vector2();
	
	WorldRenderer(SoldierGame soldierGame, World world) {
		this.soldierGame = soldierGame;
		this.world = world;
		this.batch = soldierGame.batch;
		bgImg = new Texture("bg.png");
		bgImgTop = new Texture("bg_top.gif");
		bgImgView = new Texture("bg_view.png");
		moonImg = new Texture("moon.png");
		playImg = new Texture("start.gif");
		gameOverImg = new Texture("gameOver.fw.png");
		font = new BitmapFont();
		Gdx.graphics.getWidth(); 
		Gdx.graphics.getHeight();
		Gdx.graphics.getDisplayMode();
		Gdx.graphics.setResizable(false);
	}
	
	public void render(float delta) {      
		update();
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        if(world.getStatusGame() == 1) {
        	start();
        } else if(world.getStatusGame() == 2) {
        	background();
        	font.draw(batch, "SCORE : " + world.getScore(), 10, SoldierGame.HEIGHT-10);
            	
        } else if(world.getStatusGame() == 4) {
        	gameOver();
        }
        
        batch.end();
        if(world.getStatusGame() == 2) {
			world.getPointRenderer().render();
	        world.getMonstersRenderer().render();
	        world.getArrowRenderer().render();
	        world.getSoldierRenderer().render();
        }
    }
	
	public void background() {
		batch.draw(bgImgTop, 0, 0, SoldierGame.WIDTH, SoldierGame.HEIGHT);
        batch.draw(bgImgView,(int) (0.07*mousePt.x-SoldierGame.WIDTH/2), 0, SoldierGame.WIDTH*2, SoldierGame.HEIGHT);
        batch.draw(moonImg,(int) (0.01*mousePt.x-SoldierGame.WIDTH/2-200), 50, SoldierGame.WIDTH*2, SoldierGame.HEIGHT);
        batch.draw(bgImg, 0, 0, SoldierGame.WIDTH, SoldierGame.HEIGHT);
	}
	
	public void start() {
		objPt.x = 0;
		objPt.y = 0;
		batch.draw(playImg, objPt.x, objPt.y);   
		objPt.x = SoldierGame.WIDTH/2;
		objPt.y = SoldierGame.HEIGHT/2;
    	if(world.mouseLeftClickButton(mousePt, objPt)) {
    		world.status ++;
    	}
    	System.out.println(world.status);
	}
	
	public void play() {
        
	}
	
	public void gameOver() {
		objPt.x = 0;
		objPt.y = 0;
		batch.draw(gameOverImg, objPt.x, objPt.y);   
		objPt.x = SoldierGame.WIDTH/2;
		objPt.y = SoldierGame.HEIGHT/2;
    	if(world.mouseLeftClickButton(mousePt, objPt)) {
    		world.status = 1;
    	}
    	System.out.println(world.status);
	}
	
	public void update() {
		mousePt = world.getPoint().getPositionMouse();
	}
}
