package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Point {
	private float dimension = 120;
	
	public Point(){
		
	}
	
	public float getDimension() { 
		float newDimension = getPositionMouse().y*dimension/SoldierGame.HEIGHT;
		
		if(newDimension < 15){
			newDimension = 15;
		}
		return newDimension;
	}
	
	public Vector2 getPositionMouse() {
		Vector2 position = new Vector2(0,0);
		position.x = Gdx.input.getX();
		position.y = Gdx.input.getY();
		return position;		
	}
}
