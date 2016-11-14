package com.mygdx.game;

import java.util.Random;

public class Monsters {
	private int size = 100;
	public float speedCircle=0;
	public int mapHeight = 4;
	public int mapWidth = 8;
    private int numberOfMonsters = 0;
    private boolean[][] hasMonsters;
	private boolean[][] createdMonsters;
	private float[][][] position;
	private int[][] blood;
    private Random rand;
    private World world;
    public float[][] speed;

    public Monsters(World world) {
		this.world = world;
		rand = new Random();
		hasMonsters = new boolean[mapHeight][mapWidth];
		createdMonsters = new boolean[mapHeight][mapWidth];
		position = new float[mapHeight][mapWidth][3];
		speed = new float[mapHeight][mapWidth];
		blood = new int[mapHeight][mapWidth];
		initMonsters();
    }
	
	public void initMonsters() {
		for(int r = 0; r < mapHeight; r++) {
			for(int c = 0; c < mapWidth; c++) {
				hasMonsters[r][c] = false;
				createdMonsters[r][c] = false;
			}
		}
		numberOfMonsters = 0;
		genMAP();
    }
	
	public void update() {
		updateMonsters();
	}
	
	public void genMonsters(int r, int c) {
		position[r][c][0] = (c*100) + size;
		position[r][c][1] = (4-r)*100 + size;
		blood[r][c] = getBlood() + (world.score+1)/20;
	}
	
	public int[] getMonsters(int r, int c) {
		int indexImg = 0;
		if(world.score < 20) {
			indexImg = 0;
		} else if (world.score < 40) {
			indexImg = 1;
		} else if (world.score < 60) {
			indexImg = 2;
		} else {
			indexImg = 3;
		}
		speed[r][c] = getMove();
		position[r][c][0] += speed[r][c];
		position[r][c][1] -= speed[r][c]; 
		int[] property = {size, (int) position[r][c][0], (int) position[r][c][1], blood[r][c], indexImg};
		speedCircle += 0.06;
		return property;
	}
	
	private void genMAP() {
		int[] ran = new int[3];
		for (int r = 0; r < mapHeight; r++) {
			ran[0] = rand.nextInt(3)+4;
			ran[1] = rand.nextInt(10);
			ran[2] = rand.nextInt(3);
			for(int i = 0; i < 3; i++) {
				if(i > 0 && ran[i] == ran[i-1]){
					continue;
				}
				if(ran[i] < mapWidth) {
					hasMonsters[r][ran[i]] = true;
					createdMonsters[r][ran[i]] = true;
					numberOfMonsters++;
				}
			}
		}
		speedCircle = 0;
	}
	
	private void updateMonsters() {
		for(int r = 0; r < mapHeight; r++) {
			for(int c = 0; c < mapWidth; c++) {	
				if (hasMonsterAt(r, c)){				
					int x = (int) position[r][c][0];
					int y = (int) position[r][c][1];
					if(y < 0 || x > SoldierGame.WIDTH) {
						removeMonsters(r, c);
					}
				}
			}
		}
	}
	
	public boolean hasMonsterAt(int r, int c) {
        return hasMonsters[r][c];
    }
	
	public boolean createdMonsterAt(int r, int c) {
		boolean old = createdMonsters[r][c];
		createdMonsters[r][c] = false;
        return old;
    }
	
	public int getBlood() {
		int blood = rand.nextInt(2) + 1;
		return blood;
	}
	
	public int getMove() {
		return  rand.nextInt(80)/50;
	}

    public void kill(int mouseX, int mouseY) {
    	for (int r = 0; r < mapHeight; r++) {
			 for (int c = 0; c < mapWidth; c++) {	
				 if (hasMonsterAt(r, c)){
					 int x = (int)position[r][c][0];
					 int y = (int)position[r][c][1];
					 if (world.inRange(x - size/2, mouseX, x + size/2, y - size/2, mouseY, y + size/2)) {
						 blood[r][c]--;
						 if(blood[r][c] <= 0) {
						 	world.increaseScore();
							removeMonsters(r,c);
						 }
					 }
				 }
			 }
    	}
    }
    
    public void removeMonsters(int r, int c) {
    	hasMonsters[r][c] = false;
		numberOfMonsters--;
		if(numberOfMonsters <= 0 && world.score <= world.scoreWin) {
			initMonsters();
		}
    }
}
