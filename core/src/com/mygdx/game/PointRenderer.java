package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class PointRenderer {
	private Texture pointImg;
	private Vector2 pt;
	private World world;
	private ShapeRenderer shapeRenderer = new ShapeRenderer();
	private	SpriteBatch batch;
		
	public PointRenderer(SpriteBatch batch, World world){
		this.batch = batch;
		this.world = world;
		pointImg = new Texture("point.fw.png");
	}
	
	public void update() {
		shoot();
	}
	
	private void shoot() {
		Vector2 pt = world.getPoint().getPositionMouse();
		if (world.getBullet() > 0 && world.getBlood() > 0 && Gdx.input.justTouched()){
			world.getMonsters().kill((int)pt.x, SoldierGame.HEIGHT-(int)pt.y);
			shapeRenderer.begin(ShapeType.Filled);
			shapeRenderer.setColor(1, 1, 0, 2);
			shapeRenderer.circle(pt.x, SoldierGame.HEIGHT-pt.y, 20);
			shapeRenderer.end();
			world.decreaseBullet();
		}
	}
	
	public void render() {
		update();
		float dimension = world.getPoint().getDimension();
		pt = world.getPoint().getPositionMouse();
		batch.begin();
        batch.draw(pointImg, pt.x-dimension/2 ,SoldierGame.HEIGHT-pt.y-dimension/2, dimension, dimension);
        batch.end();        
	}
}
