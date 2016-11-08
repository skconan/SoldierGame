package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class PointRenderer {
	ShapeRenderer shapeRenderer = new ShapeRenderer();
	private Texture pointImg;
	private Vector2 pt;
	private int radius = 0;
	SpriteBatch batch;
	World world;
	
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
		
		if (Gdx.input.isButtonPressed(Buttons.LEFT)){
			if(world.getBullet() > 0 && world.getBlood() > 0) {
				world.getMonsters().kill((int)pt.x, SoldierGame.HEIGHT-(int)pt.y, radius);
				shapeRenderer.begin(ShapeType.Filled);
				shapeRenderer.setColor(1, 1, 0, 2);
				shapeRenderer.circle(pt.x, SoldierGame.HEIGHT-pt.y, radius);
				shapeRenderer.end();
				radius++;
			}
			if(radius > 20) {
				radius = 0;
				world.decreaseBullet();
			}
		} else {
			radius = 0;
		}
	}
	public void render() {
		update();
		float dimension = world.getPoint().getDimension();
		pt = world.getPoint().getPositionMouse();
		
		batch.begin();
        batch.draw(pointImg, pt.x-dimension/2,SoldierGame.HEIGHT-pt.y-dimension/2,dimension,dimension);
        batch.end();
        
        
	}
}
