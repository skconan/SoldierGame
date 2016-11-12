package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SoldierRenderer {
	private Texture soldierImg, bloodImg, bloodFrameImg, hitImg;
	private Sprite soldierSprite;
	private SpriteBatch batch;
	private BitmapFont font;
	private World world;
	private float oldBlood;
	
	public SoldierRenderer(SpriteBatch batch, World world) {
		this.batch = batch;
		this.world = world;
		soldierImg = new Texture("Untitled-1.png");
		hitImg = new Texture("attack.fw.png");
		bloodImg = new Texture("blood.fw.png");
		bloodFrameImg = new Texture("bloodFrame.fw.png");
		soldierSprite = new Sprite(soldierImg);
		font = new BitmapFont();
		oldBlood = world.getBlood();
	}
	
	public void render() {
		float x = (world.getSoldier().getPostion())[0];
		float y = (world.getSoldier().getPostion())[1];
		float rotation = (world.getSoldier().getPostion())[2];
        batch.begin();
        batch.draw(soldierSprite, x, y, 0, 0, world.getSoldier().getWidth(), world.getSoldier().getHeight(), 1, 1, rotation);
        font.draw(batch, "BULLET : " + world.getBullet(), SoldierGame.WIDTH - 100, SoldierGame.HEIGHT-10);  
        font.draw(batch, "HEALTH : ", 20, 40); 
        batch.draw(bloodImg, 90, 20, world.getBlood()*2, 30);
        batch.draw(bloodFrameImg, 90, 20, 200, 30);
        if(oldBlood != world.getBlood()) {
        	oldBlood = world.getBlood();
        	batch.draw(hitImg, 0, 0);
        }
        batch.end();
   
    }
}
