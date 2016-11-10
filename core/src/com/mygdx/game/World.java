package com.mygdx.game;

public class World {
	public Arrow arrow;
	public Point point;
	public Monsters monsters;
	public Soldier soldier;
	public PointRenderer pointRenderer;
	public MonstersRenderer monstersRenderer;
	public SoldierRenderer soldierRenderer;
	public ArrowRenderer arrowRenderer;
	private int score;
	private float blood;
	private int bullet;
	
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
		bullet = 100;
	}	
	
	Monsters getMonsters() {
		return monsters;
	}
	
	Point getPoint() {
		return point;
	}
	
	Soldier getSoldier() {
		return soldier;
	}
	
	Arrow getArrow() {
        return arrow;
    }
	
	public void update() {
		monsters.update();
		arrow.update();
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
		blood -= 0.02;
		if(blood <= 0) {
			blood = 0;
		}
	}
}
