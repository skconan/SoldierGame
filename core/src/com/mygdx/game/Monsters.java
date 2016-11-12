package com.mygdx.game;

import java.util.Random;

public class Monsters {
	private int monstersSize = 100;
	private int mapHeight = 4;
    private int mapWidth = 8;
    private int numberOfMonsters = 0;
    private boolean[][] hasMonsters;
	private boolean[][] createdMonsters;
	private float[][][] monstersPosition;
	private int[][] monstersBlood;
	public float[][] monstersMove;
    private Random rand;
    private World world;
	
	public Monsters(World world) {
		this.world = world;
		rand = new Random();
		hasMonsters = new boolean[mapHeight][mapWidth];
		createdMonsters = new boolean[mapHeight][mapWidth];
		monstersPosition = new float[mapHeight][mapWidth][3];
		monstersMove = new float[mapHeight][mapWidth];
		monstersBlood = new int[mapHeight][mapWidth];
		initMonsters();
    }
	
	private void initMonsters() {
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
		if(world.score < 60) {
			monstersPosition[r][c][0] = (c*100) + monstersSize;
			monstersPosition[r][c][1] = (4-r)*100 + monstersSize;
			monstersBlood[r][c] = getBlood();
		} else {
			monstersPosition[r][c][0] = SoldierGame.WIDTH + monstersSize;
			monstersPosition[r][c][1] = 100 + monstersSize;
			monstersBlood[r][c] = 20;
		}
	}
	
	public int[] getMonsters(int r, int c) {
		if(world.score < 60) {
			monstersMove[r][c] = getMove();
			monstersPosition[r][c][0] += monstersMove[r][c];
			monstersPosition[r][c][1] -= monstersMove[r][c];
		} else {
			monstersMove[r][c] = 0;
			monstersPosition[r][c][0] += monstersMove[r][c];
			monstersPosition[r][c][1] -= monstersMove[r][c];
			monstersSize = 200;
		}
		int[] property = {monstersSize, (int) monstersPosition[r][c][0], (int) monstersPosition[r][c][1], monstersBlood[r][c]};
		return property;
	}
	
	private void genMAP() {
		int[] ran = new int[3];
		for (int r = 0; r < mapHeight && world.score < 60; r++) {
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
		if(world.score >= 60) {
			ran[2] = rand.nextInt(3);
			hasMonsters[2][ran[2]] = true;
			createdMonsters[2][ran[2]] = true;
			numberOfMonsters++;
		}
	}
	
	private void updateMonsters() {
		for(int r = 0; r < mapHeight; r++) {
			for(int c = 0; c < mapWidth; c++) {	
				if (hasMonsterAt(r, c)){				
					int x = (int) monstersPosition[r][c][0];
					int y = (int) monstersPosition[r][c][1];
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
		int blood = rand.nextInt(1) + 1;
		if(world.score >= 40) {
			blood += 2;
		} else if(world.score >= 20) {
			blood++;
		} 
		return blood;
	}
	
	public int getMove() {
//		if(world.getScore() >= 59) {
//			return 5/100;
//		}
		return  rand.nextInt(80)/50;
	}
	
	public int getMapHeight() {
        return mapHeight;
    }
 
    public int getMapWidth() {
        return mapWidth;
    }
    
    public void kill(int mouseX, int mouseY) {
    	for (int r = 0; r < mapHeight; r++) {
			 for (int c = 0; c < mapWidth; c++) {	
				 if (hasMonsterAt(r, c)){
					 int x = (int)monstersPosition[r][c][0];
					 int y = (int)monstersPosition[r][c][1];
					 if (world.inRange(x - monstersSize/2, mouseX, x + monstersSize/2, y - monstersSize/2, mouseY, y + monstersSize/2)) {
						 monstersBlood[r][c]--;
						 if(monstersBlood[r][c] <= 0) {
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
		if(numberOfMonsters <= 0 || world.score == 60) {
			initMonsters();
		}
    }
}
