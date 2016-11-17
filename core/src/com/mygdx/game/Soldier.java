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
	private int atk = 1;
	
	public Soldier(SpriteBatch batch, World world){
		this.world = world;
		this.batch = batch;
		soldierImg = new Texture("soldier0.fw.png");
		hitImg = new Texture("attack.fw.png");
		bloodImg = new Texture("blood.fw.png");
		bloodFrameImg = new Texture("bloodFrame.fw.png");
		soldierSprite = new Sprite(soldierImg);
		font = new BitmapFont();
		oldBlood = world.blood;
	}
	
	public void hitMonsters(float xMonsters, float yMonsters) {
		if(world.inRange(x, xMonsters, x + soldierWidth, 0, yMonsters, y + soldierHeight-10)) {
			world.decreaseBlood();
		}
	}
	
	public void hitCircle(float xMonsters, float yMonsters) {
		if(world.inRange(x, xMonsters, x + soldierWidth, 0, yMonsters, y + soldierHeight/2)) {
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
		if(world.score >= 40) {
			soldierImg = new Texture("soldier.png");
			soldierSprite = new Sprite(soldierImg);
			atk = 3;
		} else if(world.score >= 20) {
			soldierImg = new Texture("soldier1.fw.png");
			soldierSprite = new Sprite(soldierImg);
			atk = 2;
		}
        batch.begin();
        batch.draw(soldierSprite, x, y, 0, 0, soldierWidth, world.soldier.soldierHeight, 1, 1, rotation);
        font.draw(batch, "BULLET : " + world.bullet + " ATK : X"+atk, SoldierGame.WIDTH - 150, SoldierGame.HEIGHT-10);  
        font.draw(batch, "HP : ", SoldierGame.WIDTH/2-120,SoldierGame.HEIGHT-10); 
        batch.draw(bloodImg, SoldierGame.WIDTH/2-80, SoldierGame.HEIGHT-23, world.blood*2, 20);
        batch.draw(bloodFrameImg, SoldierGame.WIDTH/2-80, SoldierGame.HEIGHT-23, 200, 20);
        if(oldBlood != world.blood) {
        	oldBlood = world.blood;
        	batch.draw(hitImg, 0, 0);
        }
        batch.end();
    }
}
