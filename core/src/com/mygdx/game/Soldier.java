package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class Soldier {
	private static float[] ptSoldier = new float[3];
	private int height = 200;
	private int width = 300;
	World world;
	
	public Soldier(World world){
		this.world = world;
	}
	
	public float[] getPostion() {
		Vector2 ptMouse = world.getPoint().getPositionMouse();
		ptSoldier[0] = (ptMouse.x/7) + SoldierGame.WIDTH/2 ;
		ptSoldier[1] = -25 + -ptMouse.y/7;
		ptSoldier[2] = -ptMouse.x/100;
		return ptSoldier;
	}
	
	public void hitMonsters(int x, int y) {
		if(x > ptSoldier[0] + width/6 && x < ptSoldier[0] - width/6 + width && y < ptSoldier[1] + height){
			world.decreaseBlood();
		}
	}
}
