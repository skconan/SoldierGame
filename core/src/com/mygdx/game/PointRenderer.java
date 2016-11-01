package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class PointRenderer {
	private Point point = new Point();
	private Texture pointImg;
	SpriteBatch batch;
	
	public PointRenderer(SpriteBatch batch){
		this.batch = batch;
	}
	
	public void render(){
		pointImg = new Texture("point.fw.png");
		
		float dimension = point.getDimension();

		Vector2 pt = point.getPositionMouse();
		batch.begin();
        batch.draw(pointImg, pt.x-dimension/2,SoldierGame.HEIGHT-pt.y-dimension/2,dimension,dimension);
        batch.end();
	}
}
