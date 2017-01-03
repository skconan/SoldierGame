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
	public float[][][] speed;
	private int[][] blood;
    private Random rand;
    private World world;
    
    public Monsters(World world) {
		this.world = world;
		rand = new Random();
		hasMonsters = new boolean[mapHeight][mapWidth];
		createdMonsters = new boolean[mapHeight][mapWidth];
		position = new float[mapHeight][mapWidth][3];
		speed = new float[mapHeight][mapWidth][2];
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
		speed[r][c][0] = getSpeed()[0];
		speed[r][c][1] = getSpeed()[1];
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
		position[r][c][0] += speed[r][c][0];	
		position[r][c][1] -= speed[r][c][1]; 	
		int[] property = {size, (int) position[r][c][0], (int) position[r][c][1], blood[r][c], indexImg};
		speedCircle += 0.2;
		return property;
	}
	
	private void genMAP() {
		int[] ran = new int[3];
		for (int r = 1; r < mapHeight; r++) {
			ran[0] = rand.nextInt(2)+3;
			ran[1] = rand.nextInt(8);
			ran[2] = rand.nextInt(3);
			for(int i = 0; i < 3; i++) {
				if(i > 0 && ran[i] == ran[i-1]) {
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
					if(y < 0 || x > SoldierGame.WIDTH || x < size/2) {
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
	
	public float[] getSpeed() {
		float[] speed = new float[2];
		speed[1] = (rand.nextInt(50)+0.1f)/40.0f;
		speed[0] = rand.nextInt(10);
		if(world.score < 60) {
			speed[1] += world.score/60.0f;
		} else {
			speed[1] += (world.score-40)/60.0f;
		}		
		if(speed[0] <= 2) {
			speed[0] = -1;
		} else {
			speed[0] = 1;
		}
		return speed;  
	}

    public void kill(int mouseX, int mouseY) {
    	for (int r = 0; r < mapHeight; r++) {
			 for (int c = 0; c < mapWidth; c++) {	
				 if (hasMonsterAt(r, c)){
					 int x = (int)position[r][c][0];
					 int y = (int)position[r][c][1];
					 if (world.inRange(x - size/2, mouseX, x + size/2, y - size/2, mouseY, y + size/2)) {
						 if(world.score < 20) {
							 blood[r][c]--;
						 } else if(world.score < 40) {
							blood[r][c]-=2;
						 } else {
							blood[r][c]-=3;
						 } 
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
