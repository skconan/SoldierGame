package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class SoldierRenderer {
	private Texture soldierImg;
	private Point point = new Point();
	SpriteBatch batch;
	
	public SoldierRenderer(SpriteBatch batch){
		this.batch = batch;
	}
	
	public void render() {
		Vector2 pt = point.getPositionMouse();
		soldierImg = new Texture("soldierGun.fw.png");
        batch.begin();
        System.out.println(pt.x);
        batch.draw(soldierImg, pt.x-60, 0);
        batch.end();
    }
}
