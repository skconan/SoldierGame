package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.math.Vector2;

public class World {
	public Arrow arrow;
	public Point point;
	public Monsters monsters;
	public Soldier soldier;
	public MonstersRenderer monstersRenderer;
	public int score;
	public float blood;
	public int bullet;
	public int status = 1;
	
	World(SoldierGame soldierGame){
		point = new Point(soldierGame.batch, this);
		arrow = new Arrow(soldierGame.batch, this);
		soldier = new Soldier(soldierGame.batch, this);
		monsters = new Monsters(this);
		monstersRenderer = new MonstersRenderer(soldierGame.batch, this);
		init();
	}	
	
	public void init() {
		score = 0;
		blood = 100;
		bullet = 400;
	}
	
	public void update() {
		monsters.update();
		arrow.update();
		soldier.update();
	}
	
	public boolean inRange(float minX, float x, float maxX, float minY, float y, float maxY) {
		if(x >= minX && x <= maxX && y >= minY && y <= maxY) {
			return true;
		}
		return false;
	}
	
	public void increaseScore() {
		score ++;
	}
	
	public void decreaseBullet() {
		bullet --;
		if(bullet <= 0) {
			bullet = 0;
		}
	}
	
	public void increaseBullet() {
		bullet ++;
	}
	
	public void decreaseBlood() {
		blood -= 0.1;
		if(blood <= 0) {
			blood = 0;
			status = 4;
		}
	}
	
	public int getStatusGame() {
//		1 start 2 play 3 win 4 die 
		return status;
	}
	
	public boolean mouseLeftClickButton(Vector2 mousePt, Vector2 objPt) {
		System.out.println("-" + mousePt);
		System.out.println(objPt);
		if(Gdx.input.isButtonPressed(Buttons.LEFT) && inRange(objPt.x - 100, mousePt.x, objPt.x + 100, objPt.y - 50, mousePt.y, objPt.y + 50)) {
			return true;
		}
		return false;
	}
}
