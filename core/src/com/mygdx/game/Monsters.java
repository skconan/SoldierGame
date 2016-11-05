package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class Monsters {
	
	private int size[] = {100,100};
	private int height;
    private int width;
    private boolean[][] hasMonsters;
	private boolean[][] createdMonsters;
	private float[][][] monstersPosition;
    private Random rand;
    private Point point = new Point();
    private int radius = 0;
//    private ShapeRenderer shapeRenderer;
    private String MAP[] = {
			".m.m...m",
			".m...m..",
			"...m..m.",
			"..m.....",
	};
	
	public Monsters(){
		rand = new Random();
		height = MAP.length;
        width = MAP[0].length();
        monstersPosition = new float[height][width][3];
        initMonsters();
    }
	
	public void update(){
		Vector2 pt = point.getPositionMouse();
		
		if (Gdx.input.isButtonPressed(Buttons.LEFT)){
			kill((int)pt.x, SoldierGame.HEIGHT-(int)pt.y, radius);
			ShapeRenderer shapeRenderer = new ShapeRenderer();
			shapeRenderer.begin(ShapeType.Filled);
			shapeRenderer.setColor(1, 1, 0, 1);
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
	
	private void initMonsters() {
		hasMonsters = new boolean[height][width];
		createdMonsters = new boolean[height][width];
		
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
            	hasMonsters[r][c] = MAP[r].charAt(c) == 'm';
            	createdMonsters[r][c] = hasMonsters[r][c];
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
	
	public int getSize(){
		int index = rand.nextInt(size.length-1);
		return size[index];
	}
	
	public int getHeight() {
        return height;
    }
 
    public int getWidth() {
        return width;
    }
    
    
    public void getPosition(int r, int c, float x, float y, int monsterSize){
    	monstersPosition[r][c][0] = x;
    	monstersPosition[r][c][1] = y;
    	monstersPosition[r][c][2] = monsterSize;
    }
    
    public void kill(int mouseX, int mouseY, int radius){
    	for (int r = 0; r < height; r++) {
			 for (int c = 0; c < width; c++) {	
				 System.out.println(r + " " + c);
				 if (hasMonsterAt(r, c)){
					 int x = (int)monstersPosition[r][c][0];
					 int y = (int)monstersPosition[r][c][1];
					 int monsterSize = radius - 5;
					 
					 System.out.println(monsterSize);
					 System.out.println("x " + x + " " + mouseX);
					 System.out.println("y " + y + " " + mouseY);
					
					 System.out.println("===================");
					 if (mouseX >= x-monsterSize && mouseX <= x+monsterSize && mouseY >= y-monsterSize && mouseY <= y+monsterSize){
						 System.out.println("x " + x + " " + mouseX);
						 System.out.println("y " + y + " " + mouseY);
						 System.out.println("===================");
						 hasMonsters[r][c] = false;
					 }
				 }
			 }
    	}
    }
}
