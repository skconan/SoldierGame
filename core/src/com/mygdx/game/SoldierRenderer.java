package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class SoldierRenderer {
	private Texture soldierImg;
	private Sprite soldierSprite;
	private Point point = new Point();
	SpriteBatch batch;

	public SoldierRenderer(SpriteBatch batch){
		this.batch = batch;
		soldierImg = new Texture("Untitled-1.png");
		soldierSprite = new Sprite(soldierImg);
	}
	
	public void render() {
		Vector2 pt = point.getPositionMouse();

        batch.begin();
        batch.draw(soldierSprite, pt.x/7 + SoldierGame.WIDTH-350, -30 + -pt.y/10, 0, 0, 300, 200, 1, 1, -pt.x/120);
        batch.end();
    }
}
