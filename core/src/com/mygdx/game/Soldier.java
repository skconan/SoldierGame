package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class Soldier {
	private static float[] ptSoldier = new float[3];
	private int soldierHeight = 200;
	private int soldierWidth = 300;
	private World world;
	
	
	public Soldier(World world){
		this.world = world;
	}
	
	public float[] getPostion() {
		Vector2 ptMouse = world.getPoint().getPositionMouse();
		ptSoldier[0] = ptMouse.x/3 + SoldierGame.WIDTH/2;
		ptSoldier[1] = -ptMouse.y/7;
		ptSoldier[2] = -ptMouse.x/100;
		return ptSoldier;
	}
	
	public void hitMonsters(int x, int y) {
		if(world.inRange(ptSoldier[0] - soldierWidth/3, x, ptSoldier[0] + soldierWidth*2/3, 0, y, ptSoldier[1] + soldierHeight-10)){
			world.decreaseBlood();
		}
	}
	
	public int getHeight() {
		return soldierHeight;
	}
	
	public int getWidth() {
		return soldierWidth;
	}
}
