package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class Point {
	private float dimension = 120;
	private Texture pointImg;
	private Vector2 pt;
	private World world;
	private ShapeRenderer shapeRenderer = new ShapeRenderer();
	private	SpriteBatch batch;
	private int radius = 0;
	private Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/gun.wav"));

	public Point(SpriteBatch batch, World world) {
		this.batch = batch;
		this.world = world;
		pointImg = new Texture("point.fw.png");
	}
	
	public void render() {
		float dimension = getDimension();
		pt = getPositionMouse();
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(1, 1, 0, 2);
		shapeRenderer.circle(pt.x, SoldierGame.HEIGHT-pt.y, radius);
		shapeRenderer.end();
		batch.begin();
        batch.draw(pointImg, pt.x-dimension/2 ,SoldierGame.HEIGHT-pt.y-dimension/2, dimension, dimension);
        batch.end();  
        radius = 0;
	}
	
	private float getDimension() { 
		float widthRatio = ((getPositionMouse().x - (SoldierGame.WIDTH/2))/(SoldierGame.WIDTH/2))/2;
		float widthRatioAbs = Math.abs(widthRatio);
		float heightRatio = (getPositionMouse().y/SoldierGame.HEIGHT)/2;
		float newDimension = dimension;

		if(heightRatio > 0.35) {
			newDimension = (heightRatio*2)*dimension;
		} else {
			newDimension = (heightRatio + widthRatioAbs)*dimension;
		}
		
		if(newDimension < 20) {
			newDimension = 20;
		}
		return newDimension;
	}
	
	public Vector2 getPositionMouse() {
		Vector2 position = new Vector2(0,0);
		position.x = Gdx.input.getX();
		position.y = Gdx.input.getY();
		return position;		
	}
	
	public void shoot() {
		Vector2 pt = getPositionMouse();
		if (world.bullet > 0 && world.blood > 0) {
			world.monsters.kill((int)pt.x, SoldierGame.HEIGHT-(int)pt.y);
			radius = 20;
			world.decreaseBullet();
			sound.play(1.0f);
		} 
	}
}
