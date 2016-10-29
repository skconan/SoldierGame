package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Point {
	private float dimension = 120;
	
	public Point(){
		
	}
	
	public float getDimension() { 
		float widthRatio = ((getPositionMouse().x - (SoldierGame.WIDTH/2))/(SoldierGame.WIDTH/2))/2;
		float widthRatioAbs = Math.abs(widthRatio);
		float heightRatio = (getPositionMouse().y/SoldierGame.HEIGHT)/2;
		
		System.out.print(widthRatio);
  		System.out.print(" ");
		System.out.println(heightRatio);
		
		float newDimension = dimension;
		
		if(widthRatio < 0.45 || widthRatio < -0.35){
			newDimension = (heightRatio*2)*dimension;
		} else {
			newDimension = (heightRatio+widthRatioAbs)*dimension;
		}
		System.out.println(newDimension);
		System.out.println("_____________________________");
		if(newDimension < 40){
			newDimension = 40;
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
