package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class PointRenderer {
	private Point point = new Point();
	private Monsters monsters;
	ShapeRenderer shapeRenderer = new ShapeRenderer();
	private Texture pointImg;
	private Vector2 pt;
	private int radius = 0;
	SpriteBatch batch;
	
	public PointRenderer(SpriteBatch batch, Monsters monsters){
		this.batch = batch;
		pointImg = new Texture("point.fw.png");
		this.monsters = monsters;
	}
	
	public void update() {
		shoot();
	}
	
	private void shoot() {
		Vector2 pt = point.getPositionMouse();
		
		if (Gdx.input.isButtonPressed(Buttons.LEFT)){
			monsters.kill((int)pt.x, SoldierGame.HEIGHT-(int)pt.y, radius);
			shapeRenderer.begin(ShapeType.Filled);
			shapeRenderer.setColor(1, 1, 0, 2);
			shapeRenderer.circle(pt.x, SoldierGame.HEIGHT-pt.y, radius);
			shapeRenderer.end();
			radius++;
			if(radius > 20) {
				radius = 0;
			}
		} else {
			radius = 0;
		}
	}
	public void render() {
		update();
		float dimension = point.getDimension();
		pt = point.getPositionMouse();
		
		batch.begin();
        batch.draw(pointImg, pt.x-dimension/2,SoldierGame.HEIGHT-pt.y-dimension/2,dimension,dimension);
        batch.end();
        
        
	}
}
