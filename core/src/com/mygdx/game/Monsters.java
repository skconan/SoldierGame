package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class Monsters {
	
	private int size[] = {100,100};
	private int blood[] = {1,2,3};
	private int mapHeight = 4;
    private int mapWidth = 8;
    private int numberOfMonsters = 0;
    private boolean[][] hasMonsters;
	private boolean[][] createdMonsters;
	private float[][][] monstersPosition;
	private int[][] monstersSize;
	private int[][] monstersBlood;
	public float[][] monstersMove;
    private Random rand;
    private Point point = new Point();
    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private int radius = 0;
    
	
	public Monsters() {
		rand = new Random();
		hasMonsters = new boolean[mapHeight][mapWidth];
		createdMonsters = new boolean[mapHeight][mapWidth];
		monstersPosition = new float[mapHeight][mapWidth][3];
		monstersMove = new float[mapHeight][mapWidth];
		monstersSize = new int[mapHeight][mapWidth];
		monstersBlood = new int[mapHeight][mapWidth];
		initMonsters();
    }
	
	public void update() {
		updateMAP();
		updateMonsters();
	}
	
	private void initMonsters() {
		
		for(int r = 0; r < mapHeight; r++) {
			for(int c = 0; c < mapWidth; c++){
				hasMonsters[r][c] = false;
				createdMonsters[r][c] = false;
			}
		}
		numberOfMonsters = 0;
		genMAP();
    }
	
	public void genMonsters(int r, int c) {
		monstersSize[r][c] = getSize();
		monstersPosition[r][c][0] = (c*100+monstersSize[r][c]/2);
		monstersPosition[r][c][1] = (4-r)*100+monstersSize[r][c]/2;
		monstersBlood[r][c] = getBlood();
		
	}
	
	public int[] getMonsters(int r, int c) {
		monstersMove[r][c] = getMove();
		monstersPosition[r][c][0] += monstersMove[r][c];
		monstersPosition[r][c][1] -= monstersMove[r][c];
		if(monstersSize[r][c] > 100) {
			monstersSize[r][c] = (int) (monstersSize[r][c] - monstersMove[r][c]/50);
		} else {
			monstersSize[r][c] = (int) (monstersSize[r][c] + monstersMove[r][c]/50);
		}
		int[] property ={monstersSize[r][c], (int) monstersPosition[r][c][0], (int) monstersPosition[r][c][1], monstersBlood[r][c]};
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
	}
	
	private void updateMAP() {
		if(numberOfMonsters <= 0) {
			initMonsters();
		}
	}
	
	
	private void updateMonsters() {
		for(int r = 0; r < mapHeight; r++) {
			for(int c = 0; c < mapWidth; c++){	
				
				if (hasMonsterAt(r, c)){				
					int x = (int)monstersPosition[r][c][0];
					int y = (int)monstersPosition[r][c][1];
					
					if(y < -50/2 || x > SoldierGame.WIDTH+50/2) {
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

	public int getSize() {
		int index = rand.nextInt(size.length-1);
		return size[index];
	}
	
	public int getBlood() {
		int index = rand.nextInt(blood.length-1);
		return blood[index];
	}
	
	public int getMove() {
		return  rand.nextInt(80)/50;
	}
	
	public int getMapHeight() {
        return mapHeight;
    }
 
    public int getMapWidth() {
        return mapWidth;
    }
    
    public void kill(int mouseX, int mouseY, int radius) {
    	for (int r = 0; r < mapHeight; r++) {
			 for (int c = 0; c < mapWidth; c++) {	
				 if (hasMonsterAt(r, c)){
					 int x = (int)monstersPosition[r][c][0];
					 int y = (int)monstersPosition[r][c][1];
					 int monsterSize = radius - 5;
					 System.out.println(x + " " + y);
					 if (mouseX >= x-monsterSize && mouseX <= x+monsterSize && mouseY >= y-monsterSize && mouseY <= y+monsterSize){
						 removeMonsters(r,c);
					 }
				 }
			 }
    	}
    }
    
    public void removeMonsters(int r, int c) {
    	hasMonsters[r][c] = false;
		numberOfMonsters--;
    }
}
