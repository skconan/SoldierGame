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
	public int status = 2;
	
	World(SoldierGame soldierGame){
		point = new Point();
		arrow = new Arrow(this);
		soldier = new Soldier(this);
		monsters = new Monsters(this);
		soldierRenderer = new SoldierRenderer(soldierGame.batch, this);
		monstersRenderer = new MonstersRenderer(soldierGame.batch, this);
		pointRenderer = new PointRenderer(soldierGame.batch, this);
		arrowRenderer = new ArrowRenderer(soldierGame.batch, this);
		score = 0;
		blood = 100;
		bullet = 40;
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
			status = 0;
		}
	}
	
	public int getStatusGame() {
//		1 start 2 play 3 win 0 die 
		return status;
	}
	
	public boolean mouseLeftClickButton(Vector2 mousePt, Vector2 objPt) {
		System.out.println(mousePt);
		System.out.println(objPt);
		if(Gdx.input.isButtonPressed(Buttons.LEFT) && mousePt.x >= objPt.x - 200 && mousePt.x <= objPt.x + 200 && mousePt.y >= objPt.y - 25 && mousePt.y <= objPt.y + 25) {
			return true;
		}
		return false;
	}
}
