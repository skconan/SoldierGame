package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Soldier {
	private Texture soldierImg, bloodImg, bloodFrameImg, hitImg;
	private Sprite soldierSprite;
	private SpriteBatch batch;
	private BitmapFont font;
	private World world;
	private float oldBlood;
	private float x, y, rotation;
	private int soldierHeight = 200;
	private int soldierWidth = 300;
	
	public Soldier(SpriteBatch batch, World world){
		this.world = world;
		this.batch = batch;
		soldierImg = new Texture("soldier.png");
		hitImg = new Texture("attack.fw.png");
		bloodImg = new Texture("blood.fw.png");
		bloodFrameImg = new Texture("bloodFrame.fw.png");
		soldierSprite = new Sprite(soldierImg);
		font = new BitmapFont();
		oldBlood = world.blood;
	}
	
	public void hitMonsters(int xMonsters, int yMonsters) {
		if(world.inRange(x - soldierWidth/3, xMonsters, x + soldierWidth*2/3, 0, yMonsters, y + soldierHeight-10)){
			world.decreaseBlood();
		}
	}
	
	public void update() {
		Vector2 ptMouse = world.point.getPositionMouse();
		x = ptMouse.x/3 + SoldierGame.WIDTH/2;
		y = -ptMouse.y/7;
		rotation = -ptMouse.x/100;
	}
	
	public void render() {
        batch.begin();
        batch.draw(soldierSprite, x, y, 0, 0, soldierWidth, world.soldier.soldierHeight, 1, 1, rotation);
        font.draw(batch, "BULLET : " + world.bullet, SoldierGame.WIDTH - 100, SoldierGame.HEIGHT-10);  
        font.draw(batch, "HEALTH : ", 20, 40); 
        batch.draw(bloodImg, 90, 20, world.blood*2, 30);
        batch.draw(bloodFrameImg, 90, 20, 200, 30);
        if(oldBlood != world.blood) {
        	oldBlood = world.blood;
        	batch.draw(hitImg, 0, 0);
        }
        batch.end();
   
    }
}
