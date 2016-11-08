package com.mygdx.game;

public class World {
	public Point point;
	public Monsters monsters;
	public Soldier soldier;
	public PointRenderer pointRenderer;
	public MonstersRenderer monstersRenderer;
	public SoldierRenderer soldierRenderer;
	private int score;
	private float blood;
	private int bullet;
	
	World(SoldierGame soldierGame){
		point = new Point();
		soldier = new Soldier(this);
		monsters = new Monsters(this);
		soldierRenderer = new SoldierRenderer(soldierGame.batch, this);
		monstersRenderer = new MonstersRenderer(soldierGame.batch, this);
		pointRenderer = new PointRenderer(soldierGame.batch, this);
		point = new Point();
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
	public void update() {
		monsters.update();
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
	}
	
	public void decreaseBlood() {
		blood -= 0.01;
	}
}
