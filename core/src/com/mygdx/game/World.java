package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.math.Vector2;

public class World {
	private Arrow arrow;
	private Point point;
	private Monsters monsters;
	private Soldier soldier;
	private PointRenderer pointRenderer;
	private MonstersRenderer monstersRenderer;
	private SoldierRenderer soldierRenderer;
	private ArrowRenderer arrowRenderer;
	private int score;
	private float blood;
	private int bullet;
	public int status = 1;
	
	World(SoldierGame soldierGame){
		point = new Point();
		arrow = new Arrow(this);
		soldier = new Soldier(this);
		monsters = new Monsters(this);
		soldierRenderer = new SoldierRenderer(soldierGame.batch, this);
		monstersRenderer = new MonstersRenderer(soldierGame.batch, this);
		pointRenderer = new PointRenderer(soldierGame.batch, this);
		arrowRenderer = new ArrowRenderer(soldierGame.batch, this);
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
	}
	
	Monsters getMonsters() {
		return monsters;
	}
	
	MonstersRenderer getMonstersRenderer() {
		return monstersRenderer;
	}
	
	Point getPoint() {
		return point;
	}
	
	PointRenderer getPointRenderer() {
		return pointRenderer;
	}
	
	Soldier getSoldier() {
		return soldier;
	}
	
	SoldierRenderer getSoldierRenderer() {
		return soldierRenderer;
	}
	
	Arrow getArrow() {
        return arrow;
    }
	
	ArrowRenderer getArrowRenderer() {
        return arrowRenderer;
    }
	
	public boolean inRange(float minX, float x, float maxX, float minY, float y, float maxY) {
		if(x >= minX && x <= maxX && y >= minY && y <= maxY) {
			return true;
		}
		return false;
	}
	
	public int getScore() {
        return score;
    }
	
	public float getBlood() {
        return blood;
    }
	
	public int getBullet() {
        return bullet;
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
