package com.mygdx.game;

public class World {
	private SoldierGame soldierGame;
	public Point point;
	public Monsters monsters;
	public PointRenderer pointRenderer;
	public MonstersRenderer monstersRenderer;
	public SoldierRenderer soldierRenderer;
	private int score;
	private int blood;
	private int bullet;
	
	World(SoldierGame soldierGame){
		this.soldierGame = soldierGame;
		monsters = new Monsters();
		soldierRenderer = new SoldierRenderer(soldierGame.batch);
		monstersRenderer = new MonstersRenderer(soldierGame.batch, monsters);
		pointRenderer = new PointRenderer(soldierGame.batch, monsters);
		point = new Point();
		score = 0;
	}	
	
	public int getScore() {
        return score;
    }
	
	public int getBlood() {
        return score;
    }
	
	public int getBullet() {
        return score;
    }
}
