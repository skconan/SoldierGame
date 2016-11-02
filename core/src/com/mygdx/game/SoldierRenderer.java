package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class SoldierRenderer {
	private Texture soldierImg;
	private Point point = new Point();
	private SoldierGame soldierGame;
	SpriteBatch batch;
	
	public SoldierRenderer(SpriteBatch batch){
		this.batch = batch;
		soldierImg = new Texture("soldier00.fw.png");
	}
	
	public void render() {
		Vector2 pt = point.getPositionMouse();
		
        batch.begin();
//        System.out.println(pt.x);
        batch.draw(soldierImg, pt.x - 60, 0, 120, 120);
        batch.end();
    }
}
