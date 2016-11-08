package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SoldierRenderer {
	private Texture soldierImg;
	private Sprite soldierSprite;
	SpriteBatch batch;
	World world;
	
	public SoldierRenderer(SpriteBatch batch, World world){
		this.batch = batch;
		this.world = world;
		soldierImg = new Texture("Untitled-1.png");
		soldierSprite = new Sprite(soldierImg);
	}
	
	public void render() {
		float x = (world.getSoldier().getPostion())[0];
		float y = (world.getSoldier().getPostion())[1];
		float rotation = (world.getSoldier().getPostion())[2];
		
        batch.begin();
        batch.draw(soldierSprite, x, y, 0, 0, 300, 200, 1, 1, rotation);
        batch.end();
    }
}
