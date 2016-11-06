package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class Monsters {
	
	private int size[] = {100,100};
	private int height = 4;
    private int width = 8;
    private int numberOfMonsters = 0;
    private boolean[][] hasMonsters;
	private boolean[][] createdMonsters;
	private float[][][] monstersPosition;
    private Random rand;
    private Point point = new Point();
    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private int radius = 0;
	
	public Monsters() {
		rand = new Random();
        monstersPosition = new float[height][width][3];
        initMonsters();
    }
	
	public void update() {
		getShot();
		updateMAP();
	}
	
	private void initMonsters() {
		hasMonsters = new boolean[height][width];
		createdMonsters = new boolean[height][width];
		genMAP();
    }
	
	private void genMAP() {
		int[] ran = new int[3];
		
		for (int r = 0; r < height; r++) {
			ran[0] = rand.nextInt(3)+4;
			ran[1] = rand.nextInt(10);
			ran[2] = rand.nextInt(3);
			
			for(int i = 0; i < 3; i++) {
				if(ran[i] < width) {
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
	
	public boolean hasMonsterAt(int r, int c) {
        return hasMonsters[r][c];
    }
	
	public boolean createdMonsterAt(int r, int c) {
		boolean old = createdMonsters[r][c];
		createdMonsters[r][c] = false;
        return old;
    }
	
	private void getShot() {
		Vector2 pt = point.getPositionMouse();
		
		if (Gdx.input.isButtonPressed(Buttons.LEFT)){
			kill((int)pt.x, SoldierGame.HEIGHT-(int)pt.y, radius);
			shapeRenderer.begin(ShapeType.Filled);
			shapeRenderer.setColor(1, 1, 0, 2);
			shapeRenderer.circle(pt.x, SoldierGame.HEIGHT-pt.y, radius);
			shapeRenderer.end();
			radius++;
			if(radius > 20) {
				radius = 0;
			}
		} else {
			radius = 0;
		}
	}
	
	public int getSize() {
		int index = rand.nextInt(size.length-1);
		return size[index];
	}
	
	public int getHeight() {
        return height;
    }
 
    public int getWidth() {
        return width;
    }
    
    
    public void getPosition(int r, int c, float x, float y, int monsterSize) {
    	monstersPosition[r][c][0] = x;
    	monstersPosition[r][c][1] = y;
    	monstersPosition[r][c][2] = monsterSize;
    }
    
    public void kill(int mouseX, int mouseY, int radius) {
    	for (int r = 0; r < height; r++) {
			 for (int c = 0; c < width; c++) {	
				 if (hasMonsterAt(r, c)){
					 int x = (int)monstersPosition[r][c][0];
					 int y = (int)monstersPosition[r][c][1];
					 int monsterSize = radius - 5;

					 if (mouseX >= x-monsterSize && mouseX <= x+monsterSize && mouseY >= y-monsterSize && mouseY <= y+monsterSize){
						 hasMonsters[r][c] = false;
						 numberOfMonsters--;
					 }
				 }
			 }
    	}
    }
}
