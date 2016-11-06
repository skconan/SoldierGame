package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class PointRenderer {
	private Point point = new Point();
//	private Monsters monster = new Monsters();
	ShapeRenderer shapeRenderer = new ShapeRenderer();
	private Texture pointImg;
	private Vector2 pt;
//	private int radius = 0;
	SpriteBatch batch;
	
	public PointRenderer(SpriteBatch batch){
		this.batch = batch;
		pointImg = new Texture("point.fw.png");
	}
	
	public void update() {
		
//		if (Gdx.input.isButtonPressed(Buttons.LEFT)) {
//			monster.kill((int)pt.x, SoldierGame.HEIGHT-(int)pt.y, radius);
//        	System.out.println(radius);
//			shapeRenderer.begin(ShapeType.Filled);
//			shapeRenderer.setColor(1, 1, 0, 1);
//			shapeRenderer.circle(pt.x, SoldierGame.HEIGHT-pt.y, radius);
//			shapeRenderer.end();
//			
//			radius++;
//			if(radius > 15) {
//				radius = 0;
//			}	
//        }
//		else {
//			radius = 0;
//		}
	}
	
	public void render() {
//		update();
		float dimension = point.getDimension();
		pt = point.getPositionMouse();
		
		batch.begin();
        batch.draw(pointImg, pt.x-dimension/2,SoldierGame.HEIGHT-pt.y-dimension/2,dimension,dimension);
        batch.end();
        
        
	}
}
