package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class Soldier {
	World world;
	private static float[] ptSoldier = new float[3];
	
	public Soldier(World world){
		this.world = world;
	}
	
	public float[] getPostion() {
		Vector2 ptMouse = world.getPoint().getPositionMouse();
		
		ptSoldier[0] = (ptMouse.x/7) + SoldierGame.WIDTH/2;
		ptSoldier[1] = -30 + -ptMouse.y/10;
		ptSoldier[2] = -ptMouse.x/120;
		return ptSoldier;
	}
	
	public void hitMonsters(int x, int y) {
		if(x > ptSoldier[0]- 50 && y < ptSoldier[1]+200){
			world.decreaseBlood();
		}
	}
}
