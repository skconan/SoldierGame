package com.mygdx.game;

public class World {
	private SoldierGame soldierGame;
	public Arrow arrow;
	public Point point;
	public Monsters monsters;
	public Soldier soldier;
	public MonstersRenderer monstersRenderer;
	public char status = 's';
	public int score;
	public float blood;
	public int bullet;
	public int scoreWin = 90;
	public int highScore = 0;
	
	World(SoldierGame soldierGame){
		this.soldierGame = soldierGame;
		init();
	}	
	
	public void init() {
		monsters = new Monsters(this);
		point = new Point(soldierGame.batch, this);
		arrow = new Arrow(soldierGame.batch, this);
		soldier = new Soldier(soldierGame.batch, this);
		monstersRenderer = new MonstersRenderer(soldierGame.batch, this);
		score = 0;
		blood = 100;
		bullet = 60;
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
		if(score == scoreWin) {
			status = 'w';
		}
		if(score > highScore) {
			highScore = score;
		}
	}
	
	public void decreaseBullet() {
		bullet --;
		if(bullet <= 0) {
			bullet = 0;
		}
	}
	
	public void increaseBullet() {
		bullet ++;
		if(bullet >= 80) {
			bullet = 80;
		}
	}
	
	public void decreaseBlood() {
		blood -= 0.15;
		if(blood <= 0) {
			blood = 0;
			status = 'o';
		}
	}
	
	public char getStatusGame() {
		return status;
	}
}
